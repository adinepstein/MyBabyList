package com.example.adinepst.mybabylist.Model.User;

import com.example.adinepst.mybabylist.Model.Model;
import com.example.adinepst.mybabylist.UserDetailsFragment;
import com.example.adinepst.mybabylist.Utils.PostData;
import com.example.adinepst.mybabylist.Utils.UserData;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModelFirebaseUsers {
    final static String HEADNODE ="Users";
    static private ValueEventListener eventListener;

     public void addUser(UserData ud) {
         DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child(HEADNODE);
         dbRef.child(ud.getEmail()).setValue(ud);
    }



public interface FirebaseUserListener{
         void onComplete(UserData ud);
}



    public void getUser(String email, final FirebaseUserListener listener){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(HEADNODE).child(email);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData ud= dataSnapshot.getValue(UserData.class);
                listener.onComplete(ud);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
