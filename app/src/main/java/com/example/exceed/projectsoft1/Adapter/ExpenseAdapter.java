package com.example.exceed.projectsoft1.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exceed.projectsoft1.Model.Expense;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by exceed on 4/9/16 AD.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> implements Serializable{
    private ViewHolder viewHolder;
    OnItemClickListener mItemClickListener;
    private String date;

    public ExpenseAdapter(String date){
        this.date = date;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_expense, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        List<Expense> incomeDate = Storage.getInstance().getExpenseFromDate(date);
        if(incomeDate.get(i).getName().length()>8)
            viewHolder.income_name.setText("Name:" + incomeDate.get(i).getName().substring(0,8)+"...");
        else
            viewHolder.income_name.setText("Name:" + incomeDate.get(i).getName());
        viewHolder.income_price.setText("Price: " + incomeDate.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return Storage.getInstance().getExpenseFromDate(date).size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView income_name;
        private TextView income_price;

        public ViewHolder(View v) {
            super(v);
            income_name = (TextView) v.findViewById(R.id.income_name);
            income_price = (TextView) v.findViewById(R.id.income_price);
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

