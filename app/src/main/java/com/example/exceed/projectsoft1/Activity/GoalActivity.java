package com.example.exceed.projectsoft1.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.exceed.projectsoft1.Adapter.GoalAdapter;
import com.example.exceed.projectsoft1.Calendar.MyDate;
import com.example.exceed.projectsoft1.Model.Goal;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.R;

import java.text.ParseException;

public class GoalActivity extends AppCompatActivity {

    private FloatingActionButton add_goal;
    private GoalAdapter goalAdapter;
    private RecyclerView recList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        recList = (RecyclerView) findViewById(R.id.rec_goal);
        recList.setHasFixedSize(true);
        LinearLayoutManager Illm = new LinearLayoutManager(this);
        Illm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(Illm);
        goalAdapter = new GoalAdapter();
        recList.setHasFixedSize(true);
        recList.setItemAnimator(new DefaultItemAnimator());
        recList.setAdapter(goalAdapter);

        add_goal = (FloatingActionButton) findViewById(R.id.add_goal);
        add_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addgoal(v);
            }
        });
    }
    private void addgoal(View v){
        LayoutInflater layoutInflater = LayoutInflater.from(GoalActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.input_goal, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(GoalActivity.this);
        final EditText nameText = (EditText) promptView.findViewById(R.id.goal_name);
        final EditText priceText = (EditText) promptView.findViewById(R.id.goal_price);
        final EditText dateText = (EditText) promptView.findViewById(R.id.goal_date);
        final ImageButton addDate = (ImageButton) promptView.findViewById(R.id.add_date);
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
                if(isDouble(priceText.getText().toString())&&!dateText.getText().toString().equals("date")) {
                    if(!isExceed(priceText.getText().toString())) {
                        String n = nameText.getText().toString();
                        double p = Double.parseDouble(priceText.getText().toString());
                        String d = dateText.getText().toString();
                        Storage.getInstance().addGoal(new Goal(n, p, d));
                        recList.setAdapter(goalAdapter);
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
        LayoutInflater layoutInflater = LayoutInflater.from(GoalActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.input_dategoal, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(GoalActivity.this);
//        final NumberPicker hour = (NumberPicker) promptView.findViewById(R.id.hourPicker);
//        hour.setMaxValue(12);
//        hour.setMinValue(0);
//        final NumberPicker min = (NumberPicker) promptView.findViewById(R.id.minPicker);
//        min.setMaxValue(60);
//        min.setMinValue(0);
//        final NumberPicker sec = (NumberPicker) promptView.findViewById(R.id.secPicker);
//        sec.setMaxValue(60);
//        sec.setMinValue(0);
//        final Spinner m = (Spinner) promptView.findViewById(R.id.mPicker);
        final CalendarView cal = (CalendarView) promptView.findViewById(R.id.calendarView);
        final String[] res = {""};
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                String d = String.format("%2d",dayOfMonth)
                res[0] = "";
                    MyDate myDate = new MyDate(dayOfMonth,month,year);
                try {
                    res[0] += myDate.getReadableDay()+" ";
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                    res[0] += myDate.getDay()+" ";
                    res[0] += myDate.getReadableMonth2()+" ";
                    res[0] += myDate.getYear()+" ";
//
            }
        });
//        List<String> x = new ArrayList<>();
//        x.add("AM");
//        x.add("PM");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,x);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        m.setAdapter(dataAdapter);
        builder.setView(promptView);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                SimpleDateFormat s = new SimpleDateFormat("EEEE dd MMMM yyyy");
//                String am_pm = ":";
//                if(m.getSelectedItemPosition()==0) am_pm = "AM";
//                else am_pm = "PM";
//                res[0] += "\t#"+hour.getValue()+":"+min.getValue()+":"+sec.getValue()+" "+am_pm;
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

}

