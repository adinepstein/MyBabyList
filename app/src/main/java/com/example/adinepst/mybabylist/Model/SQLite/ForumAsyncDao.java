package com.example.adinepst.mybabylist.Model.SQLite;

import android.os.AsyncTask;

import java.util.List;

import com.example.adinepst.mybabylist.Utils.PostData;

public class ForumAsyncDao {

    public interface ForumAsyncDaoListener<T>{
        void onComplete (T data);
    }

    static public void getAll(final ForumAsyncDaoListener<List<PostData>> listener){
        class MyAsyncTask extends AsyncTask<String,String,List<PostData>>{

            @Override
            protected List<PostData> doInBackground(String... strings) {
//                LocalDB.db.forumDao().nukeTable();
                List<PostData> list= LocalDB.db.forumDao().getAllPosts();
                return list;
            }

            @Override
            protected void onPostExecute(List<PostData> postData) {
                super.onPostExecute(postData);
                listener.onComplete(postData);
            }
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute();
    }
    static public void insertAll(final List<PostData> list,final ForumAsyncDaoListener<Boolean> listener){

        class MyAsyncTask extends AsyncTask<PostData,Boolean,Boolean>{

            @Override
            protected Boolean doInBackground(PostData... postData) {
                LocalDB.db.forumDao().insertAll(postData);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                listener.onComplete(success);
            }
        }
        int size= list.size();
        PostData[] pdList= new PostData[size];
        for(int i=0;i<size;i++){
            pdList[i]=list.get(i);
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute(pdList);
    }
}
