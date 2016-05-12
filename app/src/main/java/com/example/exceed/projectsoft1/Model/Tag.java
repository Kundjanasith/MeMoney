package com.example.exceed.projectsoft1.Model;

import java.io.Serializable;

/**
 * Created by exceed on 4/24/16 AD.
 */
public class Tag implements Serializable {
    private long id;
    private String name;
    private int red;
    private int green;
    private int blue;

    public Tag(String name, int red, int green, int blue) {
        this.id = System.nanoTime();
        this.name = name;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void setId(long x){
        this.id = x;
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

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag income = (Tag) o;
        return id == income.id;
    }
}

