package com.example.hoyhung.bumate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Charlatte on 2016/11/27.
 */

public class CourseInfoGrade extends Fragment {

    public CourseInfoGrade() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_course_info_grade, container, false);
        final Button button = (Button) rootView.findViewById(R.id.change_view_button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                CourseInfoStat stat = new CourseInfoStat();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, stat).commit();
            }
        });

        valueTmp value = valueTmp.getInstance();
        //Log.v("Number of comments", value.comments.size() + "");

        RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.rv3);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        List<Grade> grades = new ArrayList<>();
        List<Comment> c = value.comments;
        int i = 0;
        do{
            if(i == 0) {
                Grade tmpGrade = new Grade(c.get(i).code);
                tmpGrade.commentCnt++;
                tmpGrade.addScore(c.get(i).grade);
                grades.add(tmpGrade);
                i++;
            }
            else{
                boolean tmp = false;
                for(int k = 0 ; k < grades.size() ; k++ ){
                    if(c.get(i).code.equalsIgnoreCase(grades.get(k).courseCode)) {
                        grades.get(k).commentCnt++;
                        grades.get(k).addScore(c.get(i).grade);
                        tmp = true;
                    }
                }
                if(!tmp){
                    Grade tmpGrade = new Grade(c.get(i).code);
                    tmpGrade.commentCnt++;
                    tmpGrade.addScore(c.get(i).grade);
                    grades.add(tmpGrade);
                    Log.v("Test", grades.size() + "");
                }
                i++;
            }
        }while (i < c.size());
        Collections.sort(grades);
       // List<Stat> tmpList = new ArrayList<>();

        Log.v("Test", grades.get(1).commentCnt + "");
        GradeAdapter adapter = new GradeAdapter(grades);
        rv.setAdapter(adapter);

        //myRef.setValue("Course Stat");
        // Inflate the layout for this fragment
        return rootView;
    }


}