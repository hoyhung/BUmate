package com.example.hoyhung.bumate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hoyhung on 9/11/2016.
 */

public class CourseInfoView extends Fragment {

    public CourseInfoView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("courseTitle");
        DatabaseReference rootRef = database.getReference("semester /201617 /sem1");


        /*rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });*/


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_info, container, false);
    }
}
