package com.example.pranjul.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Pranjul on 28-01-2017.
 */

public class EventCategoryAdapter extends RecyclerView.Adapter<EventCategoryAdapter.MyViewHolder> {
    private static final String LOG_TAG="class eventadapter";
    private Context mContext;
    private List<EventCategory> eventList;

    public EventCategoryAdapter(Context mContext,List<EventCategory> eventList){
        Log.v(LOG_TAG,"constructor callled");
        this.mContext=mContext;
        this.eventList=eventList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v(LOG_TAG,"oncreateviewholder callled");
        View itemView= LayoutInflater.from(mContext).inflate(R.layout.event_card,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final String callCategory[]={"Technical","NonTechnical","Cultural","Sports","Workshops"};
        Log.v(LOG_TAG,"onBindViewHolder callled");
        EventCategory event=eventList.get(position);
        holder.eventDes.setText(event.getEventName());

        Glide.with(mContext).load(event.getEventImage()).into(holder.eventCovor);
        holder.eventCovor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext,"image "+position+" clicked",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,Main3Activity.class);
                intent.putExtra("showCategory",callCategory[position]);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView eventCovor;
        public TextView eventDes;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.v(LOG_TAG,"constructor of inner class myviewholder callled");
            eventCovor=(ImageView)itemView.findViewById(R.id.event_image);
            eventDes=(TextView)itemView.findViewById(R.id.event_name);
        }


    }
}
