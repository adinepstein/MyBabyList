package com.example.adinepst.mybabylist.Model.SQLite;

import android.os.AsyncTask;

import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.SleepingData;

import java.util.List;

public class FeedingAsyncDao {

    public interface FeedingAsyncDaoListener<T>{
        void onComplete(T data);
    }

    static public void getAll(final FeedingAsyncDaoListener<List<FeedingData>> listener){
        class MyAsyncTask extends AsyncTask<String,String,List<FeedingData>>{

            @Override
            protected List<FeedingData> doInBackground(String... strings) {
//              LocalDB.db.feedingDao().nukeTable();
                List<FeedingData> list= LocalDB.db.feedingDao().geAllData();
                return list;
            }

            @Override
            protected void onPostExecute(List<FeedingData> sleepingData) {
                super.onPostExecute(sleepingData);
                listener.onComplete(sleepingData);
            }
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute();
    }
    static public void insertAll(final List<FeedingData> list,final FeedingAsyncDaoListener<Boolean> listener){

        class MyAsyncTask extends AsyncTask<FeedingData,Boolean,Boolean>{

            @Override
            protected Boolean doInBackground(FeedingData... feedingData) {
                LocalDB.db.feedingDao().insertAll(feedingData);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                listener.onComplete(success);
            }
        }
        int size= list.size();
        FeedingData[] fdList= new FeedingData[size];
        for(int i=0;i<size;i++){
            fdList[i]=list.get(i);
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute(fdList);
    }

    static public void insertData(final FeedingData fd,final FeedingAsyncDaoListener<Boolean> listener){

        class MyAsyncTask extends AsyncTask<FeedingData,Boolean,Boolean>{

            @Override
            protected Boolean doInBackground(FeedingData... feedingData) {
                LocalDB.db.feedingDao().insertAll(feedingData);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                listener.onComplete(success);
            }
        }

        MyAsyncTask task= new MyAsyncTask();
        task.execute(fd);
    }
}
