package com.example.pranjul.materialtest;

/**
 * Created by Pranjul on 28-01-2017.
 */

public class EventCategory {
    private String eventName;
    private int eventImage;

    public EventCategory(){}
    public EventCategory(String  eventName, int eventImage){
        this.eventName=eventName;
        this.eventImage=eventImage;
    }

    public String getEventName(){
        return eventName;
    }

    public void setEventName(String eventName){
        this.eventName=eventName;
    }

    public int getEventImage(){
        return eventImage;
    }

    public void setEventImage(int eventImage){
        this.eventImage=eventImage;
    }
}
