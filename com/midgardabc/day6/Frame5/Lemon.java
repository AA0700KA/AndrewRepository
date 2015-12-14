package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Lemon extends Ingrediente {

    public Lemon(double grams)
    {
        name = "Lemon";
        this.grams = grams;
        price = grams*Price.LEMON;
    }
}
