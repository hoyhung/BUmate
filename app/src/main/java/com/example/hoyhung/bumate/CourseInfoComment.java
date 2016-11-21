package com.example.hoyhung.bumate;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static android.content.ContentValues.TAG;

/**
 * Created by hoyhung on 9/11/2016.
 */

public class CourseInfoComment extends Fragment {


    public int cnt = 0;

    public CourseInfoComment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = database.getReference().child("semester");
        final View rootView = inflater.inflate(R.layout.fragment_course_info_comment, container, false);

        final ArrayList<String> tmp = new ArrayList<>();
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(), R.layout.mytextview , tmp );

        final ArrayList<String> tmp1 = new ArrayList<>();
        final ArrayAdapter<String> adapter4sem;
        adapter4sem = new ArrayAdapter<String>( getActivity().getApplicationContext(), R.layout.mytextview , tmp1 );

        final ArrayList<String> tmp2 = new ArrayList<>();
        final ArrayAdapter<String> adapter4coursecode;
        adapter4coursecode = new ArrayAdapter<String>( getActivity().getApplicationContext(), R.layout.mytextview , tmp2 );

        final Spinner spinner = (Spinner) rootView.findViewById(R.id.coursecategorycm);
        final Spinner spinner1 = (Spinner) rootView.findViewById(R.id.coursecodecm);
        final Spinner spinner2 = (Spinner) rootView.findViewById(R.id.sem);

        setupUI(rootView.findViewById(R.id.parent));

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

        spinner2.setAdapter(adapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {

                final String selectedSem = spinner2.getSelectedItem().toString();

                Log.i("selectedSem", ""+selectedSem);
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
                        tmp1.clear();
                        tmp1.addAll(set);
                        adapter4sem.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });


            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });


        spinner.setAdapter(adapter4sem);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {

                cnt++;

                final String selectedCategory = spinner.getSelectedItem().toString();

                ValueEventListener valueEventListener = rootRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Set<String> set = new HashSet<String>();

                        for (DataSnapshot year : dataSnapshot.getChildren()) {
                            for (DataSnapshot category : year.getChildren()) {
                                if (category.getKey().toString() == selectedCategory ){
                                    if(cnt > 1) {
                                        for (DataSnapshot course : category.getChildren()) {
                                            for (DataSnapshot coursename : course.getChildren()) {
                                                set.add(coursename.getValue(String.class));
                                            }
                                        }
                                    }else{
                                        set.add("");
                                    }
                                }
                            }
                        }
                        tmp2.clear();
                        tmp2.addAll(set);
                        adapter4coursecode.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });


        spinner1.setAdapter(adapter4coursecode);

        //get data from the form


        final Button button = (Button) rootView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText teacher = (EditText) rootView.findViewById(R.id.teacher);
                final EditText comment = (EditText) rootView.findViewById(R.id.comment);
                if(cnt > 1 && !teacher.getText().toString().matches("") && !comment.getText().toString().matches("")) {
                    // Perform action on click
                    Log.i("DIE", "SUBMIT");

                    String selectedCategory = spinner.getSelectedItem().toString();
                    String selectedCourse = spinner1.getSelectedItem().toString();
                    String selectedSem = spinner2.getSelectedItem().toString();


                    Spinner spinner3 = (Spinner) rootView.findViewById(R.id.grade);
                    String selectedGrade = spinner3.getSelectedItem().toString();
                    Spinner spinner4 = (Spinner) rootView.findViewById(R.id.workload);
                    String selectedWorkload = spinner4.getSelectedItem().toString();


                    Map<String, Object> map = new HashMap<String, Object>();
                    final DatabaseReference rootRef = database.getReference().child("comment");
                    DatabaseReference getRef = rootRef.child(selectedSem).child(selectedCategory).child(selectedCourse);

                    String key = getRef.push().getKey();
                    //map.put("name", selectedCourse);
                    getRef.updateChildren(map);

                    Comment newcomment = new Comment(teacher.getText().toString(), selectedGrade, selectedWorkload, comment.getText().toString());
                    getRef.child(key).setValue(newcomment);

                    final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Success!!");
                    alertDialog.setMessage("Your comment is submitted.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }else{
                    final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Fail!!");
                    alertDialog.setMessage("Please fill all form fields.");
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


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

}