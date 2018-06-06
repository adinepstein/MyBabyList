package com.example.adinepst.mybabylist.Model;

import android.util.Log;

import Utils.ActivityData;
import Utils.DiaperChangingData;

import com.example.adinepst.mybabylist.Model.Firebase.ModelFirebaseFeeding;
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
    private Model(){
        modelSQLLite= new ModelSQLite();
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

    }

    public void addPost(PostData pd){

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


}
