package com.midgardabc.day7.tanks.bf.tanks;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import com.midgardabc.day7.tanks.Direction;
import com.midgardabc.day7.tanks.bf.Destroyable;
import com.midgardabc.day7.tanks.bf.Drawable;

import javax.imageio.ImageIO;

public class Bullet implements Drawable, Destroyable {

	private int speed = 5;
	
	private int x;
	private int y;
	private Direction direction;
	private Image[] images;

	private boolean destroyed;

	public Bullet(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.destroyed = false;
		images = new Image[4];
		try {
			images[0] = ImageIO.read(new File("bullet-up.png"));
			images[1] = ImageIO.read(new File("bullet-down.png"));
			images[2] = ImageIO.read(new File("bullet-left.png"));
			images[3] = ImageIO.read(new File("bullet-right.png"));
		} catch(IOException e)
		{
			System.err.println("Thare no icon for BT-7");
		}
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
			g.drawImage(images[getDirectionId()], this.getX(), this.getY(), new ImageObserver() {
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
	
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
}
