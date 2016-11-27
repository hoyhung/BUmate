package com.example.hoyhung.bumate;

/**
 * Created by Charlatte on 2016/11/27.
 */

public class Grade implements Comparable<Grade> {
public int commentCnt = 0;
public String courseCode;
public double totalScore = 0;
    public double averageScore = 0;

public Grade(){

        }

public Grade(String courseCode){
        //this.courseTitle = courseTitle;
        this.courseCode = courseCode;
        }

public void addScore(String grade){
    double tmp = 0;
    switch(grade){
        case "A":
            tmp = 4;
            break;
        case "A-":
            tmp = 3.67;
            break;
        case "B+":
            tmp = 3.33;
            break;
        case "B":
            tmp = 3;
            break;
        case "B-":
            tmp = 2.67;
            break;
        case "C+":
            tmp = 2.33;
            break;
        case "C":
            tmp = 2;
            break;
        case "C-":
            tmp = 1.67;
            break;
        case "D":
            tmp = 1;
            break;
        default:
            break;
    }
    totalScore+=tmp;
    averageScore = totalScore / commentCnt;
}

    public double getAverageScore() {
        return averageScore;
    }

    @Override
public int compareTo(Grade s) {
        if (averageScore > s.averageScore) {
        return -1;
        }
        else if (averageScore <  s.averageScore) {
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
