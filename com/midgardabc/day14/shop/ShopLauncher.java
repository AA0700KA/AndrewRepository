package com.midgardabc.day14.shop;


import com.midgardabc.day14.shop.view.BirdsUI;
import com.midgardabc.day14.shop.view.Splash;

/**
 * Created by user on 24.07.2015.
 */
public class ShopLauncher {

    public static void main(String[] args) throws Exception
    {
        Splash splash = new Splash();
        splash.createSplash();
        new BirdsUI();
    }

}
