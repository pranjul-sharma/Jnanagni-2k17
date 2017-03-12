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
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pranjul on 31-01-2017.
 */

public class EventInfoActivity extends AppCompatActivity {

    private HashMap<String , ArrayList<String>> myHashMap=new HashMap<>();
    HashMap<String,String> map=new HashMap<>();
    Toolbar toolbar;
    TextView eventDesc,eventTimeVenue,eventJudgementalCriteria,eventCoordinators,eventTask,eventPrerequisites;
    TextView ttDesc,ttVenue,ttJudge,ttCoor,ttTask,ttSpec;
    Button btnRegForEvent;
    List<String> eventDetails=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        loadDatatoMap();
        toolbar=(Toolbar)findViewById(R.id.toolbar_event_info);
        Intent intent=getIntent();
        final String eventName=intent.getStringExtra("eventName");
        int event_cover=intent.getIntExtra("image_source",R.drawable.agni_icon);
        eventDetails=myHashMap.get(eventName.toLowerCase());
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
        eventDesc.setText(eventDetails.get(0));
        eventTask.setText(eventDetails.get(1));
        eventTimeVenue.setText(eventDetails.get(2));
        eventImage.setImageResource(event_cover);
        eventPrerequisites.setText(eventDetails.get(3));
        eventJudgementalCriteria.setText(eventDetails.get(4));
        eventCoordinators.setText(eventDetails.get(5));

        btnRegForEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                if(!sharedPreferences.getBoolean("IS_SIGNED_IN",false)){
                    Toast.makeText(getApplicationContext(),"please login first to continue register for events",Toast.LENGTH_LONG).show();
                }else{
                    initiateMap();
                    BackgroundTask backgroundTask=new BackgroundTask(EventInfoActivity.this);
                    backgroundTask.execute("evt-register",sharedPreferences.getString("USER_NAME",""),map.get(eventName.toLowerCase()));
                }
            }
        });

        if(eventName.equals("RUBIK\'S CUBE")||eventName.equals("MINI-MILITIA")|| eventName.equals("BOWLING")||eventName.equals("THROWBALL")|| eventName.equals("DART")){
           btnRegForEvent.setClickable(false);
           btnRegForEvent.setText("You need to buy token on the spot for this event");
       }

    }
    private void loadDatatoMap() {
        myHashMap.put("hydroriser",createList(R.string.desc_hydroriser,R.string.task_hydroriser,R.string.venue_hydroriser,R.string.spcf_hydroriser,R.string.judg_hydroriser,R.string.coor_hydroriser));
        myHashMap.put("ci-pher",createList(R.string.desc_cipher,R.string.task_cipher,R.string.venue_cipher,R.string.spcf_cipher,R.string.judg_cipher,R.string.coor_cipher));
        myHashMap.put("electroguisal",createList(R.string.desc_electroguisial,R.string.task_electroguisial,R.string.venue_electroguisial,R.string.spcf_electroguisial,R.string.judg_electroguisial,R.string.coor_electroguisial));
        myHashMap.put("annihilator",createList(R.string.desc_annihilator,R.string.task_annihilator,R.string.venue_annihilator,R.string.spcf_annihilator,R.string.judg_annihilator,R.string.coor_annihilator));
        myHashMap.put("apptitude",createList(R.string.desc_apptitude,R.string.task_apptitude,R.string.venue_apptitude,R.string.spcf_apptitude,R.string.judg_apptitude,R.string.coor_apptitude));
        myHashMap.put("ex-gesis",createList(R.string.desc_exgesis,R.string.task_exgesis,R.string.venue_exgesis,R.string.spcf_exgesis,R.string.judg_exgesis,R.string.coor_exgesis));
        myHashMap.put("concatenation",createList(R.string.desc_concatenation,R.string.task_concatenation,R.string.venue_concatenation,R.string.spcf_concatenation,R.string.judg_concatenation,R.string.coor_concatenation));
        myHashMap.put("electricio",createList(R.string.desc_electrocio,R.string.task_electrocio,R.string.venue_electrocio,R.string.spcf_electrocio,R.string.judg_electrocio,R.string.coor_electrocio));
        myHashMap.put("tinkerer",createList(R.string.desc_tinkerer,R.string.task_tinkerer,R.string.venue_tinkerer,R.string.spcf_tinkerer,R.string.judg_tinkerer,R.string.coor_tinkerer));
        myHashMap.put("nopc",createList(R.string.desc_nopc,R.string.task_nopc,R.string.venue_nopc,R.string.spcf_nopc,R.string.judg_nopc,R.string.coor_nopc));
        myHashMap.put("inclino",createList(R.string.desc_inclino,R.string.task_inclino,R.string.venue_inclino,R.string.spcf_inclino,R.string.judg_inclino,R.string.coor_inclino));
        myHashMap.put("cuandigo",createList(R.string.desc_cuandigo,R.string.task_cuandigo,R.string.venue_cuandigo,R.string.spcf_cuandigo,R.string.judg_cuandigo,R.string.coor_cuandigo));
        myHashMap.put("ameliorator",createList(R.string.desc_ameliorator,R.string.task_ameliorator,R.string.venue_ameliorator,R.string.spcf_ameliorator,R.string.judg_ameliorator,R.string.coor_ameliorator));
        myHashMap.put("abhivyakti",createList(R.string.desc_abhivyakti,R.string.task_abhivyakti,R.string.venue_abhivyakti,R.string.spcf_abhivyakti,R.string.judg_abhivyakti,R.string.coor_abhivyakti));
        myHashMap.put("third vision",createList(R.string.desc_third_vision,R.string.task_third_vision,R.string.venue_third_vision,R.string.spcf_third_vision,R.string.judg_third_vision,R.string.coor_third_vision));
        myHashMap.put("mist treasure hunt",createList(R.string.desc_mist,R.string.task_mist,R.string.venue_mist,R.string.spcf_mist,R.string.judg_mist,R.string.coor_mist));
        myHashMap.put("q-cognito",createList(R.string.desc_qcognito,R.string.task_qcognito,R.string.venue_qcognito,R.string.spcf_qcognito,R.string.judg_qcognito,R.string.coor_qcognito));
        myHashMap.put("freedoscrawl",createList(R.string.desc_freedscrawl,R.string.task_freedscrawl,R.string.venue_freedscrawl,R.string.spcf_freedscrawl,R.string.judg_freedscrawl,R.string.coor_freedscrawl));
        myHashMap.put("kalakriti",createList(R.string.desc_bursh_n_paint,R.string.task_bursh_n_paint,R.string.venue_bursh_n_paint,R.string.spcf_bursh_n_paint,R.string.judg_bursh_n_paint,R.string.coor_bursh_n_paint));
        myHashMap.put("crafts-villa",createList(R.string.desc_craftvilla,R.string.task_craftvilla,R.string.venue_craftvilla,R.string.spcf_craftvilla,R.string.judg_craftvilla,R.string.coor_craftvilla));
        myHashMap.put("enthuse",createList(R.string.desc_enthuse,R.string.task_enthuse,R.string.venue_enthuse,R.string.spcf_enthuse,R.string.judg_enthuse,R.string.coor_enthuse));
        myHashMap.put("cricket keeda",createList(R.string.desc_cricket_keeda,R.string.task_cricket_keeda,R.string.venue_cricket_keeda,R.string.spcf_cricket_keeda,R.string.judg_cricket_keeda,R.string.coor_cricket_keeda));
        myHashMap.put("fancy footwork",createList(R.string.desc_anukriti,R.string.task_anukriti,R.string.venue_anukriti,R.string.spcf_anukriti,R.string.judg_anukriti,R.string.coor_anukriti));
        myHashMap.put("sargam",createList(R.string.desc_sargam,R.string.task_sargam,R.string.venue_sargam,R.string.spcf_sargam,R.string.judg_sargam,R.string.coor_sargam));
        myHashMap.put("kritika",createList(R.string.desc_kritika,R.string.task_kritika,R.string.venue_kritika,R.string.spcf_kritika,R.string.judg_kritika,R.string.coor_kritika));
        myHashMap.put("lol",createList(R.string.desc_lol,R.string.task_lol,R.string.venue_lol,R.string.spcf_lol,R.string.judg_lol,R.string.coor_lol));
        myHashMap.put("nautankishala",createList(R.string.desc_nautankishala,R.string.task_nautankishala,R.string.venue_nautankishala,R.string.spcf_nautankishala,R.string.judg_nautankishala,R.string.coor_nautankishala));
        myHashMap.put("carrom",createList(R.string.desc_carrom,R.string.task_carrom,R.string.venue_carrom,R.string.spcf_carrom,R.string.judg_carrom,R.string.coor_carrom));
        myHashMap.put("table tennis",createList(R.string.desc_table_tennis,R.string.task_table_tennis,R.string.venue_table_tennis,R.string.spcf_table_tennis,R.string.judg_table_tennis,R.string.coor_table_tennis));
        myHashMap.put("chess",createList(R.string.desc_chess,R.string.task_chess,R.string.venue_chess,R.string.spcf_chess,R.string.judg_chess,R.string.coor_chess));
        myHashMap.put("badminton",createList(R.string.desc_badminton,R.string.task_badminton,R.string.venue_badminton,R.string.spcf_badminton,R.string.judg_badminton,R.string.coor_badminton));
        myHashMap.put("need for speed",createList(R.string.desc_nfs,R.string.task_nfs,R.string.venue_nfs,R.string.spcf_nfs,R.string.judg_nfs,R.string.coor_nfs));
        myHashMap.put("counter strike",createList(R.string.desc_counter_strike,R.string.task_counter_strike,R.string.venue_counter_strike,R.string.spcf_counter_strike,R.string.judg_counter_strike,R.string.coor_counter_strike));
        myHashMap.put("fifa",createList(R.string.desc_fifa,R.string.task_fifa,R.string.venue_fifa,R.string.spcf_fifa,R.string.judg_fifa,R.string.coor_fifa));
        myHashMap.put("rubik\'s cube",createList(R.string.desc_rubik_cube,R.string.task_rubik_cube,R.string.venue_rubik_cube,R.string.spcf_rubik_cube,R.string.judg_rubik_cube,R.string.coor_rubik_cube));
        myHashMap.put("mini-militia",createList(R.string.desc_minimilitia,R.string.task_minimilitia,R.string.venue_minimilitia,R.string.spcf_minimilitia,R.string.judg_minimilitia,R.string.coor_minimilitia));
        myHashMap.put("bowling",createList(R.string.desc_bowling,R.string.task_bowling,R.string.venue_bowling,R.string.spcf_bowling,R.string.judg_bowling,R.string.coor_bowling));
        myHashMap.put("dart",createList(R.string.desc_dart,R.string.task_dart,R.string.venue_dart,R.string.spcf_dart,R.string.judg_dart,R.string.coor_dart));
        myHashMap.put("throwball",createList(R.string.desc_throwball,R.string.task_throwball,R.string.venue_throwball,R.string.spcf_throwball,R.string.judg_throwball,R.string.coor_throwball));
        myHashMap.put("samagam",createList(R.string.desc_samagam,R.string.task_samagam,R.string.venue_samagam,R.string.spcf_samagam,R.string.judg_samagam,R.string.coor_samagam));
        myHashMap.put("celebrity visit",createList(R.string.desc_celebrity_visit,R.string.task_celebrity_visit,R.string.venue_celebrity_visit,R.string.spcf_celebrity_visit,R.string.judg_celebrity_visit,R.string.coor_celebrity_visit));
        myHashMap.put("startup fair",createList(R.string.desc_startup_fair,R.string.task_startup_fair,R.string.venue_startup_fair,R.string.spcf_startup_fair,R.string.judg_startup_fair,R.string.coor_startup_fair));
    }

    private ArrayList<String> createList(int desc,int task,int timeVenue,int spcf,int judg,int coor) {
        ArrayList<String> mList=new ArrayList<>();
        mList.add(getString(desc));
        mList.add(getString(task));
        mList.add(getString(timeVenue));
        mList.add(getString(spcf));
        mList.add(getString(judg));
        mList.add(getString(coor));
        return  mList;
    }


    private void nullCheck(){
        if(eventDetails.get(0).equals(""))
            ttDesc.setVisibility(View.GONE);
        if (eventDetails.get(1).equals(""))
            ttTask.setVisibility(View.GONE);
        if (eventDetails.get(2).equals(""))
            ttVenue.setVisibility(View.GONE);
        if (eventDetails.get(3).equals(""))
            ttSpec.setVisibility(View.GONE);
        if (eventDetails.get(4).equals(""))
            ttJudge.setVisibility(View.GONE);
        if (eventDetails.get(5).equals(""))
            ttCoor.setVisibility(View.GONE);
    }

    private void initiateMap(){
        map.put("hydroriser","tevent-0");
        map.put("ci-pher","tevent-1");
        map.put("electroguisal","tevent-2");
        map.put("annihilator","tevent-3");
        map.put("appitude","tevent-4");
        map.put("ex-gesis","tevent-5");
        map.put("concatenation","tevent-6");
        map.put("electricio","tevent-7");
        map.put("tinkerer","tevent-8");
        map.put("nopc","tevent-9");
        map.put("inclino","tevent-10");
        map.put("cuandigo","tevent-11");
        map.put("ameliorator","tevent-12");
        map.put("abhivyakti","ntevent-0");
        map.put("third vision","ntevent-1");
        map.put("mist treasure hunt","ntevent-2");
        map.put("q-cognito","ntevent-3");
        map.put("freedoscrawl","ntevent-4");
        map.put("kalakriti","ntevent-5");
        map.put("crafts-villa","ntevent-6");
        map.put("enthuse","ntevent-7");
        map.put("cricket keeda","ntevent-8");
        map.put("fancy footwork","cevent-0");
        map.put("sargam","cevent-1");
        map.put("kritika","cevent-2");
        map.put("lol","cevent-3");
        map.put("nautankishala","cevent-4");
        map.put("samagam","workshop-0");
        map.put("celebrity visit","workshop-1");
        map.put("startup fair","workshop-2");
        map.put("carrom","sevent-0");
        map.put("table tennis","sevent-1");
        map.put("chess","sevent-2");
        map.put("badminton","sevent-3");
        map.put("need for speed","sevent-4");
        map.put("counter strike","sevent-5");
        map.put("fifa","sevent-6");
        map.put("rubik\'s cube","fevent-0");
        map.put("mini-militia","fevent-1");
        map.put("bowling","fevent-2");
        map.put("dart","fevent-3");
        map.put("throwball","fevent-4");
    }
}
