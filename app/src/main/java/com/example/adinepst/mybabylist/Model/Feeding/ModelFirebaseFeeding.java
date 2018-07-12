package com.example.adinepst.mybabylist.Model.Feeding;

import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.SleepingData;
import com.example.adinepst.mybabylist.Utils.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModelFirebaseFeeding {



    final static String HEADNODE ="Activity";
    final static String CHILDNODE ="Feeding";


    static private ValueEventListener eventListener;

    public void addFeedingData(FeedingData fd, UserData ud) {
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getEmail()).child(CHILDNODE);
        dbRef.child(fd.getDateAndTime()).setValue(fd);
    }

    public void cancelGetAllData(UserData userData){
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(userData.getEmail()).child(CHILDNODE);
        dr.removeEventListener(eventListener);
    }

    public interface GetAllDataListener{
        public void onSuccess(List<FeedingData> feedingDataList);
    }

    public void getAllData(UserData ud, final GetAllDataListener listener){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getEmail()).child(CHILDNODE);
        eventListener = dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FeedingData> sdList = new ArrayList<>();

                for (DataSnapshot fdSnapshot: dataSnapshot.getChildren()) {
                    FeedingData fd= fdSnapshot.getValue(FeedingData.class);
                    sdList.add(fd);
                }
//                sdList.sort(new Comparator<FeedingData>() {
//                    @Override
//                    public int compare(FeedingData o1, FeedingData o2) {
//                        Date date1=null;
//                        Date date2=null;
//                        try {
//                             date1=new SimpleDateFormat("dd-M-yyyy kk:mm:ss").parse(o1.getDate() + " " + o1.getTime());
//                             date2=new SimpleDateFormat("dd-M-yyyy kk:mm:ss").parse(o2.getDate() + " " + o2.getTime());
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                       date1.compareTo(date2);
//
//                    }
//                });
                listener.onSuccess(sdList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
