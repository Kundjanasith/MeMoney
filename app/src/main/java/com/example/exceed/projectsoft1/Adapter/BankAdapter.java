package com.example.exceed.projectsoft1.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.exceed.projectsoft1.Model.Bank;
import com.example.exceed.projectsoft1.R;

import java.io.Serializable;

/**
 * Created by exceed on 4/9/16 AD.
 */
public class BankAdapter extends RecyclerView.Adapter<BankAdapter.ViewHolder> implements Serializable{
    private ViewHolder viewHolder;
    OnItemClickListener mItemClickListener;

    public BankAdapter(){

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_bank, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bank_name.setText(Bank.BANK_NAME[i]);
//        viewHolder.imageButton.setImageURI();
    }

    @Override
    public int getItemCount() {
        return Bank.BANK_NAME.length;
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView bank_name;
        private ImageButton imageButton;

        public ViewHolder(View v) {
            super(v);
            bank_name = (TextView) v.findViewById(R.id.bank_name);
            imageButton = (ImageButton) v.findViewById(R.id.imageButton);
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

