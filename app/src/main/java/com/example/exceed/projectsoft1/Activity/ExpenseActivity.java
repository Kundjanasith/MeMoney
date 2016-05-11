package com.example.exceed.projectsoft1.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.exceed.projectsoft1.Adapter.ExpenseGetTagAdapter;
import com.example.exceed.projectsoft1.Adapter.ExpenseRemainTagAdapter;
import com.example.exceed.projectsoft1.Model.Expense;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.R;

public class ExpenseActivity extends AppCompatActivity {

    private String date;
    private Expense income;
    private TextView tvDate;
    private EditText tvName;
    private EditText tvPrice;
    private EditText edDesc;
    private TextView createdDate;
    private TextView createdTime;
    private ImageButton delete_button;
    private FloatingActionButton save_button;
    private ImageButton add_tag;
    private RecyclerView rec_list;
    private ExpenseGetTagAdapter getAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        date = getIntent().getStringExtra("dateZ");
        income = (Expense) getIntent().getSerializableExtra("income");
        tvDate = (TextView) findViewById(R.id.income_date);
        tvName = (EditText) findViewById(R.id.income_name);
        tvPrice = (EditText) findViewById(R.id.income_price);
        edDesc = (EditText) findViewById(R.id.income_description);
        createdDate = (TextView) findViewById(R.id.created_income_date);
        createdTime = (TextView) findViewById(R.id.created_income_time);
        delete_button = (ImageButton) findViewById(R.id.delete_income_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().deleteDateExpense(date, income);
                DateActivity.ErecList.setAdapter(DateActivity.expenseAdapter);
                if(SearchActivity.ErecList!=null){
                    SearchActivity.ErecList.setAdapter(SearchActivity.expenseAdapter);
                    SearchActivity.refresh();
                }
                onBackPressed();
                finish();
            }
        });
        save_button = (FloatingActionButton) findViewById(R.id.save_income);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().updateExpense(date, income, tvName.getText().toString(), edDesc.getText().toString(), Double.parseDouble(tvPrice.getText().toString()));
                DateActivity.ErecList.setAdapter(DateActivity.expenseAdapter);
                if(SearchActivity.ErecList!=null){
                    SearchActivity.ErecList.setAdapter(SearchActivity.expenseAdapter);
                    SearchActivity.refresh();
                }
                onBackPressed();
                finish();
            }
        });

        tvDate.setText(date);
        tvName.setText(income.getName());
        tvPrice.setText(income.getPrice() + "");
        edDesc.setText(income.getDesc());
        String x = income.getCreatedDate().split("-")[0];
        createdDate.setText(x);
        createdTime.setText(income.getCreatedDate().split("-")[1]);
        add_tag = (ImageButton) findViewById(R.id.add_tag);
        add_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTag(v);
            }
        });

        rec_list = (RecyclerView) findViewById(R.id.rec_income_tag);
        rec_list.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rec_list.setLayoutManager(llm);

        getAdapter = new ExpenseGetTagAdapter(date,income);
        rec_list.setHasFixedSize(true);
        rec_list.setItemAnimator(new DefaultItemAnimator());
        rec_list.setAdapter(getAdapter);


    }

    private RecyclerView IrecList;
    private ExpenseRemainTagAdapter adapter;
    private void addTag(View v){
        LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
        final View promptView = layoutInflater.inflate(R.layout.show_tag, null);
        promptView.setBackgroundColor(getResources().getColor(R.color.headExpense));
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        IrecList = (RecyclerView) promptView.findViewById(R.id.rec_list);
        IrecList.setHasFixedSize(true);
        LinearLayoutManager Illm = new LinearLayoutManager(this);
        Illm.setOrientation(LinearLayoutManager.VERTICAL);
        IrecList.setLayoutManager(Illm);
        adapter = new ExpenseRemainTagAdapter(date,income,rec_list,getAdapter);
        IrecList.setHasFixedSize(true);
        IrecList.setItemAnimator(new DefaultItemAnimator());
        IrecList.setAdapter(adapter);
        builder.setView(promptView);
        builder.show();
        rec_list.setAdapter(getAdapter);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(ExpenseActivity.this, DateActivity.class);
//        String temp = date;
//        intent.putExtra("date", temp);
//        startActivity(intent);
//    }
}
