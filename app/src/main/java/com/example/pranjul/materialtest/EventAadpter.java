package com.example.pranjul.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Pranjul on 28-01-2017.
 */

public class EventAadpter extends RecyclerView.Adapter<EventAadpter.MyViewHodler> {
    private Context mContext;

    private List<Event> eventList;
    public EventAadpter(Context context,List<Event> eventList) {
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
        Event event=eventList.get(position);
        holder.textView.setText(event.getEventName());

        Glide.with(mContext).load(event.getEventCover()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,EventInfoActivity.class);
                mContext.startActivity(intent);
            }
        });

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
