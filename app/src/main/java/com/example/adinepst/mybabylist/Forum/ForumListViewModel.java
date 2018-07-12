package com.example.adinepst.mybabylist.Forum;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.adinepst.mybabylist.Model.Model;

import java.util.List;

import com.example.adinepst.mybabylist.Utils.PostData;

public class ForumListViewModel extends ViewModel {
    private LiveData<List<PostData>> data;

    public LiveData<List<PostData>> getData(){
        data= Model.instance.getAllForumData();
        return data;
    }



}
