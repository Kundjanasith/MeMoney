package com.example.exceed.projectsoft1.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by exceed on 4/14/16 AD.
 */
public class Income implements Serializable{
    private long id;
    private String name;
    private String desc;
    private double price;
    private String createdDate;
    private List<IncomeTag> incomeTagList;

    public Income(String name, String desc, double price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.incomeTagList = new ArrayList<>();
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

//    public List<IncomeTag> getIncomeTagList() {
//        return incomeTagList;
//    }
//
//    public void setIncomeTagList(List<IncomeTag> incomeTagList) {
//        this.incomeTagList = incomeTagList;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Income)) return false;
        Income income = (Income) o;
        return id == income.id;
    }

    public List<IncomeTag> getTags() {
        return incomeTagList;
    }

    public void addTag(IncomeTag tag){
        this.incomeTagList.add(tag);
    }

    public List<IncomeTag> getRemainTag() {
        List<IncomeTag> temp = new ArrayList<>();
        for(IncomeTag t: Storage.getInstance().getIncomeTags()){
            if(!this.incomeTagList.contains(t)) temp.add(t);
        }
        return temp;
    }

}
