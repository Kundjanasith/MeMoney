package com.example.exceed.projectsoft1.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.exceed.projectsoft1.Activity.STagActivity;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.Model.Tag;
import com.example.exceed.projectsoft1.R;

import java.io.Serializable;

/**
 * Created by exceed on 4/24/16 AD.
 */
public class SearchTagAdapter extends RecyclerView.Adapter<SearchTagAdapter.ViewHolder> implements Serializable {
    private ViewHolder viewHolder;
    OnItemClickListener mItemClickListener;
    private String type;

    public SearchTagAdapter(String type){
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = null;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.each_cell1, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Tag tag = null;
        if(type.equals("income")){
            tag = Storage.getInstance().getIncomeTags().get(i);
        }
        if(type.equals("expense")){
            tag = Storage.getInstance().getExpenseTags().get(i);
        }
        viewHolder.tag_name.setText(tag.getName());
        viewHolder.tag_name.setBackgroundColor(Color.rgb(tag.getRed(), tag.getGreen(), tag.getBlue()));
        final Tag finalTag = tag;
        viewHolder.tag_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), STagActivity.class);
                    intent.putExtra("tag", finalTag);
                    if(type.equals("income")) intent.putExtra("type","Income");
                    else  intent.putExtra("type","Expense");
                    v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(type.equals("income"))return Storage.getInstance().getIncomeTags().size();
        else return Storage.getInstance().getExpenseTags().size();
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
