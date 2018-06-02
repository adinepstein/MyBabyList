package com.example.adinepst.mybabylist.Model;

import android.util.Log;

import Utils.DiaperChangingData;

import com.example.adinepst.mybabylist.Model.Firebase.ModelFirebaseFeeding;
import com.example.adinepst.mybabylist.Model.SQLite.DiaperChangingSQLite;
import Utils.FeedingData;
import com.example.adinepst.mybabylist.Model.SQLite.FeedingSQLite;
import com.example.adinepst.mybabylist.Model.SQLite.ModelSQLite;
import Utils.SleepingData;
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

    public void addFeedingData(FeedingData fd){
        Log.d("Tag", "model- add feeding data");
//        FeedingSQLite.addFeedingData(fd,modelSQLLite.getWritableDatabase());
//        ModelFirebaseFeeding.addFeedingData(fd);
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
