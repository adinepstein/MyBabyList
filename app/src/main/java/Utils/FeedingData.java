package Utils;

public class FeedingData {
    private String dateTime;
    private long amount;
    private String comment;

    public FeedingData(String dateTime, long amount, String comment) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.comment = comment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public long getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
