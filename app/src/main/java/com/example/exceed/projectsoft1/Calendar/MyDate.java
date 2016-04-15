package com.example.exceed.projectsoft1.Calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by exceed on 4/8/16 AD.
 */
public class MyDate {
    private int day;
    private int month;
    private int year;
    private static String[] monthName = {
            "January","February","March","April","May","June","July","August","September","October","November","December"
    };
    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getReadableMonth(){
        return monthName[this.month-1];
    }
    public String getReadableMonth2(){
        return monthName[this.month];
    }

    public String getReadableDay() throws ParseException {
        int month2 = month+1;
        String input = day+"/"+month2+"/"+year;
        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        Date dt1= format1.parse(input);
        DateFormat format2=new SimpleDateFormat("EEEE");
        String finalDay=format2.format(dt1);
        return finalDay;
    }


}
