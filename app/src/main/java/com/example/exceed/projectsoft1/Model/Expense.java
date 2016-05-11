package com.example.exceed.projectsoft1.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by exceed on 4/15/16 AD.
 */
public class Expense extends Money implements Serializable{

    public Expense(String name, String desc, double price) {
        super(name,desc,price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense)) return false;
        Expense Expense = (Expense) o;
        return id == Expense.id;
    }

    @Override
    public List<Tag> getRemainTag() {
        List<Tag> temp = new ArrayList<>();
        for(Tag t: Storage.getInstance().getExpenseTags()){
            if(!this.TagList.contains(t)) temp.add(t);
        }
        return temp;
    }
}
