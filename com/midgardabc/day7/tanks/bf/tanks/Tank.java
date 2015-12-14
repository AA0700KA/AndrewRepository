package com.midgardabc.day7.tanks.bf.tanks;

import com.midgardabc.day7.tanks.Direction;
import com.midgardabc.day7.tanks.bf.Destroyable;
import com.midgardabc.day7.tanks.bf.Drawable;

public interface Tank extends Drawable,Destroyable {
	
	public Action setUp();

	public void move(Direction direction);

	public Bullet fire();
	
	public Direction getDirection();
	
	public void updateX(int x);

	public void updateY(int y);
	
	public int getSpeed();
	
	public int getMovePath();

	void setDirection(Direction direction);

	void turn(Direction direction);

	public void setActoin(Action actoin);

	void moveToQuadrant(int v, int h) throws Exception;


}
