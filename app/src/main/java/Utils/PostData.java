package Utils;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class PostData {
    @PrimaryKey
    @NonNull
    private String postId;
    private String comment;
    private String parentName;
    private String date;
    private String uploadImgUrl;
    private String babyImageUrl;

    public PostData(){

    }

    public PostData(String comment, String parentName, String date, String uploadImgUrl) {
        this.comment = comment;
        this.parentName = parentName;
        this.date = date;
        this.uploadImgUrl = uploadImgUrl;
        this.postId= parentName + "_" + date;

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


    public void setPostId(@NonNull String postId) {
        this.postId = postId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUploadImgUrl(String uploadImgUrl) {
        this.uploadImgUrl = uploadImgUrl;
    }


}
