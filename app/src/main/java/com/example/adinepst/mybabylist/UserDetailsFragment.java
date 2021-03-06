package com.example.adinepst.mybabylist;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adinepst.mybabylist.Activities.History.HistoryFragment;
import com.example.adinepst.mybabylist.Forum.ForumFragment;
import com.example.adinepst.mybabylist.Model.Model;
import com.example.adinepst.mybabylist.Utils.Age;
import com.example.adinepst.mybabylist.Utils.AgeCalculator;
import com.example.adinepst.mybabylist.Utils.UserData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailsFragment extends Fragment {

    private TextView name;
    private TextView age;
    private ImageView babyImage;
    private Button forumB;
    private Button activitiesB;
    private ProgressDialog progressDialog;

    public UserDetailsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        name = view.findViewById(R.id.user_details_TV_name);
        age = view.findViewById(R.id.user_details_TV_age);
        babyImage = view.findViewById(R.id.user_details_IV_babyImage);
        forumB = view.findViewById(R.id.user_details_B_forum);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Updating...");
        progressDialog.show();
        setUserData();
        activitiesB = view.findViewById(R.id.user_details_B_activities);

        forumB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForumFragment ff = new ForumFragment();
                FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction();
                tran.replace(R.id.main_frame, ff);
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
                tran.replace(R.id.main_frame, fh);
                tran.addToBackStack("");
                tran.commit();

            }
        });

        return view;

    }

    private void setUserData() {
        Model.instance.getUserData(new Model.UserDataListener() {
            @Override
            public void onComplete(UserData ud) {
                name.setText(ud.getName());
                age.setText(setAge(ud.getDateOfBirth()));
                if (ud.getImageUrl() != null) {
                    Model.instance.getImage(ud.getImageUrl(), new Model.GetImageListener() {
                        @Override
                        public void onDone(Bitmap imageBitmap) {
                            if (imageBitmap != null)
                                babyImage.setImageBitmap(imageBitmap);

                        }
                    });
                }
                progressDialog.dismiss();
            }
        });
    }

    private String setAge(String birthDate)  {
//        String[] splitDate = birthDate.split("-");
//        LocalDate birthday = LocalDate.of(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]));
//        LocalDate today = LocalDate.now();
//        Period p = Period.between(birthday, today);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date bd = null;
        try {
            bd = sdf.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Age age = AgeCalculator.calculateAge(bd);
        return age.toString();
    }
}

