package com.example.adinepst.mybabylist.Activities.Sleeping;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.adinepst.mybabylist.Model.Model;
import com.example.adinepst.mybabylist.Utils.PostData;
import com.example.adinepst.mybabylist.Utils.SleepingData;

import java.util.List;

public class SleepingListViewModel extends ViewModel {
    private LiveData<List<SleepingData>> data;

    public LiveData<List<SleepingData>> getData(){
        data= Model.instance.getAllSleepingData();
        return data;
    }



}
