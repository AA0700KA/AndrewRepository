package com.midgardabc.day6.Frame6;

import com.midgardabc.day6.Frame6.additions.Destroyeble;
import com.midgardabc.day6.Frame6.additions.Direction;
import com.midgardabc.day6.Frame6.additions.Drowable;

import java.awt.*;

/**
 * Created by user on 20.06.2015.
 */
public class Bullet implements Destroyeble, Drowable {
    private int x;
    private int y;
    private final int SPEED = 3;
    private Direction direction;

    public int getSPEED() {
        return SPEED;
    }

    public int getX() {
        return x;
    }

    public Bullet(int x, int y, Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getY() {
        return y;
    }

    public void updateX(int x){
        this.x += x;
    }

    public void updateY(int y)
    {
        this.y += y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void destroy()
    {
        x = -100;
        y = -100;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255, 255, 0));
        g.fillRect(this.getX(), this.getY(), 14, 14);
    }
}
