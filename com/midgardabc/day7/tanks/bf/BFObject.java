package com.midgardabc.day7.tanks.bf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


public class BFObject implements Drawable {

	// current position on BF
	private int x;
	private int y;

	protected Image image;

	protected boolean isDestroyed;
	
	public BFObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

	public Image getImage() {
		return image;
	}

	@Override
	public void draw(Graphics g) {
		if (!isDestroyed) {
			g.drawImage(image, this.getX(), this.getY(), new ImageObserver() {
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					return false;
				}
			});
		}else {
			try {
				g.drawImage(ImageIO.read(new File("blank.png")), this.getX(), this.getY(), new ImageObserver() {
					@Override
					public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
						return false;
					}
				});
			} catch (IOException e)
			{
				System.err.println("Only use blank.png");
			}
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
