package com.example.exceed.projectsoft1.Adapter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.exceed.projectsoft1.Model.Income;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.Model.Tag;
import com.example.exceed.projectsoft1.R;

import java.io.Serializable;

/**
 * Created by exceed on 4/11/16 AD.
 */
public class IncomeGetTagAdapter extends RecyclerView.Adapter<IncomeGetTagAdapter.ViewHolder> implements Serializable {
    private ViewHolder viewHolder;
    OnItemClickListener mItemClickListener;
    private String date;
    private Income income;
    public IncomeGetTagAdapter(String date, Income income){
        this.date = date;
        this.income = income;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.each_cell1, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final Tag tag = Storage.getInstance().getIncomeTag(date, income).get(i);
        viewHolder.tag_name.setText(tag.getName());
        viewHolder.tag_name.setBackgroundColor(Color.rgb(tag.getRed(), tag.getGreen(), tag.getBlue()));
        viewHolder.tag_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
                final View promptView = layoutInflater.inflate(R.layout.ask_y_n, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setView(promptView);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Storage.getInstance().outIncomeEachTag(date, income, tag);
                        notifyDataSetChanged();
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
        });
//        viewHolder.tag_name.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Log.i("Click", tag.getName());
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return Storage.getInstance().getIncomeTag(date,income).size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Button tag_name;
        public ViewHolder(View v) {
            super(v);
            tag_name = (Button) v.findViewById(R.id.tag_income);
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