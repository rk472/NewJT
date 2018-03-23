package com.jt.javatechnocrat;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentTransaction fragmentTransaction;
    String fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container,new HomeFragment(),"home");
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(getSupportFragmentManager().findFragmentById(R.id.main_container).getTag().equals("home"))
                    new AlertDialog.Builder(this)
                            .setTitle("Exit")
                            .setMessage("Do You really want to Exit ?")
                            .setPositiveButton("Yes, Sure", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    MainActivity.super.onBackPressed();
                                }
                            }).setNegativeButton("No, Don't",null).show();

            else{
                Fragment f=new HomeFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, f,"home");
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Fragment f=new HomeFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, f,"home");
            fragmentTransaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment f = null;
        int id = item.getItemId();
        String tag = null;
        if (id == R.id.nav_home) {
            f = new HomeFragment();
            tag="home";
        } else if (id == R.id.nav_courses) {
            f = new CoursesFragment();
            tag="other";
        } else if (id == R.id.nav_team) {
            f = new TeamFragment();
            tag="other";
        } else if (id == R.id.nav_batch) {
            f = new BatchFragment();
            tag="other";
        } else if (id == R.id.nav_notice) {
            f = new NoticeFragment();
            tag="other";
        } else if (id == R.id.nav_enquiry) {
            tag="other";
            f = new EnquiryFragment();
        }else if (id == R.id.nav_gallery) {
            f = new GalleryFragment();
            tag="other";
        }else if (id == R.id.nav_about) {
            f = new AboutFragment();
            tag="other";
        }else if (id == R.id.nav_project) {
            f = new ProjectFragment();
            tag="other";
        }else if (id == R.id.nav_contact) {
            f = new ContactFragment();
            tag="other";
        }
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, f,tag);
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void call(View v){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
            return;
        }
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:+919437010139"));
        startActivity(i);
    }
    public void mail(View v){
        Intent i=new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"javatech04@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Inquiry");
        i.putExtra(Intent.EXTRA_TEXT, "Hello I want to know about JT... !");
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i, "Send Email"));
    }
    public void find(View v){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=20.295576,85.8453781"));
        startActivity(intent);
    }
    public void fb(View view) {
        Intent i;
        i= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/javatechnocrat"));
        startActivity(i);
    }
    public void twitter(View view) {

    }
    public void gplus(View view) {

    }
    public void linkedin(View view) {

    }
    public void play(View view) {
        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
        }
    }
    public void whatsapp(View view) {
        Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://api.whatsapp.com/send?phone=+918908858864&text=Hii%20....I%20want%20to%20know%20about%20JT"));
        startActivity(i);
    }

}
