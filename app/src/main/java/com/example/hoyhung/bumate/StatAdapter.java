package com.example.hoyhung.bumate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Charlatte on 2016/11/26.
 */

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.StatViewHolder> {

    List<Stat> stats;

    public StatAdapter(List<Stat> stats){
        this.stats = stats;
    }


    public static class StatViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView code;
        TextView numberOfComment;
        //TextView comment;

        StatViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv2);
            code = (TextView) itemView.findViewById(R.id.course_code);
            numberOfComment = (TextView) itemView.findViewById(R.id.number_of_comment);
            //comment = (TextView) itemView.findViewById(R.id.comment);
        }
    }

    @Override
    public int getItemCount() {
        return stats.size();
    }

    @Override
    public StatAdapter.StatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.stat_card_comment, viewGroup, false);
        StatAdapter.StatViewHolder pvh = new StatViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(StatViewHolder statViewHolder, int i) {
        statViewHolder.code.setText(stats.get(i).courseCode);
        statViewHolder.numberOfComment.setText(stats.get(i).commentCnt + "");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
