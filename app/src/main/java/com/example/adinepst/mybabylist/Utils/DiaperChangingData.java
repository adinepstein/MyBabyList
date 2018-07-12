package com.example.adinepst.mybabylist.Utils;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class DiaperChangingData  {
    @PrimaryKey
    @NonNull
    private String dateAndTime;
    private String date;
    private String time;
    private String comment;
    private String type;


    public DiaperChangingData(){}

    @Ignore
    public DiaperChangingData(String date,String time, String type, String comment) {
        this.date=date;
        this.time=time;
        this.comment=comment;
        this.dateAndTime=date+ "-" +time;
        this.type = type;
    }
    public String getType() {
        return type;
    }

    @NonNull
    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(@NonNull String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setComment(String comment) {
        this.comment = comment;
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





}
