package com.example.adinepst.mybabylist.Model.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Utils.DiaperChangingData;

public class DiaperChangingSQLite {
    final static String TABLE= "DiaperChangingData";

    static public void createTable(SQLiteDatabase db){
        db.execSQL("create table " + TABLE + "(dateTime text primary key, type text , comment text)");
            }

     static public void dropTable(SQLiteDatabase db){
        db.execSQL("drop table " + TABLE);
     }


    static public List<DiaperChangingData> getAllDiaperChangingData(SQLiteDatabase db){
        Cursor cursor= db.query(TABLE,null,null, null, null, null, null);

        List<DiaperChangingData> list= new ArrayList<>();
        if(cursor.moveToFirst()){
        do{
//            String dateTime= cursor.getString(cursor.getColumnIndex("dateTime"));
//            String type= cursor.getString(cursor.getColumnIndex("type"));
//            String comment = cursor.getString(cursor.getColumnIndex("comment"));
//            DiaperChangingData dcd= new DiaperChangingData(dateTime,type,comment);
//            list.add(dcd);
        }while (cursor.moveToNext());
      }

      return list;
    }
    static public void addDiaperChangingData(DiaperChangingData dcd,SQLiteDatabase db){
        if(dcd!=null) {
            ContentValues values = new ContentValues();
//            values.put("dateTime", dcd.getDateTime());
//            values.put("type", dcd.getType());
//            values.put("comment", dcd.getComment());
            long rowId= db.insert(TABLE,"dateTime", values);
        }

    }

}
