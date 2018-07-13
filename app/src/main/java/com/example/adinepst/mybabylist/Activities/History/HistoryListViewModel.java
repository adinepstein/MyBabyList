package com.example.adinepst.mybabylist.Activities.History;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.adinepst.mybabylist.Model.Model;
import com.example.adinepst.mybabylist.Utils.SleepingData;

import java.util.List;

public class HistoryListViewModel extends ViewModel {
    private LiveData<List<HistoryData>> data;

    public LiveData<List<HistoryData>> getData(){
        data= Model.instance.getAllHistoryData();
        return data;
    }



}
