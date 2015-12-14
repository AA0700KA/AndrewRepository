package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Launcher {

    public static void main(String[] args) {
         Launcher l = new Launcher();
        l.Brew(new Tea(TypeTea.BLACK, 2));
        l.Brew(new Capuccino(3));
        l.Brew(new Americano(2));
        l.Brew(new CoffeeWithMilk(4));
        l.Brew(new Tea(TypeTea.GREEN, 1, 0.5, 0));
        l.Brew(new Coffee(1));
        l.Brew(new Tea(TypeTea.BERGHAMOTE, 2, 0, 0.6));
    }

    public void Brew(Drink drink)
    {
        drink.drink();
    }
}
