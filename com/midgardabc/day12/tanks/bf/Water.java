package com.midgardabc.day12.tanks.bf;

import com.midgardabc.day12.tanks.ActionField;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Water extends BFObject {
	
	public Water(int x, int y) {
		super(x, y);
		image = new ImageIcon(ActionField.class.getResource("/src/tanksimages/water.png"));
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		Composite org = g2D.getComposite();
		Composite translucent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
		g2D.setComposite(translucent);
		g.drawImage(image.getImage(), this.getX(), this.getY(), new ImageObserver() {
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				return false;
			}
		});
		g2D.setComposite(org);
	}
}
