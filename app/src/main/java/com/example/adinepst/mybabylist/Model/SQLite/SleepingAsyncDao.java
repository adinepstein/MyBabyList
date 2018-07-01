package com.example.adinepst.mybabylist.Model.SQLite;

import android.os.AsyncTask;

import com.example.adinepst.mybabylist.Utils.PostData;
import com.example.adinepst.mybabylist.Utils.SleepingData;

import java.util.List;

public class SleepingAsyncDao {

    public interface SleepingAsyncDaoListener<T>{
        void onComplete(T data);
    }

    static public void getAll(final SleepingAsyncDaoListener<List<SleepingData>> listener){
        class MyAsyncTask extends AsyncTask<String,String,List<SleepingData>>{

            @Override
            protected List<SleepingData> doInBackground(String... strings) {
//                LocalDB.db.forumDao().nukeTable();
                List<SleepingData> list= LocalDB.db.sleepingDao().geAllData();
                return list;
            }

            @Override
            protected void onPostExecute(List<SleepingData> sleepingData) {
                super.onPostExecute(sleepingData);
                listener.onComplete(sleepingData);
            }
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute();
    }
    static public void insertAll(final List<SleepingData> list,final SleepingAsyncDaoListener<Boolean> listener){

        class MyAsyncTask extends AsyncTask<SleepingData,Boolean,Boolean>{

            @Override
            protected Boolean doInBackground(SleepingData... sleepingData) {
                LocalDB.db.sleepingDao().insertAll(sleepingData);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                listener.onComplete(success);
            }
        }
        int size= list.size();
        SleepingData[] sdList= new SleepingData[size];
        for(int i=0;i<size;i++){
            sdList[i]=list.get(i);
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute(sdList);
    }
}
