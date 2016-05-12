package com.example.exceed.projectsoft1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.exceed.projectsoft1.Adapter.BankAdapter;
import com.example.exceed.projectsoft1.Model.Bank;
import com.example.exceed.projectsoft1.R;

public class BankActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BankAdapter bankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        setTitle("Bank");
        recyclerView = (RecyclerView) findViewById(R.id.bank_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager Illm = new LinearLayoutManager(this);
        Illm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(Illm);
        bankAdapter = new BankAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bankAdapter);
        bankAdapter.SetOnItemClickListener(new BankAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(BankActivity.this, WebBankActivity.class);;
                intent.putExtra("URL", Bank.BANK_URL[position]);
                startActivity(intent);
            }
        });

    }
}
