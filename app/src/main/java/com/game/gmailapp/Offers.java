package com.game.gmailapp;

import java.util.ArrayList;

public class Offers {
    String title, price, unit, comment1, comment2, comment3, process;

    public Offers(String title, String price, String unit, String comment1, String comment2, String comment3, String process) {
        this.title = title;
        this.price = price;
        this.unit = unit;
        this.comment1 = comment1;
        this.comment2 = comment2;
        this.comment3 = comment3;
        this.process = process;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getUnit() { return unit; }

    public void setUnit(String unit) { this.unit = unit; }

    public String getComment1() { return comment1; }

    public void setComment1(String comment1) { this.comment1 = comment1; }

    public String getComment2() { return comment2; }

    public void setComment2(String comment2) { this.comment2 = comment2; }

    public String getComment3() { return comment3; }

    public void setComment3(String comment3) { this.comment3 = comment3; }

    public String getProcess() { return process; }

    public void setProcess(String process) { this.process = process; }

    public static ArrayList<Offers> getOffers(){
        ArrayList<Offers> list = new ArrayList<>();
        list.add(new Offers("PURCHASE SOLAR PLANT", "Rs. 45,000", "per KW", "Claim all the benefits of installing solar from Day 1","Experience exceptional customer service & system monitoring", "Earn returns better than fixed deposit.", "Buy Solar Today"));
        list.add(new Offers("PURCHASE SOLAR ELECTRICITY", "Rs. 6.5", "per unit", "Get solar installed at zero cost at your rooftop", "Avail electricity prices that lower each year", "Get higher savings in electricity expenditure each year", "Buy Solar Electricity"));
        return list;
    }
}
