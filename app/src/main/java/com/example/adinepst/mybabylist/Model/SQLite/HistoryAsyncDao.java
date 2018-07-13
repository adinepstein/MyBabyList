package com.example.adinepst.mybabylist.Model.SQLite;

import android.os.AsyncTask;

import com.example.adinepst.mybabylist.Activities.History.HistoryData;


import java.util.List;

public class HistoryAsyncDao {

    public interface HistoryAsyncDaoListener<T>{
        void onComplete(T data);
    }

    static public void getAll(final HistoryAsyncDaoListener<List<HistoryData>> listener){
        class MyAsyncTask extends AsyncTask<String,String,List<HistoryData>>{

            @Override
            protected List<HistoryData> doInBackground(String... strings) {
//              LocalDB.db.historyDao().nukeTable();
                List<HistoryData> list= LocalDB.db.historyDao().geAllData();
                return list;
            }

            @Override
            protected void onPostExecute(List<HistoryData> historyData) {
                super.onPostExecute(historyData);
                listener.onComplete(historyData);
            }
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute();
    }
    static public void insertAll(final List<HistoryData> list,final HistoryAsyncDaoListener<Boolean> listener){

        class MyAsyncTask extends AsyncTask<HistoryData,Boolean,Boolean>{

            @Override
            protected Boolean doInBackground(HistoryData... historyData) {
                LocalDB.db.historyDao().insertAll(historyData);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                listener.onComplete(success);
            }
        }
        int size= list.size();
        HistoryData[] hdList= new HistoryData[size];
        for(int i=0;i<size;i++){
            hdList[i]=list.get(i);
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute(hdList);
    }

    static public void insertData(final HistoryData hd,final HistoryAsyncDaoListener<Boolean> listener){

        class MyAsyncTask extends AsyncTask<HistoryData,Boolean,Boolean>{

            @Override
            protected Boolean doInBackground(HistoryData... historyData) {
                LocalDB.db.historyDao().insertAll(historyData);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                listener.onComplete(success);
            }
        }

        MyAsyncTask task= new MyAsyncTask();
        task.execute(hd);
    }
}
