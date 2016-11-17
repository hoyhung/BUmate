package com.example.hoyhung.bumate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hoyhung on 9/11/2016.
 */

public class CourseInfoStat extends Fragment {

    public CourseInfoStat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Course Stat");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_info, container, false);
    }
}
