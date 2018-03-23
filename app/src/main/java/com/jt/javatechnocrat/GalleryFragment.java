package com.jt.javatechnocrat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class GalleryFragment extends Fragment {

    private AppCompatActivity main;
    private View root;
    private RecyclerView galleryList;
    private DatabaseReference galleryRef;
    private ProgressDialog pd;
    private AlertDialog dialog;
    public GalleryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main=(AppCompatActivity)getActivity();
        main.getSupportActionBar().setTitle("Gallery");
        root=inflater.inflate(R.layout.fragment_gallery, container, false);
        pd = new ProgressDialog(main);
        pd.setTitle("Please Wait");
        pd.setCancelable(false);
        pd.setMessage("Loading Contents ...");
        pd.show();
        //Nav View
        NavigationView navigationView = (NavigationView) main.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_gallery);
        galleryRef= FirebaseDatabase.getInstance().getReference().child("gallery");
        galleryList=root.findViewById(R.id.gallery_list);
        galleryRef.keepSynced(true);
        FirebaseRecyclerAdapter<Gallery, GalleryViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Gallery, GalleryViewHolder>(
                Gallery.class,
                R.layout.gallery_row,
                GalleryViewHolder.class,
                galleryRef
        ) {
            @Override
            protected void populateViewHolder(GalleryViewHolder viewHolder, final Gallery model, int position) {
                viewHolder.setImage(getContext(), model.getUrl());
                pd.dismiss();
                viewHolder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(main);
                        View mView = main.getLayoutInflater().inflate(R.layout.modal_layout,null);
                        final ImageView mImage = mView.findViewById(R.id.modal_image);
                        final ImageView mclose = mView.findViewById(R.id.modal_close);
                        final TextView mCaption=mView.findViewById(R.id.modal_caption);
                        mclose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               dialog.dismiss();
                            }
                        });
                        mCaption.setText(model.getName());
                        Picasso.with(main).load(model.getUrl()).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.galery_not)
                                .into(mImage, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError() {
                                        Picasso.with(main).load(model.getUrl()).placeholder(R.drawable.galery_not).into(mImage);
                                    }
                                });
                        mBuilder.setView(mView);
                        dialog = mBuilder.create();
                        dialog.show();
                    }
                });
            }

        };
        galleryList.setAdapter(firebaseRecyclerAdapter);
        RecyclerView.LayoutManager mLayout = new GridLayoutManager(main.getApplicationContext(),2);
        galleryList.setLayoutManager(mLayout);
        galleryList.setHasFixedSize(true);
        // Inflate the gallery_layout for this fragment
        return root;
    }
    public static class GalleryViewHolder extends RecyclerView.ViewHolder{
        ImageView img;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.gallery_image);
        }
        public void setImage(final Context ctx, final String url){
            Picasso.with(ctx).load(url).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.galery_not)
                    .into(img, new Callback() {
                        @Override
                        public void onSuccess() {
                        }
                        @Override
                        public void onError() {
                            Picasso.with(ctx).load(url).placeholder(R.drawable.galery_not).into(img);
                        }
                    });
        }
    }
}
