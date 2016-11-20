package com.example.hoyhung.bumate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

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

public class DisplayCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String coursecategory = intent.getStringExtra("coursecategory");
        String coursecode = intent.getStringExtra("coursecode");
        String sem = intent.getStringExtra("sem");

        setTitle(coursecode+" Comment");

        Log.i("coursecategory", coursecategory);
        Log.i("coursecode", coursecode);
        Log.i("sem", sem);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        final List<Comment> comments = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = database.getReference().child("comment");
        DatabaseReference getRef = rootRef.child(sem).child(coursecategory).child(coursecode);

        ValueEventListener valueEventListener = getRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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
                    comments.add(new Comment(tmpteacher, tmpgrade, tmpworkload, tmpcomment));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        RVAdapter adapter = new RVAdapter(comments);
        rv.setAdapter(adapter);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
