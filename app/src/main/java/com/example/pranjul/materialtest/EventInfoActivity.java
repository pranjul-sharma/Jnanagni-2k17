package com.example.pranjul.materialtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Pranjul on 31-01-2017.
 */

public class EventInfoActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView eventDesc,eventTimeVenue,eventJudgementalCriteria,eventCoordinators,eventTask,eventPrerequisites;
    TextView ttDesc,ttVenue,ttJudge,ttCoor,ttTask,ttSpec;
    Button btnRegForEvent;
    String[] eventDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        toolbar=(Toolbar)findViewById(R.id.toolbar_event_info);
        Intent intent=getIntent();
        String eventName=intent.getStringExtra("eventName");
        int event_cover=intent.getIntExtra("image_source",R.drawable.agni_icon);
        ArrayList<String> eventDetailList=intent.getStringArrayListExtra("eventDetails");
        eventDetails=eventDetailList.toArray(new String[eventDetailList.size()]);
        toolbar.setTitle(eventName);
        setSupportActionBar(toolbar);
        ttDesc=(TextView)findViewById(R.id.text_desc);
        ttTask=(TextView)findViewById(R.id.text_task);
        ttCoor=(TextView)findViewById(R.id.text_coor);
        ttJudge=(TextView)findViewById(R.id.text_judge);
        ttVenue=(TextView)findViewById(R.id.text_venue);
        ttSpec=(TextView)findViewById(R.id.text_spec);
        eventDesc=(TextView)findViewById(R.id.text_event_desc);
        eventTimeVenue=(TextView)findViewById(R.id.text_event_timevenue);

        ImageView  eventImage = (ImageView) findViewById(R.id.image_event_info);
        eventTask=(TextView)findViewById(R.id.text_event_task);
        eventPrerequisites=(TextView)findViewById(R.id.text_event_prerequisite);
        eventJudgementalCriteria=(TextView)findViewById(R.id.text_event_judgemental_criteria);
        eventCoordinators=(TextView)findViewById(R.id.text_event_coordinators);
        btnRegForEvent=(Button)findViewById(R.id.btn_reg_event);
        nullCheck();
        eventDesc.setText(eventDetails[0]);
        eventTask.setText(eventDetails[1]);
        eventTimeVenue.setText(eventDetails[2]);
        eventImage.setImageResource(event_cover);
        eventPrerequisites.setText(eventDetails[3]);
        eventJudgementalCriteria.setText(eventDetails[4]);
        eventCoordinators.setText(eventDetails[5]);
        btnRegForEvent.setClickable(false);

        /*
        btnRegForEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                if(!sharedPreferences.getBoolean("IS_SIGNED_IN",false)){
                    Toast.makeText(getApplicationContext(),"please login first to continue register for events",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"you can use this latter",Toast.LENGTH_LONG).show();
                }
            }
        });*/

    }

    private void nullCheck(){
        if(eventDetails[0].equals(""))
            ttDesc.setVisibility(View.GONE);
        if (eventDetails[1].equals(""))
            ttTask.setVisibility(View.GONE);
        if (eventDetails[2].equals(""))
            ttVenue.setVisibility(View.GONE);
        if (eventDetails[3].equals(""))
            ttSpec.setVisibility(View.GONE);
        if (eventDetails[4].equals(""))
            ttJudge.setVisibility(View.GONE);
        if (eventDetails[5].equals(""))
            ttCoor.setVisibility(View.GONE);
    }
}
