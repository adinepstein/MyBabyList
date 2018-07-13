package com.example.adinepst.mybabylist.Model.History;

import com.example.adinepst.mybabylist.Activities.History.HistoryData;

import com.example.adinepst.mybabylist.Utils.DiaperChangingData;
import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.SleepingData;
import com.example.adinepst.mybabylist.Utils.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModelFirebaseHistory {



    final static String HEADNODE ="Activity";
    final static String CHILDNODE ="History";


    static private ValueEventListener eventListener;

    public void addDiaperToHistory(final DiaperChangingData dd, final UserData ud){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getEmail()).child(CHILDNODE).child(dd.getDate());
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HistoryData hd;
                if (dataSnapshot.exists()) {
                    hd = dataSnapshot.getValue(HistoryData.class);
                    if(dd.getType().compareTo("poop")==0){
                        int num=Integer.parseInt(hd.getNumOfPoops());
                        num++;
                        hd.setNumOfPoops(Integer.toString(num));
                    }
                    else{
                        int num=Integer.parseInt(hd.getNumofUrines());
                        num++;
                        hd.setNumofUrines(Integer.toString(num));
                    }

                }
                else {
                    if(dd.getType().compareTo("poop")==0)
                        hd= new HistoryData(dd.getDate(),"0","0","0:0","1","0");
                    else
                        hd= new HistoryData(dd.getDate(),"0","0","0:0","0","1");
                }
                addHistoryData(hd,ud);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void addFeedingToHistory(final FeedingData fd, final UserData ud){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getEmail()).child(CHILDNODE).child(fd.getDate());
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HistoryData hd;
                if (dataSnapshot.exists()) {
                    hd = dataSnapshot.getValue(HistoryData.class);
                    int num= Integer.parseInt(hd.getNumOfFeadings());

                    double avgAmount=Double.parseDouble(hd.getAvgAmount());
                    double totalAmount= num*avgAmount;
                    totalAmount+= fd.getAmount();
                    num++;
                    hd.setNumOfFeadings(Integer.toString(num));
                    hd.setAvgAmount(Double.toString(totalAmount/num));

                }
                else
                    hd= new HistoryData(fd.getDate(),"1",Double.toString(fd.getAmount()),"0:0","0","0");



                addHistoryData(hd,ud);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void addSleepingToHistory(SleepingData sd, UserData ud){

        if(sd.getDate().compareTo(sd.getEndDate())==0)
            addSleepingTime(ud.getEmail(),sd.getDate(),sd.getTotalTime(),ud);

        else {
            int minLeft=60-getMinutes(sd.getTime());
            int houLeft =23-getHours(sd.getTime());
            String time1= Integer.toString(houLeft)+":"+Integer.toString(minLeft);
            addSleepingTime(ud.getEmail(),sd.getDate(),time1,ud);
            addSleepingTime(ud.getEmail(),sd.getEndDate(),sd.getEndTime(),ud);

        }
    }
    private void addHistoryData(HistoryData hd, UserData ud) {
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getEmail()).child(CHILDNODE);
        dbRef.child(hd.getDay()).setValue(hd);
    }
    private void addSleepingTime(String sortEmail, final String date, final String totalTime, final UserData ud){
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(sortEmail).child(CHILDNODE).child(date);
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    HistoryData hd;
                    if (dataSnapshot.exists()) {
                         hd = dataSnapshot.getValue(HistoryData.class);
                        hd.setSleepingHours(calculateTime(hd.getSleepingHours(),totalTime));
                    }
                    else {
                        hd= new HistoryData(date,"0","0",totalTime,"0","0");
                    }
                    addHistoryData(hd,ud);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

    private int getMinutes(String time){
        String[] s= time.split(":");
        int m=Integer.parseInt(s[1]);
        return m;
    }

    private int getHours(String time){
        String[] s= time.split(":");
        int h=Integer.parseInt(s[0]);
        return h;
    }

    private String calculateTime(String current,String addingTime){
        String[] split1=current.split(":");
        int h1= Integer.parseInt(split1[0]);
        int m1 = Integer.parseInt(split1[1]);
        String[] split2=addingTime.split(":");
        int h2= Integer.parseInt(split2[0]);
        int m2= Integer.parseInt(split2[1]);
        int sumMinutes=m1+m2;
        int sumHours = h1+h2;
        if(sumMinutes>59){
            sumHours++;
            sumMinutes -=60;
        }
        String total=Integer.toString(sumHours)+":"+Integer.toString(sumMinutes);
        return total;
    }


    public void cancelGetAllData(UserData userData){
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(userData.getEmail()).child(CHILDNODE);
        dr.removeEventListener(eventListener);
    }

    public interface GetAllDataListener{
        public void onSuccess(List<HistoryData> historyDataList);
    }

    public void getAllData(UserData ud, final GetAllDataListener listener){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(ud.getEmail()).child(CHILDNODE);
        eventListener = dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<HistoryData> hdList = new ArrayList<>();

                for (DataSnapshot fdSnapshot: dataSnapshot.getChildren()) {
                    HistoryData hd= fdSnapshot.getValue(HistoryData.class);
                    hdList.add(hd);
                }

                listener.onSuccess(hdList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
