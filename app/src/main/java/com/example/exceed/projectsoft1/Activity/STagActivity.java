package com.example.exceed.projectsoft1.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.exceed.projectsoft1.Adapter.ExpenseAdapterZ;
import com.example.exceed.projectsoft1.Adapter.IncomeAdapterZ;
import com.example.exceed.projectsoft1.Model.Expense;
import com.example.exceed.projectsoft1.Model.Income;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.Model.Tag;
import com.example.exceed.projectsoft1.R;

import java.util.ArrayList;
import java.util.List;

public class STagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stag);
        final Tag tag = (Tag) getIntent().getSerializableExtra("tag");
        String type = (String) getIntent().getSerializableExtra("type");
        RelativeLayout re = (RelativeLayout) findViewById(R.id.back);
        int c = Color.rgb(tag.getRed(), tag.getGreen(), tag.getBlue());
        re.setBackgroundColor(c);
        TextView tv_type = (TextView) findViewById(R.id.type);
        tv_type.setText(type);
        TextView tv_name = (TextView) findViewById(R.id.name);
        tv_name.setText(tag.getName());

        RecyclerView rec = (RecyclerView) findViewById(R.id.rec_view);
        rec.setBackgroundColor(c);
        rec.setHasFixedSize(true);
        LinearLayoutManager Illm = new LinearLayoutManager(this);
        Illm.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(Illm);
        IncomeAdapterZ IZ = new IncomeAdapterZ(tag);
        IZ.SetOnItemClickListener(new IncomeAdapterZ.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                List<String> DayDate = new ArrayList<String>();
                List<Income> incomeDate = new ArrayList<>();
                for (String s : Storage.getInstance().getDayMap().keySet()) {
                    Log.i("PP", s);
                    for (Income i : Storage.getInstance().getIncomeFromDate(s)) {
                        Log.i("PP", "C");
                        for (Tag t : i.getTags()) {
                            if (t.equals(tag)) {
                                Log.i("PP", "FUCK");
                                DayDate.add(s);
                                incomeDate.add(i);
                                break;
                            }
                        }
                    }
                }
                Intent intent = new Intent(STagActivity.this, IncomeActivity.class);
                intent.putExtra("dateZ", DayDate.get(position));
                intent.putExtra("income", incomeDate.get(position));
                startActivity(intent);
                finish();
            }
        });
        ExpenseAdapterZ EZ = new ExpenseAdapterZ(tag);
        EZ.SetOnItemClickListener(new ExpenseAdapterZ.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                List<String> DayDate = new ArrayList<String>();
                List<Expense> incomeDate = new ArrayList<>();
                for(String s: Storage.getInstance().getDayMap().keySet()){
                    Log.i("PP", s);
                    for(Expense i:Storage.getInstance().getExpenseFromDate(s)){
                        Log.i("PP", "C");
                        for(Tag t:i.getTags()){
                            if(t.equals(tag)){
                                Log.i("PP", "FUCK");
                                DayDate.add(s);
                                incomeDate.add(i);
                                break;
                            }
                        }
                    }
                }
                Intent intent = new Intent(STagActivity.this, ExpenseActivity.class);
                intent.putExtra("dateZ",  DayDate.get(position));
                intent.putExtra("income", incomeDate.get(position));
                startActivity(intent);
                finish();
            }
        });
        if(type.equals("Income")) rec.setAdapter(IZ);
        if(type.equals("Expense")) rec.setAdapter(EZ);
    }
}
