package com.jt.javatechnocrat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BatchFragment extends Fragment {


    private AppCompatActivity main;
    private View root;
    private RecyclerView batchView;
    private DatabaseReference batchRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main=(AppCompatActivity)getActivity();
        main.getSupportActionBar().setTitle("Upcoming Batches");
        root=inflater.inflate(R.layout.fragment_batch, container, false);
        //Nav View
        NavigationView navigationView = main.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_batch);

        batchView=root.findViewById(R.id.batch_list);
        batchRef= FirebaseDatabase.getInstance().getReference().child("upcoming_batches");
        FirebaseRecyclerAdapter<UpcomingBatches,BatchViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<UpcomingBatches, BatchViewHolder>(
                UpcomingBatches.class,
                R.layout.upcoming_batches_row,
                BatchViewHolder.class,
                batchRef
        ) {
            @Override
            protected void populateViewHolder(BatchViewHolder viewHolder, UpcomingBatches model, int position) {
                viewHolder.setAllText(model.getName(),model.getDate(),model.getTiming());
            }
        };
        batchView.setAdapter(firebaseRecyclerAdapter);
        batchView.setLayoutManager(new LinearLayoutManager(main));
        // Inflate the layout for this fragment
        return root;
    }
    public static class BatchViewHolder extends RecyclerView.ViewHolder{
        TextView nameText,startText,timingText;
        public BatchViewHolder(View itemView) {
            super(itemView);
            nameText= itemView.findViewById(R.id.batch_name);
            startText=itemView.findViewById(R.id.batch_date);
            timingText=itemView.findViewById(R.id.batch_timing);
        }
        public void setAllText(String name,String date,String timing){
            nameText.setText(name);
            startText.setText(date);
            timingText.setText(timing);
        }
    }

}
