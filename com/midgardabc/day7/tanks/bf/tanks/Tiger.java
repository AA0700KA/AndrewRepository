package com.midgardabc.day7.tanks.bf.tanks;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import com.midgardabc.day7.tanks.ActionField;
import com.midgardabc.day7.tanks.Direction;
import com.midgardabc.day7.tanks.bf.BattleField;

import javax.imageio.ImageIO;

public class Tiger extends AbstractTank {
	
	private int armor;
	
	public Tiger(BattleField bf, PrintStream ps) throws IOException {
		super(bf, ps);
		armor = 1;
		images = new Image[4];
		try {
			images[0] = ImageIO.read(new File("tiger-up.png"));
			images[1] = ImageIO.read(new File("tiger-down.png"));
			images[2] = ImageIO.read(new File("tiger-left.png"));
			images[3] = ImageIO.read(new File("tiger-right.png"));
		} catch(IOException e)
		{
			System.err.println("Thare no icon for tiger");
		}
	}
	
	public Tiger(BattleField bf, int x, int y, Direction direction, PrintStream ps) throws IOException {
		super(bf, x, y, direction, ps);
		armor = 1;
		images = new Image[4];
		try {
			images[0] = ImageIO.read(new File("tiger-up.png"));
			images[1] = ImageIO.read(new File("tiger-down.png"));
			images[2] = ImageIO.read(new File("tiger-left.png"));
			images[3] = ImageIO.read(new File("tiger-right.png"));
		} catch(IOException e)
		{
			System.err.println("Thare no icon for tiger");
		}
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
	public Bullet fire() {
		int bulletX = -100;
		int bulletY = -100;
		if (this.getDirection() == Direction.UP) {
			bulletX = this.getX() + 25;
			bulletY = this.getY();
		} else if (this.getDirection() == Direction.DOWN) {
			bulletX = this.getX() + 25;
			bulletY = this.getY() + 64;
		} else if (this.getDirection()== Direction.LEFT) {
			bulletX = this.getX();
			bulletY = this.getY() + 25;
		} else if (this.getDirection() == Direction.RIGHT) {
			bulletX = this.getX() + 64;
			bulletY = this.getY() + 25;
		}
		return new TigerBullet(bulletX, bulletY, this.getDirection());
	}
}
