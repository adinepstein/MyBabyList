package com.example.adinepst.mybabylist.Forum;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.adinepst.mybabylist.Model.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.example.adinepst.mybabylist.R;
import com.example.adinepst.mybabylist.Utils.PostData;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForumFragment extends Fragment {


    private EditText commentET;
    private RadioGroup parentRG;
    //    private RadioButton motherRB;
//    private RadioButton fatherRB;
    private ImageButton postIB;
    private String parentType;
    private ListView list;
    private ImageButton addImage;
    private ForumListViewModel forumListViewModel;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;
    private MyAdapter myAdapter;
    // Required empty public constructor


    public static ForumFragment newInstance() {
        ForumFragment fragment = new ForumFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        forumListViewModel = ViewModelProviders.of(this).get(ForumListViewModel.class);
        forumListViewModel.getData().observe(this, new Observer<List<PostData>>() {
            @Override
            public void onChanged(@Nullable List<PostData> postDataList) {
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forum_layout, container, false);
        myAdapter =new MyAdapter();
        list=view.findViewById(R.id.forum_list);
        list.setAdapter(myAdapter);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_add_post, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.add_post_layout, null);
        commentET = view.findViewById(R.id.addPost_ET_comment);
        parentRG = view.findViewById(R.id.addPost_RG_fatherMother);
//        motherRB = view.findViewById(R.id.addPost_RB_mother);
//        fatherRB = view.findViewById(R.id.addPost_RB_father);
        addImage = view.findViewById(R.id.addPost_IB_image);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        postIB = view.findViewById(R.id.addPost_IB_post);
        postIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String com = commentET.getText().toString();
                int selected = parentRG.getCheckedRadioButtonId();
                //TODO
                parentType = null;
                if (selected == R.id.addPost_RB_mother)
                    parentType = Model.instance.getUserData().getMotherName();
                else if (selected == R.id.addPost_RB_father)
                    parentType = Model.instance.getUserData().getMotherName();

                SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy kk:mm:ss");
                format.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
                Date curDate = new Date();
                String dateToStr = format.format(curDate);
                //TODO- add picture
                final PostData pd = new PostData(com, parentType, dateToStr, null, Model.instance.getUserData().getImageUrl());
                if (imageBitmap != null) {
                    Model.instance.saveImage(imageBitmap, new Model.SaveImageListener() {
                        @Override
                        public void onDone(String url) {
                            //save student obj

                            pd.setUploadImgUrl(url);
                            Model.instance.addPost(pd);

                        }
                    });}
                else
                    Model.instance.addPost(pd);
                dialog.cancel();
            }
        });

        return true;

//        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE &&
                resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            addImage.setImageBitmap(imageBitmap);
        }

    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return forumListViewModel.getData().getValue().size();
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
                view = LayoutInflater.from(getActivity()).inflate(R.layout.forum_row, null);
                TextView date = view.findViewById(R.id.forum_row_date);
                TextView comment = view.findViewById(R.id.forum_row_comment);
                final ImageView uploadImage = view.findViewById(R.id.forum_row_addingImg);
                final ImageView babyImage = view.findViewById(R.id.forum_row_babyImg);
                TextView parentName = view.findViewById(R.id.forum_row_TV_parentName);

                final PostData pd = forumListViewModel.getData().getValue().get(i);
                date.setText(pd.getDate());
                comment.setText(pd.getComment());
                babyImage.setImageResource(R.drawable.baby);
                parentName.setText(pd.getParentName());
                uploadImage.setTag(pd.getPostId());
                babyImage.setTag(pd.getPostId());
                if (pd.getUploadImgUrl() == null) {
                    uploadImage.setVisibility(View.GONE);
                } else {
                    Model.instance.getImage(pd.getUploadImgUrl(), new Model.GetImageListener() {
                        @Override
                        public void onDone(Bitmap imageBitmap) {
                            if (pd.getPostId().equals(uploadImage.getTag()) && imageBitmap != null)
                                uploadImage.setImageBitmap(imageBitmap);
                        }
                    });
                }
                if (pd.getBabyImageUrl() != null) {
                    Model.instance.getImage(pd.getBabyImageUrl(), new Model.GetImageListener() {
                        @Override
                        public void onDone(Bitmap imageBitmap) {
                            if (pd.getPostId().equals(babyImage.getTag()) && imageBitmap != null)
                                babyImage.setImageBitmap(imageBitmap);
                        }
                    });
                }
            }
            return view;
        }
    }
}

