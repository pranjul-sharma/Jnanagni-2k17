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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranjul on 29-01-2017.
 */


public class EventFragment extends Fragment {
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Event> eventList;
    public  String eventCat[]={"Technical","Non Technical","Cultural","Mega Events","Game On","Fun Events"};
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
        eventAdapter=new EventAdapter(getContext(),eventList);
        recyclerView.setAdapter(eventAdapter);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        prepareEvents(eventCat[page]);
        return view;
    }
    private void prepareEvents(String eventCategory) {
        switch (eventCategory){
            case "Technical":
                String[] eventsTechnical={"HYDRORISER", "CI-PHER", "ELECTROGUISIAL", "ANNIHILATOR",
                        "APPTITUD", "EX-GESIS", "CONCATENATION", "ELECTRICIO",
                        "TINKERER", "NOPC", "INCLINO", "CUANDIGO", "AMELIORATOR",
                        "HYDROBOT"};
                int[] coversTechnical={R.drawable.hydroriser,R.drawable.cipher,R.drawable.electroguisial,R.drawable.annihilator,
                        R.drawable.apptitude,R.drawable.exgesis,R.drawable.concatination,R.drawable.electricio
                        ,R.drawable.tinkerer,R.drawable.nopc,R.drawable.inclino,R.drawable.cuandigo,R.drawable.ameliorator,
                        R.drawable.hydrobot};
                prepareEvents(eventsTechnical,coversTechnical);
                break;
            case "Non Technical":
                String[] eventsNonTechnical={"ABHIVYAKTI", "THIRD VISION",
                        "MIST TREASURE HUNT", "Q-COGNITO", "FREEDOSCRAWL",
                        "KALAKRITI", "ENTHUSE"};
                int[] coversNonTechnical={R.drawable.abhivakti,R.drawable.thirdvision,R.drawable.mist,R.drawable.qcognito
                        ,R.drawable.freedoscrawl,R.drawable.brushndpaint,R.drawable.enthuse};
                prepareEvents(eventsNonTechnical,coversNonTechnical);
                break;
            case "Cultural":
                String eventsCultural[]={"FANCY FOOTWORK", "SARGAM","CRAFT-VILLA" , "KRITIKA", "LOL"};
                int coversCultural[]={R.drawable.anukriti,R.drawable.sargam,R.drawable.creativefashionshw,R.drawable.kritika,R.drawable.lol};
                prepareEvents(eventsCultural,coversCultural);
                break;
            case "Mega Events":
                String eventsMega[]={"SAMAGAM","CELEBRITY VISIT","STARTUP FAIR"};
                int coversMega[]={R.drawable.samagam,R.drawable.celeb,R.drawable.startup};
                prepareEvents(eventsMega,coversMega);
                break;
            case "Game On":
                String[] eventsSports={"CARROM", "TABLE TENNIS", "CHESS",
                        "BADMINTON", "NEED FOR SPEED", "COUNTER STRIKE",
                        "FIFA"};
                int[] coversSports={R.drawable.carrom,R.drawable.tabletennis,R.drawable.chess,R.drawable.badminton,
                        R.drawable.needforspeed,R.drawable.counterstrike,R.drawable.fifa};
                prepareEvents(eventsSports,coversSports);
                break;

            case "Fun Events":
                String eventsFun[]={"RUBIK\'S CUBE", "MINI-MILITIA", "BOWLING",
                        "DART", "THROWBALL"};
                int coversFun[]={R.drawable.rubik,R.drawable.minimilitia,R.drawable.bowling,R.drawable.dart,R.drawable.throwball};
                prepareEvents(eventsFun,coversFun);
                break;

        }
    }


    private void prepareEvents(String[] events,int[] covers){
        Event event;
        for(int i=0;i<events.length;i++){
            event=new Event(events[i],covers[i]);
            eventList.add(event);
        }
        eventAdapter.notifyDataSetChanged();
    }

}
