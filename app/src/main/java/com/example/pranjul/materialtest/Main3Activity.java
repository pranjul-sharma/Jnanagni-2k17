package com.example.pranjul.materialtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventAadpter eventAadpter;
    private List<Event> eventList;
    public static int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent=getIntent();
        String eventCat=intent.getStringExtra("showCategory");
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_event);
        eventList=new ArrayList<>();
        eventAadpter=new EventAadpter(this,eventList);
        recyclerView.setAdapter(eventAadpter);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        prepareEvents(eventCat);

    }

    private void prepareEvents(String eventCategory) {
        if(eventCategory.equals("Technical"))
            prepareTechnicals();
        else if(eventCategory.equals("NonTechnical"))
            prepareNonTechnicals();
        else if(eventCategory.equals("Cultural"))
            prepareCultural();
        else if(eventCategory.equals("Sports"))
            prepareSports();
        else if(eventCategory.equals("Workshops"))
            prepareWorkshops();
    }

    private void prepareSports() {
        String[] events={"CHESS", "CARROM", "TABLE TENNIS", "NEED FOR SPEED",
                "COUNTER STRIKE", "FIFA", "MINI MILITIA", "BADMINTON"};
        int[] eventCovers={R.drawable.chess,R.drawable.carrom,R.drawable.tabletennis,R.drawable.needforspeed,
        R.drawable.counterstrike,R.drawable.fifa,R.drawable.minimilitia,R.drawable.badminton};
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],eventCovers[i]);
            eventList.add(event);
        }
        Main3Activity.count=events.length;
        eventAadpter.notifyDataSetChanged();

    }

    private void prepareCultural() {
        String events[]={ "SYNVOGUE", "JUNOON", "ANKRITI", "FLASH MOB", "SINGING",
                "LOL", "REPARTEE", "NAUTANKI SHALA","ANUBHAV (ENTREPRENEUR)","POETRY"};
        int eventCovers[]={R.drawable.ceventgray,R.drawable.ceventgray,R.drawable.ceventgray,
        R.drawable.flashmob,R.drawable.singing,R.drawable.ceventgray,R.drawable.ceventgray,
        R.drawable.ceventgray,R.drawable.anubhav,R.drawable.poetry};
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],eventCovers[i]);
            eventList.add(event);

        }
        Main3Activity.count=events.length;
        eventAadpter.notifyDataSetChanged();


    }

    private void prepareNonTechnicals() {
        String[] events={"CRAFTSVILLA", "POSTER PRESENTATION", "APTOZIUM", "DEBATE",
                "EXTEMPORE", "RUBIK\'S CUBE", "QUIZ", "ART & CRAFT",
                "TREASURE HUNT", "SOCIAL QUOTIENT", "THIRD VISION","ROBOTICS"};
        int eventCovers=R.drawable.nteventgray;
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],eventCovers);
            eventList.add(event);
        }
        Main3Activity.count=events.length;
        eventAadpter.notifyDataSetChanged();

    }

    private void prepareWorkshops() {
        Main3Activity.count=0;
        Toast.makeText(getApplicationContext(),"working",Toast.LENGTH_LONG).show();
    }

    private void prepareTechnicals() {
        String[] events={"CI-PHER", "AMELIORATOR", "APPTITUDE", "CUANDIGO", "NOPC",
                "FANGZHI", "ELECTROGUISIAL", "TINKERER", "AERIALTRONICS", "CIRCUIT DEBUGGING",
                "SIMPRO", "HYDRORISER", "CONCATENATION", "INCLINO", "COLONIZER & SKY-SCREPER"};
        int[] eventCovers={R.drawable.cipher,R.drawable.teventgray,R.drawable.apptitude,
                R.drawable.cuandigo,R.drawable.nopc,R.drawable.teventgray,R.drawable.teventgray,
                R.drawable.tinkerer,R.drawable.aerialtronics,R.drawable.circuitdebugging,
                R.drawable.simpro,R.drawable.hydroriser,R.drawable.teventgray,R.drawable.inclino,
                R.drawable.teventgray};
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],eventCovers[i]);
            eventList.add(event);
        }
        Main3Activity.count=events.length;
        eventAadpter.notifyDataSetChanged();
    }
}
