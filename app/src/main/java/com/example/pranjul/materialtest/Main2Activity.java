package com.example.pranjul.materialtest;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends Activity {

    private RecyclerView recyclerView;
    private EventCategoryAdapter eventsAdapter;
    private List<EventCategory> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        eventList = new ArrayList<>();
        eventsAdapter = new EventCategoryAdapter(this, eventList);
        recyclerView.setAdapter(eventsAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        prepareEventCategories();
    }


    private void prepareEventCategories() {
        int covers[] = new int[]{R.drawable.teventgray, R.drawable.nteventgray,
                R.drawable.ceventgray, R.drawable.seventgray, R.drawable.workshopgray};
        EventCategory event = new EventCategory("Technical Events", covers[0]);
        eventList.add(event);
        event = new EventCategory("Non Technical Events", covers[1]);
        eventList.add(event);
        event = new EventCategory("Cultural Events", covers[2]);
        eventList.add(event);
        event = new EventCategory("Sports events", covers[3]);
        eventList.add(event);
        event = new EventCategory("WorkShops", covers[4]);
        eventList.add(event);

        eventsAdapter.notifyDataSetChanged();


    }
}
