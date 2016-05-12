package com.example.exceed.projectsoft1.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.exceed.projectsoft1.Adapter.SearchTagAdapter;
import com.example.exceed.projectsoft1.R;

public class SearchTag extends AppCompatActivity {


    public static RecyclerView SIrec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tag);
        this.init();
    }

    private void init(){

        SIrec = (RecyclerView) findViewById(R.id.show_income_view);
        SIrec.setHasFixedSize(true);
        LinearLayoutManager SIllm = new LinearLayoutManager(this);
        SIllm.setOrientation(LinearLayoutManager.VERTICAL);
        SIrec.setLayoutManager(SIllm);
        SIrec.setItemAnimator(new DefaultItemAnimator());

        RecyclerView Irec = (RecyclerView) findViewById(R.id.income_view);
        Irec.setHasFixedSize(true);
        LinearLayoutManager Illm = new LinearLayoutManager(this);
        Illm.setOrientation(LinearLayoutManager.HORIZONTAL);
        Irec.setLayoutManager(Illm);
        SearchTagAdapter Iadapter = new SearchTagAdapter("income");
        Irec.setHasFixedSize(true);
        Irec.setItemAnimator(new DefaultItemAnimator());
        Irec.setAdapter(Iadapter);

        RecyclerView Erec = (RecyclerView) findViewById(R.id.expense_view);
        Erec.setHasFixedSize(true);
        LinearLayoutManager Ellm = new LinearLayoutManager(this);
        Ellm.setOrientation(LinearLayoutManager.HORIZONTAL);
        Erec.setLayoutManager(Ellm);
        SearchTagAdapter Eadapter = new SearchTagAdapter("expense");
        Erec.setHasFixedSize(true);
        Erec.setItemAnimator(new DefaultItemAnimator());
        Erec.setAdapter(Eadapter);


    }
}
