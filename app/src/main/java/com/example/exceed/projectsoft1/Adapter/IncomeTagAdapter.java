package com.example.exceed.projectsoft1.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.exceed.projectsoft1.Model.IncomeTag;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.R;

import java.io.Serializable;

/**
 * Created by exceed on 4/10/16 AD.
 */
public class IncomeTagAdapter extends RecyclerView.Adapter<IncomeTagAdapter.ViewHolder> implements Serializable {
    private ViewHolder viewHolder;
    OnItemClickListener mItemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_incometag, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final IncomeTag tag = Storage.getInstance().getIncomeTags().get(i);
        viewHolder.tag_name.setText(tag.getName());
        viewHolder.tag_name.setBackgroundColor(Color.rgb(tag.getRed(), tag.getGreen(), tag.getBlue()));
    }

    @Override
    public int getItemCount() {
        return Storage.getInstance().getIncomeTags().size();
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

