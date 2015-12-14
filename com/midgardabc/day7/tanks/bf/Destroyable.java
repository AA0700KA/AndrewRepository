package com.midgardabc.day7.tanks.bf;

public interface Destroyable extends Cordinate {
	
	public void destroy();
	
	public boolean isDestroyed();

	void setDestroyed(boolean destroyed);

	
}
