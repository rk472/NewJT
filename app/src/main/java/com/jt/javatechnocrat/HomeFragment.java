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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.HashMap;


public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    private AppCompatActivity main;
    private View root;
    private SliderLayout mDemoSlider;
    private FragmentTransaction fragmentTransaction;
    private AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        main=(AppCompatActivity)getActivity();
        main.getSupportActionBar().setTitle("JT Home");
        root=inflater.inflate(R.layout.fragment_home, container, false);
        MobileAds.initialize(main, "ca-app-pub-3940256099942544~3347511713");
        mAdView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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
                fragmentTransaction.replace(R.id.main_container,new CoursesFragment(),"other");
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_team).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new TeamFragment(),"other");
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_batch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new BatchFragment(),"other");
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_project).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new ProjectFragment(),"other");
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new NoticeFragment(),"other");
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_enquiry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new EnquiryFragment(),"other");
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new GalleryFragment(),"other");
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new AboutFragment(),"other");
                fragmentTransaction.commit();
            }
        });
        root.findViewById(R.id.home_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction=main.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new ContactFragment(),"other");
                fragmentTransaction.commit();
            }
        });

        //Inflate the gallery_layout for this fragment
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
