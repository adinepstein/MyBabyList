package com.example.adinepst.mybabylist.Model.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.example.adinepst.mybabylist.Utils.PostData;

public class ModelFirebaseForum {

    final static String HEADNODE ="Forum";

    static private ValueEventListener eventListener;

    public void addPostData(PostData pd) {
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child(HEADNODE);
        dbRef.child(pd.getPostId()).setValue(pd);
    }

    public void cancelGetAllPostData(){
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference().child(HEADNODE);
        dr.removeEventListener(eventListener);
    }

    public interface GetAllDataListener{
        public void onSuccess(List<PostData> postDataList);
    }

    public void getAllPostData(final ModelFirebaseForum.GetAllDataListener listener){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(HEADNODE);
        eventListener = dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<PostData> pdList = new ArrayList<>();

                for (DataSnapshot fdSnapshot: dataSnapshot.getChildren()) {
                    PostData pd= fdSnapshot.getValue(PostData.class);
                    pdList.add(pd);
                }
                listener.onSuccess(pdList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
