package com.example.adinepst.mybabylist.Model.Forum;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import com.example.adinepst.mybabylist.Model.SQLite.ForumAsyncDao;
import com.example.adinepst.mybabylist.Utils.PostData;

public class ForumListLiveData extends MutableLiveData<List<PostData>> {
    private ModelFirebaseForum modelFirebaseForum;

    public ForumListLiveData() {
        setValue(new ArrayList<PostData>());
        modelFirebaseForum= new ModelFirebaseForum();
    }

    @Override
    protected void onActive() {
        super.onActive();
        ForumAsyncDao.getAll(new ForumAsyncDao.ForumAsyncDaoListener<List<PostData>>() {
            @Override
            public void onComplete(List<PostData> data) {
               setValue(data);

                modelFirebaseForum.getAllPostData(new ModelFirebaseForum.GetAllDataListener() {
                    @Override
                    public void onSuccess(List<PostData> postDataList) {
                        setValue(postDataList);

                        ForumAsyncDao.insertAll(postDataList, new ForumAsyncDao.ForumAsyncDaoListener<Boolean>() {
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
        modelFirebaseForum.cancelGetAllPostData();
    }
}
