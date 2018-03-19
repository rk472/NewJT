package com.jt.javatechnocrat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InquiryFragment extends Fragment {
    private AppCompatActivity main;
    private View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main=(AppCompatActivity)getActivity();
        main.getSupportActionBar().setTitle("Inquiry");
        root=inflater.inflate(R.layout.fragment_inquiry, container, false);

        // Inflate the layout for this fragment
        return root;
    }
}
