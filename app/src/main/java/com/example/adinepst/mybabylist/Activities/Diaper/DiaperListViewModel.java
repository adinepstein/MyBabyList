package com.example.adinepst.mybabylist.Activities.Diaper;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.adinepst.mybabylist.Model.Model;
import com.example.adinepst.mybabylist.Utils.DiaperChangingData;
import com.example.adinepst.mybabylist.Utils.SleepingData;

import java.util.List;

public class DiaperListViewModel extends ViewModel {
    private LiveData<List<DiaperChangingData>> data;

    public LiveData<List<DiaperChangingData>> getData(){
        data= Model.instance.getAllDiaperData();
        return data;
    }



}
