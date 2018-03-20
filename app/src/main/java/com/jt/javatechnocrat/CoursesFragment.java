package com.jt.javatechnocrat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Script;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;


public class CoursesFragment extends Fragment {

    private AppCompatActivity main;
    private View root;
    private RecyclerView courseList;
    private DatabaseReference courseRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main=(AppCompatActivity)getActivity();
        main.getSupportActionBar().setTitle("Our Courses");
        root=inflater.inflate(R.layout.fragment_courses, container, false);
        //Nav View
        NavigationView navigationView = (NavigationView) main.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_courses);
        courseList=root.findViewById(R.id.course_list);
        courseRef= FirebaseDatabase.getInstance().getReference().child("courses");
        FirebaseRecyclerAdapter<Course,CourseViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Course, CourseViewHolder>(
                Course.class,
                R.layout.course_row,
                CourseViewHolder.class,
                courseRef
        ) {
            @Override
            protected void populateViewHolder(CourseViewHolder viewHolder, final Course model, int position) {
                viewHolder.setAllData(getContext(),model.getImage_url(),model.getName(),model.getDuration(),model.getPrice());
                viewHolder.syllabus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url=model.getSyllabus();
                        final ProgressDialog dialog=new ProgressDialog(main);
                        StorageReference downloadStore= FirebaseStorage.getInstance().getReferenceFromUrl(url);
                        File root = getExternalStorageDirectory();
                        File dir=new File(root.getAbsolutePath(),"/JT");
                        dir.mkdirs();
                        final File file = new File(dir,model.getName()+".pdf");
                        downloadStore.getFile(file).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                dialog.setTitle("Downloading..");
                                dialog.setMessage(taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount()*100+"%");
                                dialog.show();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(main, "Successfully downloaded to "+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(main, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });
            }
        };
        courseList.setAdapter(firebaseRecyclerAdapter);
        courseList.setLayoutManager(new LinearLayoutManager(main));
        courseList.setHasFixedSize(true);
        // Inflate the layout for this fragment
        return root;
    }
    public static class CourseViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView nameText,durationText,priceText;
        Button syllabus;
        public CourseViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.course_image);
            nameText=itemView.findViewById(R.id.course_name);
            durationText=itemView.findViewById(R.id.course_duration);
            syllabus=itemView.findViewById(R.id.course_button);
            priceText=itemView.findViewById(R.id.course_fee);
        }
        public void setAllData(final Context ctx, final String url, String name, String duration, String fee){
            nameText.setText(name);
            durationText.setText("Duration : "+duration);
            priceText.setText("rs "+fee);
            Picasso.with(ctx).load(url).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.logo_def)
                    .into(img, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            Picasso.with(ctx).load(url).placeholder(R.drawable.logo_def).into(img);
                        }
                    });
        }
    }
}
