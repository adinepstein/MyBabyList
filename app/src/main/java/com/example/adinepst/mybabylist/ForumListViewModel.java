package com.example.adinepst.mybabylist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.adinepst.mybabylist.Model.Model;

import java.util.List;

import Utils.PostData;

public class ForumListViewModel extends ViewModel {
    private LiveData<List<PostData>> data;

    public LiveData<List<PostData>> getData(){
        data= Model.instance.getAllForumData();
        return data;
    }



}
