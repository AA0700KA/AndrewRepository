package com.midgardabc.day14.shop.view;

import java.awt.*;

/**
 * Created by user on 03.09.2015.
 */
public class Splash {

    private int percent = 0;

    public void createSplash() throws InterruptedException {
        SplashScreen splash = SplashScreen.getSplashScreen();

        Graphics2D g = splash.createGraphics();
        g.setColor(Color.blue);
        int loading = 0;
        while (loading < 600) {
            g.fillRect(0, 400, loading, 2);
            if (loading % 100 == 0) {
                draw(g);
            }
            Thread.sleep(500);
            loading += 20;
            splash.update();
        }
        Thread.sleep(2000);
        splash.close();
    }

    private void draw(Graphics g) {
        if (percent <= 100) {
            g.drawString("Loading..." + percent + " %", 15, 370);
            percent += 20;
        }
    }

}
