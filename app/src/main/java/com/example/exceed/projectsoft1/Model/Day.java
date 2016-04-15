package com.example.exceed.projectsoft1.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by exceed on 4/14/16 AD.
 */
public class Day implements Serializable{
    private String name;
    private List<Income> incomes;
    private List<Expense> expenses;

    public Day(String name) {
        this.name = name;
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Income> getIncomes() {
        return incomes;
    }
    public List<Expense> getExpenses() {
        return expenses;
    }

    public double getIncomeDay(){
        double res = 0.0;
        for(Income i:incomes){
            res+=i.getPrice();
        }
        return res;
    }

    public double getExpenseDay(){
        double res = 0.0;
        for(Expense i:expenses){
            res+=i.getPrice();
        }
        return res;
    }


    public double getTotal(){
        double result = 0;
        for(Income i:incomes){
            result+=i.getPrice();
        }
        for(Expense e:expenses){
            result-=e.getPrice();
        }
        return result;
    }

}
