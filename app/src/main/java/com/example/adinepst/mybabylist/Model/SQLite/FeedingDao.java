package com.example.adinepst.mybabylist.Model.SQLite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.SleepingData;

import java.util.List;

@Dao
public interface FeedingDao {
    @Query("select * from FeedingData")
    List<FeedingData> geAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(FeedingData... fd);

    @Delete
    void delete(FeedingData fd);

    @Query("DELETE FROM FeedingData")
    public void nukeTable();

}
