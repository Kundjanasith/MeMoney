package com.example.exceed.projectsoft1.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by exceed on 4/15/16 AD.
 */
public class Expense implements Serializable{
    private long id;
    private String name;
    private String desc;
    private double price;
    private String createdDate;
    private List<ExpenseTag> ExpenseTagList;

    public Expense(String name, String desc, double price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.ExpenseTagList = new ArrayList<>();
        SimpleDateFormat s = new SimpleDateFormat("EEEE dd MMMM yyyy-HH:mm:ss");
        this.createdDate = s.format(new Date());
        this.id = System.nanoTime();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense)) return false;
        Expense Expense = (Expense) o;
        return id == Expense.id;
    }

    public List<ExpenseTag> getTags() {
        return ExpenseTagList;
    }

    public void addTag(ExpenseTag tag){
        this.ExpenseTagList.add(tag);
    }

    public List<ExpenseTag> getRemainTag() {
        List<ExpenseTag> temp = new ArrayList<>();
        for(ExpenseTag t: Storage.getInstance().getExpenseTags()){
            if(!this.ExpenseTagList.contains(t)) temp.add(t);
        }
        return temp;
    }
}
