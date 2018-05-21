package com.example.adinepst.mybabylist.Model.SQLite;

public class DiaperChangingData {
    private String dateTime;
    private String type;
    private String comment;

    public DiaperChangingData(String dateTime, String type, String comment) {
        this.dateTime = dateTime;
        this.type = type;
        this.comment = comment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }
}
