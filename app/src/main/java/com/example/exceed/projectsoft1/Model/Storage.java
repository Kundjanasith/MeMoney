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
    private List<ExpenseTag> expenseTags;
    private static Storage instance = null;
    private Storage(){
        incomeTags = new ArrayList<>();
        expenseTags = new ArrayList<>();
        dayMap = new HashMap<>();
        this.debugIncomeTag();
        this.debugExpenseTag();
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
    private void debugExpenseTag(){
        ExpenseTag food_beverage = new ExpenseTag("Food&Beverage",0,0,255);
        food_beverage.setId(1);
        ExpenseTag bills_utilities = new ExpenseTag("Bills&Utilities",0,255,0);
        bills_utilities.setId(2);
        ExpenseTag transportation = new ExpenseTag("Transportation",255,0,0);
        transportation.setId(3);
        ExpenseTag shopping = new ExpenseTag("Shopping",255,255,0);
        shopping.setId(4);
        ExpenseTag friends_lover = new ExpenseTag("Friends&Lover",255,0,255);
        friends_lover.setId(5);
        ExpenseTag entertainment = new ExpenseTag("Entertainment",0,175,175);
        entertainment.setId(6);
        ExpenseTag travel = new ExpenseTag("Travel",175,175,0);
        travel.setId(7);
        ExpenseTag health_fitness = new ExpenseTag("Health&Fitness",175,0,175);
        health_fitness.setId(8);
        ExpenseTag other = new ExpenseTag("Others",0,255,255);
        other.setId(9);
        expenseTags.add(food_beverage);
        expenseTags.add(bills_utilities);
        expenseTags.add(transportation);
        expenseTags.add(shopping);
        expenseTags.add(friends_lover);
        expenseTags.add(entertainment);
        expenseTags.add(travel);
        expenseTags.add(health_fitness);
        expenseTags.add(other);
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
    public void deleteDateIncome(String date,Income i){
        dayMap.get(date).getIncomes().remove(i);
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
    public List<ExpenseTag> getExpenseTags(){
        return this.expenseTags;
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
    public void updateExpenseTag(ExpenseTag expenseTag,String name,int red,int green,int blue){
        for(ExpenseTag i:expenseTags){
            if(i.getId()==expenseTag.getId()){
                int index = expenseTags.indexOf(i);
                expenseTags.get(index).setName(name);
                expenseTags.get(index).setRed(red);
                expenseTags.get(index).setGreen(green);
                expenseTags.get(index).setBlue(blue);
            }
        }
    }
    public void addIncomeTag(IncomeTag incomeTag){
        incomeTags.add(incomeTag);
    }
    public void addExpenseTag(ExpenseTag expenseTag){
        expenseTags.add(expenseTag);
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
    public void deleteIncomeTag(IncomeTag incomeTag){
        incomeTags.remove(incomeTag);
    }
    public void deleteExpenseTag(ExpenseTag expenseTag){
        expenseTags.remove(expenseTag);
    }
}
