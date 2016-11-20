package com.example.hoyhung.bumate;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xdeveloper on 23/9/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CommentViewHolder> {

    List<Comment> comments;

    RVAdapter(List<Comment> comments){
       this.comments = comments;
   }


    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView teacher;
        TextView workload;
        TextView grade;
        TextView comment;

        CommentViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            teacher = (TextView) itemView.findViewById(R.id.teacher);
            workload = (TextView) itemView.findViewById(R.id.workload);
            grade = (TextView) itemView.findViewById(R.id.grade);
            comment = (TextView) itemView.findViewById(R.id.comment);
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.comment_card, viewGroup, false);
        CommentViewHolder pvh = new CommentViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder commentViewHolder, int i) {
        commentViewHolder.teacher.setText(comments.get(i).teacher);
        commentViewHolder.workload.setText(comments.get(i).workload);
        commentViewHolder.grade.setText(comments.get(i).grade);
        commentViewHolder.comment.setText("" + comments.get(i).comment);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
