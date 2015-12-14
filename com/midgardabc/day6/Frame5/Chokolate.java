package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Chokolate extends Ingrediente {

    public Chokolate(double grams)
    {
        name = "Chokolate";
        this.grams = grams;
        price = grams*Price.CHOKOLATE;
    }

}
