package com.example.hoyhung.bumate;

/**
 * Created by Charlatte on 2016/11/26.
 */

public class Stat implements Comparable<Stat> {
    public int commentCnt = 0;
    public String courseTitle;
    public String courseCode;
    public String courseGrade;

    public Stat(){

    }

    public Stat(String courseCode){
        //this.courseTitle = courseTitle;
        this.courseCode = courseCode;
    }


    @Override
    public int compareTo(Stat s) {
        if (commentCnt > s.commentCnt) {
            return -1;
        }
        else if (commentCnt <  s.commentCnt) {
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString(){
        return courseCode;
    }
}
