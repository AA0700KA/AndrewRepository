package com.midgardabc.day6.Frame6.objectsBattleField;

import com.midgardabc.day6.Frame6.ActionField;
import com.midgardabc.day6.Frame6.BattleField;
import com.midgardabc.day6.Frame6.additions.Destroyeble;
import com.midgardabc.day6.Frame6.additions.Drowable;

import java.awt.*;

/**
 * Created by user on 06.07.2015.
 */
public class Rock implements Drowable, Destroyeble {

    BattleField bf;
    ActionField af;

    private int x;
    private int y;

    public Rock(BattleField bf, ActionField af) {
        this.bf = bf;
        this.af = af;
    }

    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < bf.getDimentionY(); j++) {
            for (int k = 0; k < bf.getDimentionX(); k++) {
                if (bf.scanQuadrant(j, k).equals(this)) {
                    String coordinates = af.getQuadrantXY(j + 1, k + 1);
                    int separator = coordinates.indexOf("_");
                    y = Integer.parseInt(coordinates.substring(0, separator));
                    x = Integer.parseInt(coordinates.substring(separator + 1));
                    g.setColor(new Color(0, 0, 0));
                    g.fillRect(x, y, 64, 64);
                }
            }
        }
    }

    @Override
    public void destroy() {
        y = -100;
        x = -100;
    }
}

