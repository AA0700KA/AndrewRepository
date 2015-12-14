package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Milk extends Ingrediente {

    public Milk(double grams)
    {
        name = "Milk";
        this.grams = grams;
        price = grams*Price.MILK;
    }
}
