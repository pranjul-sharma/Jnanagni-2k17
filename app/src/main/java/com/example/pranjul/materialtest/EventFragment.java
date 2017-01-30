package com.example.pranjul.materialtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranjul on 29-01-2017.
 */


public class EventFragment extends Fragment {
    private RecyclerView recyclerView;
    private EventAadpter eventAadpter;
    private List<Event> eventList;
    public  String eventCat[]={"Technical","NonTechnical","Cultural","Sports","Workshops"};
    public  static int count=0;
    private int page;
    private String title;

    public static EventFragment newInstance(int page,String title){
        EventFragment eventFragment=new EventFragment();
        Bundle args=new Bundle();
        args.putInt("intValue",page);
        args.putString("title",title);
        eventFragment.setArguments(args);
        return eventFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page=getArguments().getInt("intValue");
        title=getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.event_fragment,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view_event);
        eventList=new ArrayList<>();
        eventAadpter=new EventAadpter(getContext(),eventList);
        recyclerView.setAdapter(eventAadpter);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        prepareEvents(eventCat[page]);
        return view;
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
        eventAadpter.notifyDataSetChanged();

    }

    private void prepareWorkshops() {
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

        eventAadpter.notifyDataSetChanged();

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

        eventAadpter.notifyDataSetChanged();
    }



}
