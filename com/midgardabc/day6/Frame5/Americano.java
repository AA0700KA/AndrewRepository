package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Americano extends Coffee {

    protected double grams = 0.3;

    public Americano(double sugar)
    {
        super(sugar);
        name = "Americano";
        ingredientes[CHOKOLATE] = new Chokolate(grams);
        price += ingredientes[CHOKOLATE].getPrice();
    }

    @Override
    public void drink() {
        PrintMenu.print(this);
    }
}
