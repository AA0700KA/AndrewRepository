package com.midgardabc.day12.tanks.bf;

public interface Destroyable extends Cordinate {
	
	void destroy();
	
	boolean isDestroyed();

	void setDestroyed(boolean destroyed);

	
}
