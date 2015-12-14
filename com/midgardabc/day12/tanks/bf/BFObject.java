package com.midgardabc.day12.tanks.bf;

import com.midgardabc.day12.tanks.ActionField;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


public class BFObject implements Drawable {


	private int x;
	private int y;

	protected ImageIcon image;

	protected boolean isDestroyed;
	
	public BFObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

	public ImageIcon getImage() {
		return image;
	}

	@Override
	public void draw(Graphics g) {
		if (!isDestroyed) {
			g.drawImage(image.getImage(), this.getX(), this.getY(), new ImageObserver() {
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					return false;
				}
			});
		}else {
				g.drawImage(new ImageIcon(ActionField.class.getResource("/src/tanksimages/blank.png")).getImage(), this.getX(), this.getY(), new ImageObserver() {
					@Override
					public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
						return false;
					}
				});
		}
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}


	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
