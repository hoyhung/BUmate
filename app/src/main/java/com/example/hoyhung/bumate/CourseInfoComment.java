package com.example.hoyhung.bumate;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hoyhung on 9/11/2016.
 */

public class CourseInfoComment extends Fragment {

    public CourseInfoComment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_course_info_comment, container, false);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.coursecategorycm);
        final Spinner spinner1 = (Spinner) rootView.findViewById(R.id.coursecodecm);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                // Object item = parentView.getItemAtPosition(position);

                String[] mTestArray;
                ArrayAdapter<String> adapter;

                Log.i("POSITIONCM", ""+position);
                switch(position){
                    case 0:
                        mTestArray = getResources().getStringArray(R.array.universityEng);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 1:
                        mTestArray = getResources().getStringArray(R.array.universityChi);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 2:
                        mTestArray = getResources().getStringArray(R.array.PS);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 3:
                        mTestArray = getResources().getStringArray(R.array.IT);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 4:
                        mTestArray = getResources().getStringArray(R.array.NU);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 5:
                        mTestArray = getResources().getStringArray(R.array.PE);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 6:
                        mTestArray = getResources().getStringArray(R.array.HC);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 7:
                        mTestArray = getResources().getStringArray(R.array.VM);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 8:
                        mTestArray = getResources().getStringArray(R.array.GDAR);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 9:
                        mTestArray = getResources().getStringArray(R.array.GDBU);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 10:
                        mTestArray = getResources().getStringArray(R.array.GDCV);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 11:
                        mTestArray = getResources().getStringArray(R.array.GDSC);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 12:
                        mTestArray = getResources().getStringArray(R.array.GDSS);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                    case 13:
                        mTestArray = getResources().getStringArray(R.array.Interdisciplinary);
                        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),
                                R.layout.mytextview , mTestArray);
                        spinner1.setAdapter(adapter);
                        break;
                }



            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });
        // Inflate the layout for this fragment
        return rootView;
    }

}
