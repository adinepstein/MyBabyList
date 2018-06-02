package Utils;

public abstract class ActivityData {
    private String dateTime;
    private String comment;
    private String activityType;


    public ActivityData(String dateTime, String comment, String activityType){
        this.dateTime=dateTime;
        this.comment=comment;
        this.activityType=activityType;
    }

    public String getDateTime() {
        return dateTime;
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

    public String getActivityType(){return activityType;}

}
