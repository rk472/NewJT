package com.jt.javatechnocrat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    private Button callBtn, enterBtn;
    private Animation bottom,top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Button Assign
        callBtn = findViewById(R.id.call_button);
        enterBtn = findViewById(R.id.enter_button);
        //Animation Assign
        bottom = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        top = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        callBtn.setAnimation(top);
        enterBtn.setAnimation(bottom);
    }

    public void exploreNow(View view) {
        startActivity(new Intent(SplashActivity.this , MainActivity.class));
        finish();
    }

    public void callUs(View view) {

    }
}
