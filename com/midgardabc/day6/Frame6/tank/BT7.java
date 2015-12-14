package com.midgardabc.day6.Frame6.tank;

import com.midgardabc.day6.Frame6.ActionField;
import com.midgardabc.day6.Frame6.BattleField;
import com.midgardabc.day6.Frame6.additions.Direction;

/**
 * Created by user on 20.06.2015.
 */
public class BT7 extends AbstractTank {

    public BT7(ActionField af, BattleField bf) {
        super(af, bf);
        name = "BT7";
        speed = super.speed/2;
    }

    public BT7(int x, int y, ActionField af, BattleField bf, Direction direction) {
        super(x, y, af, bf, direction);
        speed = super.speed/2;
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
