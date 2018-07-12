package com.example.adinepst.mybabylist.Model.SQLite;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.adinepst.mybabylist.Model.SQLite.ForumDao;
import com.example.adinepst.mybabylist.MyApplication;

import com.example.adinepst.mybabylist.Utils.DiaperChangingData;
import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.PostData;
import com.example.adinepst.mybabylist.Utils.SleepingData;
import com.example.adinepst.mybabylist.Utils.UserData;

@Database(entities = {PostData.class, UserData.class, SleepingData.class, FeedingData.class, DiaperChangingData.class},version =5,exportSchema = false)
abstract class LocalDBRepository extends RoomDatabase{
    public abstract ForumDao forumDao();
    public abstract UserDao userDao();
    public abstract SleepingDao sleepingDao();
    public abstract FeedingDao feedingDao();
    public abstract DiaperDao diaperDao();
}

public class LocalDB {
    static public LocalDBRepository db= Room.databaseBuilder(MyApplication.context,LocalDBRepository.class,"dbFileName.db").fallbackToDestructiveMigration().build();
}
