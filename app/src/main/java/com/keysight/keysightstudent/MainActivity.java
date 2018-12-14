package com.keysight.keysightstudent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    Class fragmentClass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_navigation();
        init_fragment();


    }

    protected void init_navigation() {
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void init_fragment() {
        Fragment fragment = new Fragment();
        fragment.setArguments(getIntent().getExtras());
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        try {
            fragment = HomeFragment.class.newInstance();
        } catch(Exception e){
            e.printStackTrace();
        }
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //create the fragment and specify the fragment to show (based on which is clicked)
        Fragment fragment = null;
        drawer = findViewById(R.id.drawer_layout);

        switch(item.getItemId()) {
            case R.id.navigation_home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.navigation_edu:
                fragmentClass = EduFragment.class;
                break;
            case R.id.navigation_blog:
                fragmentClass = BlogFragment.class;
                break;
            case R.id.navigation_about:
                fragmentClass = AboutFragment.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch(Exception e){
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            fragmentManager.beginTransaction().addToBackStack(null).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}