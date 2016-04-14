package com.example.exceed.projectsoft1.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.exceed.projectsoft1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GoalActivity extends AppCompatActivity {

    private FloatingActionButton add_goal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        add_goal = (FloatingActionButton) findViewById(R.id.add_goal);
        add_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addgoal(v);
            }
        });
    }
    private void addgoal(View v){
        final String[] date = {""};
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
//                String temp = priceText.getText().toString();
//                if(isDouble(temp)){
//                    if(isExceed(temp)){
//                        Toast.makeText(getApplicationContext(), "Please input less than 7 digits", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        Income i = new Income(nameText.getText().toString(),"", Double.parseDouble(temp));
//                        Log.i("idid", i.getId() + "");
//                        Storage.getInstance().addDateIncome(showDate.getText().toString(),i);
//                        Double t = Storage.getInstance().getTotal(showDate.getText().toString());
//                        amountTotal.setText("Amount : "+Storage.getInstance().getTotal(showDate.getText().toString()));
//                    }
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Please input number", Toast.LENGTH_SHORT).show();
//                }
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
        final NumberPicker hour = (NumberPicker) promptView.findViewById(R.id.hourPicker);
        hour.setMaxValue(12);
        hour.setMinValue(0);
        final NumberPicker min = (NumberPicker) promptView.findViewById(R.id.minPicker);
        min.setMaxValue(60);
        min.setMinValue(0);
        final NumberPicker sec = (NumberPicker) promptView.findViewById(R.id.secPicker);
        sec.setMaxValue(60);
        sec.setMinValue(0);
        final Spinner m = (Spinner) promptView.findViewById(R.id.mPicker);
        final CalendarView cal = (CalendarView) promptView.findViewById(R.id.calendarView);
        List<String> x = new ArrayList<>();
        x.add("AM");
        x.add("PM");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,x);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m.setAdapter(dataAdapter);
        builder.setView(promptView);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SimpleDateFormat s = new SimpleDateFormat("EEEE dd MMMM yyyy");
                String res = "";
                res = s.format(cal.getDate());
                String am_pm = ":";
                if(m.getSelectedItemPosition()==0) am_pm = "AM";
                else am_pm = "PM";
                res += "\t"+hour.getValue()+":"+min.getValue()+":"+sec.getValue()+" "+am_pm;
                tv.setText(res);
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

}

