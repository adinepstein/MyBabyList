package com.example.adinepst.mybabylist.Model;

import android.arch.lifecycle.LiveData;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.URLUtil;

import Utils.ActivityData;
import Utils.DiaperChangingData;

import com.example.adinepst.mybabylist.Model.Firebase.ModelFirebaseFeeding;
import com.example.adinepst.mybabylist.Model.Firebase.ModelFirebaseForum;
import com.example.adinepst.mybabylist.Model.Firebase.ModelFirebaseUsers;
import com.example.adinepst.mybabylist.Model.SQLite.DiaperChangingSQLite;
import Utils.FeedingData;
import com.example.adinepst.mybabylist.Model.SQLite.FeedingSQLite;
import com.example.adinepst.mybabylist.Model.SQLite.ModelSQLite;

import Utils.PostData;
import Utils.SleepingData;
import Utils.UserData;

import com.example.adinepst.mybabylist.Model.SQLite.SleepingSQLite;

import java.util.List;

public class Model {

    public static Model instance = new Model();
    private ModelSQLite modelSQLLite;
    private ModelFirebaseUsers modelFirebaseUsers;
    private ModelImageHandler modelImageHandler;
    private ForumListLiveData forumListLiveData;
    private ModelFirebaseForum modelFirebaseForum;


    private Model(){
        modelFirebaseUsers= new ModelFirebaseUsers();
        modelSQLLite= new ModelSQLite();
        modelImageHandler = new ModelImageHandler();
        forumListLiveData= new ForumListLiveData();
        modelFirebaseForum = new ModelFirebaseForum();
    }

    public List<FeedingData> getAllFeedingData(){
        return FeedingSQLite.getAllFeedingData(modelSQLLite.getReadableDatabase());
    }

    public void addActivityData(ActivityData ad, UserData ud){
        Log.d("Tag", "model- add feeding data");
//        FeedingSQLite.addActivityData(fd,modelSQLLite.getWritableDatabase());
        ModelFirebaseFeeding.addActivityData(ad,ud);
    }
    public void addUser(UserData ud){
        modelFirebaseUsers.addUser(ud);
    }

    public void addPost(PostData pd){
        modelFirebaseForum.addPostData(pd);
    }

    public List<SleepingData> getAllSleepingData(){
        return SleepingSQLite.getAllSleepingData(modelSQLLite.getReadableDatabase());
    }

    public void addSleepingData(SleepingData sd){
        SleepingSQLite.addSleepingData(sd,modelSQLLite.getWritableDatabase());
    }

    public List<DiaperChangingData> getAllDiaperChangingData(){
        return DiaperChangingSQLite.getAllDiaperChangingData(modelSQLLite.getReadableDatabase());
    }

    public void addDiaperChangingData(DiaperChangingData dcd){
        DiaperChangingSQLite.addDiaperChangingData(dcd,modelSQLLite.getWritableDatabase());
    }

    public LiveData<List<PostData>> getAllForumData() {
        return forumListLiveData;
    }

    public interface SaveImageListener{
        void onDone(String url);
    }


    public void saveImage(Bitmap imageBitmap, SaveImageListener listener) {
        modelImageHandler.saveImage(imageBitmap,listener);
    }

    public interface GetImageListener{
        void onDone(Bitmap imageBitmap);
    }

    public void getImage(final String url, final GetImageListener listener ){
        modelImageHandler.getImage(url,listener);
    }


}
