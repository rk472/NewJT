package com.jt.javatechnocrat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InquiryFragment extends Fragment {
    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getActionBar().setTitle("Inquiry");
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_inquiry, container, false);

        return mView;
    }


}
