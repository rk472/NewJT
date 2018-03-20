package com.jt.javatechnocrat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AppCompatActivity main;
    private View root;
    private SliderLayout mDemoSlider;
    private FragmentTransaction fragmentTransaction;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        main=(AppCompatActivity)getActivity();
        main.getSupportActionBar().setTitle("JT Home");
        root=inflater.inflate(R.layout.fragment_home, container, false);
        //Nav View
        NavigationView navigationView = (NavigationView) main.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
        //Image Slider
        mDemoSlider = root.findViewById(R.id.slider);
        //Image set
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.drawable.hannibal);
        file_maps.put("BBT",R.drawable.bigbang);
        file_maps.put("House of Cards",R.drawable.house);
        file_maps.put("GOT", R.drawable.game_of_thrones);
        //Image Slider
        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(main);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Foreground2Background);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new ChildAnimationExample());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        //Icons Initialization and OnClick set
        root.findViewById(R.id.home_courses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new CoursesFragment());
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_team).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new TeamFragment());
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_batch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new BatchFragment());
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_project).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new ProjectFragment());
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new NoticeFragment());
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_enquiry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new EnquiryFragment());
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new GalleryFragment());
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new AboutFragment());
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new ContactFragment());
                fragmentTransaction.commit();
            }
        });

        //Inflate the layout for this fragment
        return root;
    }
    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(main,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
