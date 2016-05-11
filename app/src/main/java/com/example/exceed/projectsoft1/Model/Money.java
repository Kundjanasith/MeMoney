package com.example.exceed.projectsoft1.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by exceed on 4/24/16 AD.
 */
public abstract class Money implements Serializable{
    protected long id;
    private String name;
    private String desc;
    private double price;
    private String createdDate;
    protected List<Tag> TagList;

    public Money(String name, String desc, double price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.TagList = new ArrayList<>();
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

    public List<Tag> getTags() {
        return TagList;
    }

    public void addTag(Tag tag){
        this.TagList.add(tag);
    }

    public abstract List<Tag> getRemainTag();

}
