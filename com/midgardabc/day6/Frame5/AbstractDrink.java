package com.midgardabc.day6.Frame5;

/**
 * Created by user on 04.07.2015.
 */
public abstract class AbstractDrink implements Drink {

    public final int WATER = 0;
    public final int SUGAR = 1;
    public final int MILK = 2;
    public final int CHOKOLATE = 2;
    public final int FOAM = 4;

    protected String name;
    protected double price;
    protected Ingrediente[] ingredientes;

    @Override
    public void drink() {
        PrintMenu.print(this);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Ingrediente[] getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Ingrediente[] ingredientes) {
        this.ingredientes = ingredientes;
    }
}
