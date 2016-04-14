package com.example.exceed.projectsoft1;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.exceed.projectsoft1.Calendar.MyCalendar;
import com.example.exceed.projectsoft1.Calendar.MyDate;
import com.example.exceed.projectsoft1.Model.Storage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button currentDate;
    private Button bDate;
    private Button bLeft;
    private Button bRight;
    private Date date;
    private String stringDate;
    private TextView amount;
    private static SimpleDateFormat formatMonthChar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static SimpleDateFormat formatMonthNumb = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bDate = (Button) findViewById(R.id.bDate);
        stringDate = formatMonthChar.format(new Date()).split(" ")[0];
        String[] tempStart = stringDate.split("/");
        MyDate startDate = new MyDate(Integer.parseInt(tempStart[0]),Integer.parseInt(tempStart[1]),Integer.parseInt(tempStart[2]));
        bDate.setText(startDate.getReadableMonth() + " / " + startDate.getYear());
        currentDate = (Button) findViewById(R.id.current_date);
        final Handler h = new Handler();
        final int delay = 1000;
        h.postDelayed(new Runnable() {
            public void run() {
                h.postDelayed(this, delay);
                date = new Date();
                String time = DateFormat.getTimeInstance().format(date);
                String day = formatMonthChar.format(date).split(" ")[0];
                stringDate = formatMonthNumb.format(date).split(" ")[0];
                currentDate.setText("Date : " + day + "\n" + "Time : " + time);
                amount.setText("Amount : "+ Storage.getInstance().getTotal());
            }
        }, delay);

        bLeft = (Button) findViewById(R.id.bLeft);
        bLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDate left = MyCalendar.getInstance().getDateLeft();
                bDate.setText(left.getReadableMonth()+" / "+left.getYear());
                initCalendar();
            }
        });
        bRight = (Button) findViewById(R.id.bRight);
        bRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDate right = MyCalendar.getInstance().getDateRight();
                bDate.setText(right.getReadableMonth() + " / " + right.getYear());
                initCalendar();
            }
        });
        amount = (TextView) findViewById(R.id.amount);
        initCalendar();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
// else if (id == R.id.nav_tag) {
//            Intent intent = new Intent(MainActivity.this, TagActivity.class);
//            Storage.getInstance().setContextIncomeTag(this);
//            startActivity(intent);
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Button[][] calendar ;
    private void initCalendar(){
        calendar = new Button[6][7];
        int[][] calId = {
                {R.id.SUN1,R.id.MON1,R.id.TUE1,R.id.WED1,R.id.THU1,R.id.FRI1,R.id.SAT1},
                {R.id.SUN2,R.id.MON2,R.id.TUE2,R.id.WED2,R.id.THU2,R.id.FRI2,R.id.SAT2},
                {R.id.SUN3,R.id.MON3,R.id.TUE3,R.id.WED3,R.id.THU3,R.id.FRI3,R.id.SAT3},
                {R.id.SUN4,R.id.MON4,R.id.TUE4,R.id.WED4,R.id.THU4,R.id.FRI4,R.id.SAT4},
                {R.id.SUN5,R.id.MON5,R.id.TUE5,R.id.WED5,R.id.THU5,R.id.FRI5,R.id.SAT5},
                {R.id.SUN6,R.id.MON6,R.id.TUE6,R.id.WED6,R.id.THU6,R.id.FRI6,R.id.SAT6},
        };
        int[] calColor ={R.color.colorSunday,R.color.colorMonday,R.color.colorTueday,R.color.colorWednesday,
                R.color.colorThursday,R.color.colorFriday,R.color.colorSaturnday};
        for( int i=0 ; i<6 ; i++){
            for( int j=0 ; j<7 ; j++){
                calendar[i][j] = (Button) findViewById(calId[i][j]);
                calendar[i][j].setBackgroundColor(getResources().getColor(calColor[j]));
                calendar[i][j].setEnabled(true);
                calendar[i][j].setTextColor(Color.WHITE);
                final int finalI = i;
                final int finalJ = j;
                calendar[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(MainActivity.this, DateActivity.class);
//                        intent.putExtra("date",dayName[finalJ]+" "+calendar[finalI][finalJ].getText().toString()+" "+
//                                MyCalendar.getInstance().getDate().getReadableMonth()+" "+ MyCalendar.getInstance().getDate().getYear());
//                        startActivity(intent);
                    }
                });
            }
        }
        updateCalendar();
    }
    private String[] dayName = { "Sunday","Monday","Tueday","Wednesday","Thursday","Friday","Saturday"};
    private int[] monthDay = {  31,28,31,30,31,30,31,31,30,31,30,31 };
    public void updateCalendar(){
        int d = Integer.parseInt(stringDate.split("/")[0]);
        int m = Integer.parseInt(stringDate.split("/")[1]);
        int y = Integer.parseInt(stringDate.split("/")[2]);
        int month = MyCalendar.getInstance().getDate().getMonth();
        int year = MyCalendar.getInstance().getDate().getYear();
        if(year%4==0) monthDay[1]+=1;
        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        Date dt1= null;
        try {
            dt1 = format1.parse("01/"+month+"/"+year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(dt1);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int dayPre = 0 ;
        if(month-2==-1) dayPre = monthDay[11];
        else dayPre = monthDay[month-2];
        int dayNum = 1;
        int dayNext = 1;
        for(int i=dayOfWeek-2 ; i>=0 ; i--){
            calendar[0][i].setText(dayPre+"");
            calendar[0][i].setBackgroundColor(getResources().getColor(R.color.colorNext));
            calendar[0][i].setEnabled(false);
            dayPre--;
        }
        calendar[0][dayOfWeek-1].setText(dayNum+"");
        for(int i=dayOfWeek ; i<7 ; i++){
            dayNum++;
            calendar[0][i].setText(dayNum+"");
        }
        for(int i=1 ; i<6 ; i++){
            for(int j=0 ; j<7 ; j++){
                dayNum++;
                calendar[i][j].setText(dayNum + "");
                if(dayNum>monthDay[month-1]) {
                    calendar[i][j].setText(dayNext + "");
                    calendar[i][j].setBackgroundColor(getResources().getColor(R.color.colorNext));
                    calendar[i][j].setEnabled(false);
                    dayNext++;
                }
                if(Integer.parseInt(calendar[i][j].getText().toString())==d&&month==m&&year==y){
                    Log.i("check", "L");
                    calendar[i][j].setTextColor(Color.BLACK);
                }
            }
        }
        if(year%4==0) monthDay[1]-=1;
    }

}
