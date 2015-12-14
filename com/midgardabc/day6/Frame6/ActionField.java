package com.midgardabc.day6.Frame6;

import com.midgardabc.day6.Frame6.additions.Direction;
import com.midgardabc.day6.Frame6.additions.Drowable;
import com.midgardabc.day6.Frame6.objectsBattleField.Brick;
import com.midgardabc.day6.Frame6.objectsBattleField.Eagle;
import com.midgardabc.day6.Frame6.objectsBattleField.Rock;
import com.midgardabc.day6.Frame6.objectsBattleField.Water;
import com.midgardabc.day6.Frame6.objectsBattleField.Ground;
import com.midgardabc.day6.Frame6.tank.AbstractTank;
import com.midgardabc.day6.Frame6.tank.T34;
import com.midgardabc.day6.Frame6.tank.Tiger;

import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 20.06.2015.
 */
public class ActionField extends JPanel {

    private AbstractTank tank;
    private AbstractTank agressor;
    private Bullet bullet;
    private BattleField bf;
    private Water water;
    private Brick brick;
    private Eagle eagle;
    private Rock rock;
    private Ground ground;

    void runTheGame() throws Exception {
        //tank.setDirection(Direction.DOWN);
        // while(true) {
        //    tank.fire();
        //}
        tank.clean();
        tank.moveRandom();
        System.out.println(tank.toString());

    }

