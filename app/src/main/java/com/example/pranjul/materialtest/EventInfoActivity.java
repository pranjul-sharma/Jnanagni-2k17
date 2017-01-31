package com.example.pranjul.materialtest;

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
       toolbar.setTitle("Event Name Here");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_event_info,new EventInfoFragment());
        fragmentTransaction.commit();
    }
}
