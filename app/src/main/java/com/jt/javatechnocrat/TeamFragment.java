package com.jt.javatechnocrat;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;


public class TeamFragment extends Fragment {


    private AppCompatActivity main;
    private View root;
    private RecyclerView teamList;
    private DatabaseReference teamRef;
    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main=(AppCompatActivity)getActivity();
        main.getSupportActionBar().setTitle("Our Team");
        root=inflater.inflate(R.layout.fragment_team, container, false);
        //Nav View
        NavigationView navigationView = (NavigationView) main.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_team);
        teamList=root.findViewById(R.id.team_list);
        teamRef= FirebaseDatabase.getInstance().getReference().child("faculty");
        pd = new ProgressDialog(main);
        pd.setTitle("Please Wait");
        pd.setCancelable(false);
        pd.setMessage("Loading Contents ...");
        pd.show();
        teamRef.keepSynced(true);
        FirebaseRecyclerAdapter<Team,TeamViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Team, TeamViewHolder>(
                Team.class,
                R.layout.faculty_row,
                TeamViewHolder.class,
                teamRef
        ) {
            @Override
            protected void populateViewHolder(TeamViewHolder viewHolder, final Team model, int position) {
                pd.dismiss();
                viewHolder.setAllData(model.getName(),model.getSubject(),model.getImage_url(),getContext());
                viewHolder.know.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog fullscreenDialog = new Dialog(getContext(), R.style.DialogFullscreen);
                        fullscreenDialog.setContentView(R.layout.activity_team);
                        final CircleImageView logo = fullscreenDialog.findViewById(R.id.team_logo);
                        TextView name = fullscreenDialog.findViewById(R.id.team_name);
                        TextView sub = fullscreenDialog.findViewById(R.id.team_sub);
                        TextView desc = fullscreenDialog.findViewById(R.id.team_des);
                        name.setText(model.getName());
                        sub.setText(model.getSubject());
                        desc.setText(model.getDesc());
                        Picasso.with(main).load(model.getImage_url()).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.logo_def)
                                .into(logo, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError() {
                                        Picasso.with(main).load(model.getImage_url()).placeholder(R.drawable.logo_def).into(logo);
                                    }
                                });
                        ImageView img_dialog_fullscreen_close = fullscreenDialog.findViewById(R.id.img_dialog_fullscreen_close);
                        img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                fullscreenDialog.dismiss();
                            }
                        });
                        fullscreenDialog.show();

                    }
                });
            }
        };
        teamList.setLayoutManager(new LinearLayoutManager(main));
        teamList.setHasFixedSize(true);
        teamList.setAdapter(firebaseRecyclerAdapter);

        // Inflate the gallery_layout for this fragment
        return root;
    }
    public static class TeamViewHolder extends RecyclerView.ViewHolder{
        View mView;
        CircleImageView img;
        TextView nameText,subjectText,know;
        public TeamViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            img=mView.findViewById(R.id.faculty_image);
            nameText=mView.findViewById(R.id.faculty_name);
            subjectText=mView.findViewById(R.id.faculty_subject);
            know = mView.findViewById(R.id.faculty_know_more);
        }
        public void setAllData(String name, String subject, final String url, final Context ctx){
            nameText.setText(name);
            subjectText.setText(subject);
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
