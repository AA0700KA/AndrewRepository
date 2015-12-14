package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Price {

    public static final double WATER = 6;  // price to 1L
    public static final double BLACK_TEA = 9;
    public static final double GREEN_TEA = 9.2;
    public static final double BERGHAMOTE_TEA = 9.6;
    public static final double SUGAR = 1.2; // price for 1 item
    public static final double CHOKOLATE = 10;
    public static final double MILK = 8;
    public static final double LEMON = 5; // price for kilogram
    public static final double COFFEE = 8.8;
    public static final double FOAM = 0.88;

    public static double priceTea(TypeTea type)
    {
        if (type == TypeTea.BLACK)
        {
            return BLACK_TEA;
        }
        else if (type == TypeTea.GREEN)
        {
           return GREEN_TEA;
        }
        return BERGHAMOTE_TEA;
    }

}
