package com.example.adinepst.mybabylist.Model.Feeding;

import android.arch.lifecycle.MutableLiveData;

import com.example.adinepst.mybabylist.Model.SQLite.FeedingAsyncDao;
import com.example.adinepst.mybabylist.Model.SQLite.SleepingAsyncDao;
import com.example.adinepst.mybabylist.Model.Sleeping.ModelFirebaseSleeping;
import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.SleepingData;
import com.example.adinepst.mybabylist.Utils.UserData;

import java.util.ArrayList;
import java.util.List;

public class FeedingListLiveData extends MutableLiveData<List<FeedingData>> {
    private ModelFirebaseFeeding modelFirebaseFeeding;
    private UserData userData;

    public FeedingListLiveData(UserData userData) {
        setValue(new ArrayList<FeedingData>());
        modelFirebaseFeeding= new ModelFirebaseFeeding();
        this.userData=userData;
    }

    @Override
    protected void onActive() {
        super.onActive();

        FeedingAsyncDao.getAll(new FeedingAsyncDao.FeedingAsyncDaoListener<List<FeedingData>>() {
            @Override
            public void onComplete(List<FeedingData> data) {
               setValue(data);

                modelFirebaseFeeding.getAllData(userData,new ModelFirebaseFeeding.GetAllDataListener() {
                    @Override
                    public void onSuccess(List<FeedingData> feedingDataList) {
                        setValue(feedingDataList);

                        FeedingAsyncDao.insertAll(feedingDataList, new FeedingAsyncDao.FeedingAsyncDaoListener<Boolean>() {
                            @Override
                            public void onComplete(Boolean data) {

                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        modelFirebaseFeeding.cancelGetAllData(userData);
    }
}
