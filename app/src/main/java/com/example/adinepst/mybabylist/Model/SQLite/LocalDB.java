package com.example.adinepst.mybabylist.Model.SQLite;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.adinepst.mybabylist.MyApplication;

import com.example.adinepst.mybabylist.Utils.PostData;
import com.example.adinepst.mybabylist.Utils.UserData;

@Database(entities = {PostData.class, UserData.class},version =2)
abstract class LocalDBRepository extends RoomDatabase{
    public abstract ForumDao forumDao();
    public abstract UserDao userDao();
}

public class LocalDB {
    static public LocalDBRepository db= Room.databaseBuilder(MyApplication.context,LocalDBRepository.class,"dbFileName.db").fallbackToDestructiveMigration().build();
}
