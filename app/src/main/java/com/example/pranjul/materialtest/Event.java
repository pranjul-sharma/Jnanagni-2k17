package com.example.pranjul.materialtest;

/**
 * Created by Pranjul on 28-01-2017.
 */

public class Event {

    private int eventCover;
    private String eventName;


    public Event(String eventName,int eventCover){
        this.eventName=eventName;
        this.eventCover=eventCover;
    }

    public String getEventName(){
        return eventName;
    }

    public int getEventCover(){
        return eventCover;
    }

}

