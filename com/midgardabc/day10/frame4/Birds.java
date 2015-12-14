package com.midgardabc.day10.frame4;

/**
 * Created by user on 28.08.2015.
 */
public class Birds {

    public String name;
    public int price;
    public int free;
    public int soldBird;
    public long id;

    public String getName() {
        return name;
    }

    public Birds(String name, int price, int soldBird)
    {
        this.name = name;
        this.price = price;
        this.soldBird = soldBird;
    }

    public Birds() {

    }

    public int getPrice() {
        return price;
    }

    public int getFree() {
        return free;
    }

    public int getSoldBird() {
        return soldBird;
    }

    public long getId() {
        return id;
    }


}
