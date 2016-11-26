package com.example.hoyhung.bumate;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import java.lang.*;

/**
 * Created by Karsten on 2016/11/18.
 */

public class course {
   // public ArrayList<String> comment;
    public String name;

    public course() {

    }

    public course(String name) {
        this.name = name;
        //this.comment = comment;
    }

    public String getName(){
        return name;
    }

//    public ArrayList<String> getComment(){
//        return comment;
//    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        //result.put("comment", comment);
        return result;
    }

    public String toString(){
        return getName();
    }
}

