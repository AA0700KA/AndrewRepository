package com.midgardabc.day8.Frame4;

import java.util.Random;

import java.awt.*;

/**
 * Created by user on 26.07.2015.
 */
public class Ball {

    private int x;
    private int y;
    private String text;
    private AgreeFriend af;
    private int speed = 1;

    public void setText(String text) {
        this.text = text;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ball(AgreeFriend af)
    {
        this.af = af;
        text = "Click me:)";
        x = 256;
        y = 192;
    }

    public void moveDrive() throws Exception
    {
        Random r = new Random();
        int i = r.nextInt(5);
        if (i > 0)
        {
            processMove(i);
        }

    }

    public void processMove(int direction) throws Exception
    {
        int covered = 0;
        if ((direction == 1 && y == 0) || (direction == 2 && y >= 512) || (direction == 3 && x == 0) || (direction == 4 && x >= 512))
        {
            x = 256;
            y = 192;
            af.repaint();
            return;
        }
        while (covered < 64)
        {
            if (direction == 1)
            {
                y--;
            } else if (direction == 2)
            {
                y++;
            }
            else if (direction == 3)
            {
                x--;
            }
            else {
                x++;
            }
            covered++;
            Thread.sleep(speed);
            af.repaint();
        }
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillOval(x, y, 64, 64);
        g.setColor(Color.BLACK);
        g.drawString(text, x + 5, y + 35);
    }

}
