package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Capuccino extends CoffeeWithMilk {

    private final int CHOKOLATE = 3;
    private double chokolateItem = 0.1;
    private double foamItem = 1;

    public Capuccino(double sugar)
    {
        super(sugar);
        name = "Capuccino";
        milkItem = 0.6;
        ingredientes[MILK] = new Milk(milkItem);
        ingredientes[CHOKOLATE] = new Chokolate(chokolateItem);
        ingredientes[FOAM] = new Foam(foamItem);
        price = ingredientes[WATER].getPrice() + ingredientes[SUGAR].getPrice() + Price.COFFEE + ingredientes[CHOKOLATE].getPrice() +
        ingredientes[MILK].getPrice() + ingredientes[FOAM].getPrice();
    }

    @Override
    public void drink() {
        PrintMenu.print(this);
    }
}
