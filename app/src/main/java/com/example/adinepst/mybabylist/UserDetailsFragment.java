package com.example.adinepst.mybabylist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailsFragment extends Fragment {

    private TextView name;
    private TextView age;
    private ImageView babyImage;
    private Button forumB;
    private Button activitiesB;

    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_details, container, false);
        name= view.findViewById(R.id.user_details_TV_name);
        age= view.findViewById(R.id.user_details_TV_age);
        babyImage = view.findViewById(R.id.user_details_IV_babyImage);
        forumB = view.findViewById(R.id.user_details_B_forum);
        activitiesB= view.findViewById(R.id.user_details_B_activities);

        forumB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForumFragment ff=new ForumFragment();
                FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction();
                tran.add(R.id.main_frame, ff);
                tran.addToBackStack("");
                tran.commit();
                Log.d("TAG", "forum was clicked");
            }
        });

        activitiesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryFragment fh = new HistoryFragment();
                FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction();
                tran.add(R.id.main_frame, fh);
                tran.addToBackStack("");
                tran.commit();

            }
        });

        return view;

    }

}
