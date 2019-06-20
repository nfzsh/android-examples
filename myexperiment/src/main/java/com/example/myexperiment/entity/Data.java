package com.example.myexperiment.entity;

import java.io.Serializable;

public class Data implements Serializable {
    private int id;
    private float price;
    private int num;

    public Data(int id, float price, int num) {
        this.id = id;
        this.price = price;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void AddNum() {
        num++;
    }

    public int getNum() {
        return num;
    }

    public float getPrice() {
        return price;
    }

    public void setNum(int num) {
        this.num = num;
    }

    ;
}
