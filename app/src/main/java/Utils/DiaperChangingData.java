package Utils;

public class DiaperChangingData extends ActivityData {
    private String type;
    private static String ACTIVITYTYPE= "diaper";

    public DiaperChangingData(String dateTime, String type, String comment) {
        super(dateTime,comment,ACTIVITYTYPE);
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }





}
