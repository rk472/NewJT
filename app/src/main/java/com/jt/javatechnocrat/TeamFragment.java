package com.jt.javatechnocrat;

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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class TeamFragment extends Fragment {


    private AppCompatActivity main;
    private View root;
    private RecyclerView teamList;
    private DatabaseReference teamRef;

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
        FirebaseRecyclerAdapter<Team,TeamViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Team, TeamViewHolder>(
                Team.class,
                R.layout.faculty_row,
                TeamViewHolder.class,
                teamRef
        ) {
            @Override
            protected void populateViewHolder(TeamViewHolder viewHolder, Team model, int position) {
                viewHolder.setAllData(model.getName(),model.getSubject(),model.getImage_url(),getContext());
            }
        };
        teamList.setLayoutManager(new LinearLayoutManager(main));
        teamList.setHasFixedSize(true);
        teamList.setAdapter(firebaseRecyclerAdapter);

        // Inflate the layout for this fragment
        return root;
    }
    public static class TeamViewHolder extends RecyclerView.ViewHolder{
        View mView;
        ImageView img;
        TextView nameText,subjectText;
        public TeamViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            img=mView.findViewById(R.id.faculty_image);
            nameText=mView.findViewById(R.id.faculty_name);
            subjectText=mView.findViewById(R.id.faculty_subject);
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
