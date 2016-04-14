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

import com.example.exceed.projectsoft1.Adapter.ExpenseAdapter;
import com.example.exceed.projectsoft1.Adapter.IncomeAdapter;
import com.example.exceed.projectsoft1.Model.Expense;
import com.example.exceed.projectsoft1.Model.Income;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.R;


public class DateActivity extends AppCompatActivity {

    private TextView showDate;
    private RelativeLayout layout;

    private RecyclerView IrecList;
    private IncomeAdapter incomeAdapter;
    private Button addIncomeButton;

    private RecyclerView ErecList;
    private ExpenseAdapter expenseAdapter;
    private Button addExpenseButton;

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
                Intent intent = new Intent(DateActivity.this, IncomeActivity.class);;
                intent.putExtra("dateZ", s);
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

        ErecList = (RecyclerView) findViewById(R.id.expense_view);
        ErecList.setHasFixedSize(true);
        LinearLayoutManager Ellm = new LinearLayoutManager(this);
        Ellm.setOrientation(LinearLayoutManager.VERTICAL);
        ErecList.setLayoutManager(Ellm);
        expenseAdapter = new ExpenseAdapter(s);
        ErecList.setHasFixedSize(true);
        ErecList.setItemAnimator(new DefaultItemAnimator());
        ErecList.setAdapter(expenseAdapter);
        expenseAdapter.SetOnItemClickListener(new ExpenseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(DateActivity.this, ExpenseActivity.class);;
                intent.putExtra("dateZ", s);
                intent.putExtra("income", Storage.getInstance().getExpenseFromDate(s).get(position));
                startActivity(intent);
                finish();
            }
        });
        addExpenseButton = (Button) findViewById(R.id.addExpense);
        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialogExpense();
            }
        });

        amountTotal = (TextView) findViewById(R.id.amount);
        String t = showDate.getText().toString();
        amountTotal.setText("Amount : "+ Storage.getInstance().getTotal(t)+"");
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
    private void showInputDialogExpense() {
        LayoutInflater layoutInflater = LayoutInflater.from(DateActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.input_expense, null);
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
                        Expense i = new Expense(nameText.getText().toString(),"", Double.parseDouble(temp));
                        Log.i("idid",i.getId()+"");
                        Storage.getInstance().addDateExpense(showDate.getText().toString(),i);
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
