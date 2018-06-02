package Utils;

public class FeedingData extends ActivityData {

    private double amount;
    private static String ACTIVITYTYPE= "feeding";

    public FeedingData(String dateTime, double amount, String comment) {
        super(dateTime,comment,ACTIVITYTYPE);
        this.amount = amount;


    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

}
