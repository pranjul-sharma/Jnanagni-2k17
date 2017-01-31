package com.example.pranjul.materialtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
    private ShareActionProvider shareActionProvider;
    private Button login;
    private NavigationView navigationView;
    private int currentPosition;
    private MenuItem prevItem=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        login=(Button)nav_header_home.findViewById(R.id.login);
        TextView headerTV=(TextView)nav_header_home.findViewById(R.id.headerTV);
        SharedPreferences sharedpreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpreferences.edit();
        if(!sharedpreferences.getBoolean("IS_SIGNED_IN", false)) {
            editor.putBoolean("IS_SIGNED_IN", false);
            login.setText("Login");
            headerTV.setText("Jnanagni 2017");
        }
        else {
            login.setText("Logout");
            headerTV.setText("Hi "+sharedpreferences.getString("USER_NAME", ""));
        }

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager fragMan=getFragmentManager();
                Fragment fragment=fragMan.findFragmentByTag("visible_fragment");
                Menu menuNav=navigationView.getMenu();
                MenuItem item=null;
                //Log.e("I'm :", "here");
                if(fragment instanceof HomeFragment) {
                    item=menuNav.findItem(R.id.nav_home);
                    getSupportActionBar().setTitle("Home");
                }
                else if(fragment instanceof FeedbackFragment) {
                    //currentPosition=5;
                    item=menuNav.findItem(R.id.nav_send);
                    getSupportActionBar().setTitle("Feedback");
                }
                if(item!=null) {
                    prevItem.setChecked(false);
                    item.setChecked(true);
                    prevItem=item;
                }
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
            if (fragmentManager.getBackStackEntryCount() == 1)
                super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        item.setCheckable(true);
        item.setChecked(true);
        prevItem.setChecked(false);
        prevItem=item;
        if (id == R.id.nav_home) {
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new HomeFragment(), "visible_fragment");
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            getSupportActionBar().setTitle("Jnanagni '17");
        } else if (id == R.id.nav_events) {
            startActivity(new Intent(this, Main3Activity.class));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new FeedbackFragment(), "visible_fragment");
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            getSupportActionBar().setTitle("Feedback");
        } else if(id==R.id.nav_contact) {
            getSupportActionBar().setTitle("Contact Us");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        /*int size = navigationView.getMenu().size();
        for (int i = 0; i < size; i++)
            navigationView.getMenu().getItem(i).setChecked(false);*/
    }
}
