package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Water extends Ingrediente {

    public Water(double grams)
    {
        name = "Water";
        this.grams = grams;
        price = grams*Price.WATER;
    }
}
