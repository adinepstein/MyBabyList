package com.example.adinepst.mybabylist.Utils;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class SleepingData extends ActivityData {
    private String endDate;
    private String endTime;
    private static String ACTIVITYTYPE= "add_sleeping";

    public SleepingData(){}

    public SleepingData(String date,String time, String endDate,String endTime, String comment) {
        super(date,time,comment,ACTIVITYTYPE);
        this.endDate=endDate;
        this.endTime=endTime;
    }
    public String getEndDate() {
        return endDate;
    }

    public String getEndTime() {
        return endTime;
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

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public static void setACTIVITYTYPE(String ACTIVITYTYPE) {
        SleepingData.ACTIVITYTYPE = ACTIVITYTYPE;
    }
}
