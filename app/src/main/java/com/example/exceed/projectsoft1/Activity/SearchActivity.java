package com.example.exceed.projectsoft1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.exceed.projectsoft1.Adapter.SearchAdapter;
import com.example.exceed.projectsoft1.Model.Expense;
import com.example.exceed.projectsoft1.Model.Income;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText searchET;
    public static SearchAdapter incomeAdapter = null;
    public static SearchAdapter expenseAdapter = null;

    private static List<String> incomeString = new ArrayList<>();
    private static List<String> expenseString = new ArrayList<>();

    private static List<Income> incomeList = new ArrayList<>();
    private static List<Expense> expenseList = new ArrayList<>();

    public static RecyclerView IrecList = null;
    public static RecyclerView  ErecList = null;

    private static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        text = "";
        incomeList = new ArrayList<>();
        expenseList = new ArrayList<>();
        incomeString = new ArrayList<>();
        expenseString = new ArrayList<>();
        this.init();
    }

    private void init() {
        IrecList = (RecyclerView) findViewById(R.id.search_income_list);
        IrecList.setHasFixedSize(true);
        LinearLayoutManager Illm = new LinearLayoutManager(this);
        Illm.setOrientation(LinearLayoutManager.VERTICAL);
        IrecList.setLayoutManager(Illm);

        ErecList = (RecyclerView) findViewById(R.id.search_expense_list);
        ErecList.setHasFixedSize(true);
        LinearLayoutManager Ellm = new LinearLayoutManager(this);
        Ellm.setOrientation(LinearLayoutManager.VERTICAL);
        ErecList.setLayoutManager(Ellm);

        incomeAdapter = new SearchAdapter(this,"income");
        IrecList.setHasFixedSize(true);
        IrecList.setItemAnimator(new DefaultItemAnimator());
        IrecList.setAdapter(incomeAdapter);

        expenseAdapter = new SearchAdapter(this,"expense");
        ErecList.setHasFixedSize(true);
        ErecList.setItemAnimator(new DefaultItemAnimator());
        ErecList.setAdapter(expenseAdapter);

        incomeAdapter.SetOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(SearchActivity.this, IncomeActivity.class);
                System.out.println(incomeString.get(position));
                String x = incomeString.get(position);
                intent.putExtra("dateZ", x);
                intent.putExtra("income", incomeList.get(position));
                startActivity(intent);
            }
        });

        expenseAdapter.SetOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(SearchActivity.this, ExpenseActivity.class);
                intent.putExtra("dateZ",  expenseString.get(position));
                intent.putExtra("income", expenseList.get(position));
                startActivity(intent);
            }
        });


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.search_board_bar);
        actionBar.setDisplayShowTitleEnabled(false);
        searchET = (EditText) actionBar.getCustomView().findViewById(R.id.search_board);
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("search", s + "");
                text = searchET.getText().toString();
                if (text.equals("")) {

                    incomeList.clear();
                    incomeString.clear();
                    incomeAdapter.notifyDataSetChanged();

                    expenseList.clear();
                    expenseString.clear();
                    expenseAdapter.notifyDataSetChanged();

                } else {
                    filterIncome();
                    filterExpense();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private static void filterIncome() {
        String query = text.toLowerCase();
        incomeList.clear();
        incomeString.clear();
        List<Income> incomes =  Storage.getInstance().loadIncome();
        Log.i("searhc incomes",incomes.size()+"");
        for(int i=0 ; i<incomes.size() ; i++){
            if(incomes.get(i).getName().contains(query)){
                Log.i("searhc size","yesy");
                incomeList.add(Storage.getInstance().loadIncome().get(i));
                incomeString.add(Storage.getInstance().loadDayIncome().get(i));
            }
        }
        Log.i("searhc size",incomeList.size()+"");
        incomeAdapter.notifyDataSetChanged();
    }

    private static void filterExpense() {
        String query = text.toLowerCase();
        expenseList.clear();
        expenseString.clear();
        List<Expense> expenses = Storage.getInstance().loadExpense();
        for(int i=0 ; i<expenses.size() ; i++){
            if(expenses.get(i).getName().contains(query)){
                expenseList.add(Storage.getInstance().loadExpense().get(i));
                expenseString.add(Storage.getInstance().loadDayExpense().get(i));
            }
        }
        expenseAdapter.notifyDataSetChanged();
    }

    public List<Income> getIncomeList(){
        return this.incomeList;
    }

    public List<Expense> getExpenseList(){
        return this.expenseList;
    }

    public static void refresh(){
        filterExpense();
        filterIncome();
    }


}
