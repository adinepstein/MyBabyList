package com.example.adinepst.mybabylist.Activities.Sleeping;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adinepst.mybabylist.R;
import com.example.adinepst.mybabylist.Utils.SleepingData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SleepingDetailsFragment extends Fragment {

    private ListView list;
    private SleepingListViewModel sleepingListViewModel;
    private MyAdapter myAdapter;


    public static SleepingDetailsFragment newInstance() {
        SleepingDetailsFragment fragment = new SleepingDetailsFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        sleepingListViewModel = ViewModelProviders.of(this).get(SleepingListViewModel.class);
        sleepingListViewModel.getData().observe(this, new Observer<List<SleepingData>>() {
            @Override
            public void onChanged(@Nullable List<SleepingData> SleepingDataList) {
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sleeping_details, container, false);
        myAdapter = new MyAdapter();
        list = view.findViewById(R.id.sleepingDetails_LV_list);
        list.setAdapter(myAdapter);

        return view;
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return sleepingListViewModel.getData().getValue().size();
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
            if (view == null) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.sleeping_row, null);
            }
                TextView start = view.findViewById(R.id.sleeping_row_TV_start);
                TextView end = view.findViewById(R.id.sleeping_row_TV_end);
                TextView total = view.findViewById(R.id.sleeping_row_TV_total);
                TextView comment = view.findViewById(R.id.sleeping_row_TV_comment);
                final SleepingData sd = sleepingListViewModel.getData().getValue().get(i);
                start.setText(sd.getDate() + " " + sd.getTime());
                comment.setText(sd.getComment());
                end.setText(sd.getEndDate() + " " + sd.getEndTime());
                total.setText(sd.getTotalTime());


            return view;
        }
    }
}

