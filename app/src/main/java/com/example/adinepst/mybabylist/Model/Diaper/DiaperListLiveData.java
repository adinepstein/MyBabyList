package com.example.adinepst.mybabylist.Model.Diaper;

import android.arch.lifecycle.MutableLiveData;


import com.example.adinepst.mybabylist.Model.SQLite.DiaperAsyncDao;
import com.example.adinepst.mybabylist.Utils.DiaperChangingData;
import com.example.adinepst.mybabylist.Utils.UserData;

import java.util.ArrayList;
import java.util.List;

public class DiaperListLiveData extends MutableLiveData<List<DiaperChangingData>> {
    private ModelFirebaseDiaper modelFirebaseDiaper;
    private UserData userData;

    public DiaperListLiveData(UserData userData) {
        setValue(new ArrayList<DiaperChangingData>());
        modelFirebaseDiaper= new ModelFirebaseDiaper();
        this.userData=userData;
    }

    @Override
    protected void onActive() {
        super.onActive();

        DiaperAsyncDao.getAll(new DiaperAsyncDao.DiaperAsyncDaoListener<List<DiaperChangingData>>() {
            @Override
            public void onComplete(List<DiaperChangingData> data) {
               setValue(data);

                modelFirebaseDiaper.getAllData(userData,new ModelFirebaseDiaper.GetAllDataListener() {
                    @Override
                    public void onSuccess(List<DiaperChangingData> diaperDataList) {
                        setValue(diaperDataList);

                        DiaperAsyncDao.insertAll(diaperDataList, new DiaperAsyncDao.DiaperAsyncDaoListener<Boolean>() {
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
        modelFirebaseDiaper.cancelGetAllData(userData);
    }
}
