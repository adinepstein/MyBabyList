package com.example.adinepst.mybabylist.Model.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Utils.ActivityData;
import Utils.FeedingData;
import Utils.UserData;

public class ModelFirebaseFeeding {
    final static String HEADNODE ="Activity";

    static private ValueEventListener eventListener;

    static public void addActivityData(ActivityData ad, UserData ud) {
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getId());
        dbRef.child(ad.getDate()).child(ad.getTime()).setValue(ad);
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
