package com.midgardabc.day6.Frame5;

/**
 * Created by user on 05.07.2015.
 */
public class PrintMenu {

    private static boolean check = false;
    private static int checkCounter = 0;

    public static void print(AbstractDrink drink)
    {
        if (!check)
        {
            System.out.println("NAME \t Ingredientes \t Price");
        }
        System.out.println(drink.getName() + "\t\t\t\t\t" + drink.getPrice());
        check = true;
        checkCounter++;
        for (int i = 0; i < drink.getIngredientes().length; i++)
        {
            if (drink.getIngredientes()[i] != null) {
                System.out.println("            " + drink.getIngredientes()[i].getName() + " " + drink.getIngredientes()[i].getPrice());
            }
        }
        if ((checkCounter % 5) == 0)
        {
            check = false;
        }
    }
}
