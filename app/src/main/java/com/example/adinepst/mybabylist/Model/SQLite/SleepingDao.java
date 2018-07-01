package com.example.adinepst.mybabylist.Model.SQLite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.adinepst.mybabylist.Utils.SleepingData;
import com.example.adinepst.mybabylist.Utils.UserData;

import java.util.List;

@Dao
public interface SleepingDao {
    @Query("select * from SleepingData")
    List<SleepingData> geAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SleepingData... sd);

    @Delete
    void delete(SleepingData sd);

    @Query("DELETE FROM SleepingData")
    public void nukeTable();

}
