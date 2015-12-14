package com.midgardabc.day5.Frame3;

/**
 * Created by user on 01.07.2015.
 */
public class Duck extends Bird {

    public Duck()
    {
        aviableBird = 10;
        name = "Duck";
        price = 3;
    }

    public Duck(int aviableBird)
    {
        super(aviableBird);
        name = "Duck";
        price = 3;
    }
}
