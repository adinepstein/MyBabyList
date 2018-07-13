package com.example.adinepst.mybabylist.Model.History;

import android.arch.lifecycle.MutableLiveData;

import com.example.adinepst.mybabylist.Activities.History.HistoryData;
import com.example.adinepst.mybabylist.Model.Feeding.ModelFirebaseFeeding;
import com.example.adinepst.mybabylist.Model.SQLite.FeedingAsyncDao;
import com.example.adinepst.mybabylist.Model.SQLite.HistoryAsyncDao;
import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.UserData;

import java.util.ArrayList;
import java.util.List;

public class HistoryListLiveData extends MutableLiveData<List<HistoryData>> {
    private ModelFirebaseHistory modelFirebaseHistory;
    private UserData userData;

    public HistoryListLiveData(UserData userData) {
        setValue(new ArrayList<HistoryData>());
        modelFirebaseHistory= new ModelFirebaseHistory();
        this.userData=userData;
    }

    @Override
    protected void onActive() {
        super.onActive();

        HistoryAsyncDao.getAll(new HistoryAsyncDao.HistoryAsyncDaoListener<List<HistoryData>>() {
            @Override
            public void onComplete(List<HistoryData> data) {
               setValue(data);

                modelFirebaseHistory.getAllData(userData,new ModelFirebaseHistory.GetAllDataListener() {
                    @Override
                    public void onSuccess(List<HistoryData> historyDataList) {
                        setValue(historyDataList);

                        HistoryAsyncDao.insertAll(historyDataList, new HistoryAsyncDao.HistoryAsyncDaoListener<Boolean>() {
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
        modelFirebaseHistory.cancelGetAllData(userData);
    }
}
