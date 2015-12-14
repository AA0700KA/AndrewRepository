package com.midgardabc.day7.tanks.bf.tanks;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import com.midgardabc.day7.tanks.ActionField;
import com.midgardabc.day7.tanks.Direction;
import com.midgardabc.day7.tanks.bf.BattleField;

import javax.imageio.ImageIO;

public class T34 extends AbstractTank {
	
	public T34(BattleField bf, PrintStream ps) throws IOException{
		super(bf, 64, 512, Direction.UP, ps);
		images = new Image[4];
		try {
			images[0] = ImageIO.read(new File("defender-up.png"));
			images[1] = ImageIO.read(new File("defender-down.png"));
			images[2] = ImageIO.read(new File("defender-left.png"));
			images[3] = ImageIO.read(new File("defender-right.png"));
		} catch(IOException e)
		{
			System.err.println("Thare no icon for defender");
		}
	}
	
	public T34(BattleField bf, int x, int y, Direction direction, PrintStream ps) throws IOException{
		super(bf, x, y, direction, ps);
		images = new Image[4];
		try {
			images[0] = ImageIO.read(new File("defender-up.png"));
			images[1] = ImageIO.read(new File("defender-down.png"));
			images[2] = ImageIO.read(new File("defender-left.png"));
			images[3] = ImageIO.read(new File("defender-right.png"));
		} catch(IOException e)
		{
			System.err.println("Thare no icon for defender");
		}
	}

}
