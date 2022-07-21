package com.wizelp.smsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.sentry.Sentry;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , DrawerLayout.DrawerListener {
    Fragment fragment;
    ImageView crashButton ;
    DrawerLayout drawer;
    TextView header_text,header_tv;
    ImageView back,setting_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sentry

        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }



        //SharedPreferences settings = MainActivity.this.getSharedPreferences("njkws", 0);
        fragment = new Smsfragment();
        attachFragment();




//        header_tv.setText(settings.getString(utils.Phonekey,""));
//        String myStrValue1 = settings.getString("aman", "");
// Log.e("dfjdkfj","sdjkdjd"+myStrValue+""+myStrValue1);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.purple_200));

        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        drawer.setDrawerListener(this);
        back=findViewById(R.id.back);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        header_tv = header.findViewById(R.id.header_tv);
        setting_icon=findViewById(R.id.setting_icon);
        setting_icon.setVisibility(View.INVISIBLE);
        back.setImageResource(R.drawable.menu);

        header_text=findViewById(R.id.header_text);
        back.setVisibility(View.VISIBLE);



      String amna=utils.getDeviceToken(MainActivity.this);


            header_tv.setText(amna);




//        header_tv.setText(amna);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        //need an editor to edit and save values


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        header_text.setText("SMS");
        navigationView.setNavigationItemSelectedListener(this);
        crashButton = new ImageView(this);
        crashButton.setImageResource(R.drawable.back_circle);
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                Log.e("crashButton","crashButton>>");
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        addContentView(crashButton, new ViewGroup.LayoutParams
                (
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        int topmarg =(int) getResources().getDimension(R.dimen._55sdp);
        int rightmarg =(int) getResources().getDimension(R.dimen._33sdp);
        params.setMargins(0, topmarg, rightmarg, 0);
        params.gravity= Gravity.RIGHT;
        crashButton.setLayoutParams(params);
        crashButton.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.dashboard)
        {
            header_text.setText("SMS");
            back.setImageResource(R.drawable.menu);
            setting_icon.setVisibility(View.INVISIBLE);
            //Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
            fragment = new Smsfragment();
            attachFragment();
        }
        else if (id==R.id.addContact)
        {
            header_text.setText("SMS");
            back.setImageResource(R.drawable.menu);
            setting_icon.setVisibility(View.INVISIBLE);
            fragment = new AddContact();
            attachFragment();

        }




        else if (id == R.id.logout)
        {
//            header_text.setText("Logout");
            back.setImageResource(R.drawable.menu);
            setting_icon.setVisibility(View.INVISIBLE);

            utils.setLogOut(MainActivity.this);
            utils.setCountLogout(MainActivity.this);
            utils.deleteCache(MainActivity.this);
//            fragment = new MyProfile_Fragment();
            //Utils.Logout("",this);

        }
        attachFragment();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {

        super.onResume();
    }
    private void attachFragment() {
        if (fragment != null) {
            FragmentTransaction ft;
            Bundle args = new Bundle();
            args.putString("Key", "NavigationDrawer");
            fragment.setArguments(args);
            ft  = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_container,fragment);
            try {
                ft.commit();

            } catch (IllegalStateException ignored) {

            }


        } else {


        }
    }
    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        Log.e("drawerView","drawerView----1");
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            Log.e("drawerView","drawerView----1--2");
            crashButton.setVisibility(View.GONE);
        }


    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView)
    {
        Log.e("drawerView","drawerView----2");
        crashButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView)
    {
        crashButton.setVisibility(View.GONE);
        Log.e("drawerView","drawerView----3");

    }

    @Override
    public void onDrawerStateChanged(int newState) {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            //drawer is open
            crashButton.setVisibility(View.VISIBLE);
        }
        Log.e("drawerView","drawerView----4"+newState);
    }



}