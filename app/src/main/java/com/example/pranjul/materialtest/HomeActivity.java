package com.example.pranjul.materialtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static HomeActivity currObject;
    private ShareActionProvider shareActionProvider;
    private Button login;
    private NavigationView navigationView;
    private MenuItem prevItem=null;
    private TextView title;
    private TextView title_year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currObject=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Jnanagni '17");
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout nav_header_home=(LinearLayout)navigationView.getHeaderView(0);
        title=(TextView)nav_header_home.findViewById(R.id.title_nav);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"Neptune.otf");
        title.setTypeface(typeface);
        title.setAllCaps(true);

        //setting typeface for the title year
        title_year=(TextView)nav_header_home.findViewById(R.id.title_nav_year);
        title_year.setTypeface(typeface);
        //login=(Button)nav_header_home.findViewById(R.id.login);
        /*TextView headerTV=(TextView)nav_header_home.findViewById(R.id.headerTV);
        SharedPreferences sharedpreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpreferences.edit();
        if(!sharedpreferences.getBoolean("IS_SIGNED_IN", false)) {
            editor.putBoolean("IS_SIGNED_IN", false);
            login.setText("Login");
            headerTV.setText("Home");
        }
        else {
            login.setText("Logout");
            headerTV.setText("Hi "+sharedpreferences.getString("USER_NAME", ""));
        }*/

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager fragMan=getFragmentManager();
                Fragment fragment=fragMan.findFragmentByTag("visible_fragment");
                Menu menuNav=navigationView.getMenu();
                MenuItem item;
                if(fragment instanceof HomeFragment) {
                    getSupportActionBar().setTitle("Home");
                    item=menuNav.findItem(R.id.nav_home);
                    prevItem.setChecked(false);
                    item.setChecked(true);
                    prevItem=item;
                }

                //Log.e("I'm :", "here in back stack changed ");
                /*if(!(fragment instanceof HomeFragment)) {
                    item=menuNav.findItem(R.id.nav_home);
                    getSupportActionBar().setTitle("Home");
                    prevItem.setChecked(false);
                    item.setChecked(true);
                    prevItem=item;
                }
                else if(fragment instanceof FeedbackFragment) {
                    item=menuNav.findItem(R.id.nav_send);
                    getSupportActionBar().setTitle("Feedback");
                }
                else if(fragment instanceof ContactFragment) {
                    //currentPosition=5;
                    item=menuNav.findItem(R.id.nav_contact);
                    getSupportActionBar().setTitle("Contact");
                }
                if(item!=null) {
                    prevItem.setChecked(false);
                    item.setChecked(true);
                    prevItem=item;
                }*/
            }
        });
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new HomeFragment(), "visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        prevItem=navigationView.getMenu().getItem(0);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();
            //Fragment fragment=fragmentManager.findFragmentByTag("visible_fragment");
            Log.e("No. of back entries :", fragmentManager.getBackStackEntryCount()+"");
            if (fragmentManager.getBackStackEntryCount() == 1)
                super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        int id = item.getItemId();
        item.setCheckable(true);
        prevItem.setChecked(false);
        item.setChecked(true);
        prevItem=item;
        if (id == R.id.nav_home) {
            ft.replace(R.id.content_frame, new HomeFragment(), "visible_fragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            getSupportActionBar().setTitle("Home");
        } else if (id == R.id.nav_events) {
            startActivity(new Intent(this, Main3Activity.class));
        } else if (id == R.id.nav_location) {
            ft.replace(R.id.content_frame, new LocationFragment(), "visible_fragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            getSupportActionBar().setTitle("Location");
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            ft.replace(R.id.content_frame, new FeedbackFragment(), "visible_fragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            getSupportActionBar().setTitle("Feedback");
        } else if(id==R.id.nav_contact) {
            ft.replace(R.id.content_frame, new ContactFragment(), "visible_fragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            getSupportActionBar().setTitle("Contact Us");
        }
        if(id!=R.id.nav_home&&getFragmentManager().getBackStackEntryCount()!=2)
            ft.addToBackStack(null);
        ft.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        //Log.e("I'm in", "the start method");
        if(prevItem!=null) {
            prevItem.setChecked(false);
            prevItem=navigationView.getMenu().findItem(R.id.nav_home);
            prevItem.setChecked(true);
        }
    }
}
