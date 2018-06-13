package com.example.adinepst.mybabylist.Model.SQLite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import com.example.adinepst.mybabylist.Utils.PostData;

@Dao
public interface ForumDao {
    @Query("select * from PostData")
    List<PostData> getAllPosts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PostData... postDatas);

    @Delete
    void delete(PostData postData);

    @Query("DELETE FROM PostData")
    public void nukeTable();

}
