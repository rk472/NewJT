package com.jt.javatechnocrat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    private Button callBtn, enterBtn;
    private Animation bottom, top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Button Assign
        callBtn = findViewById(R.id.call_button);
        enterBtn = findViewById(R.id.enter_button);
        //Animation Assign
        bottom = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        top = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        callBtn.setAnimation(top);
        enterBtn.setAnimation(bottom);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(SplashActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SplashActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:+919437010139"));
                startActivity(i);

            }
        });
    }

    public void exploreNow(View view) {
        startActivity(new Intent(SplashActivity.this , MainActivity.class));
        finish();
    }

    public void callUs(View view) {

    }
}
