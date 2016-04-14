package com.example.exceed.projectsoft1.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by exceed on 4/14/16 AD.
 */
public class Storage {
    private static Map<String,Day> dayMap;
    private List<IncomeTag> incomeTags;
    private static Storage instance = null;
    private Storage(){
        incomeTags = new ArrayList<>();
        dayMap = new HashMap<>();
        this.debugIncomeTag();
    }
    private void debugIncomeTag(){
        IncomeTag award = new IncomeTag("Award",255,0,0);
        award.setId(1);
        IncomeTag interes = new IncomeTag("Interest Money",0,0,255);
        interes.setId(2);
        IncomeTag salary  = new IncomeTag("Salary",0,255,0);
        salary.setId(3);
        IncomeTag gift = new IncomeTag("Gifts",255,0,255);
        gift.setId(4);
        IncomeTag sell = new IncomeTag("Selling",255,255,0);
        sell.setId(5);
        IncomeTag other = new IncomeTag("Others",0,255,255);
        other.setId(6);
        incomeTags.add(award);
        incomeTags.add(interes);
        incomeTags.add(salary);
        incomeTags.add(gift);
        incomeTags.add(sell);
        incomeTags.add(other);
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
    public List<IncomeTag> getIncomeTags(){
        return this.incomeTags;
    }
    public void updateIncomeTag(IncomeTag incomeTag,String name,int red,int green,int blue){
        for(IncomeTag i:incomeTags){
            if(i.getId()==incomeTag.getId()){
                int index = incomeTags.indexOf(i);
                incomeTags.get(index).setName(name);
                incomeTags.get(index).setRed(red);
                incomeTags.get(index).setGreen(green);
                incomeTags.get(index).setBlue(blue);
            }
        }
    }
    public void addIncomeTag(IncomeTag incomeTag){
        incomeTags.add(incomeTag);
    }
    public List<IncomeTag> getRemainTag(String date,Income income){
        return dayMap.get(date).getIncomes().get(dayMap.get(date).getIncomes().indexOf(income)).getRemainTag();
    }
    public void addIncomeEachTag(String date,Income i,IncomeTag tag){
        for(Income s:this.getIncomeFromDate(date)){
            if(s.equals(i))
                s.addTag(tag);
        }
    }
    public List<IncomeTag> getIncomeTag(String date,Income income){
//        String[] temp = date.split(" ");
//        String s = temp[0]+"/"+temp[1]+"/"+temp[2]+"/"+temp[3];

        Log.i("cpesk",date);
        Log.i("cpesk",income.getName()+ "");
        List<Income> y = dayMap.get(date).getIncomes();
        return  y.get(dayMap.get(date).getIncomes().indexOf(income)).getTags();
    }
    public void outIncomeEachTag(String date,Income i,IncomeTag tag) {
        for (Income s : this.getIncomeFromDate(date)) {
            if (s.equals(i)) {
                s.getTags().remove(tag);
                s.getRemainTag().add(tag);
            }
        }
    }
}
