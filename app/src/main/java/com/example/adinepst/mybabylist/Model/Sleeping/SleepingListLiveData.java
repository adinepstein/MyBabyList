package com.example.adinepst.mybabylist.Model.Sleeping;

import android.arch.lifecycle.MutableLiveData;

import com.example.adinepst.mybabylist.Model.Sleeping.ModelFirebaseSleeping;
import com.example.adinepst.mybabylist.Model.SQLite.SleepingAsyncDao;
import com.example.adinepst.mybabylist.Utils.SleepingData;
import com.example.adinepst.mybabylist.Utils.UserData;

import java.util.ArrayList;
import java.util.List;

public class SleepingListLiveData extends MutableLiveData<List<SleepingData>> {
    private ModelFirebaseSleeping modelFirebaseSleeping;
    private UserData userData;

    public SleepingListLiveData(UserData userData) {
        setValue(new ArrayList<SleepingData>());
        modelFirebaseSleeping= new ModelFirebaseSleeping();
        this.userData=userData;
    }

    @Override
    protected void onActive() {
        super.onActive();

        SleepingAsyncDao.getAll(new SleepingAsyncDao.SleepingAsyncDaoListener<List<SleepingData>>() {
            @Override
            public void onComplete(List<SleepingData> data) {
               setValue(data);

                modelFirebaseSleeping.getAllData(userData,new ModelFirebaseSleeping.GetAllDataListener() {
                    @Override
                    public void onSuccess(List<SleepingData> sleepingDataList) {
                        setValue(sleepingDataList);

                        SleepingAsyncDao.insertAll(sleepingDataList, new SleepingAsyncDao.SleepingAsyncDaoListener<Boolean>() {
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
        modelFirebaseSleeping.cancelGetAllData(userData);
    }
}
