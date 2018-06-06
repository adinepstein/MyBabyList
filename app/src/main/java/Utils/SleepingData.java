package Utils;

public class SleepingData extends ActivityData {
    private String endDate;
    private String endTime;
    private static String ACTIVITYTYPE= "sleeping";

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
}
