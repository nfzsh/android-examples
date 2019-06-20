package com.example.myexperiment.entity;

public class Food {
    public int id;
    public String picAdress;
    public String title;
    public String subtitle;
    public String price;
    public int num;

    public Food(int id, String title, String subtitle, String price, String picAdress) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.picAdress = picAdress;
    }

    public Food(int id, String title, String subtitle, String price, int num, String picAdress) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.num = num;
        this.picAdress = picAdress;
    }
}
