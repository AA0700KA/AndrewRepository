package com.midgardabc.day12.tanks.bf;

import com.midgardabc.day12.tanks.ActionField;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Brick extends BFObject implements Destroyable {

	public Brick(int x, int y) {
		super(x, y);
		image = new ImageIcon(ActionField.class.getResource("/src/tanksimages/brick.png"));
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
