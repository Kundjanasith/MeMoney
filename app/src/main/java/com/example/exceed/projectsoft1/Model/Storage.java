package com.example.exceed.projectsoft1.Model;

import android.util.Log;

import com.example.exceed.projectsoft1.Calendar.MyDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by exceed on 4/14/16 AD.
 */
public class Storage {
    public static String INCOME = "INCOME";
    public static String EXPENSE = "EXPENSE";
    private static Map<String,Day> dayMap;
    private List<Tag> incomeTags;
    private List<Tag> expenseTags;
    private List<Goal> goals;
    private static Storage instance = null;
    private Storage(){
        incomeTags = new ArrayList<>();
        expenseTags = new ArrayList<>();
        goals = new ArrayList<>();
        dayMap = new HashMap<>();
        this.debugIncomeTag();
        this.debugExpenseTag();
    }
    private void debugIncomeTag(){
        Tag award = new Tag("Award",234,183,242);
        award.setId(11);
        Tag interes = new Tag("Interest Money",156,164,242);
        interes.setId(12);
        Tag salary  = new Tag("Salary",147,242,181);
        salary.setId(13);
        Tag gift = new Tag("Gifts",95,242,96);
        gift.setId(14);
        Tag sell = new Tag("Selling",242,160,116);
        sell.setId(15);
        Tag other = new Tag("Others",221,242,82);
        other.setId(16);
        incomeTags.add(award);
        incomeTags.add(interes);
        incomeTags.add(salary);
        incomeTags.add(gift);
        incomeTags.add(sell);
        incomeTags.add(other);
    }
    private void debugExpenseTag(){
        Tag food_beverage = new Tag("Food&Beverage",165,216,242);
        food_beverage.setId(21);
        Tag bills_utilities = new Tag("Bills&Utilities",242,196,116);
        bills_utilities.setId(22);
        Tag transportation = new Tag("Transportation",34,242,155);
        transportation.setId(23);
        Tag shopping = new Tag("Shopping",242,127,93);
        shopping.setId(24);
        Tag friends_lover = new Tag("Friends&Lover",242,221,108);
        friends_lover.setId(25);
        Tag entertainment = new Tag("Entertainment",196,242,208);
        entertainment.setId(26);
        Tag travel = new Tag("Travel",211,158,242);
        travel.setId(27);
        Tag health_fitness = new Tag("Health&Fitness",117,158,242);
        health_fitness.setId(28);
        Tag other = new Tag("Others",242,189,92);
        other.setId(29);
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
    public void addDateExpense(String date,Expense e){
        dayMap.get(date).getExpenses().add(e);
    }
    public void deleteDateIncome(String date,Income i){
        dayMap.get(date).getIncomes().remove(i);
    }
    public void deleteDateExpense(String date,Expense e){
        dayMap.get(date).getExpenses().remove(e);
    }
    public List<Income> getIncomeFromDate(String date){
        return dayMap.get(date).getIncomes();
    }
    public List<Expense> getExpenseFromDate(String date){
        return dayMap.get(date).getExpenses();
    }
    public double getIncomeDate(String date){
        return dayMap.get(date).getIncomeDay();
    }
    public double getExpenseDate(String date){
        return dayMap.get(date).getExpenseDay();
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
    public void updateExpense(String date,Expense e,String name,String desc,double price){
        Log.i("id-tem-tem",e.getId()+"");
        for(int index=0 ; index<dayMap.get(date).getExpenses().size() ; index++){
            Log.i("id-tem",e.getId()+"");
            if(e.getId()==dayMap.get(date).getExpenses().get(index).getId()){
                dayMap.get(date).getExpenses().get(index).setName(name);
                dayMap.get(date).getExpenses().get(index).setPrice(price);
                dayMap.get(date).getExpenses().get(index).setDesc(desc);
            }
        }
    }
    public List<Tag> getIncomeTags(){
        return this.incomeTags;
    }
    public List<Tag> getExpenseTags(){
        return this.expenseTags;
    }
    public void updateIncomeTag(Tag incomeTag,String name,int red,int green,int blue){
        for(Tag i:incomeTags){
            if(i.getId()==incomeTag.getId()){
                int index = incomeTags.indexOf(i);
                incomeTags.get(index).setName(name);
                incomeTags.get(index).setRed(red);
                incomeTags.get(index).setGreen(green);
                incomeTags.get(index).setBlue(blue);
            }
        }
    }
    public void updateGoal(Goal goal,String name,double price,String date){
        for(Goal g:goals){
            if(g.getId()==goal.getId()){
                int index = goals.indexOf(g);
                goals.get(index).setName(name);
                goals.get(index).setPrice(price);
                goals.get(index).setDueDate(date);
            }
        }
    }
    public void updateExpenseTag(Tag expenseTag,String name,int red,int green,int blue){
        for(Tag i:expenseTags){
            if(i.getId()==expenseTag.getId()){
                int index = expenseTags.indexOf(i);
                expenseTags.get(index).setName(name);
                expenseTags.get(index).setRed(red);
                expenseTags.get(index).setGreen(green);
                expenseTags.get(index).setBlue(blue);
            }
        }
    }
    public void addIncomeTag(Tag incomeTag){
        incomeTags.add(incomeTag);
    }
    public void addExpenseTag(Tag expenseTag){
        expenseTags.add(expenseTag);
    }
    public List<Tag> getRemainTagI(String date,Income income){
        return dayMap.get(date).getIncomes().get(dayMap.get(date).getIncomes().indexOf(income)).getRemainTag();
    }
    public List<Tag> getRemainTagE(String date,Expense expense){
        return dayMap.get(date).getExpenses().get(dayMap.get(date).getExpenses().indexOf(expense)).getRemainTag();
    }
    public void addIncomeEachTag(String date,Income i,Tag tag){
        for(Income s:this.getIncomeFromDate(date)){
            if(s.equals(i))
                s.addTag(tag);
        }
    }
    public void addExpenseEachTag(String date,Expense e,Tag tag){
        for(Expense s:this.getExpenseFromDate(date)){
            if(s.equals(e))
                s.addTag(tag);
        }
    }
    public List<Tag> getIncomeTag(String date,Income income){
        List<Income> y = dayMap.get(date).getIncomes();
        return  y.get(dayMap.get(date).getIncomes().indexOf(income)).getTags();
    }
    public List<Tag> getExpenseTag(String date,Expense expense){
        List<Expense> y = dayMap.get(date).getExpenses();
        return  y.get(dayMap.get(date).getExpenses().indexOf(expense)).getTags();
    }
    public void outIncomeEachTag(String date,Income i,Tag tag) {
        for (Income s : this.getIncomeFromDate(date)) {
            if (s.equals(i)) {
                s.getTags().remove(tag);
                s.getRemainTag().add(tag);
            }
        }
    }
    public void outExpenseEachTag(String date,Expense i,Tag tag) {
        for (Expense s : this.getExpenseFromDate(date)) {
            if (s.equals(i)) {
                s.getTags().remove(tag);
                s.getRemainTag().add(tag);
            }
        }
    }
    public void deleteIncomeTag(Tag incomeTag){
        incomeTags.remove(incomeTag);
    }
    public void deleteExpenseTag(Tag expenseTag){
        expenseTags.remove(expenseTag);
    }

    public void deleteGoal(Goal goal){
        goals.remove(goal);
    }

    public void addGoal(Goal goal){
        goals.add(goal);
    }

    public List<Goal> getGoals(){
        return goals;
    }

    //return money
    public double getMoneyTo(Goal goal){
        double result = 0.0;
        SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy");
        Log.i("dd",goal.getDueDate());
        Date goalDate = null;
        try {
            String[] temp = goal.getDueDate().split(" ");
            String parseTemp = temp[1]+"/"+MyDate.getMonthName(temp[2])+"/"+temp[3];
            goalDate = sim.parse(parseTemp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(String key:dayMap.keySet()){
            String[] temp = key.split(" ");
            String parseTemp = temp[1]+"/"+MyDate.getMonthName(temp[2])+"/"+temp[3];
            Date currDate = null;
            try {
                currDate = sim.parse(parseTemp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.i("goalDiff",(goalDate==null)+"");
            Log.i("currDiff",(currDate==null)+"");
            if(daysBetween(goalDate,currDate)<=0){
                result+=dayMap.get(key).getTotal();
            }
            dayMap.get(key).getTotal();
        }
        return result;
    }

    //return date count down status
    public void goalStatus(Goal goal){
        String result = "";

        String goalDate = goal.getDueDate().split("#")[0];
        int goalDay = Integer.parseInt(goalDate.split(" ")[1]);
        int goalMonth = MyDate.getMonthName(goalDate.split(" ")[2]);
        int goalYear = Integer.parseInt(goalDate.split(" ")[3]);
        int allGoal = goalDay+goalMonth+goalYear;
        Log.i("x1",allGoal+"");


        SimpleDateFormat s = new SimpleDateFormat("dd MM yyyy#HH:mm:ss");
        Date date = new Date();
        String dateString = s.format(date);
        String curDate = dateString.split("#")[0];
        int curDay = Integer.parseInt(curDate.split(" ")[0]);
        int curMonth = Integer.parseInt(curDate.split(" ")[1]);
        int curYear = Integer.parseInt(curDate.split(" ")[2]);
        int allCur = curDay+curMonth+curYear;
        Log.i("x2",allCur+"");
        int index = goals.indexOf(goal);

        SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy");
        String[] curDateArr = curDate.split(" ");
        String[] goalDateArr = goalDate.split(" ");
        Date date1 = null;
        Date date2 = null;
        try {
            String d1 = curDateArr[0]+"/"+curDateArr[1]+"/"+curDateArr[2];
            String d2 = goalDateArr[1]+"/"+ MyDate.getMonthName(goalDateArr[2])+"/"+goalDateArr[3];
            Log.i("d1",d1);
            Log.i("d2",d2);
            date1 = sim.parse(d1);
            date2 = sim.parse(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int x = daysBetween(date1,date2);
        Log.i("d3",x+"");
        if(x>0)
            goals.get(index).setStatus(x+" Day");
        else if(x==0)
            goals.get(index).setStatus("Today");
        else
            goals.get(index).setStatus("Expired");
    }
    private int daysBetween(Date date2,Date date1){
        return (int) ((date1.getTime()-date2.getTime())/(1000*60*60*24));
    }
//    private int getNumMonth(String s){
//        switch (s){
//            case "January": return 1;
//            case "Fabruary" : return 2;
//            case "March" : return 3;
//            case "April" : return 4;
//            case "May" : return 5;
//            case "June" : return 6;
//            case "July" : return 7;
//            case "August" : return 8;
//            case "September" : return 9;
//            case "October" : return 10;
//            case "November" : return 11;
//            case "December" : return 12;
//        }
//        return 0;
//    }
    public List<Income> loadIncome(){
        List<Income> temp = new ArrayList<>();
        for(String key:dayMap.keySet()){
            for(Income i:dayMap.get(key).getIncomes()){
                temp.add(i);
            }
        }
        Log.i("size income",temp.size()+"");
        return temp;
    }
    public List<String> loadDayIncome(){
        List<String> temp = new ArrayList<>();
        for(String key:dayMap.keySet()){
            for(Income i:dayMap.get(key).getIncomes()){
                temp.add(key);
            }
        }
        return temp;
    }
    public List<Expense> loadExpense(){
        List<Expense> temp = new ArrayList<>();
        for(String key:dayMap.keySet()){
            for(Expense i:dayMap.get(key).getExpenses()){
                temp.add(i);
            }
        }
        return temp;
    }
    public List<String> loadDayExpense(){
        List<String> temp = new ArrayList<>();
        for(String key:dayMap.keySet()){
            for(Expense i:dayMap.get(key).getExpenses()){
                temp.add(key);
            }
        }
        return temp;
    }
    public Map<String,Day> getDayMap(){
        return dayMap;
    }
}
