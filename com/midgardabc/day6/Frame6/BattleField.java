package com.midgardabc.day6.Frame6;

/**
 * Created by user on 20.06.2015.
 */
import com.midgardabc.day6.Frame6.additions.Drowable;
import com.midgardabc.day6.Frame6.random.*;

import com.midgardabc.day6.Frame6.objectsBattleField.*;

import java.awt.*;

public class BattleField implements Drowable {
    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;

    final boolean COLORDED_MODE = false;

    private Brick brick;
    private Eagle eagle;
    private Rock rock;
    private Water water;
    private Ground ground;
    private Object[][] battleField;

    public void createObjects(Brick brick, Eagle eagle, Rock rock, Water water, Ground ground) {
        this.eagle = eagle;
        this.brick = brick;
        this.rock = rock;
        this.water = water;
        this.ground = ground;
        battleField = new Object[][]{
                {ground, ground, ground, ground, ground, brick, ground, ground, brick},
                {ground, ground, ground, brick, ground, ground, brick, brick, brick},
                {water, water, water, ground, ground, brick, ground, ground, ground},
                {water, water, water, brick, brick, brick, water, water, water},
                {rock, brick, ground, ground, ground, ground, water, water, water},
                {ground, rock, brick, ground, ground, ground, ground, ground, eagle},
                {rock, brick, ground, ground, ground, ground, ground, ground, ground},
                {ground, ground, ground, brick, brick, ground, brick, brick, brick},
                {ground, brick, ground, ground, eagle, ground, brick, brick, ground}
        };
    }




	/**/

    public Object scanQuadrant(int v, int h)
    {
        return battleField[v][h];
    }


	/**/

    void updateQuadrant(int v, int h, Object object)
    {
        if (v >= 0 && v < 9 && h >= 0 && h < 9)
        {
            battleField[v][h] = object;
        }
    }

    public boolean isBrick(Object object)
    {
        if (object.equals(brick))
        {
            return true;
        }
        return false;
    }

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public String isLocation() {
        Random r = new Random();
        return r.returnLocation();
    }


    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    public int getDimentionX()
    {
        return BF_WIDTH/64;
    }

    public int getDimentionY()
    {
        return BF_HEIGHT/64;
    }

    public BattleField() {

    }

    public BattleField(String[][] battleField)  {
        this.battleField = battleField;
    }

    public void draw(Graphics g)
    {
        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if (COLORDED_MODE) {
                    if (i % 2 == 0) {
                        cc = new Color(252, 241, 177);
                    } else {
                        cc = new Color(233, 243, 255);
                    }
                } else {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }
    }


}
