package com.example.adinepst.mybabylist.Activities.History;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class HistoryData {
    @PrimaryKey
    @NonNull
    String day;
    String numOfFeadings;
    String avgAmount;
    String sleepingHours;
    String numOfPoops;
    String numofUrines;

    public HistoryData() {
    }

    public HistoryData(String day, String numOfFeadings, String avgAmount, String sleepingHours, String numOfPoops, String numofUrines) {
        this.day = day;
        this.numOfFeadings = numOfFeadings;
        this.avgAmount = avgAmount;
        this.sleepingHours = sleepingHours;
        this.numOfPoops = numOfPoops;
        this.numofUrines = numofUrines;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNumOfFeadings() {
        return numOfFeadings;
    }

    public void setNumOfFeadings(String numOfFeadings) {
        this.numOfFeadings = numOfFeadings;
    }

    public String getAvgAmount() {
        return avgAmount;
    }

    public void setAvgAmount(String avgAmount) {
        this.avgAmount = avgAmount;
    }

    public String getSleepingHours() {
        return sleepingHours;
    }

    public void setSleepingHours(String sleepingHours) {
        this.sleepingHours = sleepingHours;
    }

    public String getNumOfPoops() {
        return numOfPoops;
    }

    public void setNumOfPoops(String numOfPoops) {
        this.numOfPoops = numOfPoops;
    }

    public String getNumofUrines() {
        return numofUrines;
    }

    public void setNumofUrines(String numofUrines) {
        this.numofUrines = numofUrines;
    }
}
