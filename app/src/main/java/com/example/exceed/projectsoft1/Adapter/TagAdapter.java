package com.example.exceed.projectsoft1.Adapter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.Model.Tag;
import com.example.exceed.projectsoft1.R;

import java.io.Serializable;

/**
 * Created by exceed on 4/24/16 AD.
 */
public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> implements Serializable {
    private ViewHolder viewHolder;
    OnItemClickListener mItemClickListener;
    private String type;

    public TagAdapter(String type){
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = null;
        if(type.equals("income")){
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cell_incometag, viewGroup, false);
        }
        if(type.equals("expense")){
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cell_expensetag, viewGroup, false);
        }
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
        viewHolder.tag_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("income")) editIncomeTag(v, i);
                else editExpenseTag(v, i);
            }
        });
        viewHolder.tag_name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (type.equals("income")) deleteIncomeTag(v, i);
                else deleteExpenseTag(v, i);
                return false;
            }
        });
    }

    private void editIncomeTag(View v,int position){
        Log.i("clean", position + "");
        final Tag temp = Storage.getInstance().getIncomeTags().get(position);
        final int[] red = {(int) ((temp.getRed() / 255.0) * 100)};
        final int[] green = {(int) ((temp.getGreen() / 255.0) * 100)};
        final int[] blue = {(int) ((temp.getBlue() / 255.0) * 100)};
        final LayoutInflater[] layoutInflater = {LayoutInflater.from(v.getContext())};
        final View promptView = layoutInflater[0].inflate(R.layout.input_incometag, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        final EditText nameText = (EditText) promptView.findViewById(R.id.input_income_tag);
        nameText.setText(temp.getName());
        final TextView textView = (TextView) promptView.findViewById(R.id.color);
        textView.setBackgroundColor(Color.rgb(temp.getRed(), temp.getGreen(), temp.getBlue()));
        final SeekBar redSeek = (SeekBar) promptView.findViewById(R.id.progressBar_Red);
        redSeek.setProgress(red[0]);
        final SeekBar blueSeek = (SeekBar) promptView.findViewById(R.id.progress_Blue);
        blueSeek.setProgress(blue[0]);
        final SeekBar greenSeek = (SeekBar) promptView.findViewById(R.id.progressBar_Green);
        greenSeek.setProgress(green[0]);
        final ImageButton ib = (ImageButton) promptView.findViewById(R.id.delete_incometag_button);
        redSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("seekRd", progress + "");
                red[0] = (progress*255)/100;
                textView.setBackgroundColor(Color.rgb(red[0], green[0], blue[0]));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        blueSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("seekRd", progress + "");
                blue[0] = (progress * 255) / 100;
                textView.setBackgroundColor(Color.rgb(red[0], green[0], blue[0]));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        greenSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("seekRd", progress + "");
                green[0] = (progress * 255) / 100;
                textView.setBackgroundColor(Color.rgb(red[0], green[0], blue[0]));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder.setView(promptView);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!nameText.getText().toString().equals("")) {
                    Storage.getInstance().updateIncomeTag(temp, nameText.getText().toString(), red[0], green[0], blue[0]);
                    notifyDataSetChanged();
                    red[0] = 0;
                    green[0] = 0;
                    blue[0] = 0;
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void editExpenseTag(View v,int position) {
        Log.i("clean", position + "");
        final Tag temp = Storage.getInstance().getExpenseTags().get(position);
        final int[] red = {(int) ((temp.getRed() / 255.0) * 100)};
        final int[] green = {(int) ((temp.getGreen() / 255.0) * 100)};
        final int[] blue = {(int) ((temp.getBlue() / 255.0) * 100)};
        final LayoutInflater[] layoutInflater = {LayoutInflater.from(v.getContext())};
        final View promptView = layoutInflater[0].inflate(R.layout.input_expensetag, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        final EditText nameText = (EditText) promptView.findViewById(R.id.input_income_tag);
        nameText.setText(temp.getName());
        final TextView textView = (TextView) promptView.findViewById(R.id.color);
        textView.setBackgroundColor(Color.rgb(temp.getRed(), temp.getGreen(), temp.getBlue()));
        final SeekBar redSeek = (SeekBar) promptView.findViewById(R.id.progressBar_Red);
        redSeek.setProgress(red[0]);
        final SeekBar blueSeek = (SeekBar) promptView.findViewById(R.id.progress_Blue);
        blueSeek.setProgress(blue[0]);
        final SeekBar greenSeek = (SeekBar) promptView.findViewById(R.id.progressBar_Green);
        greenSeek.setProgress(green[0]);
        final ImageButton ib = (ImageButton) promptView.findViewById(R.id.delete_incometag_button);
        redSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("seekRd", progress + "");
                red[0] = (progress*255)/100;
                textView.setBackgroundColor(Color.rgb(red[0], green[0], blue[0]));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        blueSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("seekRd", progress + "");
                blue[0] = (progress * 255) / 100;
                textView.setBackgroundColor(Color.rgb(red[0], green[0], blue[0]));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        greenSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("seekRd", progress + "");
                green[0] = (progress * 255) / 100;
                textView.setBackgroundColor(Color.rgb(red[0], green[0], blue[0]));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder.setView(promptView);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!nameText.getText().toString().equals("")) {
                    Storage.getInstance().updateExpenseTag(temp, nameText.getText().toString(), red[0], green[0], blue[0]);
                    notifyDataSetChanged();
                    red[0] = 0;
                    green[0] = 0;
                    blue[0] = 0;
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void deleteIncomeTag(View v, final int i){
        final LayoutInflater[] layoutInflater = {LayoutInflater.from(v.getContext())};
        final View promptView = layoutInflater[0].inflate(R.layout.ask_y_n, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setView(promptView);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Tag temp = Storage.getInstance().getIncomeTags().get(i);
                Storage.getInstance().deleteIncomeTag(temp);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void deleteExpenseTag(View v, final int i){
        final LayoutInflater[] layoutInflater = {LayoutInflater.from(v.getContext())};
        final View promptView = layoutInflater[0].inflate(R.layout.ask_y_n, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setView(promptView);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Tag temp = Storage.getInstance().getExpenseTags().get(i);
                Storage.getInstance().deleteExpenseTag(temp);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
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
