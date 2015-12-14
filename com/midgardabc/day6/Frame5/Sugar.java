package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Sugar extends Ingrediente  {

    public Sugar(double grams)
    {
        name = "Sugar";
        this.grams = grams;
        price = grams*Price.SUGAR;
    }
}
