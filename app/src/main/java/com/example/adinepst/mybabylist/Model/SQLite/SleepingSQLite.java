package com.example.adinepst.mybabylist.Model.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Utils.SleepingData;

public class SleepingSQLite {
    final static String TABLE= "SleepingData";

    static public void createTable(SQLiteDatabase db){
        db.execSQL("create table " + TABLE + "(startDateTime text primary key, endDateTime text , comment text)");
            }

     static public void dropTable(SQLiteDatabase db){
        db.execSQL("drop table " + TABLE);
     }


    static public List<SleepingData> getAllSleepingData(SQLiteDatabase db){
        Cursor cursor= db.query(TABLE,null,null, null, null, null, null);

        List<SleepingData> list= new ArrayList<>();
        if(cursor.moveToFirst()){
        do{
            String startDateTime= cursor.getString(cursor.getColumnIndex("startDateTime"));
            String endDateTime= cursor.getString(cursor.getColumnIndex("endDateTime"));
            String comment = cursor.getString(cursor.getColumnIndex("comment"));
            SleepingData sd= new SleepingData(startDateTime,endDateTime,comment);
            list.add(sd);
        }while (cursor.moveToNext());
      }

      return list;
    }
    static public void addSleepingData(SleepingData sd,SQLiteDatabase db){
        if(sd!=null) {
            ContentValues values = new ContentValues();
            values.put("startDateTime", sd.getStartDateTime());
            values.put("endDateTime", sd.getEndDateTime());
            values.put("comment", sd.getComment());
            long rowId= db.insert(TABLE,"startDateTime", values);
        }

    }

}
