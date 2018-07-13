package com.example.adinepst.mybabylist.Model.SQLite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.adinepst.mybabylist.Activities.History.HistoryData;
import com.example.adinepst.mybabylist.Utils.FeedingData;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("select * from HistoryData")
    List<HistoryData> geAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(HistoryData... hd);

    @Delete
    void delete(HistoryData hd);

    @Query("DELETE FROM HistoryData")
    public void nukeTable();

}
