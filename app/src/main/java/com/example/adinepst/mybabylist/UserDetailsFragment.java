package com.example.adinepst.mybabylist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        return view;

    }

}
