package com.example.exceed.projectsoft1.Model;

/**
 * Created by exceed on 4/14/16 AD.
 */
public class IncomeTag {
    private static int id = 0;
    private String name;
    private int red;
    private int green;
    private int blue;

    public IncomeTag(String name, int red, int green, int blue) {
        id++;
        this.name = name;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static int getId() {
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
}
