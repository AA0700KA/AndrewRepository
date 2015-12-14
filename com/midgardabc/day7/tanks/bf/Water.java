package com.midgardabc.day7.tanks.bf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Water extends BFObject {
	
	public Water(int x, int y) {
		super(x, y);
		try {
			image = ImageIO.read(new File("water.png"));
		} catch(IOException e)
		{
			System.err.println("Thare no icon for water");
		}
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		Composite org = g2D.getComposite();
		Composite translucent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
		g2D.setComposite(translucent);
		g.drawImage(image, this.getX(), this.getY(), new ImageObserver() {
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				return false;
			}
		});
		g2D.setComposite(org);
	}
}
