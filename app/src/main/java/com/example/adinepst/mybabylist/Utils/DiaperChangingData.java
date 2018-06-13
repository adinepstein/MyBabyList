package com.example.adinepst.mybabylist.Utils;

public class DiaperChangingData extends ActivityData {
    private String type;
    private static String ACTIVITYTYPE= "diaper";

    public DiaperChangingData(){}


    public DiaperChangingData(String date,String time, String type, String comment) {
        super(date, time,comment,ACTIVITYTYPE);
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }

    public String getActivityType(){return activityType;}



}
