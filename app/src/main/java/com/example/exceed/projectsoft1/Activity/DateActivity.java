package com.example.exceed.projectsoft1.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exceed.projectsoft1.Adapter.IncomeAdapter;
import com.example.exceed.projectsoft1.Model.Income;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.R;


public class DateActivity extends AppCompatActivity {

    private TextView showDate;
    private RelativeLayout layout;

    private RecyclerView IrecList;
    private IncomeAdapter incomeAdapter;
    private Button addIncomeButton;
    private TextView amountTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        showDate = (TextView) findViewById(R.id.showDate);
        layout = (RelativeLayout) findViewById(R.id.layout);
        final String s = getIntent().getStringExtra("date");
        setBackColor(s.split(" ")[0]);
        showDate.setText(s);

        IrecList = (RecyclerView) findViewById(R.id.income_view);
        IrecList.setHasFixedSize(true);
        LinearLayoutManager Illm = new LinearLayoutManager(this);
        Illm.setOrientation(LinearLayoutManager.VERTICAL);
        IrecList.setLayoutManager(Illm);

        incomeAdapter = new IncomeAdapter(s);
        IrecList.setHasFixedSize(true);
        IrecList.setItemAnimator(new DefaultItemAnimator());
        IrecList.setAdapter(incomeAdapter);
        incomeAdapter.SetOnItemClickListener(new IncomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(DateActivity.this, IncomeActivity.class);
                Log.i("dateZ", s);
                intent.putExtra("dateZ", s);
                Log.i("idid-temop",Storage.getInstance().getIncomeFromDate(s).get(position).getId()+"");
                intent.putExtra("income", Storage.getInstance().getIncomeFromDate(s).get(position));
                startActivity(intent);
                finish();
            }
        });
        addIncomeButton = (Button) findViewById(R.id.addIncome);
        addIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialogIncome();
            }
        });

        amountTotal = (TextView) findViewById(R.id.amount);

        Log.i("fucksuck",showDate.getText().toString());
        String t = showDate.getText().toString();
        String st = t.split(" ")[0]+"/"+t.split(" ")[1]+"/"+t.split(" ")[2]+"/"+t.split(" ")[3];
        Log.i("fucksuck",st);
        amountTotal.setText("Amount : "+ Storage.getInstance().getTotal(t)+"");

//        Double t = Storage.getInstance().getTotalAmount(showDate.getText().toString());
    }
        private void showInputDialogIncome() {
        LayoutInflater layoutInflater = LayoutInflater.from(DateActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.input_income, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(DateActivity.this);
        final EditText nameText = (EditText) promptView.findViewById(R.id.input_income_name);
        final EditText priceText = (EditText) promptView.findViewById(R.id.input_income_price);
        builder.setView(promptView);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String temp = priceText.getText().toString();
                if(isDouble(temp)){
                    if(isExceed(temp)){
                        Toast.makeText(getApplicationContext(), "Please input less than 7 digits", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Income i = new Income(nameText.getText().toString(),"", Double.parseDouble(temp));
                        Log.i("idid",i.getId()+"");
                        Storage.getInstance().addDateIncome(showDate.getText().toString(),i);
                        Double t = Storage.getInstance().getTotal(showDate.getText().toString());
                        amountTotal.setText("Amount : "+Storage.getInstance().getTotal(showDate.getText().toString()));
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please input number", Toast.LENGTH_SHORT).show();
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

    private void setBackColor(String day){
        switch (day){
            case "Monday":
                layout.setBackgroundColor(getResources().getColor(R.color.colorMonday));
                break;
            case "Tueday":
                layout.setBackgroundColor(getResources().getColor(R.color.colorTueday));
                break;
            case "Wednesday":
                layout.setBackgroundColor(getResources().getColor(R.color.colorWednesday));
                break;
            case "Thursday":
                layout.setBackgroundColor(getResources().getColor(R.color.colorThursday));
                break;
            case "Friday":
                layout.setBackgroundColor(getResources().getColor(R.color.colorFriday));
                break;
            case "Saturday":
                layout.setBackgroundColor(getResources().getColor(R.color.colorSaturnday));
                break;
            case "Sunday":
                layout.setBackgroundColor(getResources().getColor(R.color.colorSunday));
                break;
        }
    }
}
