package com.example.adinepst.mybabylist.Model.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Utils.FeedingData;

public class ModelFirebaseFeeding {
    final static String HEADNODE ="FeedingData";
    static private ValueEventListener eventListener;
    static public void addFeedingData(FeedingData fd) {
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child(HEADNODE);
        dbRef.child(HEADNODE).child(fd.getDateTime()).setValue(fd);
    }

    interface GetAllDataListener{
        public void onSuccess(List<FeedingData> feedingDataList);
    }

    static public void getAllFeedingData(final GetAllDataListener listener){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(HEADNODE);
        eventListener = dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FeedingData> fdList = new ArrayList<>();

                for (DataSnapshot fdSnapshot: dataSnapshot.getChildren()) {
                      FeedingData fd= fdSnapshot.getValue(FeedingData.class);
                    fdList.add(fd);
                }
                listener.onSuccess(fdList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
