package com.example.adinepst.mybabylist.Utils;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class FeedingData  {
    @PrimaryKey
    @NonNull
    private String dateAndTime;
    private String date;
    private String time;
    private String comment;
    private double amount;

    public FeedingData(){}
    @Ignore
    public FeedingData(String date,String time, double amount, String comment) {
        this.date=date;
        this.time=time;
        this.comment=comment;
        this.dateAndTime=date+ "-" +time;
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
