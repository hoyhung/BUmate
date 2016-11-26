package com.example.hoyhung.bumate;

import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.content.ContentValues.TAG;

/**
 * Created by Charlatte on 2016/11/26.
 */

public class getAllCommentFromDB {
    FirebaseDatabase database;
    final DatabaseReference rootRef;
    List<Comment> comments = new ArrayList<>();
    ArrayList<String> semList = new ArrayList<>();
    ArrayList<String> categoryList = new ArrayList<>();
    ArrayList<String> codeList = new ArrayList<>();

    public getAllCommentFromDB(){
        database = FirebaseDatabase.getInstance();
        rootRef = database.getReference().child("comment");
    }

    public void startRunning(){
        ValueEventListener valueEventListener = rootRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                valueTmp value = valueTmp.getInstance();
                value.dataReset();

                Set<String> semSet = new HashSet<String>();
                semSet.clear();
                for (DataSnapshot sem : dataSnapshot.getChildren()) {
                    semSet.add(sem.getKey().toString());
                }
                value.semList.clear();
                semList.clear();
                semList.addAll(semSet);
                value.setSemList(semList);

                Set<String> set = new HashSet<String>();
                set.clear();
                for(String targetSem : semList) {
                    for (DataSnapshot sem : dataSnapshot.getChildren()) {
                        if (sem.getKey().toString() == targetSem) {
                            for (DataSnapshot category : sem.getChildren()) {
                                set.add(category.getKey().toString());
                            }
                        }
                    }
                }
                value.categoryList.clear();
                categoryList.clear();
                categoryList.addAll(set);
                value.setCategoryList(categoryList);

                Set<String> codeSet = new HashSet<String>();
                codeSet.clear();
                for(String targetCategory : categoryList) {
                    for (DataSnapshot year : dataSnapshot.getChildren()) {
                        for (DataSnapshot category : year.getChildren()) {
                            if (category.getKey().toString() == targetCategory) {
                                for (DataSnapshot course : category.getChildren()) {
                                    codeSet.add(course.getKey().toString());
                                }
                            }
                        }
                    }
                }
                value.codeList.clear();
                codeList.clear();
                codeList.addAll(codeSet);
                value.setCodeList(codeList);

                value.comments.clear();
                for(String targetSem : semList){
                    for(String targetCategory : categoryList){
                        for(String targetCode : codeList){
                            getComments(targetSem, targetCategory, targetCode);
                        }
                    }
                }
                Log.v("Size of code set", codeList.size() + "");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        Log.v("Size of code set", codeList.size() + "");
    }

    public void getComments(String targetSem, String targetCategory, final String targetCourseCode){
        //final List<Comment> comments = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = database.getReference().child("comment");
        DatabaseReference getRef = rootRef.child(targetSem).child(targetCategory).child(targetCourseCode);

        ValueEventListener valueEventListener = getRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                valueTmp value = valueTmp.getInstance();
                for (DataSnapshot sem : dataSnapshot.getChildren()) {
                    Map<String, Object> td = (HashMap<String,Object>) sem.getValue();

                    Set<String> keys = td.keySet();  //get all keys
                    String tmpteacher = "";
                    String tmpgrade = "";
                    String tmpworkload = "";
                    String tmpcomment = "";
                    for(String i: keys)
                    {
                        if(i.equals("teacher")){tmpteacher = td.get(i).toString();}
                        if(i.equals("grade")){tmpgrade = td.get(i).toString();}
                        if(i.equals("workload")){tmpworkload = td.get(i).toString();}
                        if(i.equals("comment")){tmpcomment = td.get(i).toString();}
                    }
                    Comment tmpComment = new Comment(tmpteacher, tmpgrade, tmpworkload, tmpcomment);
                    tmpComment.code = targetCourseCode;
                    value.comments.add(tmpComment);
                    Log.v("Check: ", "Comment added");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

       // return comments;
    }
}
