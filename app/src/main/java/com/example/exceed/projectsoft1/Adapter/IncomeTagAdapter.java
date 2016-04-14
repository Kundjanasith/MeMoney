package com.example.exceed.projectsoft1.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public IncomeTagAdapter(){

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_incometag, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
//        List<IncomeTag> incomeTags = Storage.getInstance().getIncomeTags();
        final IncomeTag tag = Storage.getInstance().getIncomeTags().get(i);
//        Log.i("temtemtem",tag.getIncomeTagName());
        viewHolder.tag_name.setText(tag.getName());
        viewHolder.tag_name.setBackgroundColor(Color.rgb(tag.getRed(), tag.getGreen(), tag.getBlue()));
//        viewHolder.tag_name.setText(tag.getName());
//        viewHolder.tag_name.setBackgroundColor(Color.rgb(tag.getColorR(), tag.getColorG(), tag.getColorB()));
//        viewHolder.edit_tag.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
//                final View promptView = layoutInflater.inflate(R.layout.ask_y_n, null);
//                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                builder.setView(promptView);
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                            Storage.getInstance().deleteIncomeTag(tag);
//                            notifyDataSetChanged();
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                builder.show();
//                return false;
//            }
//        });
//        viewHolder.edit_tag.setOnClickListener(new View.OnClickListener() {
//                private int red = (tag.getRed()/255)*100;
//                private int blue =  (tag.getBlue()/255)*100;
//                private int green = (tag.getGreen()/255)*100;
//                @Override
//                public void onClick(View v) {
//                    LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
//                    final View promptView = layoutInflater.inflate(R.layout.edit_incometag, null);
//                    final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                    final EditText nameText = (EditText) promptView.findViewById(R.id.input_income_tag);
//                    IncomeTag temp = Storage.getInstance().getIncomeTags().get(i);
//                    nameText.setText(temp.getIncomeTagName());
//                    final TextView textView = (TextView) promptView.findViewById(R.id.color);
//                    textView.setBackgroundColor(Color.rgb(temp.getRed(), temp.getGreen(), temp.getBlue()));
//                    final SeekBar redSeek = (SeekBar) promptView.findViewById(R.id.progressBar_Red);
//                    redSeek.setProgress(red);
//                    final SeekBar blueSeek = (SeekBar) promptView.findViewById(R.id.progress_Blue);
//                    blueSeek.setProgress(blue);
//                    final SeekBar greenSeek = (SeekBar) promptView.findViewById(R.id.progressBar_Green);
//                    greenSeek.setProgress(green);
//                    redSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                        @Override
//                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                            Log.i("seekRd", progress + "");
//                            red = (progress*255)/100;
//                            textView.setBackgroundColor(Color.rgb(red,green,blue));
//                        }
//
//                        @Override
//                        public void onStartTrackingTouch(SeekBar seekBar) {
//
//                        }
//
//                        @Override
//                        public void onStopTrackingTouch(SeekBar seekBar) {
//
//                        }
//                    });
//                    blueSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                        @Override
//                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                            Log.i("seekRd", progress + "");
//                            blue = (progress * 255) / 100;
//                            textView.setBackgroundColor(Color.rgb(red, green, blue));
//                        }
//
//                        @Override
//                        public void onStartTrackingTouch(SeekBar seekBar) {
//
//                        }
//
//                        @Override
//                        public void onStopTrackingTouch(SeekBar seekBar) {
//
//                        }
//                    });
//                    greenSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                        @Override
//                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                            Log.i("seekRd", progress + "");
//                            green = (progress*255)/100;
//                            textView.setBackgroundColor(Color.rgb(red,green,blue));
//                        }
//
//                        @Override
//                        public void onStartTrackingTouch(SeekBar seekBar) {
//
//                        }
//
//                        @Override
//                        public void onStopTrackingTouch(SeekBar seekBar) {
//
//                        }
//                    });
//                    builder.setView(promptView);
//                    builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            if (!nameText.getText().toString().equals("")) {
//                                Log.i("PP", "thonglek");
////                                Storage.getInstance().addIncomeTag(new IncomeTag(nameText.getText().toString(), red, green, blue));
////                                Storage.getInstance().setIncomeTag(i,new IncomeTag(nameText.getText().toString(), red, green, blue));
//                                tag.setIncomeTagName(nameText.getText().toString());
//                                tag.setRed(red);
//                                tag.setGreen(green);
//                                tag.setBlue(blue);
//                                Storage.getInstance().upIncomeTag(tag);
//                                Storage.getInstance().printIncomeTag();
//
////                                Storage.getInstance().refreshIncomeTag();
//                                notifyDataSetChanged();
//                                red = 0;
//                                green = 0;
//                                blue = 0;
//                            }
//                        }
//                    });
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                    builder.show();
//                }
//            });
    }

    @Override
    public int getItemCount() {
        Log.i("size-tem", Storage.getInstance().getIncomeTags().size()+"");
        return Storage.getInstance().getIncomeTags().size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tag_name;
//        private ImageButton edit_tag;
        public ViewHolder(View v) {
            super(v);
            tag_name = (TextView) v.findViewById(R.id.tag_income);
//            edit_tag = (ImageButton) v.findViewById(R.id.edit_income_tag_button);
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

