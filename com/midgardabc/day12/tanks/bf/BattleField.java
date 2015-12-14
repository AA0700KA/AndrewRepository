package com.midgardabc.day12.tanks.bf;

import com.midgardabc.day12.tanks.Direction;
import com.midgardabc.day12.tanks.bf.tanks.Tank;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BattleField implements Drawable {
	
	public static final String BRICK = "B";
	public static final String EAGLE = "E";
	public static final String ROCK = "R";
	public static final String WATER = "W";

	private Eagle eagle;
	private java.util.List<Water> waters;

	private String[][] battleFieldTemplate = {
			{"B", "B", "B", "B", "B", "B", "B", "B", "B"},
			{" ", "B", " ", "B", " ", "B", "B", "B", " "},
			{" ", "B", "B", " ", "B", "B", "B", "B", " "},
			{" ", "B", " ", "R", "R", " ", "R", "R", "R"},
			{"W", "W", "W", " ", "B", "W", "W", "W", "W"},
			{"W", "W", " ", "B", " ", "R", " ", "W", "W"},
			{"W", "R", " ", " ", " ", " ", " ", "B", "B"},
			{"R", "R", " ", "B", "B", "B", " ", " ", "B"},
			{"R", " ", " ", "R", "E", "B", " ", " ", "B"}
		};


	private BFObject[][] battleField =  new BFObject[9][9];

	public Eagle getEagle() {
		return eagle;
	}

	public BattleField() {
		waters = new ArrayList<>();
		drawBattleField();
	}

	public BattleField(String[][] battleField) {
		waters = new ArrayList<>();
		this.battleFieldTemplate = battleField;
		drawBattleField();
	}

	public List<Water> getWaters() {
		return waters;
	}

	public String getQuadrant(int x, int y) {
		return y / 64 + "_" + x / 64;
	}

	public BFObject nextBfObject(Tank tank)
	{
		String tankQuadrant = getQuadrant(tank.getX(), tank.getY());
		int v = Integer.parseInt(tankQuadrant.split("_")[0]);
		int h = Integer.parseInt(tankQuadrant.split("_")[1]);
		Direction direction = tank.getDirection();

		if ((direction == Direction.UP && tank.getY() == 0) || (direction == Direction.DOWN && tank.getY() >= 512)
				|| (direction == Direction.LEFT && tank.getX() == 0) || (direction == Direction.RIGHT && tank.getX() >= 512)) {
			System.out.println("[illegal move] direction111: " + direction
					+ " tankX: " + tank.getX() + ", tankY: " + tank.getY());
			return null;
		}

		if (tank.getDirection() == Direction.UP) {
			v--;
		} else if (tank.getDirection() == Direction.DOWN) {
			v++;
		} else if (tank.getDirection() == Direction.RIGHT) {
			h++;
		} else if (tank.getDirection() == Direction.LEFT) {
			h--;
		}

		BFObject bfobject = scanQuadrant(v, h);
		return bfobject;
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
					waters.add((Water)bfObject);
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

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}
}
