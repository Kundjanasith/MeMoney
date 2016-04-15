package com.example.exceed.projectsoft1.Adapter;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.exceed.projectsoft1.Calendar.MyDate;
import com.example.exceed.projectsoft1.Model.Goal;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.R;

import java.io.Serializable;
import java.text.ParseException;

/**
 * Created by exceed on 4/15/16 AD.
 */
public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.ViewHolder> implements Serializable {
    private ViewHolder viewHolder;
    OnItemClickListener mItemClickListener;
    private RecyclerView recyclerView;

    public GoalAdapter(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }


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
        viewHolder.progressBar.setMax((int) goal.getPrice());
        viewHolder.progressBar.setProgress((int) Storage.getInstance().getMoneyTo(goal));
        double earn = Storage.getInstance().getMoneyTo(goal);
        Log.i("earn",earn+"");
        final Handler h = new Handler();
        final int delay = 1000;
        Storage.getInstance().goalStatus(goal);
        viewHolder.goal_status.setText(goal.getStatus());
//        h.postDelayed(new Runnable() {
//            public void run() {
//                h.postDelayed(this, delay);
//                Storage.getInstance().goalStatus(goal);
//                viewHolder.goal_status.setText(goal.getStatus());
//            }
//        }, delay);
        viewHolder.edit_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGoal(goal,v);
            }
        });
        viewHolder.delete_goal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                deleteGoal(goal,v);
            }
        });
    }

    private void deleteGoal(Goal goal,View v){
        Storage.getInstance().deleteGoal(goal);
        recyclerView.setAdapter(GoalAdapter.this);
    }

    private void editGoal(final Goal goal,View v){
        LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
        final View promptView = layoutInflater.inflate(R.layout.input_goal, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        final EditText nameText = (EditText) promptView.findViewById(R.id.goal_name);
        final EditText priceText = (EditText) promptView.findViewById(R.id.goal_price);
        final EditText dateText = (EditText) promptView.findViewById(R.id.goal_date);
        final ImageButton addDate = (ImageButton) promptView.findViewById(R.id.add_date);
        nameText.setText(goal.getName());
        priceText.setText(goal.getPrice()+"");
        dateText.setText(goal.getDueDate());
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDate(dateText,v);
            }
        });
        builder.setView(promptView);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isDouble(priceText.getText().toString()) && !dateText.getText().toString().equals("date")) {
                    if (!isExceed(priceText.getText().toString())) {
                        String n = nameText.getText().toString();
                        double p = Double.parseDouble(priceText.getText().toString());
                        String d = dateText.getText().toString();
                        Storage.getInstance().updateGoal(goal,n,p,d);
                        recyclerView.setAdapter(GoalAdapter.this);
                    }
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void addDate(final EditText tv,View v){
        LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
        final View promptView = layoutInflater.inflate(R.layout.input_dategoal, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        final CalendarView cal = (CalendarView) promptView.findViewById(R.id.calendarView);
        final String[] res = {""};
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                res[0] = "";
                MyDate myDate = new MyDate(dayOfMonth, month, year);
                try {
                    res[0] += myDate.getReadableDay() + " ";
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                res[0] += myDate.getDay() + " ";
                res[0] += myDate.getReadableMonth2() + " ";
                res[0] += myDate.getYear() + " ";
            }
        });
        builder.setView(promptView);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv.setText(res[0]);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
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

    private boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
    private boolean isExceed(String s){
        return Double.parseDouble(s)>=9999999;
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

