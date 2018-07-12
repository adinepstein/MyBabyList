package com.example.adinepst.mybabylist.Model.Diaper;

import com.example.adinepst.mybabylist.Utils.DiaperChangingData;
import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModelFirebaseDiaper {



    final static String HEADNODE ="Activity";
    final static String CHILDNODE ="Diaper";


    static private ValueEventListener eventListener;

    public void addDiaperData(DiaperChangingData dd, UserData ud) {
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getEmail()).child(CHILDNODE);
        dbRef.child(dd.getDateAndTime()).setValue(dd);
    }

    public void cancelGetAllData(UserData userData){
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(userData.getEmail()).child(CHILDNODE);
        dr.removeEventListener(eventListener);
    }

    public interface GetAllDataListener{
        public void onSuccess(List<DiaperChangingData> diaperDataList);
    }

    public void getAllData(UserData ud, final GetAllDataListener listener){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getEmail()).child(CHILDNODE);
        eventListener = dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DiaperChangingData> sdList = new ArrayList<>();

                for (DataSnapshot fdSnapshot: dataSnapshot.getChildren()) {
                    DiaperChangingData dd= fdSnapshot.getValue(DiaperChangingData.class);
                    sdList.add(dd);
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
