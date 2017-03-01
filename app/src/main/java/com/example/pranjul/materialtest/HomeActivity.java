package com.example.pranjul.materialtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static HomeActivity currObject;
    private NavigationView navigationView;
    private MenuItem prevItem=null;
    private TextView title;
    private TextView title_year;
    private FragmentManager fragMan;
    private FragmentTransaction ft;
    //private Handler mHandler = new Handler();
    private DrawerLayout drawer;
    private Runnable  mPendingRunnable;
    private int id;
    private View headerView;
    private boolean navItemClicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currObject=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Jnanagni '17");
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                if(navItemClicked)
                    mPendingRunnable.run();
                //if (mPendingRunnable != null) {
                    //mHandler.post(mPendingRunnable);
                    //mPendingRunnable = null;
                //}
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView=navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        LinearLayout nav_header_home=(LinearLayout)navigationView.getHeaderView(0);
        title=(TextView)nav_header_home.findViewById(R.id.title_nav);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"Neptune.otf");
        title.setTypeface(typeface);
        title.setAllCaps(true);

        //setting typeface for the title year
        title_year=(TextView)nav_header_home.findViewById(R.id.title_nav_year);
        title_year.setTypeface(typeface);

        fragMan=getFragmentManager();
        fragMan.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
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
            }
        });
        ft=fragMan.beginTransaction();
        ft.replace(R.id.content_frame, new HomeFragment(), "visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        prevItem=navigationView.getMenu().getItem(0);
        navigationView.getMenu().getItem(0).setChecked(true);

        mPendingRunnable=new Runnable() {
            @Override
            public void run() {
                ft=fragMan.beginTransaction();
                if (id == R.id.nav_home) {
                    if(fragMan.getBackStackEntryCount()>1)
                        fragMan.popBackStack();
                    getSupportActionBar().setTitle("Home");
                } else if (id == R.id.nav_events) {
                    startActivity(new Intent(HomeActivity.currObject, Main3Activity.class));
                } else if (id == R.id.nav_location) {
                    ft.replace(R.id.content_frame, new LocationFragment(), "visible_fragment");
                    getSupportActionBar().setTitle("Location");
                } else if (id == R.id.nav_login) {
                    ft.replace(R.id.content_frame,new RegisterFragment(),"visible_fragment");
                    getSupportActionBar().setTitle("Register");
                } else if (id == R.id.nav_share) {

                } else if (id == R.id.nav_send) {
                    ft.replace(R.id.content_frame, new FeedbackFragment(), "visible_fragment");
                    getSupportActionBar().setTitle("Feedback");
                } else if(id==R.id.nav_contact) {
                    ft.replace(R.id.content_frame, new ContactFragment(), "visible_fragment");
                    getSupportActionBar().setTitle("Contact Us");
                }
                if(id!=R.id.nav_home&&getFragmentManager().getBackStackEntryCount()!=2)
                    ft.addToBackStack(null);
                if(id!=R.id.nav_events)
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                navItemClicked=false;
            }
        };
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else {
            fragMan.popBackStack();
            if (fragMan.getBackStackEntryCount() == 1)
                super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        navItemClicked=true;
        id = item.getItemId();
        item.setCheckable(true);
        prevItem.setChecked(false);
        item.setChecked(true);
        prevItem=item;

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(prevItem!=null) {
            prevItem.setChecked(false);
            prevItem=navigationView.getMenu().findItem(R.id.nav_home);
            prevItem.setChecked(true);
        }
    }
}
