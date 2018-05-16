package com.example.adinepst.mybabylist;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView list= findViewById(R.id.history_LV_list);
        HistoryListAdapter adapter = new HistoryListAdapter();
        for(int i=0; i<50; i++){
            HistoryData h= new HistoryData(""+ i+ "/01/18",""+i, "150." + i, "15."+ i, ""+i, ""+ i);
            adapter.data.add(h);
        }
        list.setAdapter(adapter);
    }

    public class HistoryListAdapter extends BaseAdapter{

        List<HistoryData> data = new ArrayList<>();


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view = LayoutInflater.from(HistoryActivity.this).inflate(R.layout.history_row,null);
            }
         HistoryData hd= data.get(i);
            TextView date= view.findViewById(R.id.history_row_day);
            date.setText(hd.day);
            TextView numFeading = view.findViewById(R.id.history_row_numOfFood);
            numFeading.setText(hd.numOfFeadings);
            TextView avgAmount= view.findViewById(R.id.history_row_avgAmount);
            avgAmount.setText(hd.avgAmount);
            TextView hoursSleeping = view.findViewById(R.id.history_row_hoursPerDaySleeping);
            hoursSleeping.setText(hd.sleepingHours);
            TextView numUrine= view.findViewById(R.id.history_row_numUrine);
            numUrine.setText(hd.numofUrines);
            TextView numPoop = view.findViewById(R.id.history_row_numPoop);
            numPoop.setText(hd.numOfPoops);

           return view;

        }
    }

    public class HistoryData{
        String day;
        String numOfFeadings;
        String avgAmount;
        String sleepingHours;
        String numOfPoops;
        String numofUrines;

        public HistoryData(String day, String numOfFeadings, String avgAmount, String sleepingHours, String numOfPoops, String numofUrines) {
            this.day = day;
            this.numOfFeadings = numOfFeadings;
            this.avgAmount = avgAmount;
            this.sleepingHours = sleepingHours;
            this.numOfPoops = numOfPoops;
            this.numofUrines = numofUrines;
        }
    }

}
