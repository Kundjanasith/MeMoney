package com.example.exceed.projectsoft1.Model;

/**
 * Created by exceed on 4/15/16 AD.
 */
public class Goal {
    private String name;
    private double price;
    private String dueDate;
    private String status;
    public Goal(String name, double price, String dueDate) {
        this.name = name;
        this.price = price;
        this.dueDate = dueDate;
        this.status = ". . .";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
