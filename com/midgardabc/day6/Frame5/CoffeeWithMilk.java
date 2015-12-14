package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class CoffeeWithMilk extends Coffee {

    protected double milkItem = 0.8;

    public CoffeeWithMilk(double sugar)
    {
        super(sugar);
        name = "Coffee with Milk";
        ingredientes[MILK] = new Milk(milkItem);
        price += ingredientes[MILK].getPrice();
    }

    @Override
    public void drink() {
        PrintMenu.print(this);
    }
}
