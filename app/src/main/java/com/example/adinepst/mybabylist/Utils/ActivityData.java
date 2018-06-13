package com.example.adinepst.mybabylist.Utils;

public abstract class ActivityData {
    protected String date;
    protected String time;
    protected String comment;
    protected String activityType;

    public ActivityData(){

    }


    public ActivityData(String date,String time, String comment, String activityType){
        this.date=date;
        this.time = time;
        this.comment=comment;
        this.activityType=activityType;
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
