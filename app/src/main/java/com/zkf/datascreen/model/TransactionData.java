package com.zkf.datascreen.model;

public class TransactionData {


    private String name;
    private String kg;
    private String price_unit;
    private String price;
    private String time;

    public TransactionData(String name, String kg, String price_unit, String price, String time) {
        this.name = name;
        this.kg = kg;
        this.price_unit = price_unit;
        this.price = price;
        this.time = time;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    public String getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
