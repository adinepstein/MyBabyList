package Utils;

public class SleepingData extends ActivityData {
    private String endDateTime;
    private static String ACTIVITYTYPE= "sleeping";

    public SleepingData(String dateTime, String endDateTime, String comment) {
        super(dateTime,comment,ACTIVITYTYPE);
        this.endDateTime=endDateTime;
    }
    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

}