    public ActionField() {
        bf = new BattleField();
        bullet = new Bullet(-100, -100, Direction.UP);
        tank = new T34(this, bf);
        String location = bf.isLocation();
        int x = Integer.parseInt(location.substring(0, location.indexOf("_")));
        int y = Integer.parseInt(location.substring(location.indexOf("_") + 1));
        agressor = new Tiger(x, y, this, bf, Direction.DOWN);
        water = new Water(bf, this);
        brick = new Brick(bf, this);
        eagle = new Eagle(bf, this);
        rock = new Rock(bf, this);
        ground = new Ground();
        bf.createObjects(brick, eagle, rock, water, ground);
        JFrame frame = new JFrame("BATTLE FIELD, DAY 2");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(bf.getBF_WIDTH(), bf.getBF_HEIGHT() + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void tankCordinate(AbstractTank tank) throws Exception {
        String location = bf.isLocation();
        if (location != tank.getX() + "_" + tank.getY()) {
            tank.setX(Integer.parseInt(location.substring(0, location.indexOf("_"))));
            tank.setY(Integer.parseInt(location.substring(location.indexOf("_") + 1)));
        } else {
            tank.setX(64);
            tank.setY(64);
        }
    }


    public void brickDestroy() throws Exception {
        String cordinates = getQuadrant(tank.getX(), tank.getY());
        int separator = cordinates.indexOf("_");
        String getX = cordinates.substring(separator + 1);
        String getY = cordinates.substring(0, separator);
        int x = Integer.parseInt(getX);
        int y = Integer.parseInt(getY);

        if (tank.getDirection() == Direction.UP && y > 0 && bf.isBrick(bf.scanQuadrant(y - 1, x))) {
            tank.fire();
        } else if (tank.getDirection() == Direction.DOWN && y < 8 && bf.isBrick(bf.scanQuadrant(y + 1, x))) {
            tank.fire();
        } else if (tank.getDirection() == Direction.LEFT && x > 0 && bf.isBrick(bf.scanQuadrant(y, x - 1))) {
            tank.fire();
        } else if (tank.getDirection() == Direction.RIGTH && x < 8 && bf.isBrick(bf.scanQuadrant(y, x + 1))) {
            tank.fire();
        }
    }


    public String getQuadrant(int x, int y) {
        int vert = y / 64, gor = x / 64;
        return vert + "_" + gor;
    }

    public String getQuadrantXY(int v, int h) {
        int vertical = (v - 1) * 64;
        int gorisontal = (h - 1) * 64;

        return vertical + "_" + gorisontal;
    }

    boolean processInterception() throws Exception {
        String cordinates = getQuadrant(bullet.getX(), bullet.getY());
        String x1 = cordinates.split("_")[1];
        String y1 = cordinates.split("_")[0];
        int x = Integer.parseInt(x1);
        int y = Integer.parseInt(y1);
        if (x < 9 && x >= 0 && y < 9 && y >= 0)
            if (!bf.scanQuadrant(y, x).equals(ground) && !bf.scanQuadrant(y, x).equals(water) && !bf.scanQuadrant(y, x).equals(rock)) {
                bf.updateQuadrant(y, x, ground);
                return true;
            }
        if (checkCrossingBulletAndTank(agressor)) {
            return true;
        }
        if (checkCrossingBulletAndTank(tank)) {
            return true;
        }

        return false;
    }


    private boolean checkCrossingBulletAndTank(AbstractTank tank) throws Exception
    {
         if (bullet.getDirection() == Direction.DOWN)
         {
             if (tank.getX()+25 == bullet.getX() && tank.getY() == bullet.getY())
             {
                 tank.destroy();
                 return true;
             }
         }else if (bullet.getDirection() == Direction.LEFT)
         {
             if (tank.getX()+50 == bullet.getX() && tank.getY()+25 == bullet.getY())
             {
                 tank.destroy();
                 return true;
             }
         }else if (bullet.getDirection() == Direction.RIGTH)
         {
             if (tank.getX() == bullet.getX() && tank.getY()+25 == bullet.getY())
             {
                 tank.destroy();
                 return true;
             }
         }else{
             if (tank.getX()+25 == bullet.getX() && tank.getY()+50 == bullet.getY())
             {
                 tank.destroy();
                 return true;
             }
         }
        return false;
    }

    public void processFire(Bullet bullet) throws Exception
    {
        this.bullet = bullet;
        bullet.updateX(25);
        bullet.updateY(25);

        while ((bullet.getX() < 576 && bullet.getX() > 0) && (bullet.getY() > 0 && bullet.getY() < 576)) {
            if (tank.getDirection() == Direction.UP)
            {
                bullet.updateY(-1);
                if (processInterception()) {
                    bullet.destroy();
                }
            }
            else if (tank.getDirection() == Direction.DOWN)
            {
                bullet.updateY(1);
                if (processInterception()) {
                    bullet.destroy();
                }
            }
            else if (tank.getDirection() == Direction.LEFT)
            {
                bullet.updateX(-1);
                if (processInterception()) {
                    bullet.destroy();
                }
            }

            else {
                bullet.updateX(1);
                if (processInterception()) {
                    bullet.destroy();
                }
            }
            repaint();
            Thread.sleep(bullet.getSPEED());
        }
    }

    public void processMove(AbstractTank t) throws Exception
    {
        tank = t;
        int covered = 0;
        if ((t.getDirection() == Direction.UP && tank.getY() == 0)||(t.getDirection() == Direction.DOWN && t.getY() >= 512) ||(t.getDirection() == Direction.LEFT && tank.getX() == 0)||(t.getDirection() == Direction.RIGTH && tank.getX() >= 512)) {
            System.out.println(t.getDirection() + "tankX:" + tank.getX() + "tankY:" + tank.getY());
            return;
        }
        t.turn(t.getDirection());


        while (covered < 64) {

            if (t.getDirection() == Direction.UP) {

                t.updateY(-1);
                System.out.println(t.getDirection() + "tankX:" + tank.getX() + "tankY:" + tank.getY());
            }

            else if (t.getDirection() == Direction.DOWN) {

                t.updateY(1);
                System.out.println(t.getDirection() + "tankX:" + tank.getX() + "tankY:" + tank.getY());
            }

            else if (t.getDirection() == Direction.LEFT) {

                t.updateX(-1);
                System.out.println(t.getDirection() + "tankX:" + tank.getX() + "tankY:" + tank.getY());
            }

            else {

                t.updateX(1);
                System.out.println(t.getDirection() + "tankX:" + tank.getX() + "tankY:" + tank.getY());
            }
            covered++;
            repaint();
            Thread.sleep(t.getSpeed());
        }
    }

    public void check(Direction direction) throws Exception
    {
        String cordinates = getQuadrant(tank.getX(), tank.getY());
        int separator = cordinates.indexOf("_");
        String getX = cordinates.substring(separator + 1);
        String getY = cordinates.substring(0, separator);
        int x = Integer.parseInt(getX);
        int y = Integer.parseInt(getY);
        tank.setDirection(direction);
        if (direction == Direction.UP) {
            while(y >= 0) {
                if (bf.isBrick(bf.scanQuadrant(y, x)))
                {
                    tank.fire();
                }
                y--;
            }
        }
        else if (direction == Direction.DOWN)
        {
            while (y <= 8) {
                if (bf.isBrick(bf.scanQuadrant(y, x)))
                {
                    tank.fire();
                }
                y++;
            }
        }
        else if (direction == Direction.LEFT)
        {
            while (x >= 0) {
                if (bf.isBrick(bf.scanQuadrant(y, x)))
                {
                    tank.fire();
                }
                x--;
            }
        }
        else
        {
            while (x <= 8) {
                if (bf.isBrick(bf.scanQuadrant(y, x)))
                {
                    tank.fire();
                }
                x++;
            }
        }

    }

   public void processTurn(AbstractTank t) throws Exception
    {
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        bf.draw(g);
        brick.draw(g);
        eagle.draw(g);
        water.draw(g);
        rock.draw(g);
        tank.draw(g);
        agressor.draw(g);
        bullet.draw(g);
    }

}

