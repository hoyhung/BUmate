package com.example.hoyhung.bumate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charlatte on 2016/11/26.
 */
public class valueTmp {
    private static valueTmp ourInstance = new valueTmp();
    List<Comment> comments = new ArrayList<>();
    ArrayList<String> semList = new ArrayList<>();
    ArrayList<String> categoryList = new ArrayList<>();
    ArrayList<String> codeList = new ArrayList<>();
    public static valueTmp getInstance() {
        return ourInstance;
    }

    private valueTmp() {
    }

    public void setSemList(ArrayList<String> semList) {
        this.semList.clear();
        this.semList.addAll(semList);
    }

    public void setCategoryList(ArrayList<String> categoryList) {
        this.categoryList.clear();
        this.categoryList.addAll(categoryList);
    }

    public void setCodeList(ArrayList<String> codeList) {
        this.codeList.clear();
        this.codeList.addAll(codeList);
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void dataReset(){
        comments.clear();
        codeList.clear();
        categoryList.clear();
        semList.clear();
    }
}
