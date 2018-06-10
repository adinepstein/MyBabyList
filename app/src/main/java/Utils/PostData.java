package Utils;

public class PostData {
    private String comment;
    private String parentName;
    private String date;
    private String uploadImgUrl;
    private String postId;
    private String babyImageUrl;

    public PostData(String comment, String parentName, String date, String uploadImgUrl, String babyImageUrl) {
        this.comment = comment;
        this.parentName = parentName;
        this.date = date;
        this.uploadImgUrl = uploadImgUrl;
        this.postId= parentName + "_" + date;
        this.babyImageUrl= babyImageUrl;
    }

    public String getComment() {
        return comment;
    }

    public String getParentName() {
        return parentName;
    }

    public String getDate() {
        return date;
    }

    public String getUploadImgUrl() {
        return uploadImgUrl;
    }

    public String getPostId() {
        return postId;
    }

    public String getBabyImageUrl() {
        return babyImageUrl;
    }
}
