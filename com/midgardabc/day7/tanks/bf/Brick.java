package com.midgardabc.day7.tanks.bf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Brick extends BFObject implements Destroyable {


	public Brick(int x, int y) {
		super(x, y);
		try {
			image = ImageIO.read(new File("brick.png"));
		} catch(IOException e)
		{
			System.err.println("Thare no icon for brick");
		}
	}

	@Override
	public void destroy() {
		isDestroyed = true;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		isDestroyed = destroyed;
	}
}
