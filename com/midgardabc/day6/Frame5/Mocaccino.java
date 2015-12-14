package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Mocaccino extends Americano {

    public Mocaccino(double sugar)
    {
        super(sugar);
        grams = 0.5;
        name = "Mocaccino";
        ingredientes[CHOKOLATE] = new Chokolate(grams);
        price = ingredientes[WATER].getPrice() + ingredientes[SUGAR].getPrice() + Price.COFFEE + ingredientes[CHOKOLATE].getPrice();
    }

    @Override
    public void drink() {
        PrintMenu.print(this);
    }
}
