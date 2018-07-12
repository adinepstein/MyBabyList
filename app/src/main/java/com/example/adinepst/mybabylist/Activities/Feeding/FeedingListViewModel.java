package com.example.adinepst.mybabylist.Activities.Feeding;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.adinepst.mybabylist.Model.Model;
import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.SleepingData;

import java.util.List;

public class FeedingListViewModel extends ViewModel {
    private LiveData<List<FeedingData>> data;

    public LiveData<List<FeedingData>> getData(){
        data= Model.instance.getAllFeedingData();
        return data;
    }



}
