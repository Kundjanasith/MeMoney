package com.example.exceed.projectsoft1.Activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.exceed.projectsoft1.Adapter.IncomeTagAdapter;
import com.example.exceed.projectsoft1.Model.IncomeTag;
import com.example.exceed.projectsoft1.Model.Storage;
import com.example.exceed.projectsoft1.R;

public class TagActivity extends AppCompatActivity {

    private RecyclerView IrecList;
    private IncomeTagAdapter incomeAdapter;
    private FloatingActionButton add_income_tag;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
//        if(Storage.getInstance().getIncomeTags().size()!=0) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        IrecList = (RecyclerView) findViewById(R.id.income_view);
        IrecList.setHasFixedSize(true);
        LinearLayoutManager Illm = new LinearLayoutManager(this);
        Illm.setOrientation(LinearLayoutManager.VERTICAL);
        IrecList.setLayoutManager(Illm);

        incomeAdapter = new IncomeTagAdapter();
        IrecList.setHasFixedSize(true);
        IrecList.setItemAnimator(new DefaultItemAnimator());
        IrecList.setAdapter(incomeAdapter);

        incomeAdapter.SetOnItemClickListener(new IncomeTagAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v,int position) {
                final IncomeTag temp = Storage.getInstance().getIncomeTags().get(position);
                final int[] red = {(int) ((temp.getRed() / 255.0) * 100)};
                final int[] green = {(int) ((temp.getGreen() / 255.0) * 100)};
                final int[] blue = {(int) ((temp.getBlue() / 255.0) * 100)};
                final LayoutInflater[] layoutInflater = {LayoutInflater.from(v.getContext())};
                final View promptView = layoutInflater[0].inflate(R.layout.edit_incometag, null);
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
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Log.i("s","L");
//                        Storage.getInstance().deIncomeTag(temp);
//                        finish();
                    }
                });
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
                        green[0] = (progress*255)/100;
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
                            Log.i("PP", "thonglek");
//                                Storage.getInstance().addIncomeTag(new IncomeTag(nameText.getText().toString(), red, green, blue));
//                                Storage.getInstance().setIncomeTag(i,new IncomeTag(nameText.getText().toString(), red, green, blue));
//                            temp.setIncomeTagName(nameText.getText().toString());
//                            temp.setRed(red[0]);
//                            temp.setGreen(green[0]);
//                            temp.setBlue(blue[0]);
//                            Storage.getInstance().upIncomeTag(temp);
                            Storage.getInstance().updateIncomeTag(temp,nameText.getText().toString(),red[0],green[0],blue[0]);
//                            Storage.getInstance().printIncomeTag();
//                                    Storage.getInstance().refreshIncomeTag();
                            IrecList.setAdapter(incomeAdapter);
//                                    finish();
//                                    startActivity(new Intent(TagActivity.this, TagActivity.class));
//                                Storage.getInstance().refreshIncomeTag();
//                                    notifyDataSetChanged();
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
        });


//        incomeAdapter.SetOnItemClickListener(new IncomeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(TagActivity.this, IncomeActivity.class);
//                intent.putExtra("dateZ", s);
//                intent.putExtra("income", Storage.getInstance().getIncomeFromDate(s).get(position));
////                intent.putExtra("board", boards.get(position));
////                startActivity(intent);
//            }
//        });
        add_income_tag = (FloatingActionButton) findViewById(R.id.add_incomeTag);
        add_income_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIncomeTag(v, savedInstanceState);
            }
        });

//        final Handler h = new Handler();
//        final int delay = 1000;
//        h.postDelayed(new Runnable() {
//            public void run() {
//                h.postDelayed(this, delay);
//                Storage.getInstance().refreshIncomeTag();
//            }
//        }, delay);
//        }
//        else{
//            Log.i("FUCK","SHIT");
//        }
    }
    private void addIncomeTag(final View v, final Bundle bundle){
        final int[] red = {0};
        final int[] blue = {0};
        final int[] green = {0};
        LayoutInflater layoutInflater = LayoutInflater.from(TagActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.input_incometag, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(TagActivity.this);
        final EditText nameText = (EditText) promptView.findViewById(R.id.input_income_tag);
        final TextView textView = (TextView) promptView.findViewById(R.id.color);
        final SeekBar redSeek = (SeekBar) promptView.findViewById(R.id.progressBar_Red);
        final SeekBar blueSeek = (SeekBar) promptView.findViewById(R.id.progress_Blue);
        final SeekBar greenSeek = (SeekBar) promptView.findViewById(R.id.progressBar_Green);
        textView.setBackgroundColor(Color.rgb(red[0], green[0], blue[0]));
        redSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("seekRd", progress + "");
                red[0] = (progress * 255) / 100;
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
                green[0] = (progress*255)/100;
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

        builder.setView(promptView);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!nameText.getText().toString().equals("")) {
                    Log.i("PP", "thonglek");
//                        Storage.getInstance().setContext(v.getContext());
//                        ProgressDialog progressDialog = new ProgressDialog(v.getContext());
//                        progressDialog.setCancelable(false);
//                        progressDialog.setTitle("ProcessingSSS");
//                        progressDialog.setMessage("Please wait . . .");
                    Log.i("PP",red[0]+"/"+green[0]+"/"+blue[0]+"/");
                    Storage.getInstance().addIncomeTag(new IncomeTag(nameText.getText().toString(), red[0], green[0], blue[0]));
//                        Storage.getInstance().setContext(v.getContext());
//                        Storage.getInstance().refreshIncomeTag();
//                    finish();
//                        startActi
// vity(new Intent(TagActivity.this, TagActivity.class));
//                        startActivity(getIntent());
                    IrecList.setAdapter(incomeAdapter);
                    red[0] = 0;
                    green[0] = 0;
                    blue[0] = 0;

//                        progressDialog.dismiss();
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

    public void onBackPressed() {
        super.onBackPressed();
    }
}
