package com.midgardabc.day7.tanks.bf.tanks;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import com.midgardabc.day7.tanks.ActionField;
import com.midgardabc.day7.tanks.Direction;
import com.midgardabc.day7.tanks.bf.BattleField;
import com.midgardabc.day7.tanks.bf.Eagle;
import com.midgardabc.day7.tanks.bf.Rock;

import javax.imageio.ImageIO;

public class BT7 extends AbstractTank {
	
	public BT7(BattleField bf, PrintStream ps) throws IOException {
		super(bf, ps);
		movePath = 2;
		images = new Image[4];
		try {
            images[0] = ImageIO.read(new File("BT-7-up.png"));
			images[1] = ImageIO.read(new File("BT-7-down.png"));
			images[2] = ImageIO.read(new File("BT-7-left.png"));
			images[3] = ImageIO.read(new File("BT-7-right.png"));
		} catch(IOException e)
		{
			System.err.println("Thare no icon for BT-7");
		}

	}
	
	public BT7(BattleField bf, int x, int y, Direction direction, PrintStream ps) throws IOException {
		super(bf, x, y, direction, ps);
		movePath = 2;
		images = new Image[4];
		try {
			images[0] = ImageIO.read(new File("BT-7-up.png"));
			images[1] = ImageIO.read(new File("BT-7-down.png"));
			images[2] = ImageIO.read(new File("BT-7-left.png"));
			images[3] = ImageIO.read(new File("BT-7-right.png"));
		} catch(IOException e)
		{
			System.err.println("Thare no icon for BT-7");
		}
	}

}
