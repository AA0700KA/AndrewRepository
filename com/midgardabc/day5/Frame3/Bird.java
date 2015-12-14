package com.midgardabc.day5.Frame3;

/**
 * Created by user on 01.07.2015.
 */
public class Bird {

    protected int aviableBird;
    protected int soldBird;
    protected String name;
    protected int price;

    public int getSoldBird() {
        return soldBird;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void updateCount(int count)
    {
        soldBird += count;
        aviableBird -= count;
    }

    public Bird(int aviableBird)
    {
        this.aviableBird = aviableBird;
    }

    public Bird()
    {

    }

    public String getName() {
        return name;
    }

    public int getAviableBird() {
        return aviableBird;
    }
}
