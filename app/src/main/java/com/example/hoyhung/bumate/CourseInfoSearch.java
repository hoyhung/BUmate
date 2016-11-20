package com.example.hoyhung.bumate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static android.content.ContentValues.TAG;

/**
 * Created by hoyhung on 9/11/2016.
 */

public class CourseInfoSearch extends Fragment {

    public int cntCategory = 0;

    public CourseInfoSearch() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = database.getReference().child("comment");
        final View rootView = inflater.inflate(R.layout.fragment_course_info_search, container, false);
        Log.i("rootref", ""+rootRef.getKey());

        final ArrayAdapter<String> adapter;
        final ArrayList<String> tmp = new ArrayList<>();
        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(), R.layout.mytextview , tmp );

        ValueEventListener valueEventListener = rootRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);

                Set<String> set = new HashSet<String>();
//                Iterator i = dataSnapshot.getChildren().iterator();
//
//                while (i.hasNext()) {
//                    set.add(((DataSnapshot) i.next()).getKey());
//                }
                for (DataSnapshot sem : dataSnapshot.getChildren()) {
                    set.add(sem.getKey().toString());
                }
                tmp.clear();
                tmp.addAll(set);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        final Spinner spinner = (Spinner) rootView.findViewById(R.id.coursecategory);
        final Spinner spinner1 = (Spinner) rootView.findViewById(R.id.coursecode);
        final Spinner spinner2 = (Spinner) rootView.findViewById(R.id.sem);

        spinner2.setAdapter(adapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {

                final String selectedSem = spinner2.getSelectedItem().toString();

                Log.i("selectedSem", ""+selectedSem);

                final ArrayAdapter<String> adapter4sem;
                final ArrayList<String> tmp = new ArrayList<>();
                adapter4sem = new ArrayAdapter<String>( getActivity().getApplicationContext(), R.layout.mytextview , tmp );

                ValueEventListener valueEventListener = rootRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Set<String> set = new HashSet<String>();

                        for (DataSnapshot sem : dataSnapshot.getChildren()) {
                            if (sem.getKey().toString() == selectedSem){
                                for (DataSnapshot category : sem.getChildren()) {
                                    set.add(category.getKey().toString());
                                }
                            }
                        }
                        tmp.clear();
                        tmp.addAll(set);
                        adapter4sem.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

                spinner.setAdapter(adapter4sem);
            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                cntCategory++;

                final String selectedCategory = spinner.getSelectedItem().toString();

                final ArrayAdapter<String> adapter4coursecode;
                final ArrayList<String> tmp = new ArrayList<>();
                adapter4coursecode = new ArrayAdapter<String>( getActivity().getApplicationContext(), R.layout.mytextview , tmp );

                ValueEventListener valueEventListener = rootRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Set<String> set = new HashSet<String>();

                        for (DataSnapshot year : dataSnapshot.getChildren()) {
                            for (DataSnapshot category : year.getChildren()) {
                                if(cntCategory > 2) {
                                    if (category.getKey().toString() == selectedCategory){
                                        for (DataSnapshot course : category.getChildren()) {
                                            set.add(course.getKey().toString());
                                        }
                                    }
                                }else{
                                    set.add("");
                                }
                            }
                        }
                        tmp.clear();
                        tmp.addAll(set);
                        adapter4coursecode.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

                spinner1.setAdapter(adapter4coursecode);

            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });

        final Button button = (Button) rootView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(cntCategory > 2) {
                    Log.i("DIE", "SUBMIT");
                    Intent intent = new Intent(getActivity(), DisplayCommentActivity.class);
                    intent.putExtra("coursecategory", spinner.getSelectedItem().toString());
                    intent.putExtra("coursecode", spinner1.getSelectedItem().toString());
                    intent.putExtra("sem", spinner2.getSelectedItem().toString());
                    startActivity(intent);
                }
                else{
                    final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Comment not found");
                    alertDialog.setMessage("Please select the course!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

}
