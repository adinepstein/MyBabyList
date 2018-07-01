package com.example.adinepst.mybabylist.Model;

import android.arch.lifecycle.LiveData;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.adinepst.mybabylist.Model.SQLite.UserAsyncDao;
import com.example.adinepst.mybabylist.Utils.ActivityData;
import com.example.adinepst.mybabylist.Utils.DiaperChangingData;

import com.example.adinepst.mybabylist.Model.Firebase.ModelFirebaseSleeping;
import com.example.adinepst.mybabylist.Model.Firebase.ModelFirebaseForum;
import com.example.adinepst.mybabylist.Model.Firebase.ModelFirebaseUsers;
import com.example.adinepst.mybabylist.Model.SQLite.DiaperChangingSQLite;
import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Model.SQLite.FeedingSQLite;
import com.example.adinepst.mybabylist.Model.SQLite.ModelSQLite;

import com.example.adinepst.mybabylist.Utils.PostData;
import com.example.adinepst.mybabylist.Utils.SleepingData;
import com.example.adinepst.mybabylist.Utils.UserData;

import com.example.adinepst.mybabylist.Model.SQLite.SleepingSQLite;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Model {

    public static Model instance = new Model();
    private ModelSQLite modelSQLLite;
    private ModelFirebaseUsers modelFirebaseUsers;
    private ModelImageHandler modelImageHandler;
    private ForumListLiveData forumListLiveData;
    private ModelFirebaseForum modelFirebaseForum;
    private FirebaseAuth firebaseAuth;
    private UserData userData;


    private Model(){
        modelFirebaseUsers= new ModelFirebaseUsers();
        modelSQLLite= new ModelSQLite();
        modelImageHandler = new ModelImageHandler();
        forumListLiveData= new ForumListLiveData();
        modelFirebaseForum = new ModelFirebaseForum();
        firebaseAuth =FirebaseAuth.getInstance();

    }

    public List<FeedingData> getAllFeedingData(){
        return FeedingSQLite.getAllFeedingData(modelSQLLite.getReadableDatabase());
    }

    public void addActivityData(ActivityData ad, UserData ud){
        Log.d("Tag", "model- add feeding data");
//        FeedingSQLite.addActivityData(fd,modelSQLLite.getWritableDatabase());
        ModelFirebaseSleeping.addActivityData(ad,ud);
    }
    public void addUser(UserData ud){
        UserAsyncDao.insertUser(ud, new UserAsyncDao.UserAsyncDaoListener<Boolean>() {
            @Override
            public void onComplete(Boolean data) {

            }
        });

        modelFirebaseUsers.addUser(ud);

    }
    public interface UserDataListener{
        public void onComplete(UserData ud);
    }

    public void getUserData(final UserDataListener listener){
        if(userData!=null){
            Log.d("TAG","User data 1");
            listener.onComplete(userData);
        }
        else{
            Log.d("TAG","User data 2");
            UserAsyncDao.getUser(new UserAsyncDao.UserAsyncDaoListener<UserData>() {
                @Override
                public void onComplete(UserData data) {
                    if (userData==null){
                        getUserFromFireBase(listener);
                    }
                    userData=data;
                    Log.d("TAG", userData.getEmail());
                    listener.onComplete(data);

                }
            });

        }

    }
    private void getUserFromFireBase(final UserDataListener listener){
        final String sortEmail= firebaseAuth.getCurrentUser().getEmail().split("@")[0];
        modelFirebaseUsers.getUser(sortEmail, new ModelFirebaseUsers.FirebaseUserListener() {
            @Override
            public void onComplete(UserData ud) {
                if(ud!=null){
                    Log.d("TAG","User data 3");
                    userData=ud;
                    listener.onComplete(ud);
                }
                else{
                    Log.d("TAG","User data 4");
                    Log.d("TAG","NO sort EMail "+ sortEmail);
                }
            }
        });

    }


    public void addPost(PostData pd){
        modelFirebaseForum.addPostData(pd);
    }

    public List<SleepingData> getAllSleepingData(){
        return SleepingSQLite.getAllSleepingData(modelSQLLite.getReadableDatabase());
    }

    public void addSleepingData(SleepingData sd){
        SleepingSQLite.addSleepingData(sd,modelSQLLite.getWritableDatabase());
    }

    public List<DiaperChangingData> getAllDiaperChangingData(){
        return DiaperChangingSQLite.getAllDiaperChangingData(modelSQLLite.getReadableDatabase());
    }

    public void addDiaperChangingData(DiaperChangingData dcd){
        DiaperChangingSQLite.addDiaperChangingData(dcd,modelSQLLite.getWritableDatabase());
    }

    public LiveData<List<PostData>> getAllForumData() {
        return forumListLiveData;
    }

    public interface SaveImageListener{
        void onDone(String url);
    }


    public void saveImage(Bitmap imageBitmap, SaveImageListener listener) {
        modelImageHandler.saveImage(imageBitmap,listener);
    }

    public interface GetImageListener{
        void onDone(Bitmap imageBitmap);
    }

    public void getImage(final String url, final GetImageListener listener ){
        modelImageHandler.getImage(url,listener);
    }



}
