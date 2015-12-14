package com.midgardabc.day12.tanks.bf.tanks;

import com.midgardabc.day12.tanks.ActionField;
import com.midgardabc.day12.tanks.Direction;
import com.midgardabc.day12.tanks.bf.BFObject;
import com.midgardabc.day12.tanks.bf.BattleField;
import com.midgardabc.day12.tanks.bf.Water;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public abstract class AbstractTank implements Tank {

	protected int speed = 10;
	protected ImageIcon[] images;
    protected ActionField af;
	protected boolean xOrY = true;
	private Bullet bullet;
	private Direction direction;
	private int x;
	private int y;
	private boolean destroyed;
	protected BattleField bf;

	@Override
	public Bullet getBullet() {
		return bullet;
	}

	@Override
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	public AbstractTank(BattleField bf, ActionField af)  {
		this(bf, 128, 512, Direction.UP, af);
	}

	public AbstractTank(BattleField bf, int x, int y, Direction direction, ActionField af)  {
		this.af = af;
		bullet = new Bullet(-100, -100, Direction.NONE);
		this.bf = bf;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.destroyed = false;
	}

	public void turn(Direction direction) {
		this.direction = direction;
	}

	public Bullet fire() {
		int bulletX = -100;
		int bulletY = -100;
		if (direction == Direction.UP) {
			bulletX = x + 25;
			bulletY = y;
		} else if (direction == Direction.DOWN) {
			bulletX = x + 25;
			bulletY = y + 64;
		} else if (direction == Direction.LEFT) {
			bulletX = x;
			bulletY = y + 25;
		} else if (direction == Direction.RIGHT) {
			bulletX = x + 64;
			bulletY = y + 25;
		}
		return new Bullet(bulletX, bulletY, direction);
	}
	
	public void draw(Graphics g) {
		if (!destroyed) {
			g.drawImage(images[getDirectionId()].getImage(), this.getX(), this.getY(), new ImageObserver() {
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					return false;
				}
			});

			for (Water water : bf.getWaters()) {
				water.draw(g);
			}
		}
	}
	
	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	public void destroy() {
		destroyed = true;
	}

	protected int getDirectionId()
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

	public Direction getDirection() {
		return direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	protected Direction randomDirection() {
		Random random = new Random();
		Direction[] directions = Direction.values();
		int index = random.nextInt(5);
		if (index == 0) {
			index += 1;
		}
		return directions[index];
	}

}