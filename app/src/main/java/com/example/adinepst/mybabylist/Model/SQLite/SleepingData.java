package com.example.adinepst.mybabylist.Model.SQLite;

public class SleepingData {
    private String startDateTime;
    private String endDateTime;
    private String comment;

    public SleepingData(String startDateTime, String endDateTime, String comment) {
        this.startDateTime = startDateTime;
        this.endDateTime=endDateTime;
        this.comment = comment;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
