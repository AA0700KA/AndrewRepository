package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class Tea extends AbstractDrink {

    private TypeTea type;
    private final double liters = 0.4;

    public Tea(TypeTea type, double sugar, double milk, double lemon)
    {
        if (milk != 0 && lemon == 0) {
            teaWithIgrediente(type, new Milk(milk), sugar);
        } else {
            teaWithIgrediente(type, new Lemon(lemon), sugar);
        }
    }

    public Tea(TypeTea type, double sugar)
    {
            setType(type);
            ingredientes = new Ingrediente[2];
            ingredientes[WATER] = new Water(liters);
            ingredientes[SUGAR] = new Sugar(sugar);
            price = ingredientes[WATER].getPrice() + Price.priceTea(type) + ingredientes[SUGAR].getPrice();
    }

    private void teaWithIgrediente(TypeTea typeTea, Ingrediente ingrediente, double sugar)
    {
        setType(typeTea);
        ingredientes = new Ingrediente[3];
        ingredientes[WATER] = new Water(liters);
        ingredientes[SUGAR] = new Sugar(sugar);
        ingredientes[2] = ingrediente;
        price = ingredientes[WATER].getPrice() + Price.priceTea(typeTea) + ingredientes[2].getPrice()+ ingredientes[SUGAR].getPrice();
    }


    public TypeTea getType() {
        return type;
    }

    public void setType(TypeTea type) {
        this.type = type;
        if (type == TypeTea.GREEN){
            name = "Green Tea";
        }else if (type == TypeTea.BLACK)
        {
            name = "Black Tea";
        }else{
            name = "Berghamote Tea";
        }
    }

    @Override
    public void drink() {
        PrintMenu.print(this);
    }
}
