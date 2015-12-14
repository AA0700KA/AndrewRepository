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

public class T34 extends AbstractTank {
	
	public T34(BattleField bf, ActionField af) {
		super(bf, 64, 512, Direction.UP, af);
		images = new ImageIcon[4];

			images[0] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/defender-up.png"));
			images[1] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/defender-down.png"));
			images[2] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/defender-left.png"));
			images[3] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/defender-right.png"));

	}
	
	public T34(BattleField bf, int x, int y, Direction direction, ActionField af) {
		super(bf, x, y, direction, af);
		images = new ImageIcon[4];

			images[0] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/defender-up.png"));
			images[1] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/defender-down.png"));
			images[2] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/defender-left.png"));
			images[3] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/defender-right.png"));

	}

	@Override
	public Action setUp() {

		if (!af.getAggressor().isDestroyed()) {
			if ((this.getX() / 64 == af.getAggressor().getX()/ 64 || this.getY()/ 64 == af.getAggressor().getY()/ 64) && !bf.checkToRock(this, af.getAggressor())) {
				if (this.getX()/ 64 == af.getAggressor().getX()/ 64 && this.getY()/64 < af.getAggressor().getY()/ 64) {
					this.setDirection(Direction.DOWN);
				} else if (this.getX()/ 64 == af.getAggressor().getX()/ 64 && this.getY()/ 64 > af.getAggressor().getY()/ 64) {
					this.setDirection(Direction.UP);
				} else if (this.getY()/ 64 == af.getAggressor().getY()/ 64 && this.getX()/ 64 < af.getAggressor().getX()/ 64) {
					this.setDirection(Direction.RIGHT);
				} else {
					this.setDirection(Direction.LEFT);
				}
				return Action.FIRE;
			}
            if (!af.getTiger().isDestroyed()) {
				if ((this.getX() / 64 == af.getTiger().getX() / 64 || this.getY() / 64 == af.getTiger().getY() / 64) && !bf.checkToRock(this, af.getTiger())) {
					if (this.getX() / 64 == af.getTiger().getX() / 64 && this.getY() / 64 < af.getTiger().getY() / 64) {
						this.setDirection(Direction.DOWN);
					} else if (this.getX() / 64 == af.getTiger().getX() / 64 && this.getY() / 64 > af.getTiger().getY() / 64) {
						this.setDirection(Direction.UP);
					} else if (this.getY() / 64 == af.getTiger().getY() / 64 && this.getX() / 64 < af.getTiger().getX() / 64) {
						this.setDirection(Direction.RIGHT);
					} else {
						this.setDirection(Direction.LEFT);
					}
					return Action.FIRE;
				}
			}

			if (xOrY) {
				if (this.getX() < af.getAggressor().getX()) {
					this.setDirection(Direction.RIGHT);
				} else {
					this.setDirection(Direction.LEFT);
				}
			} else {
				if (this.getY() < af.getAggressor().getY()) {
					this.setDirection(Direction.DOWN);
				} else {
					this.setDirection(Direction.UP);
				}
			}

			BFObject bfObject = bf.nextBfObject(this);
			if (bfObject instanceof Brick && !bfObject.isDestroyed()) {
				return Action.FIRE;
			}
			if ((bfObject instanceof Rock || bfObject instanceof Eagle) && !bfObject.isDestroyed()) {
				xOrY = !xOrY;
				this.setDirection(randomDirection());
				if (bf.nextBfObject(this) instanceof Brick  && !bf.nextBfObject(this).isDestroyed()) {
					return Action.FIRE;
				}
			}
			xOrY = !xOrY;
			return Action.MOVE;
		} else {
			if ((this.getX() / 64 == af.getTiger().getX() / 64 || this.getY() / 64 == af.getTiger().getY() / 64)  && !bf.checkToRock(this, af.getTiger())) {
				if (this.getX() / 64 == af.getTiger().getX() / 64 && this.getY() / 64 < af.getTiger().getY() / 64) {
					this.setDirection(Direction.DOWN);
				} else if (this.getX() / 64 == af.getTiger().getX() / 64 && this.getY() / 64 > af.getTiger().getY() / 64) {
					this.setDirection(Direction.UP);
				} else if (this.getY() / 64 == af.getTiger().getY() / 64 && this.getX() / 64 < af.getTiger().getX() / 64) {
					this.setDirection(Direction.RIGHT);
				} else {
					this.setDirection(Direction.LEFT);
				}
				return Action.FIRE;
			}
			if (xOrY) {
				if (this.getX() < af.getTiger().getX()) {
					this.setDirection(Direction.RIGHT);
				} else {
					this.setDirection(Direction.LEFT);
				}
			} else {
				if (this.getY() < af.getTiger().getY()) {
					this.setDirection(Direction.DOWN);
				} else {
					this.setDirection(Direction.UP);
				}
			}

			BFObject bfObject = bf.nextBfObject(this);
			if (bfObject instanceof Brick && !bfObject.isDestroyed()) {
				return Action.FIRE;
			}
			if ((bfObject instanceof Rock || bfObject instanceof Eagle) && !bfObject.isDestroyed()) {
				xOrY = !xOrY;
				this.setDirection(randomDirection());
				if (bf.nextBfObject(this) instanceof Brick  && !bf.nextBfObject(this).isDestroyed()) {
					return Action.FIRE;
				}
			}
			xOrY = !xOrY;
			return Action.MOVE;

		}
	}

}
