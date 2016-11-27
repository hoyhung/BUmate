package com.example.hoyhung.bumate;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Charlatte on 2016/11/27.
 */

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.CommentViewHolder> {

    List<Grade> grades;

    GradeAdapter(List<Grade> grades){
        this.grades = grades;
    }


    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView code;
        TextView grade;

        CommentViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv3);
            code = (TextView) itemView.findViewById(R.id.course_code_grade);
            grade = (TextView) itemView.findViewById(R.id.average_score);
        }
    }

    @Override
    public int getItemCount() {
        return grades.size();
    }

    @Override
    public GradeAdapter.CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.grade_stat_comment, viewGroup, false);
        GradeAdapter.CommentViewHolder pvh = new GradeAdapter.CommentViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(GradeAdapter.CommentViewHolder commentViewHolder, int i) {
        commentViewHolder.code.setText(grades.get(i).courseCode);
        commentViewHolder.grade.setText((grades.get(i).getAverageScore())+ "");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}