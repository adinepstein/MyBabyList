package com.example.adinepst.mybabylist.Utils;

public class FeedingData extends ActivityData {

    private double amount;
    private static String ACTIVITYTYPE= "feeding";

    public FeedingData(){}

    public FeedingData(String date,String time, double amount, String comment) {
        super(date,time,comment,ACTIVITYTYPE);
        this.amount = amount;


    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
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
