package com.example.adinepst.mybabylist.Model.SQLite;

import android.os.AsyncTask;

import com.example.adinepst.mybabylist.Utils.DiaperChangingData;


import java.util.List;

public class DiaperAsyncDao {

    public interface DiaperAsyncDaoListener<T>{
        void onComplete(T data);
    }

    static public void getAll(final DiaperAsyncDaoListener<List<DiaperChangingData>> listener){
        class MyAsyncTask extends AsyncTask<String,String,List<DiaperChangingData>>{

            @Override
            protected List<DiaperChangingData> doInBackground(String... strings) {
//              LocalDB.db.diaperDao().nukeTable();
                List<DiaperChangingData> list= LocalDB.db.diaperDao().geAllData();
                return list;
            }

            @Override
            protected void onPostExecute(List<DiaperChangingData> diaperData) {
                super.onPostExecute(diaperData);
                listener.onComplete(diaperData);
            }
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute();
    }
    static public void insertAll(final List<DiaperChangingData> list,final DiaperAsyncDaoListener<Boolean> listener){

        class MyAsyncTask extends AsyncTask<DiaperChangingData,Boolean,Boolean>{

            @Override
            protected Boolean doInBackground(DiaperChangingData... diaperData) {
                LocalDB.db.diaperDao().insertAll(diaperData);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                listener.onComplete(success);
            }
        }
        int size= list.size();
        DiaperChangingData[] ddList= new DiaperChangingData[size];
        for(int i=0;i<size;i++){
            ddList[i]=list.get(i);
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute(ddList);
    }

    static public void insertData(final DiaperChangingData dd,final DiaperAsyncDaoListener<Boolean> listener){

        class MyAsyncTask extends AsyncTask<DiaperChangingData,Boolean,Boolean>{

            @Override
            protected Boolean doInBackground(DiaperChangingData... diaperData) {
                LocalDB.db.diaperDao().insertAll(diaperData);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                listener.onComplete(success);
            }
        }

        MyAsyncTask task= new MyAsyncTask();
        task.execute(dd);
    }
}
