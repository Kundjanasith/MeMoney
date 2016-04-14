package com.example.exceed.projectsoft1.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by exceed on 4/8/16 AD.
 */
public class MyCalendar {
    private static MyCalendar instance = null;
    private static final SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private MyDate date;
    private MyCalendar(){
        Date d = new Date();
        String temp = this.getDateFormat().format(d);
        String[] temps = temp.split(" ")[0].split("/");
        date = new MyDate(Integer.parseInt(temps[0]),Integer.parseInt(temps[1]),Integer.parseInt(temps[2]));
    }
    public static MyCalendar getInstance(){
        if(instance==null) return instance = new MyCalendar();
        return instance;
    }
    public MyDate getDate(){
        return this.date;
    }
    public void setDate(MyDate date){
        this.date = date;
    }
    public MyDate getDateLeft(){
        this.date.setMonth(this.date.getMonth()-1);
        if(this.date.getMonth()==0){
            this.date.setMonth(12);
            this.date.setYear(this.date.getYear()-1);
        }
        return this.date;
    }
    public MyDate getDateRight(){
        this.date.setMonth(this.date.getMonth()+1);
        if(this.date.getMonth()==13){
            this.date.setMonth(1);
            this.date.setYear(this.date.getYear()+1);
        }
        return this.date;
    }
    public SimpleDateFormat getDateFormat(){
        return this.s;
    }
}
