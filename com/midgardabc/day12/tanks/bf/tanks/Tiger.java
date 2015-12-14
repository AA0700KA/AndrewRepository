package com.midgardabc.day12.tanks.bf.tanks;

import com.midgardabc.day12.tanks.ActionField;
import com.midgardabc.day12.tanks.Direction;
import com.midgardabc.day12.tanks.bf.BFObject;
import com.midgardabc.day12.tanks.bf.BattleField;
import com.midgardabc.day12.tanks.bf.Blank;
import com.midgardabc.day12.tanks.bf.Water;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Tiger extends AbstractTank {
	
	private int armor;
	
	public Tiger(BattleField bf, ActionField af)  {
		super(bf, af);
		armor = 1;
		images = new ImageIcon[4];

			images[0] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/tiger-up.png"));
			images[1] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/tiger-down.png"));
			images[2] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/tiger-left.png"));
			images[3] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/tiger-right.png"));

	}
	
	public Tiger(BattleField bf, int x, int y, Direction direction, ActionField af)  {
		super(bf, x, y, direction, af);
		armor = 1;
		images = new ImageIcon[4];

		images[0] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/tiger-up.png"));
		images[1] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/tiger-down.png"));
		images[2] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/tiger-left.png"));
		images[3] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/tiger-right.png"));
	}
	
	@Override
	public void destroy() {
		if (armor > 0) {
			armor--;
		} else {
			super.destroy();
		}
	}


	@Override
	public Action setUp() {
		if ((this.getX() / 64 == af.getDefender().getX() / 64 || this.getY() / 64 == af.getDefender().getY() / 64) ) {
			if (this.getX() / 64 == af.getDefender().getX() / 64 && this.getY() / 64 < af.getDefender().getY() / 64) {
				this.setDirection(Direction.DOWN);
			} else if (this.getX() / 64 == af.getDefender().getX() / 64 && this.getY() / 64 > af.getDefender().getY() / 64) {
				this.setDirection(Direction.UP);
			} else if (this.getY() / 64 == af.getDefender().getY() / 64 && this.getX() / 64 < af.getDefender().getX() / 64) {
				this.setDirection(Direction.RIGHT);
			} else {
				this.setDirection(Direction.LEFT);
			}
			return Action.FIRE;
		}
		if (xOrY) {
			if (this.getX() < af.getDefender().getX()) {
				this.setDirection(Direction.RIGHT);
			} else {
				this.setDirection(Direction.LEFT);
			}
		} else {
			if (this.getY() < af.getDefender().getY()) {
				this.setDirection(Direction.DOWN);
			} else {
				this.setDirection(Direction.UP);
			}
		}

		BFObject bfObject = bf.nextBfObject(this);

		if (!(bfObject instanceof Blank || bfObject instanceof Water) && !bfObject.isDestroyed()) {
			return Action.FIRE;
		}

		xOrY = !xOrY;
		return Action.MOVE;
	}
}
