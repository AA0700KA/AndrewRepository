package com.midgardabc.day6.Frame6.tank;

import com.midgardabc.day6.Frame6.ActionField;
import com.midgardabc.day6.Frame6.BattleField;
import com.midgardabc.day6.Frame6.additions.Direction;

import java.awt.*;

/**
 * Created by user on 20.06.2015.
 */
public class Tiger extends AbstractTank {

    int armor;

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;

    }

    public Tiger(int x, int y, ActionField af, BattleField bf, Direction direction) {
        super(x, y, af, bf, direction);
        armor = 1;

    }

    public Tiger(ActionField af, BattleField bf) {
        super(af, bf);
        name = "Tiger";
        armor = 1;
    }

    @Override
    public void destroy() throws Exception {
        if (armor == 0) {
            Thread.sleep(3000);
            af.tankCordinate(this);
            armor = 1;
        }else armor--;
    }

    @Override
    public String toString() {
        return name + " Speed - " + getSpeed();
    }

    @Override
    void move(Direction direction) throws Exception {
        super.move(direction);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(55, 12, 170));
        g.fillRect(this.getX(), this.getY(), 64, 64);

        g.setColor(new Color(222, 155, 200));
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
