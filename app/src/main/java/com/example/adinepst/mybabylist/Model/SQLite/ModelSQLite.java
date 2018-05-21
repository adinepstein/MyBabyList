package com.example.adinepst.mybabylist.Model.SQLite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.adinepst.mybabylist.MyApplication;

public class ModelSQLite extends SQLiteOpenHelper {
    final static int VERSION= 1;
    public ModelSQLite() {
        super(MyApplication.context, "localDB.db", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        FeedingSQLite.createTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            FeedingSQLite.dropTable(sqLiteDatabase);
            onCreate(sqLiteDatabase);
    }
}
