package com.example.adinepst.mybabylist.Model.Sleeping;

import com.example.adinepst.mybabylist.Utils.SleepingData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.example.adinepst.mybabylist.Utils.ActivityData;
import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.UserData;

public class ModelFirebaseSleeping {



    final static String HEADNODE ="Activity";
    final static String CHILDNODE ="Sleeping";


    static private ValueEventListener eventListener;

    public void addSleepingData(SleepingData sd, UserData ud) {
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getEmail()).child(CHILDNODE);
     dbRef.child(sd.getDateAndTime()).setValue(sd);

    }

    public void cancelGetAllData(UserData userData){
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(userData.getEmail()).child(CHILDNODE);
        dr.removeEventListener(eventListener);
    }

    public interface GetAllDataListener{
        public void onSuccess(List<SleepingData> sleepingDataList);
    }

    public void getAllData(UserData ud, final GetAllDataListener listener){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getEmail()).child(CHILDNODE);
        eventListener = dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<SleepingData> sdList = new ArrayList<>();

                for (DataSnapshot fdSnapshot: dataSnapshot.getChildren()) {
                    SleepingData sd= fdSnapshot.getValue(SleepingData.class);
                    sdList.add(sd);
                }
                listener.onSuccess(sdList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
