package com.midgardabc.day14.shop.domain;

import com.midgardabc.day14.shop.Category;

/**
 * Created by user on 01.07.2015.
 */
public class Bird {

    private int aviableBird;
    private int soldBird;
    private String name;
    private double startingPrice;
    private double realPrice;
    private double profit;
    private Category category;
    private long id;

    public static final double SALES = 1.5;

    public double getProfit() {
        return profit;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public void updatePrice(double price) {

        this.startingPrice = price;
        if (profit > 1000) {
            price = price - (price * 0.1);
        } else if (profit > 500) {
            price = price - (price * 0.05);
        }

        this.realPrice = price;
    }

    public int getSoldBird() {
        return soldBird;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void updateSoldBird(int count) {
        aviableBird += count;
    }

    public void updateProfit(double count) {
        profit += count;
    }

    public void updateCount(int count)
    {
        soldBird += count;
        aviableBird -= count;
    }

    public long getId() {
        return id;
    }

    public Bird(long id, String name, Category category, int aviableBird, double price)
    {
        this.name = name;
        this.aviableBird = aviableBird;
        startingPrice = price;
        realPrice = startingPrice;
        this.category = category;
        this.id = id;
    }

    public Bird(long id, String name, Category category, int aviableBird, double price, double profit, int sold)
    {
        this.name = name;
        this.aviableBird = aviableBird;
        startingPrice = price;
        realPrice = startingPrice;
        if (profit > 1000) {
            realPrice = startingPrice - startingPrice * 0.1;
        } else if (profit > 500) {
            realPrice = startingPrice - startingPrice * 0.05;
        }
        this.category = category;
        this.id = id;
        this.profit = profit;
        soldBird = sold;
    }

    public Bird(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAviableBird() {
        return aviableBird;
    }

}
