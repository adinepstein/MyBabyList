package com.example.adinepst.mybabylist.Model;

import android.arch.lifecycle.LiveData;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.adinepst.mybabylist.Activities.History.HistoryData;
import com.example.adinepst.mybabylist.Model.Diaper.DiaperListLiveData;
import com.example.adinepst.mybabylist.Model.Diaper.ModelFirebaseDiaper;
import com.example.adinepst.mybabylist.Model.Feeding.FeedingListLiveData;
import com.example.adinepst.mybabylist.Model.Feeding.ModelFirebaseFeeding;
import com.example.adinepst.mybabylist.Model.Forum.ForumListLiveData;
import com.example.adinepst.mybabylist.Model.History.HistoryListLiveData;
import com.example.adinepst.mybabylist.Model.History.ModelFirebaseHistory;
import com.example.adinepst.mybabylist.Model.SQLite.DiaperAsyncDao;
import com.example.adinepst.mybabylist.Model.SQLite.FeedingAsyncDao;
import com.example.adinepst.mybabylist.Model.SQLite.ForumAsyncDao;
import com.example.adinepst.mybabylist.Model.Sleeping.SleepingListLiveData;
import com.example.adinepst.mybabylist.Model.SQLite.SleepingAsyncDao;
import com.example.adinepst.mybabylist.Model.SQLite.UserAsyncDao;
import com.example.adinepst.mybabylist.Utils.DiaperChangingData;

import com.example.adinepst.mybabylist.Model.Sleeping.ModelFirebaseSleeping;
import com.example.adinepst.mybabylist.Model.Forum.ModelFirebaseForum;
import com.example.adinepst.mybabylist.Model.User.ModelFirebaseUsers;
import com.example.adinepst.mybabylist.Utils.FeedingData;

import com.example.adinepst.mybabylist.Utils.PostData;
import com.example.adinepst.mybabylist.Utils.SleepingData;
import com.example.adinepst.mybabylist.Utils.UserData;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Model {

    public static Model instance = new Model();

    private ModelFirebaseUsers modelFirebaseUsers;
    private ModelImageHandler modelImageHandler;
    private ForumListLiveData forumListLiveData;
    private SleepingListLiveData sleepingListLiveData;
    private ModelFirebaseForum modelFirebaseForum;
    private FirebaseAuth firebaseAuth;
    private UserData userData;
    private ModelFirebaseSleeping modelFirebaseSleeping;
    private ModelFirebaseFeeding modelFirebaseFeeding;
    private ModelFirebaseDiaper modelFirebaseDiaper;
    private ModelFirebaseHistory modelFirebaseHistory;
    private FeedingListLiveData feedingListLiveData;
    private DiaperListLiveData diaperListLiveData;
    private HistoryListLiveData historyListLiveData;



    private Model() {
        modelFirebaseUsers = new ModelFirebaseUsers();
        modelImageHandler = new ModelImageHandler();
        forumListLiveData = new ForumListLiveData();
        modelFirebaseForum = new ModelFirebaseForum();
        modelFirebaseSleeping = new ModelFirebaseSleeping();
        firebaseAuth = FirebaseAuth.getInstance();
        modelFirebaseFeeding = new ModelFirebaseFeeding();
        modelFirebaseDiaper = new ModelFirebaseDiaper();
        modelFirebaseHistory = new ModelFirebaseHistory();


    }

    public LiveData<List<HistoryData>> getAllHistoryData() {

        return historyListLiveData;
    }

    public LiveData<List<FeedingData>> getAllFeedingData() {

        return feedingListLiveData;
    }

    public LiveData<List<DiaperChangingData>> getAllDiaperData() {

        return diaperListLiveData;
    }

    public void addSleepingData(SleepingData sd) {
        SleepingAsyncDao.insertData(sd, new SleepingAsyncDao.SleepingAsyncDaoListener<Boolean>() {
            @Override
            public void onComplete(Boolean data) {

            }
        });
        modelFirebaseSleeping.addSleepingData(sd, userData);
        modelFirebaseHistory.addSleepingToHistory(sd,userData);
    }

    public void addFeedingData(FeedingData fd) {
        FeedingAsyncDao.insertData(fd, new FeedingAsyncDao.FeedingAsyncDaoListener<Boolean>() {
            @Override
            public void onComplete(Boolean data) {

            }
        });
        modelFirebaseFeeding.addFeedingData(fd, userData);
        modelFirebaseHistory.addFeedingToHistory(fd,userData);
    }

    public void addDiaperData(DiaperChangingData dd) {
        DiaperAsyncDao.insertData(dd, new DiaperAsyncDao.DiaperAsyncDaoListener<Boolean>() {
            @Override
            public void onComplete(Boolean data) {

            }
        });
        modelFirebaseDiaper.addDiaperData(dd, userData);
        modelFirebaseHistory.addDiaperToHistory(dd,userData);
    }


    public void addUser(UserData ud) {
        UserAsyncDao.insertUser(ud, new UserAsyncDao.UserAsyncDaoListener<Boolean>() {
            @Override
            public void onComplete(Boolean data) {

            }
        });

        modelFirebaseUsers.addUser(ud);

    }

    public interface UserDataListener {
        public void onComplete(UserData ud);
    }

    public void getUserData(final UserDataListener listener) {
       final String sortEmail = firebaseAuth.getCurrentUser().getEmail().split("@")[0];
        modelFirebaseUsers.getUser(sortEmail, new ModelFirebaseUsers.FirebaseUserListener() {
            @Override
            public void onComplete(UserData ud) {
                if (ud != null) {
                    userData = ud;
                    feedingListLiveData = new FeedingListLiveData(ud);
                    sleepingListLiveData= new SleepingListLiveData(ud);
                    diaperListLiveData = new DiaperListLiveData(ud);
                    historyListLiveData =new HistoryListLiveData(ud);
                    listener.onComplete(ud);
                } else {

                }
            }
        });
    }

    public void addPost(PostData pd) {
        ForumAsyncDao.insertData(pd, new ForumAsyncDao.ForumAsyncDaoListener<Boolean>() {
            @Override
            public void onComplete(Boolean data) {

            }
        });
        modelFirebaseForum.addPostData(pd);
    }

    public LiveData<List<SleepingData>> getAllSleepingData() {

        return sleepingListLiveData;
    }

    public LiveData<List<PostData>> getAllForumData() {
        return forumListLiveData;
    }

    public interface SaveImageListener {
        void onDone(String url);
    }


    public void saveImage(Bitmap imageBitmap, SaveImageListener listener) {
        modelImageHandler.saveImage(imageBitmap, listener);
    }
    public UserData getUserData(){return  userData;}

    public interface GetImageListener {
        void onDone(Bitmap imageBitmap);
    }

    public void getImage(final String url, final GetImageListener listener) {
        modelImageHandler.getImage(url, listener);
    }


}
