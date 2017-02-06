package com.example.pranjul.materialtest;

import android.graphics.drawable.RippleDrawable;
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
    public  String eventCat[]={"Technical","Non Technical","Game On","Fun Events","Cultural","Mega Shows"};
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
       if (eventCategory.equals("Technical"))
            prepareTechnicals();
        else if(eventCategory.equals("Non Technical"))
           prepareNonTechnicals();
        else if(eventCategory.equals("Game On"))
           prepareSports();
        else if(eventCategory.equals("Cultural"))
           prepareCultural();
        else if(eventCategory.equals("Fun Events"))
           prepareFunEvents();
        else if(eventCategory.equals("Mega Shows"))
           prepareMegaShows();
    }




    private void prepareTechnicals() {
        String[] events={"HYDRORISER", "CI-PHER", "ELECTROGUISIAL", "ANNIHILATOR",
                "APPTITUDE", "EX-GESIS", "CONCATENATION", "ELECTRICIO",
                "TINKERER", "NOPC", "INCLINO", "CUANDIGO", "AMELIORATOR",
                "COLONIZER"};
        int[] eventCovers={R.drawable.hydroriser,R.drawable.cipher,R.drawable.electroguisial,R.drawable.annihilator,
                R.drawable.apptitude,R.drawable.exgesis,R.drawable.concatination,R.drawable.electricio
                ,R.drawable.tinkerer,R.drawable.nopc,R.drawable.inclino,R.drawable.cuandigo,R.drawable.ameliorator,
                R.drawable.colonizer};
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],eventCovers[i]);
            eventList.add(event);
        }

        eventAadpter.notifyDataSetChanged();
    }
    private void prepareNonTechnicals() {
        String[] events={"ABHIVYAKTI", "THIRD VISION",
                "MIST TREASURE HUNT", "Q-COGNITO", "FREEDOSCRAWL",
                "BRUSH AND PAINT", "CREATIVE FASHION SHOW", "ENTHUSE"};
        int[] eventCovers={R.drawable.abhivakti,R.drawable.thirdvision,R.drawable.mist,R.drawable.qcognito
                ,R.drawable.freedoscrawl,R.drawable.brushndpaint,R.drawable.creativefashionshw,R.drawable.enthuse};
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],eventCovers[i]);
            eventList.add(event);
        }
        eventAadpter.notifyDataSetChanged();

    }



    private void prepareSports() {
        String[] events={"CARROM", "TABLE TENNIS", "CHESS",
                "BADMINTON", "NEED FOR SPEED", "COUNTER STRIKE",
                "FIFA"};
        int[] eventCovers={R.drawable.carrom,R.drawable.tabletennis,R.drawable.chess,R.drawable.badminton,
                R.drawable.needforspeed,R.drawable.counterstrike,R.drawable.fifa};
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],eventCovers[i]);
            eventList.add(event);
        }

        eventAadpter.notifyDataSetChanged();

    }

    private void prepareCultural() {
        String events[]={"ANUKRITI", "SARGAM", "ROCK SYNDROME", "KRITIKA", "LOL"};
        int eventCovers[]={R.drawable.anukriti,R.drawable.sargam,R.drawable.rocksynrom,R.drawable.kritika,R.drawable.lol};
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],eventCovers[i]);
            eventList.add(event);

        }

        eventAadpter.notifyDataSetChanged();


    }

    private void prepareFunEvents() {
        String events[]={"RUBIK\'S CUBE", "MINI-MILITIA", "BOWLING",
                "DART", "THROWBALL"};
        int eventCovers[]={R.drawable.rubik,R.drawable.minimilitia,R.drawable.bowling,R.drawable.dart,R.drawable.throwball};
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],eventCovers[i]);
            eventList.add(event);
        }
        eventAadpter.notifyDataSetChanged();


    }

    private void prepareMegaShows() {
        String events[]={"SAMAGAM","SYMVOGUE"};
        int eventCovers[]={R.drawable.samagam, R.drawable.symvogue};
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],eventCovers[i]);
            eventList.add(event);
        }
        eventAadpter.notifyDataSetChanged();
    }

}
