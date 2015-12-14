package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Coffee extends AbstractDrink {

    protected final double liters = 0.25;

    public Coffee(double sugar)
    {
        name = "Black Coffee";
        ingredientes = new Ingrediente[5];
        ingredientes[WATER] = new Water(liters);
        ingredientes[SUGAR] = new Sugar(sugar);
        price = ingredientes[WATER].getPrice() + ingredientes[SUGAR].getPrice() + Price.COFFEE;
    }

    @Override
    public void drink() {
        PrintMenu.print(this);
    }
}
