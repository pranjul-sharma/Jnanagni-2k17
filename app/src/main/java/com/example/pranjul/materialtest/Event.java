package com.example.pranjul.materialtest;

/**
 * Created by Pranjul on 28-01-2017.
 */

public class Event {

    private int eventCover;
    private String eventName;
    private String eventDescription;
    private String eventTask;
    private String eventPrerequisites;
    private String eventTime;
    private String eventJudgementalCriteria;
    private Coordinator[] eventCoordinator;
    public Event(){}

    public Event(String eventName,int eventCover){
        this.eventName=eventName;
        this.eventCover=eventCover;
    }
    public Event(int eventCover,String eventDescription,String eventTask,String eventPrerequisites,
                 String eventTime,String eventJudgementalCriteria,Coordinator[] coordinators){
        this.eventCover=eventCover;
        this.eventDescription=eventDescription;
        this.eventTask=eventTask;
        this.eventPrerequisites=eventPrerequisites;
        this.eventTime=eventTime;
        this.eventJudgementalCriteria=eventJudgementalCriteria;
        eventCoordinator=coordinators;
    }

    public String getEventName(){
        return eventName;
    }

    public int getEventCover(){
        return eventCover;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventTask() {
        return eventTask;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventPrerequisites() {
        return eventPrerequisites;
    }

    public String getEventJudgementalCriteria() {
        return eventJudgementalCriteria;
    }

    public String getEventCoordinator() {
        return eventCoordinator.toString();
    }
}

