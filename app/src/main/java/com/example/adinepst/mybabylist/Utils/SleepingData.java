package com.example.adinepst.mybabylist.Utils;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class SleepingData  {

    @PrimaryKey
    @NonNull
    private String dateAndTime;

    private String date;
    private String time;
    private String comment;
    private String endDate;
    private String endTime;
    private String totalTime;

    public SleepingData(){}

    @Ignore
    public SleepingData(String date,String time, String endDate,String endTime, String comment) {
        this.date=date;
        this.time=time;
        this.comment=comment;
        this.endDate=endDate;
        this.endTime=endTime;
        this.dateAndTime=date+ "-" +time;
        calculateTimedifference();
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

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private void calculateTimedifference(){
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy HH:mm");
        Date d1=null;
        Date d2=null;
        try {
            d1=format.parse(date + " " + time);
            d2= format.parse(endDate + " " + endTime);
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;

            totalTime=""+diffHours+":"+ diffMinutes;


        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    }
