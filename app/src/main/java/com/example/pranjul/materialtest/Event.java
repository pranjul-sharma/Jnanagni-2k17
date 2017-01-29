package com.example.pranjul.materialtest;

/**
 * Created by Pranjul on 28-01-2017.
 */

public class Event {

    private int eventCover;
    private String eventName;
    private int eventDescription;
    private int eventTask;
    private int eventPrerequisites;
    private int eventTime;
    private int eventVenue;
    private Coordinator[] eventCoordinator;
    public Event(){}

    public Event(String eventName,int eventCover){
        this.eventName=eventName;
        this.eventCover=eventCover;
    }
    public Event(String eventName,int eventDescription,int eventTask,int eventPrerequisites,
                 int eventTime,int eventVenue,Coordinator[] coordinators){
        this.eventName=eventName;
        this.eventDescription=eventDescription;
        this.eventTask=eventTask;
        this.eventPrerequisites=eventPrerequisites;
        this.eventTime=eventTime;
        this.eventVenue=eventVenue;
        eventCoordinator=coordinators;
    }

    public String getEventName(){
        return eventName;
    }

    public int getEventCover(){
        return eventCover;
    }


}
