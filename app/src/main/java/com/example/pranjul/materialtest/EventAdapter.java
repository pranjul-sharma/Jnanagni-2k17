package com.example.pranjul.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Pranjul on 28-01-2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHodler> {
    private Context mContext;

    private List<Event> eventList;
    private HashMap<String , ArrayList<String>> myHashMap=new HashMap<>();
    public EventAdapter(Context context,List<Event> eventList) {
        this.mContext=context;
        this.eventList=eventList;

    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView= LayoutInflater.from(mContext).inflate(R.layout.event_card,parent,false);
        return new MyViewHodler(mView);
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        final Event event=eventList.get(position);
        holder.textView.setText(event.getEventName());
        loadDatatoMap();

        Glide.with(mContext).load(event.getEventCover()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,EventInfoActivity.class);
                String eventName=event.getEventName();
                intent.putExtra("eventName",eventName);
                intent.putExtra("image_source",event.getEventCover());
                intent.putStringArrayListExtra("eventDetails",myHashMap.get(eventName.toLowerCase()));
                mContext.startActivity(intent);
            }
        });

    }

    private void loadDatatoMap() {
        myHashMap.put("hydroriser",createList(R.string.desc_hydroriser,R.string.task_hydroriser,R.string.venue_hydroriser,R.string.spcf_hydroriser,R.string.judg_hydroriser,R.string.coor_hydroriser));
        myHashMap.put("ci-pher",createList(R.string.desc_cipher,R.string.task_cipher,R.string.venue_cipher,R.string.spcf_cipher,R.string.judg_cipher,R.string.coor_cipher));
        myHashMap.put("electroguisial",createList(R.string.desc_electroguisial,R.string.task_electroguisial,R.string.venue_electroguisial,R.string.spcf_electroguisial,R.string.judg_electroguisial,R.string.coor_electroguisial));
        myHashMap.put("annihilator",createList(R.string.desc_annihilator,R.string.task_annihilator,R.string.venue_annihilator,R.string.spcf_annihilator,R.string.judg_annihilator,R.string.coor_annihilator));
        myHashMap.put("apptitud",createList(R.string.desc_apptitude,R.string.task_apptitude,R.string.venue_apptitude,R.string.spcf_apptitude,R.string.judg_apptitude,R.string.coor_apptitude));
        myHashMap.put("ex-gesis",createList(R.string.desc_exgesis,R.string.task_exgesis,R.string.venue_exgesis,R.string.spcf_exgesis,R.string.judg_exgesis,R.string.coor_exgesis));
        myHashMap.put("concatenation",createList(R.string.desc_concatenation,R.string.task_concatenation,R.string.venue_concatenation,R.string.spcf_concatenation,R.string.judg_concatenation,R.string.coor_concatenation));
        myHashMap.put("electricio",createList(R.string.desc_electrocio,R.string.task_electrocio,R.string.venue_electrocio,R.string.spcf_electrocio,R.string.judg_electrocio,R.string.coor_electrocio));
        myHashMap.put("tinkerer",createList(R.string.desc_tinkerer,R.string.task_tinkerer,R.string.venue_tinkerer,R.string.spcf_tinkerer,R.string.judg_tinkerer,R.string.coor_tinkerer));
        myHashMap.put("nopc",createList(R.string.desc_nopc,R.string.task_nopc,R.string.venue_nopc,R.string.spcf_nopc,R.string.judg_nopc,R.string.coor_nopc));
        myHashMap.put("inclino",createList(R.string.desc_inclino,R.string.task_inclino,R.string.venue_inclino,R.string.spcf_inclino,R.string.judg_inclino,R.string.coor_inclino));
        myHashMap.put("cuandigo",createList(R.string.desc_cuandigo,R.string.task_cuandigo,R.string.venue_cuandigo,R.string.spcf_cuandigo,R.string.judg_cuandigo,R.string.coor_cuandigo));
        myHashMap.put("ameliorator",createList(R.string.desc_ameliorator,R.string.task_ameliorator,R.string.venue_ameliorator,R.string.spcf_ameliorator,R.string.judg_ameliorator,R.string.coor_ameliorator));
        myHashMap.put("hydrobot",createList(R.string.desc_hydrobot,R.string.task_hydrobot,R.string.venue_hydrobot,R.string.spcf_hydrobot,R.string.judg_hydrobot,R.string.coor_hydrobot));
        myHashMap.put("abhivyakti",createList(R.string.desc_abhivyakti,R.string.task_abhivyakti,R.string.venue_abhivyakti,R.string.spcf_abhivyakti,R.string.judg_abhivyakti,R.string.coor_abhivyakti));
        myHashMap.put("third vision",createList(R.string.desc_third_vision,R.string.task_third_vision,R.string.venue_third_vision,R.string.spcf_third_vision,R.string.judg_third_vision,R.string.coor_third_vision));
        myHashMap.put("mist treasure hunt",createList(R.string.desc_mist,R.string.task_mist,R.string.venue_mist,R.string.spcf_mist,R.string.judg_mist,R.string.coor_mist));
        myHashMap.put("q-cognito",createList(R.string.desc_qcognito,R.string.task_qcognito,R.string.venue_qcognito,R.string.spcf_qcognito,R.string.judg_qcognito,R.string.coor_qcognito));
        myHashMap.put("freedoscrawl",createList(R.string.desc_freedscrawl,R.string.task_freedscrawl,R.string.venue_freedscrawl,R.string.spcf_freedscrawl,R.string.judg_freedscrawl,R.string.coor_freedscrawl));
        myHashMap.put("kalakriti",createList(R.string.desc_bursh_n_paint,R.string.task_bursh_n_paint,R.string.venue_bursh_n_paint,R.string.spcf_bursh_n_paint,R.string.judg_bursh_n_paint,R.string.coor_bursh_n_paint));
        myHashMap.put("enthuse",createList(R.string.desc_enthuse,R.string.task_enthuse,R.string.venue_enthuse,R.string.spcf_enthuse,R.string.judg_enthuse,R.string.coor_enthuse));
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
        myHashMap.put("fancy footwork",createList(R.string.desc_anukriti,R.string.task_anukriti,R.string.venue_anukriti,R.string.spcf_anukriti,R.string.judg_anukriti,R.string.coor_anukriti));
        myHashMap.put("sargam",createList(R.string.desc_sargam,R.string.task_sargam,R.string.venue_sargam,R.string.spcf_sargam,R.string.judg_sargam,R.string.coor_sargam));
        myHashMap.put("craft-villa",createList(R.string.desc_craftvilla,R.string.task_craftvilla,R.string.venue_craftvilla,R.string.spcf_craftvilla,R.string.judg_craftvilla,R.string.coor_craftvilla));
        myHashMap.put("kritika",createList(R.string.desc_kritika,R.string.task_kritika,R.string.venue_kritika,R.string.spcf_kritika,R.string.judg_kritika,R.string.coor_kritika));
        myHashMap.put("lol",createList(R.string.desc_lol,R.string.task_lol,R.string.venue_lol,R.string.spcf_lol,R.string.judg_lol,R.string.coor_lol));
        myHashMap.put("samagam",createList(R.string.desc_samagam,R.string.task_samagam,R.string.venue_samagam,R.string.spcf_samagam,R.string.judg_samagam,R.string.coor_samagam));
        myHashMap.put("celebrity visit",createList(R.string.desc_celebrity_visit,R.string.task_celebrity_visit,R.string.venue_celebrity_visit,R.string.spcf_celebrity_visit,R.string.judg_celebrity_visit,R.string.coor_celebrity_visit));
        myHashMap.put("startup fair",createList(R.string.desc_startup_fair,R.string.task_startup_fair,R.string.venue_startup_fair,R.string.spcf_startup_fair,R.string.judg_startup_fair,R.string.coor_startup_fair));
    }

    private ArrayList<String> createList(int desc,int task,int timeVenue,int spcf,int judg,int coor) {
        ArrayList<String> mList=new ArrayList<>();
        mList.add(mContext.getString(desc));
        mList.add(mContext.getString(task));
        mList.add(mContext.getString(timeVenue));
        mList.add(mContext.getString(spcf));
        mList.add(mContext.getString(judg));
        mList.add(mContext.getString(coor));
        return  mList;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public CardView cardView;
        public MyViewHodler(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.event_image);
            textView=(TextView)itemView.findViewById(R.id.event_name);
            cardView=(CardView)itemView.findViewById(R.id.event_card);
            cardView.setMinimumHeight(200);
            cardView.setMinimumWidth(itemView.getWidth()/2);
        }
    }
}
