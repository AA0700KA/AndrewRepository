package com.midgardabc.day7.tanks.bf;

import com.midgardabc.day7.tanks.bf.tanks.Tank;

import java.awt.Graphics;
import java.util.Arrays;

public class BattleField implements Drawable {
	
	public static final String BRICK = "B";
	public static final String EAGLE = "E";
	public static final String ROCK = "R";
	public static final String WATER = "W";
	
	private int bfWidth = 576;
	private int bfHeight = 576;

	private Eagle eagle;


	private String[][] battleFieldTemplate = {
			{"B", "B", "B", "B", "B", "B", "B", "B", "B"},
			{" ", " ", " ", " ", " ", "B", "B", "B", " "},
			{" ", "B", " ", " ", "B", "B", "B", "B", "B"},
			{" ", "B", "R", "R", "R", " ", "R", "R", "R"},
			{"W", "W", "W", " ", "B", "W", "W", "W", "W"},
			{"W", "W", " ", "B", " ", "R", " ", "W", "W"},
			{"W", "R", " ", " ", " ", " ", " ", "B", "B"},
			{"R", "R", " ", "R", "B", "B", " ", " ", "B"},
			{"R", " ", " ", "R", "E", "B", " ", " ", "B"}
		};

	public String[][] getBattleFieldTemplate() {
		return battleFieldTemplate;
	}

	private BFObject[][] battleField =  new BFObject[9][9];

	public Eagle getEagle() {
		return eagle;
	}

	public BattleField() {
		drawBattleField();
	}

	public BFObject[][] getBattleField() {
		return battleField;
	}


	public BattleField(String[][] battleField) {
		this.battleFieldTemplate = battleField;
		drawBattleField();
	}

	
	private String getQuadrantXY(int v, int h) {
		return (v - 1) * 64 + "_" + (h - 1) * 64;
	}
	
	public void drawBattleField() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String coordinates = getQuadrantXY(i + 1, j + 1);
				int separator = coordinates.indexOf("_");
				int y = Integer.parseInt(coordinates.substring(0, separator));
				int x = Integer.parseInt(coordinates.substring(separator + 1));

				String obj = battleFieldTemplate[i][j];
				BFObject bfObject;
				if (obj.equals(BRICK)) {
					bfObject = new Brick(x, y);
				} else if (obj.equals(ROCK)) {
					bfObject = new Rock(x, y);
				} else if (obj.equals(EAGLE)) {
					bfObject = new Eagle(x, y);
					eagle = (Eagle)bfObject;
				} else if (obj.equals(WATER)) {
					bfObject = new Water(x, y);
				} else {
					bfObject = new Blank(x, y);
				}
				battleField[i][j] = bfObject;
			}
		}
	}
	
	@Override
	public void draw(Graphics g) {
		for (int j = 0; j < battleField.length; j++) {
			for (int k = 0; k < battleField.length; k++) {
				battleField[j][k].draw(g); 
			}
		}
	}

	public boolean checkToRock(Tank tank, Destroyable object)
	{
		int x = tank.getX()/64;
		int y = tank.getY()/64;

		int xObject = object.getX()/64;
		int yObject = object.getY()/64;

		if (tank.getX() == object.getX() || tank.getY() == object.getY()) {
			if (tank.getY() < object.getY()) {
				while (y < yObject) {
					if (scanQuadrant(y, x) instanceof Rock && !scanQuadrant(y, x).isDestroyed()) return true;
					y++;
				}
			} else if (tank.getY() > object.getY()) {
				while (y >= yObject) {
					if (scanQuadrant(y, x) instanceof Rock && !scanQuadrant(y, x).isDestroyed()) return true;
					y--;
				}
			}
			if (tank.getX() < object.getX()) {
				while (x < xObject) {
					if (scanQuadrant(y, x) instanceof Rock && !scanQuadrant(y, x).isDestroyed()) return true;
					x++;
				}
			} else if (tank.getX() > object.getX()) {
				while (x >= xObject) {
					if (scanQuadrant(y, x) instanceof Rock && !scanQuadrant(y, x).isDestroyed()) return true;
					x--;
				}
			}
		}
		return false;
	}
	
	public void destroyObject(int v, int h) {
		if (battleField[v][h] instanceof Destroyable) {
			Destroyable d = (Destroyable)battleField[v][h];
			d.destroy();
		}
	}

	public BFObject scanQuadrant(int v, int h) {
		return battleField[v][h];
	}


	public String getAggressorLocation() {
		return "64_0";
	}

	public int getBfWidth() {
		return bfWidth;
	}

	public void battleFieldToString()
	{
		for (int i = 0; i < battleField.length; i++) {
			System.out.println(Arrays.toString(battleField[i]));
		}
	}
	
	public int getBfHeight() {
		return bfHeight;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}
}
