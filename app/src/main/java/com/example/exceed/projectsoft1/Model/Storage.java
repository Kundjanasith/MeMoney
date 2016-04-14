package com.example.exceed.projectsoft1.Model;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by exceed on 4/14/16 AD.
 */
public class Storage {
    private static Map<String,Day> dayMap;
    private static Storage instance = null;
    private Storage(){
        dayMap = new HashMap<>();
    }
    public static Storage getInstance(){
        if(instance==null) instance = new Storage();
        return instance;
    }
    public double getTotal(){
        double result = 0;
        for(String key:dayMap.keySet()) {
            result += dayMap.get(key).getTotal();
        }
        return result;
    }
    public double getTotal(String date){
        Log.i("x1", date);
        return dayMap.get(date).getTotal();
    }
    public void addDateIncome(String date,Income i){
        dayMap.get(date).getIncomes().add(i);
    }
    public List<Income> getIncomeFromDate(String date){
        return dayMap.get(date).getIncomes();
    }
    public void createDate(String date){
        Log.i("x2", date);
        if(dayMap.get(date)==null)dayMap.put(date,new Day(date));
    }
    public void updateIncome(String date,Income i,String name,String desc,double price){
        Log.i("id-tem-tem",i.getId()+"");
        for(int index=0 ; index<dayMap.get(date).getIncomes().size() ; index++){
            Log.i("id-tem",i.getId()+"");
            if(i.getId()==dayMap.get(date).getIncomes().get(index).getId()){
                dayMap.get(date).getIncomes().get(index).setName(name);
                dayMap.get(date).getIncomes().get(index).setPrice(price);
                dayMap.get(date).getIncomes().get(index).setDesc(desc);
            }
        }
    }
}
