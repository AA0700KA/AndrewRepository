package com.midgardabc.day12.tanks.bf.tanks;

import com.midgardabc.day12.tanks.Direction;
import com.midgardabc.day12.tanks.bf.Destroyable;
import com.midgardabc.day12.tanks.bf.Drawable;

import java.util.List;

public interface Tank extends Drawable,Destroyable {
	
	Action setUp();

	Bullet fire();
	
	Direction getDirection();
	
	void updateX(int x);

	void updateY(int y);
	
	int getSpeed();

	void setDirection(Direction direction);

	void turn(Direction direction);

	Bullet getBullet();

	void setBullet(Bullet bullet);

	void setX(int x);

	void setY(int y);

}
