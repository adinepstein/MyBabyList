package com.example.adinepst.mybabylist.Model;

import com.example.adinepst.mybabylist.Model.SQLite.DiaperChangingData;
import com.example.adinepst.mybabylist.Model.SQLite.DiaperChangingSQLite;
import com.example.adinepst.mybabylist.Model.SQLite.FeedingData;
import com.example.adinepst.mybabylist.Model.SQLite.FeedingSQLite;
import com.example.adinepst.mybabylist.Model.SQLite.ModelSQLite;
import com.example.adinepst.mybabylist.Model.SQLite.SleepingData;
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
        FeedingSQLite.addFeedingData(fd,modelSQLLite.getWritableDatabase());
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
