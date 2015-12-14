package com.midgardabc.day6.Frame6.tank;

import com.midgardabc.day6.Frame6.ActionField;
import com.midgardabc.day6.Frame6.BattleField;
import com.midgardabc.day6.Frame6.additions.Direction;

/**
 * Created by user on 20.06.2015.
 */
public class T34 extends AbstractTank {

    public T34(ActionField af, BattleField bf) {
        super(af, bf);
        name = "T34";
    }



    @Override
    public String toString() {
        return name + " Speed - " + speed;
    }

    @Override
    void move(Direction direction) throws Exception {
        super.move(direction);
    }
}
