package com.jt.javatechnocrat;

import android.app.ProgressDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnquiryFragment extends Fragment {
    private AppCompatActivity main;
    private View root;
    private CheckBox[] checkBoxes=new CheckBox[15];
    private EditText nameText,profText,fatherText,fatherProfText,motherText,motherProfText,collegeText,semText,tradeText,emailText,contactNoText,altNoText,refFriendtext,refMarketingText;
    private RadioGroup placeRadio;
    private Button submitButton;
    private TextView dateText;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main=(AppCompatActivity)getActivity();
        main.getSupportActionBar().setTitle("Enquiry");
        root=inflater.inflate(R.layout.fragment_enquiry, container, false);
        nameText=root.findViewById(R.id.enquiry_name);
        profText=root.findViewById(R.id.enquiry_proffession);
        fatherText=root.findViewById(R.id.enquiry_father);
        fatherProfText=root.findViewById(R.id.enquiry_father_proffession);
        motherText=root.findViewById(R.id.enquiry_mother);
        motherProfText=root.findViewById(R.id.enquiry_mother_proffession);
        collegeText=root.findViewById(R.id.enquiry_college);
        semText=root.findViewById(R.id.enquiry_semester);
        tradeText=root.findViewById(R.id.enquiry_trade);
        emailText=root.findViewById(R.id.enquiry_email);
        contactNoText=root.findViewById(R.id.enquiry_contact_no);
        altNoText=root.findViewById(R.id.enquiry_alternative_no);
        refFriendtext=root.findViewById(R.id.enquiry_reference_friend);
        refMarketingText=root.findViewById(R.id.enquiry_reference_marketing);
        checkBoxes[0]=root.findViewById(R.id.enquiry_c);
        checkBoxes[1]=root.findViewById(R.id.enquiry_cpp);
        checkBoxes[2]=root.findViewById(R.id.enquiry_core_java);
        checkBoxes[3]=root.findViewById(R.id.enquiry_adv_java);
        checkBoxes[4]=root.findViewById(R.id.enquiry_oracle);
        checkBoxes[5]=root.findViewById(R.id.enquiry_android);
        checkBoxes[6]=root.findViewById(R.id.enquiry_iot);
        checkBoxes[7]=root.findViewById(R.id.enquiry_php);
        checkBoxes[8]=root.findViewById(R.id.enquiry_python);
        checkBoxes[9]=root.findViewById(R.id.enquiry_web_technology);
        checkBoxes[10]=root.findViewById(R.id.enquiry_spring);
        checkBoxes[11]=root.findViewById(R.id.enquiry_data_structure);
        checkBoxes[12]=root.findViewById(R.id.enquiry_net);
        checkBoxes[13]=root.findViewById(R.id.enquiry_bigdata);
        checkBoxes[14]=root.findViewById(R.id.enquiry_english);
        placeRadio=root.findViewById(R.id.enquiry_place);
        dateText=root.findViewById(R.id.inquiry_date);
        submitButton=root.findViewById(R.id.enquiry_submit);
        Calendar c=Calendar.getInstance();
        String date=Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        final String month=Integer.toString(c.get(Calendar.MONTH)+1);
        String year=Integer.toString(c.get(Calendar.YEAR));
        dateText.setText(date+"/"+month+"/"+year);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) main.getSystemService(Context.INPUT_METHOD_SERVICE);
                String name = nameText.getText().toString();
                String prof = profText.getText().toString();
                String father = fatherText.getText().toString();
                String fatherProf = fatherProfText.getText().toString();
                String mother = motherText.getText().toString();
                String motherProf = motherProfText.getText().toString();
                String college = collegeText.getText().toString();
                String sem = semText.getText().toString();
                String trade = tradeText.getText().toString();
                String email=emailText.getText().toString();
                String contactNo = contactNoText.getText().toString();
                String altNo = altNoText.getText().toString();
                String friend = refFriendtext.getText().toString();
                String marketing = refMarketingText.getText().toString();
                int b=0;
                for(CheckBox c:checkBoxes){
                    if(c.isChecked()) {
                        b=1;
                    }
                }
                if(placeRadio.getCheckedRadioButtonId()==-1){
                    Toast.makeText(main, "You Must Select the place", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(name)) {
                    Toast.makeText(main, "Name can't be blank", Toast.LENGTH_SHORT).show();
                    nameText.requestFocus();
                    imm.showSoftInput(nameText, InputMethodManager.SHOW_IMPLICIT);
                }else if (TextUtils.isEmpty(prof)) {
                    profText.requestFocus();
                    Toast.makeText(main, "Proffession can't be blank", Toast.LENGTH_SHORT).show();
                    imm.showSoftInput(profText, InputMethodManager.SHOW_IMPLICIT);
                }else if (TextUtils.isEmpty(father)) {
                    profText.requestFocus();
                    Toast.makeText(main, "Father's Name can't be blank", Toast.LENGTH_SHORT).show();
                    imm.showSoftInput(fatherText, InputMethodManager.SHOW_IMPLICIT);
                }else if (TextUtils.isEmpty(mother)) {
                    profText.requestFocus();
                    Toast.makeText(main, "mother's Name can't be blank", Toast.LENGTH_SHORT).show();
                    imm.showSoftInput(motherText, InputMethodManager.SHOW_IMPLICIT);
                }else if (TextUtils.isEmpty(college)) {
                    profText.requestFocus();
                    Toast.makeText(main, "College Name can't be blank", Toast.LENGTH_SHORT).show();
                    imm.showSoftInput(collegeText, InputMethodManager.SHOW_IMPLICIT);
                }else if(TextUtils.isEmpty(sem)){
                    profText.requestFocus();
                    Toast.makeText(main, "Semester can't be blank", Toast.LENGTH_SHORT).show();
                    imm.showSoftInput(semText, InputMethodManager.SHOW_IMPLICIT);
                }else if(TextUtils.isEmpty(trade)){
                    profText.requestFocus();
                    Toast.makeText(main, "Trade can't be blank", Toast.LENGTH_SHORT).show();
                    imm.showSoftInput(tradeText, InputMethodManager.SHOW_IMPLICIT);
                }
                else if(TextUtils.isEmpty(email)){
                    profText.requestFocus();
                    Toast.makeText(main, "Email Address No can't be blank", Toast.LENGTH_SHORT).show();
                    imm.showSoftInput(emailText, InputMethodManager.SHOW_IMPLICIT);
                }else if(TextUtils.isEmpty(contactNo)){
                    profText.requestFocus();
                    Toast.makeText(main, "Contact No can't be blank", Toast.LENGTH_SHORT).show();
                    imm.showSoftInput(profText, InputMethodManager.SHOW_IMPLICIT);
                }else if(b==0){
                    Toast.makeText(main, "You must Check atleast one of the Courses", Toast.LENGTH_SHORT).show();
                }else{
                    final ProgressDialog p=new ProgressDialog(main);
                    p.setTitle("please Wait ");
                    p.setMessage("Please wait while we are submitting your request");
                    p.setCanceledOnTouchOutside(false);
                    p.setCancelable(false);
                    p.show();
                    if(TextUtils.isEmpty(fatherProf))fatherProf="none";
                    if(TextUtils.isEmpty(motherProf))motherProf="none";
                    if(TextUtils.isEmpty(altNo))altNo="none";
                    if(TextUtils.isEmpty(friend))friend="none";
                    if(TextUtils.isEmpty(marketing))marketing="none";
                    Map m=new HashMap();
                    m.put("date",dateText.getText().toString());
                    int id=placeRadio.getCheckedRadioButtonId();
                    RadioButton r=root.findViewById(id);
                    m.put("place",r.getText().toString());
                    m.put("profession",prof);
                    m.put("father",father);
                    m.put("father_prof",fatherProf);
                    m.put("mother",mother);
                    m.put("mother_prof",motherProf);
                    m.put("college",college);
                    m.put("sem",sem);
                    m.put("trade",trade);
                    m.put("email",email);
                    m.put("contact_no",contactNo);
                    m.put("alt_no",altNo);
                    m.put("ref_friend",friend);
                    m.put("ref_marketing",marketing);
                    for(CheckBox c:checkBoxes){
                        if(c.getText().equals(".Net")){
                            m.put("dotNet",c.isChecked());
                        }else{
                            m.put(c.getText().toString(),c.isChecked());
                        }
                    }
                    DatabaseReference d= FirebaseDatabase.getInstance().getReference().child("enquiry");
                    String key=d.push().getKey();
                    d.child(key).updateChildren(m).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            p.dismiss();
                            if(task.isSuccessful()) {
                                Toast.makeText(main, "Your query is successfully Submited..", Toast.LENGTH_SHORT).show();
                                View view = main.getCurrentFocus();
                                if (view != null) {
                                    InputMethodManager imm = (InputMethodManager)main.getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                }
                                Fragment f=new HomeFragment();
                                FragmentTransaction fragmentTransaction = main.getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.main_container, f);
                                fragmentTransaction.commit();
                            }
                            else
                                Toast.makeText(main, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        return root;
    }
}
