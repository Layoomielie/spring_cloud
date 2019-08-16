package com.example.entity;

/**
 * @author：张鸿建
 * @time：2019/8/16 16:22
 * @desc：
 **/
public class GoodEntity {

    private String name;
    private String price;
    private String date;

    public GoodEntity(String name, String price, String date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
