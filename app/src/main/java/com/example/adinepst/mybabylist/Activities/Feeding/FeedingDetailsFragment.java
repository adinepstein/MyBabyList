package com.example.adinepst.mybabylist.Activities.Feeding;


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

import com.example.adinepst.mybabylist.Activities.Sleeping.SleepingListViewModel;
import com.example.adinepst.mybabylist.R;
import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.SleepingData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedingDetailsFragment extends Fragment {

    private ListView list;
    private FeedingListViewModel feedingListViewModel;
    private MyAdapter myAdapter;


    public static FeedingDetailsFragment newInstance() {
        FeedingDetailsFragment fragment = new FeedingDetailsFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        feedingListViewModel = ViewModelProviders.of(this).get(FeedingListViewModel.class);
        feedingListViewModel.getData().observe(this, new Observer<List<FeedingData>>() {
            @Override
            public void onChanged(@Nullable List<FeedingData> FeedingDataList) {
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feeding_details, container, false);
        myAdapter = new MyAdapter();
        list = view.findViewById(R.id.feedingDetails_LV_list);
        list.setAdapter(myAdapter);

        return view;
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return feedingListViewModel.getData().getValue().size();
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
                view = LayoutInflater.from(getActivity()).inflate(R.layout.feeding_row, null);
                TextView date = view.findViewById(R.id.feeding_row_TV_date);
                TextView amount = view.findViewById(R.id.feeding_row_TV_amount);
                TextView comment = view.findViewById(R.id.feeding_row_TV_comment);
                final FeedingData fd = feedingListViewModel.getData().getValue().get(i);
                date.setText(fd.getDateAndTime());
                comment.setText(fd.getComment());
                amount.setText(Double.toString(fd.getAmount()));

            }
            return view;
        }
    }
}

