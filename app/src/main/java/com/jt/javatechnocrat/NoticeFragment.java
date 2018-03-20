package com.jt.javatechnocrat;

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


public class NoticeFragment extends Fragment {
    private AppCompatActivity main;
    private View root;
    private RecyclerView noticeList;
    private DatabaseReference noticeRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main=(AppCompatActivity)getActivity();
        main.getSupportActionBar().setTitle("Notice");
        root=inflater.inflate(R.layout.fragment_notice, container, false);
        //Nav View
        NavigationView navigationView = (NavigationView) main.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_notice);

        noticeRef= FirebaseDatabase.getInstance().getReference().child("notice");
        noticeList=root.findViewById(R.id.notice_list);
        FirebaseRecyclerAdapter<Notice,NoticeViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Notice, NoticeViewHolder>(
                Notice.class,
                R.layout.notice_row,
                NoticeViewHolder.class,
                noticeRef
        ) {
            @Override
            protected void populateViewHolder(NoticeViewHolder viewHolder, Notice model, int position) {
                viewHolder.setAllText(model.getTitle(),model.getDate(),model.getDescription());
            }
        };
        noticeList.setAdapter(firebaseRecyclerAdapter);
        noticeList.setLayoutManager(new LinearLayoutManager(main));
        noticeList.hasFixedSize();
        return root;
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder{
        TextView titleText,dateText,descText;
        public NoticeViewHolder(View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.notice_title);
            dateText=itemView.findViewById(R.id.notice_date);
            descText=itemView.findViewById(R.id.notice_description);

        }
        public void setAllText(String title,String date,String desc){
            titleText.setText(title);
            dateText.setText("Date : "+date);
            descText.setText(desc);
        }
    }
}
