package com.zkf.datascreen.model;

public class PriceData {
    private String name;
    private String price;
    private String unit;
    private String date;

    public PriceData(String name, String price, String unit, String date) {
        this.name = name;
        this.price = price;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
