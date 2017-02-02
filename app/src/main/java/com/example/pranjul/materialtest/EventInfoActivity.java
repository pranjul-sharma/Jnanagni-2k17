package com.example.pranjul.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


/**
 * Created by Pranjul on 31-01-2017.
 */

public class EventInfoActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        toolbar=(Toolbar)findViewById(R.id.toolbar_event_info);
        Intent intent=getIntent();
        String eventName=intent.getStringExtra("eventName");
        toolbar.setTitle(eventName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EventInfoFragment eventInfoFragment=new EventInfoFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_event_info,eventInfoFragment);
        fragmentTransaction.commit();
    }
}
