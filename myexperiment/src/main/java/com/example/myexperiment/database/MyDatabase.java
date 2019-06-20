package com.example.myexperiment.database;

import com.example.myexperiment.entity.Data;

import java.util.ArrayList;

public class MyDatabase {
    private static ArrayList<Data> datas = new ArrayList<>();

    public static ArrayList<Data> getDatas() {
        return datas;
    }

    public static void setDatas(ArrayList<Data> datas) {
        MyDatabase.datas = datas;
    }
}
