package com.midgardabc.day5.Frame3;


import java.awt.SplashScreen;

/**
 * Created by user on 24.07.2015.
 */
public class Launcher {

    public static void main(String[] args) throws Exception
    {
   //     SplashScreen splash = SplashScreen.getSplashScreen();
        Thread.sleep(5000);
//        splash.close();

        Shop shop = new Shop();

        shop.birdsOnStack();

        shop.buyBird("Andrew", new Eagle(), 3);
        shop.buyBird("Oleg", new Duck(), 2);
        shop.buyBird("Vadim", new Duck(), 1);
        shop.buyBird("Artur", new Duck(), 2);
        shop.buyBird("Sergey", new Duck(), 4);
        shop.buyBird("Vadim", new Duck(), 1);

        shop.getTransactionInfo();
        shop.getStock().printCustomers();


        shop.printPriceInfo();


    }

}
