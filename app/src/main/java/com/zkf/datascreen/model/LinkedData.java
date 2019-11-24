package com.zkf.datascreen.model;

public class LinkedData {
    private String name;
    private String number;
    private int score;
    private boolean complaint;

    public LinkedData(String name, String number, int score, boolean complaint) {
        this.name = name;
        this.number = number;
        this.score = score;
        this.complaint = complaint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isComplaint() {
        return complaint;
    }

    public void setComplaint(boolean complaint) {
        this.complaint = complaint;
    }
}
