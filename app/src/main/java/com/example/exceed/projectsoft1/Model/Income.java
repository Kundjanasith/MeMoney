package com.example.exceed.projectsoft1.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by exceed on 4/14/16 AD.
 */
public class Income extends Money implements Serializable{

    public Income(String name, String desc, double price) {
        super(name,desc,price);
    }

    @Override
    public List<Tag> getRemainTag() {
        List<Tag> temp = new ArrayList<>();
        for(Tag t: Storage.getInstance().getIncomeTags()){
            if(!this.TagList.contains(t)) temp.add(t);
        }
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Income)) return false;
        Income income = (Income) o;
        return id == income.id;
    }

}
