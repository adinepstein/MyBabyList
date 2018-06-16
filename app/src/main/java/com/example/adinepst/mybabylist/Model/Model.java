package com.example.adinepst.mybabylist.Model;

import android.arch.lifecycle.LiveData;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.adinepst.mybabylist.Model.SQLite.LocalDB;
import com.example.adinepst.mybabylist.Model.SQLite.UserAsyncDao;
import com.example.adinepst.mybabylist.Utils.ActivityData;
import com.example.adinepst.mybabylist.Utils.DiaperChangingData;

import com.example.adinepst.mybabylist.Model.Firebase.ModelFirebaseFeeding;
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
        ModelFirebaseFeeding.addActivityData(ad,ud);
    }
    public void addUser(UserData ud){
        UserAsyncDao.insertUser(ud, new UserAsyncDao.UserAsyncDaoListener<Boolean>() {
            @Override
            public void onComplete(Boolean data) {

            }
        });

        modelFirebaseUsers.addUser(ud);

    }
    public interface GetUserListener{
        public void onSuccess(UserData ud);
    }

    public void getUserFromLocal(GetUserListener listener){
        UserAsyncDao.getUser(listener);
    }
    public void getUserFromFireBase(String emailSort,GetUserListener listener){
        modelFirebaseUsers.getUser(emailSort,listener);
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

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void getUserDataFromDB() {
        String s= firebaseAuth.getCurrentUser().getEmail();
        String[] strings= s.split("@");
        getUserFromFireBase(strings[0], new GetUserListener() {
            @Override
            public void onSuccess(UserData ud) {
               setUserData(ud);
            }
        });
        this.userData = userData;
    }
}
