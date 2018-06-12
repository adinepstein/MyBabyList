package com.example.adinepst.mybabylist.Model.SQLite;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.adinepst.mybabylist.MyApplication;

import Utils.PostData;

@Database(entities = {PostData.class},version =1)
abstract class LocalDBRepository extends RoomDatabase{
    public abstract ForumDao forumDao();
}

public class LocalDB {
    static public LocalDBRepository db= Room.databaseBuilder(MyApplication.context,LocalDBRepository.class,"dbFileName.db").fallbackToDestructiveMigration().build();
}
