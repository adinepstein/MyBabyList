package com.example.adinepst.mybabylist.Activities.History;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adinepst.mybabylist.Activities.Diaper.AddDiaperFragment;
import com.example.adinepst.mybabylist.Activities.Diaper.DiaperDetailsFragment;
import com.example.adinepst.mybabylist.Activities.Feeding.AddFeedingFragment;
import com.example.adinepst.mybabylist.Activities.Feeding.FeedingDetailsFragment;
import com.example.adinepst.mybabylist.Activities.Feeding.FeedingListViewModel;
import com.example.adinepst.mybabylist.Activities.Sleeping.AddSleepingFragment;
import com.example.adinepst.mybabylist.Activities.Sleeping.SleepingDetailsFragment;
import com.example.adinepst.mybabylist.R;
import com.example.adinepst.mybabylist.Activities.History.HistoryData;
import com.example.adinepst.mybabylist.Utils.FeedingData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private HistoryListViewModel historyListViewModel;
    private HistoryListAdapter adapter;
    private Button sleepingDetailBT;
    private Button feedingDetailBT;
    private Button diaperDetailBT;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        historyListViewModel = ViewModelProviders.of(this).get(HistoryListViewModel.class);
        historyListViewModel.getData().observe(this, new Observer<List<HistoryData>>() {
            @Override
            public void onChanged(@Nullable List<HistoryData> historyDataList) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.history_layout, container, false);
        ListView list= view.findViewById(R.id.history_LV_list);
        sleepingDetailBT= view.findViewById(R.id.history_B_sleepingDetails);
        feedingDetailBT = view.findViewById(R.id.history_B_foodDetails);
        diaperDetailBT = view.findViewById(R.id.history_B_DiapersDetails);
        adapter = new HistoryListAdapter();
        list.setAdapter(adapter);
        sleepingDetailBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new SleepingDetailsFragment();
                FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction();
                tran.replace(R.id.main_frame, fragment);
                tran.addToBackStack(" ");
                tran.commit();
            }
        });
        feedingDetailBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new FeedingDetailsFragment();
                FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction();
                tran.replace(R.id.main_frame, fragment);
                tran.addToBackStack(" ");
                tran.commit();
            }
        });
        diaperDetailBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new DiaperDetailsFragment();
                FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction();
                tran.replace(R.id.main_frame, fragment);
                tran.addToBackStack(" ");
                tran.commit();
            }
        });

        setHasOptionsMenu(true);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_add_activity,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean switched=false;
        Fragment fragment=null;
        switch (id) {
            case R.id.menu_MI_add_feeding:
                fragment = new AddFeedingFragment();
                switched=true;
                break;
            case R.id.menu_MI_add_sleeping:
                fragment = new AddSleepingFragment();
                switched=true;
                break;
            case R.id.menu_MI_add_diaper:
                fragment = new AddDiaperFragment();
                switched=true;
                break;
        }
        if (switched){
            FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction();
            tran.replace(R.id.main_frame, fragment);
            tran.addToBackStack(" ");
            tran.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public class HistoryListAdapter extends BaseAdapter {

        List<HistoryData> data = new ArrayList<>();


        @Override
        public int getCount() {
            return historyListViewModel.getData().getValue().size();
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
            if(view==null) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.history_row, null);

                HistoryData hd = historyListViewModel.getData().getValue().get(i);
                TextView date = view.findViewById(R.id.history_row_day);
                date.setText(hd.getDay());
                TextView numFeading = view.findViewById(R.id.history_row_numOfFood);
                numFeading.setText(hd.getNumOfFeadings());
                TextView avgAmount = view.findViewById(R.id.history_row_avgAmount);
                avgAmount.setText(hd.getAvgAmount());
                TextView hoursSleeping = view.findViewById(R.id.history_row_hoursPerDaySleeping);
                hoursSleeping.setText(hd.getSleepingHours());
                TextView numUrine = view.findViewById(R.id.history_row_numUrine);
                numUrine.setText(hd.getNumofUrines());
                TextView numPoop = view.findViewById(R.id.history_row_numPoop);
                numPoop.setText(hd.getNumOfPoops());
            }
            return view;

        }
    }


}
