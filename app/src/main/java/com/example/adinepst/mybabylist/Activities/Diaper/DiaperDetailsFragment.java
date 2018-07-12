package com.example.adinepst.mybabylist.Activities.Diaper;


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
import com.example.adinepst.mybabylist.Utils.DiaperChangingData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaperDetailsFragment extends Fragment {

    private ListView list;
    private DiaperListViewModel diaperListViewModel;
    private MyAdapter myAdapter;


    public static DiaperDetailsFragment newInstance() {
        DiaperDetailsFragment fragment = new DiaperDetailsFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        diaperListViewModel = ViewModelProviders.of(this).get(DiaperListViewModel.class);
        diaperListViewModel.getData().observe(this, new Observer<List<DiaperChangingData>>() {
            @Override
            public void onChanged(@Nullable List<DiaperChangingData> DiaperDataList) {
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diaper_details, container, false);
        myAdapter = new MyAdapter();
        list = view.findViewById(R.id.diaperDetails_LV_list);
        list.setAdapter(myAdapter);

        return view;
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return diaperListViewModel.getData().getValue().size();
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
                view = LayoutInflater.from(getActivity()).inflate(R.layout.diaper_row, null);
                TextView start = view.findViewById(R.id.diaper_row_TV_date);
                TextView type = view.findViewById(R.id.diaper_row_TV_type);
                TextView comment = view.findViewById(R.id.diaper_row_TV_comment);
                final DiaperChangingData dd = diaperListViewModel.getData().getValue().get(i);
                start.setText(dd.getDateAndTime());
                comment.setText(dd.getComment());
                type.setText(dd.getType());

            }
            return view;
        }
    }
}

