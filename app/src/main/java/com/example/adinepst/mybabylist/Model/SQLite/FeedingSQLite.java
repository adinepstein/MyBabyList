package com.example.adinepst.mybabylist.Model.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Utils.FeedingData;

public class FeedingSQLite {
    final static String TABLE= "FeedingData";

    static public void createTable(SQLiteDatabase db){
        db.execSQL("create table " + TABLE + "(dateTime text primary key, amount long , comment text)");
            }

     static public void dropTable(SQLiteDatabase db){
        db.execSQL("drop table " + TABLE);
     }


    static public List<FeedingData> getAllFeedingData(SQLiteDatabase db){
        Cursor cursor= db.query(TABLE,null,null, null, null, null, null);

        List<FeedingData> list= new ArrayList<>();
        if(cursor.moveToFirst()){
        do{
//            String dateTime= cursor.getString(cursor.getColumnIndex("dateTime"));
//            long amount= cursor.getLong(cursor.getColumnIndex("amount"));
//            String comment = cursor.getString(cursor.getColumnIndex("comment"));
//            FeedingData fd= new FeedingData(dateTime,amount,comment);
//            list.add(fd);
        }while (cursor.moveToNext());
      }

      return list;
    }
    static public void addFeedingData(FeedingData fd,SQLiteDatabase db){
        if(fd!=null) {
            ContentValues values = new ContentValues();
            values.put("dateTime", fd.getDate());
            values.put("amount", fd.getAmount());
            values.put("comment", fd.getComment());
            long rowId= db.insert(TABLE,"dateTime", values);
        }

    }

}
