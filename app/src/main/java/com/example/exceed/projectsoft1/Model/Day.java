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

    public Day(String name) {
        this.name = name;
        this.incomes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public double getTotal(){
        double result = 0;
        for(Income i:incomes){
            result+=i.getPrice();
        }
        return result;
    }

}
