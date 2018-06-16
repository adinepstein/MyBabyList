package com.example.adinepst.mybabylist.Model.SQLite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.adinepst.mybabylist.Utils.PostData;
import com.example.adinepst.mybabylist.Utils.UserData;

import java.util.List;

@Dao
public interface UserDao {
    @Query("select * from UserData")
    UserData getUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserData userData);

    @Delete
    void delete(UserData userData);

    @Query("DELETE FROM UserData")
    public void nukeTable();

}
