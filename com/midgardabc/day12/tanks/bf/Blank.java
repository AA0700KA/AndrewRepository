package com.midgardabc.day12.tanks.bf;

import com.midgardabc.day12.tanks.ActionField;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Blank extends BFObject {
	
	public Blank(int x, int y) {
		super(x, y);
		image = new ImageIcon(ActionField.class.getResource("/src/tanksimages/blank.png"));
	}

}
