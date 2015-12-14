package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Foam extends Ingrediente {

    public Foam(double grams)
    {
        name = "Foam";
        this.grams = grams;
        price = grams*Price.FOAM;
    }
}
