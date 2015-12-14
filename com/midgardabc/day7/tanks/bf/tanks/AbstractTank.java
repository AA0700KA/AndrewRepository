package com.midgardabc.day7.tanks.bf.tanks;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.midgardabc.day7.tanks.Direction;
import com.midgardabc.day7.tanks.bf.BFObject;
import com.midgardabc.day7.tanks.bf.BattleField;
import com.midgardabc.day7.tanks.bf.Water;

public abstract class AbstractTank implements Tank {
	
	private int speed = 10;
	protected int movePath = 1;
	protected Image[] images;
	private PrintStream ps;

	// 1 - up, 2 - down, 3 - left, 4 - right
	private Direction direction;

	// current position on BF
	private int x;
	private int y;
	
	private boolean destroyed;
	
	protected BattleField bf;
	
	protected Color tankColor;
	protected Color towerColor;

	protected Action action;

	public void setActoin(Action actoin) {
		this.action = actoin;
	}

	@Override
	public Action setUp() {
		return action;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
		System.err.println(direction + "_" + this.getClass().getSimpleName());
	}
	
	public AbstractTank(BattleField bf, PrintStream ps) throws IOException {
		this(bf,  128, 512, Direction.UP, ps);
	}


	public AbstractTank(BattleField bf, int x, int y, Direction direction, PrintStream ps) throws IOException {
		this.ps = ps;
		System.setErr(ps);
		this.bf = bf;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.destroyed = false;
	}

	public void turn(Direction direction) {
		this.direction = direction;
	}

	public void move(Direction direction) {
		this.direction = direction;
		System.err.println(direction + "_" + this.getClass().getSimpleName());
		action = Action.MOVE;
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
			g.drawImage(images[getDirectionId()], this.getX(), this.getY(), new ImageObserver() {
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					return false;
				}
			});

			BFObject bfObject = bf.scanQuadrant(this.getY()/64, this.getX()/64);

			if (bfObject instanceof Water)
			{
				Water w = (Water) bfObject;
				Graphics2D g2D = (Graphics2D) g;
				Composite org = g2D.getComposite();
				Composite translucent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
				g2D.setComposite(translucent);
				g.drawImage(w.getImage(), w.getX(), w.getY(), new ImageObserver() {
						@Override
						public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
							return false;
						}
					});
				g2D.setComposite(org);
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
	
	public void moveToQuadrant(int v, int h) throws Exception {
		int x = h*64;
		int y = v*64;
		if (getX() < x)
		{
			while (getX() < x)
			{
				move(Direction.RIGHT);
			}
		}
		else {
			while (getX() > x)
			{
				move(Direction.LEFT);
			}
		}

		if (getY() < y)
		{
			while (getY() < y)
			{
				move(Direction.DOWN);
			}
		}
		else {
			while (getY() > y)
			{
				move(Direction.UP);
			}
		}
	}
	
	public void moveRandom() throws Exception { 
	}

	public void clean() throws Exception {
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
	public int getMovePath() {
		return movePath;
	}



}