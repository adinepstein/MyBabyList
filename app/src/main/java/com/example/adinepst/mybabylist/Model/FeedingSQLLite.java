package com.example.adinepst.mybabylist.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.adinepst.mybabylist.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class FeedingSQLLite {
    final  static int VERSION =1;
    MyHelper helper=new MyHelper(MyApplication.context);

    public List<FeedingData> getAllFeedingData(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor= db.query("FeedingData",null,null, null, null, null, null);

        List<FeedingData> list= new ArrayList<>();
        if(cursor.moveToFirst()){
        do{
            String dateTime= cursor.getString(cursor.getColumnIndex("dateTime"));
            long amount= cursor.getLong(cursor.getColumnIndex("amount"));
            String comment = cursor.getString(cursor.getColumnIndex("comment"));
            FeedingData fd= new FeedingData(dateTime,amount,comment);
            list.add(fd);
        }while (cursor.moveToNext());
      }

      return list;
    }
    public void addFeedingData(FeedingData fd){
        if(fd!=null) {
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("dataTime", fd.getDateTime());
            values.put("amount", fd.getAmount());
            values.put("comment", fd.getComment());
            long rowId= db.insert("FeedingData","dateTime", values);
        }

    }

    class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context) {
            super(context, "localDB.db", null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL("create table FeedingData (dateTime text primary key,amount real, comment text)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                sqLiteDatabase.execSQL("drop table FeedingData;");
                onCreate(sqLiteDatabase);
        }
    }
}
