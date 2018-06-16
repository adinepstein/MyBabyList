package com.example.adinepst.mybabylist.Model.SQLite;

import android.os.AsyncTask;

import com.example.adinepst.mybabylist.Model.Model;
import com.example.adinepst.mybabylist.Utils.PostData;
import com.example.adinepst.mybabylist.Utils.UserData;

import java.util.List;

public class UserAsyncDao {

    public interface UserAsyncDaoListener<T>{
        void onComplete(T data);
    }

    static public void getUser(final Model.GetUserListener listener){
        class MyAsyncTask extends AsyncTask<String,String,UserData>{

            @Override
            protected UserData doInBackground(String... strings) {
//                LocalDB.db.userDao().nukeTable();
                UserData ud= LocalDB.db.userDao().getUser();
                return ud;
            }

            @Override
            protected void onPostExecute(UserData ud) {
                super.onPostExecute(ud);
                listener.onSuccess(ud);
            }
        }
        MyAsyncTask task= new MyAsyncTask();
        task.execute();
    }
    static public void insertUser(final UserData ud,final UserAsyncDaoListener<Boolean> listener){

        class MyAsyncTask extends AsyncTask<UserData,Boolean,Boolean>{

            @Override
            protected Boolean doInBackground(UserData... ud) {
                LocalDB.db.userDao().insertUser(ud[0]);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                listener.onComplete(success);
            }
        }


        MyAsyncTask task= new MyAsyncTask();
        task.execute(ud);
    }
}
