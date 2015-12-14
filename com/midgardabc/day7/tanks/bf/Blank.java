package com.midgardabc.day7.tanks.bf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Blank extends BFObject {
	
	public Blank(int x, int y) {
		super(x, y);
		try {
			image = ImageIO.read(new File("blank.png"));
		} catch(IOException e)
		{
			System.err.println("Thare no icon for blank");
		}
	}
}
