package Utils;

public class PostData {
    private String comment;
    private String parentType;
    private String date;
    private String url;

    public PostData(String comment, String parentType, String date, String url) {
        this.comment = comment;
        this.parentType = parentType;
        this.date = date;
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public String getParentType() {
        return parentType;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
