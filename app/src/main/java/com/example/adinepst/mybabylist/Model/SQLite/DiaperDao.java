package com.example.adinepst.mybabylist.Model.SQLite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.adinepst.mybabylist.Utils.DiaperChangingData;
import com.example.adinepst.mybabylist.Utils.FeedingData;

import java.util.List;

@Dao
public interface DiaperDao {
    @Query("select * from DiaperChangingData")
    List<DiaperChangingData> geAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(DiaperChangingData... fd);

    @Delete
    void delete(DiaperChangingData fd);

    @Query("DELETE FROM DiaperChangingData")
    public void nukeTable();

}
