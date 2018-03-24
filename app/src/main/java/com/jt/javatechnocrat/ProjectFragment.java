package com.jt.javatechnocrat;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProjectFragment extends Fragment {

    private AppCompatActivity main;
    private View root;
    private DatabaseReference projectRef,d;
    private RecyclerView projectList;
    private Spinner projectSubjectList;
    private List<String> list;
    private ProgressDialog pd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        main=(AppCompatActivity)getActivity();
        main.getSupportActionBar().setTitle("Projects");
        root=inflater.inflate(R.layout.fragment_project, container, false);
        //Nav View
        NavigationView navigationView =main.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_project);
        projectList=root.findViewById(R.id.project_list);
        projectList.setLayoutManager(new LinearLayoutManager(main));
        projectList.setHasFixedSize(true);
        projectSubjectList=root.findViewById(R.id.project_subject_list);
        pd = new ProgressDialog(main);
        pd.setTitle("Please Wait");
        pd.setCancelable(false);
        pd.setMessage("Loading Contents ...");
        pd.show();
        projectRef= FirebaseDatabase.getInstance().getReference().child("projects");
        projectRef.keepSynced(true);
        projectRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list=new ArrayList<>();
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    list.add(d.getKey().toString());
                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(main,android.R.layout.simple_spinner_item,list);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                projectSubjectList.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        projectSubjectList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String project= list.get(i);
                d=projectRef.child(project);
                d.keepSynced(true);
                FirebaseRecyclerAdapter<Project,ProjectViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Project, ProjectViewHolder>(
                        Project.class,
                        R.layout.project_row,
                        ProjectViewHolder.class,
                        d
                ) {
                    @Override
                    protected void populateViewHolder(ProjectViewHolder viewHolder, Project model, int position) {
                        pd.dismiss();
                        viewHolder.setAllData(model.getName(),model.getTechnology());
                    }
                };
                projectList.setAdapter(firebaseRecyclerAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return root;
    }
    public static class ProjectViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView technologyText;
        public ProjectViewHolder(View itemView) {
            super(itemView);
            nameText=itemView.findViewById(R.id.project_name);
            technologyText=itemView.findViewById(R.id.project_technology);
        }
        public void setAllData(String name,String tech){
            nameText.setText("Project : "+name);
            technologyText.setText("Technology Used : "+tech);
        }
    }
}
