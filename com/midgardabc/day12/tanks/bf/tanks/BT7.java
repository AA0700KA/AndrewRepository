package com.midgardabc.day12.tanks.bf.tanks;

import com.midgardabc.day12.tanks.ActionField;
import com.midgardabc.day12.tanks.Direction;
import com.midgardabc.day12.tanks.bf.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class BT7 extends AbstractTank {

	
	public BT7(BattleField bf, ActionField af) {
		super(bf, af);
		speed = 8;
		images = new ImageIcon[4];

			images[0] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/BT-7-up.png"));
			images[1] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/BT-7-down.png"));
			images[2] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/BT-7-left.png"));
			images[3] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/BT-7-right.png"));
	}
	
	public BT7(BattleField bf, int x, int y, Direction direction, ActionField af)  {
		super(bf, x, y, direction, af);
		speed = 8;
		images = new ImageIcon[4];

			images[0] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/BT-7-up.png"));
			images[1] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/BT-7-down.png"));
			images[2] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/BT-7-left.png"));
			images[3] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/BT-7-right.png"));

	}

	@Override
	public Action setUp() {

			if ((this.getX() == af.getEagle().getX() || this.getY() == af.getEagle().getY()) && !bf.checkToRock(this, af.getEagle())) {
				if (this.getX() == af.getEagle().getX() && this.getY() < af.getEagle().getY()) {
					this.setDirection(Direction.DOWN);
				} else if (this.getX() == af.getEagle().getX() && this.getY() > af.getEagle().getY()) {
					this.setDirection(Direction.UP);
				} else if (this.getY() == af.getEagle().getY() && this.getX() < af.getEagle().getX()) {
					this.setDirection(Direction.RIGHT);
				} else {
					this.setDirection(Direction.LEFT);
				}
				return Action.FIRE;
			}

		if (!af.getDefender().isDestroyed()) {
			if ((this.getX() / 64 == af.getDefender().getX() / 64 || this.getY() / 64 == af.getDefender().getY() / 64) && !bf.checkToRock(this, af.getDefender())) {
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
		}
			if (xOrY) {
				if (this.getX() < af.getEagle().getX()) {
					this.setDirection(Direction.RIGHT);
				} else {
					this.setDirection(Direction.LEFT);
				}
			} else {
				if (this.getY() < af.getEagle().getY()) {
					this.setDirection(Direction.DOWN);
				} else {
					this.setDirection(Direction.UP);
				}
			}

			BFObject bfObject = bf.nextBfObject(this);
		    if ( bfObject instanceof Rock && !bfObject.isDestroyed()) {
				 xOrY = !xOrY;
				this.setDirection(randomDirection());
				System.err.println(this.getY() + "_" + this.getX() + ":" + this.getDirection());
				if ((bf.nextBfObject(this) instanceof Brick || bf.nextBfObject(this) instanceof Eagle) && !bf.nextBfObject(this).isDestroyed()) {
					return Action.FIRE;
				}
		    }

			if ( (bfObject instanceof Brick || bfObject instanceof Eagle) && !bfObject.isDestroyed()) {
				return Action.FIRE;
			}

		   if (bf.nextBfObject(this) != null && bf.nextBfObject(this).getX() == af.getTiger().getX() && bf.nextBfObject(this).getY() == af.getTiger().getY() && !af.getTiger().isDestroyed()) {
			   return Action.FIRE;
		   }

		    xOrY = !xOrY;
			return Action.MOVE;
		}


}
