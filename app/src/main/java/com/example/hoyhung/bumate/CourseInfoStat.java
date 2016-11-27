package com.example.hoyhung.bumate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by hoyhung on 9/11/2016.
 */

public class CourseInfoStat extends Fragment {

    public CourseInfoStat() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course_stat, container, false);
        final Button button = (Button) rootView.findViewById(R.id.change_view_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                CourseInfoGrade grade = new CourseInfoGrade();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, grade).commit();
            }
        });
        valueTmp value = valueTmp.getInstance();
        Log.v("Number of comments", value.comments.size() + "");

        RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.rv2);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        List<Stat> stats = new ArrayList<>();
        List<Comment> c = value.comments;
        int i = 0;
        do{
            if(i == 0) {
                Stat tmpStat = new Stat(c.get(i).code);
                tmpStat.commentCnt++;
                stats.add(tmpStat);
                i++;
            }
            else{
                boolean tmp = false;
                for(int k = 0 ; k < stats.size() ; k++ ){
                    if(c.get(i).code.equalsIgnoreCase(stats.get(k).courseCode)) {
                        stats.get(k).commentCnt++;
                        tmp = true;
                    }
                }
                if(!tmp){
                    Stat tmpStat = new Stat(c.get(i).code);
                    tmpStat.commentCnt++;
                    stats.add(tmpStat);
                    Log.v("Test", stats.size() + "");
                }
                i++;
            }
        }while (i < c.size());
        Collections.sort(stats);
        List<Stat> tmpList = new ArrayList<>();

        Log.v("Test", stats.get(1).commentCnt + "");
        StatAdapter adapter = new StatAdapter(stats);
        rv.setAdapter(adapter);

        //myRef.setValue("Course Stat");
        // Inflate the layout for this fragment
        return rootView;
    }
}
