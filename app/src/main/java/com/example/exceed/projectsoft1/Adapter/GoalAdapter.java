package com.example.exceed.projectsoft1.Adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.exceed.projectsoft1.Model.Goal;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.R;

import java.io.Serializable;

/**
 * Created by exceed on 4/15/16 AD.
 */
public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.ViewHolder> implements Serializable {
    private ViewHolder viewHolder;
    OnItemClickListener mItemClickListener;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_goal, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final Goal goal = Storage.getInstance().getGoals().get(i);
        Log.i("goal", goal.getName());
        viewHolder.goal_name.setText(goal.getName());
        viewHolder.goal_price.setText(goal.getPrice()+"");
        viewHolder.goal_date.setText(goal.getDueDate());
        viewHolder.goal_status.setText(goal.getStatus());
//        viewHolder.progressBar
        final Handler h = new Handler();
        final int delay = 1000;
        h.postDelayed(new Runnable() {
            public void run() {
                h.postDelayed(this, delay);
                Storage.getInstance().goalStatus(goal);
                viewHolder.goal_status.setText(goal.getStatus());
            }
        }, delay);
    }

    @Override
    public int getItemCount() {
        return Storage.getInstance().getGoals().size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView goal_name;
        private TextView goal_price;
        private TextView goal_date;
        private TextView goal_status;
        private ImageButton edit_goal;
        private ImageButton delete_goal;
        private ProgressBar progressBar;
        public ViewHolder(View v) {
            super(v);
            goal_name = (TextView) v.findViewById(R.id.goal_name);
            goal_price = (TextView) v.findViewById(R.id.goal_price);
            goal_date = (TextView) v.findViewById(R.id.goal_date);
            goal_status = (TextView) v.findViewById(R.id.goal_status);
            edit_goal = (ImageButton) v.findViewById(R.id.edit_goal);
            delete_goal = (ImageButton) v.findViewById(R.id.delete_goal);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }

    }
}

