package com.midgardabc.day12.tanks.bf.tanks;

import com.midgardabc.day12.tanks.ActionField;
import com.midgardabc.day12.tanks.Direction;
import com.midgardabc.day12.tanks.bf.Destroyable;
import com.midgardabc.day12.tanks.bf.Drawable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Bullet implements Drawable, Destroyable {

	private int speed = 5;
	
	private int x;
	private int y;
	private Direction direction;
	private ImageIcon[] images;

	private boolean destroyed;

	public Bullet(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		if ((x > -14 && x < 590) && (y > -14 && y < 590)) {
			this.destroyed = false;
		}
		else {
			this.destroyed = true;
		}
		images = new ImageIcon[4];

			images[0] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/bullet-up.png"));
			images[1] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/bullet-down.png"));
			images[2] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/bullet-left.png"));
			images[3] = new ImageIcon(ActionField.class.getResource("/src/tanksimages/bullet-right.png"));

	}

	private int getDirectionId()
	{
		if (direction == Direction.UP)
		{
			return 0;
		}else if (direction == Direction.DOWN)
		{
			return 1;
		}else if (direction == Direction.LEFT)
		{
			return 2;
		}
		return 3;
	}

	public void updateX(int x) {
		this.x += x;
	}

	public void updateY(int y) {
		this.y += y;
	}
	
	@Override
	public void draw(Graphics g) {
		if (!destroyed) {
			g.drawImage(images[getDirectionId()].getImage(), this.getX(), this.getY(), new ImageObserver() {
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					return false;
				}
			});
		}
	}
	
	public void destroy() {
		destroyed = true;
	}
	
	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	public int getSpeed() {
		return speed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

}
