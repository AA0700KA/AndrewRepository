package com.midgardabc.day6.Frame6.tank;

import com.midgardabc.day6.Frame6.*;
import com.midgardabc.day6.Frame6.additions.Destroyeble;
import com.midgardabc.day6.Frame6.additions.Direction;
import com.midgardabc.day6.Frame6.additions.Drowable;
import com.midgardabc.day6.Frame6.random.*;

import java.awt.*;

/**
 * Created by user on 20.06.2015.
 */
public abstract class AbstractTank implements Destroyeble, Drowable {
    protected String name;
    private String color;
    private int crew;
    protected int speed = 10;
    private Direction direction;
    protected int x;
    protected int y;
    protected ActionField af;
    protected BattleField bf;


    public AbstractTank(ActionField af, BattleField bf){
        this(512, 128, af, bf, Direction.UP);
    }

    public AbstractTank(int x, int y, ActionField af, BattleField bf, Direction direction) {
        this.x = x;
        this.y = y;
        this.af = af;
        this.bf = bf;
        this.direction = direction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public int getSpeed() {
        return speed;
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void updateX(int x) {
        this.x += x;
    }

    public int getY() {
        return y;
    }

    public void updateY(int y) {
        this.y += y;
    }

    public void destroy() throws Exception
    {
        Thread.sleep(3000);
        af.tankCordinate(this);
    }

    void move(Direction direction) throws Exception {
        this.direction = direction;
        af.brickDestroy();
        af.processMove(this);
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void moveToQuadrant(int v, int h) throws Exception {
        String cordinates = af.getQuadrantXY(v, h);
        int separator =  cordinates.indexOf("_");
        String getX = cordinates.substring(separator + 1);
        String getY = cordinates.substring(0, separator);
        int x = Integer.valueOf(getX);
        int y = Integer.valueOf(getY);
        af.brickDestroy();
        if (getX() < x)
        {
            while (getX() < x)
            {
                move(Direction.RIGTH);
            }
        }
        else {
            while (getX() > x)
            {
                move(Direction.LEFT);
            }
        }

        if (getY() < y)
        {
            while (getY() < y)
            {
                move(Direction.DOWN);
            }
        }
        else {
            while (getY() > y)
            {
                move(Direction.UP);
            }
        }


    }

    public void clean() throws Exception
    {
        for (int i = 0; i < bf.getDimentionY(); i++)
        {
            for (int j = 0; j < bf.getDimentionX(); j++)
            {
                af.check(Direction.UP);
                af.check(Direction.LEFT);
                if (bf.isBrick(bf.scanQuadrant(i, j)))
                {
                    moveToQuadrant(i+1, j+1);
                }
                af.check(Direction.DOWN);

                af.check(Direction.RIGTH);
            }
        }

    }



    public void fire() throws Exception {
        Bullet bullet = new Bullet(x, y, direction);
        af.processFire(bullet);
    }

    public void turn(Direction direction) throws Exception {
        if (this.direction != direction) {
            this.direction = direction;
        }
        af.processTurn(this);
    }

    public void moveRandom() throws Exception
    {
        af.brickDestroy();
        while (true) {
            Random r = new Random();
            int i = r.nextInt(5);
            if (i > 0) {
                if (i == 1) {
                    move(Direction.UP);
                }
                else if (i == 2) {
                    move(Direction.DOWN);
                }
                else if (i == 3) {
                    move(Direction.LEFT);
                }
                else {
                    move(Direction.RIGTH);
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(88, 156, 0));
        g.fillRect(this.getX(), this.getY(), 64, 64);

        g.setColor(new Color(0, 255, 200));
        if (this.getDirection() == Direction.UP) {
            g.fillRect(this.getX() + 20, this.getY(), 24, 34);
        } else if (this.getDirection() == Direction.DOWN) {
            g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
        } else if (this.getDirection() == Direction.LEFT) {
            g.fillRect(this.getX(), this.getY() + 20, 34, 24);
        } else {
            g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
        }
    }
}
