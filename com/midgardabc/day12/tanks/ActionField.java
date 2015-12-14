package com.midgardabc.day12.tanks;

import com.midgardabc.day12.tanks.bf.*;
import com.midgardabc.day12.tanks.bf.tanks.Action;
import com.midgardabc.day12.tanks.bf.tanks.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.*;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Updated to object oriented style.
 * 
 * @version 3.0
 */
public class ActionField extends JPanel {

	private BattleField battleField;
	private Tank defender;
	private Tank aggressor;
	private Tank tiger;
	private Eagle eagle;

	private boolean recording = false;

	private Thread threadKeyboard;
	private PrintStream ps;
	private List<Integer> keyCode;
	private String player1;
	private String player2;
	private Maps maps;
	private String choseMap;
	private UI ui;

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public Maps getMaps() {
		return maps;
	}

	public void setChoseMap(String choseMap) {
		this.choseMap = choseMap;
	}

	public void runTheGame(Tank t1, Tank t2) {

         Thread t1Thr = tankThread(t1);
		 t1Thr.start();
		 Thread t2Thr = tankThread(t2);
		 t2Thr.start();
		 graficThread().start();
	}

	public void twoPlayersMode(Tank t1, Tank t2, Tank t3) {

		Thread t1Thread = tankTwoPlayersThread(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
		t1Thread.start();
		Thread t2Thread = tankTwoPlayersThread(t2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SHIFT);
		t2Thread.start();
		Thread t3Thread = tankThread(t3);
		t3Thread.start();
		graficThread().start();
	}


	private Runnable twoPlayersThread(Tank tank, int up, int down, int left, int right, int fire) {
		return new Runnable() {
			@Override
			public void run() {
				while (!tank.isDestroyed() && !eagle.isDestroyed() ) {
					if (isPressedKey(up)) {
						tank.setDirection(Direction.UP);
						processAction(Action.MOVE, tank);
					}
					if (isPressedKey(down)) {
						tank.setDirection(Direction.DOWN);
						processAction(Action.MOVE, tank);
					}
					if (isPressedKey(left)) {
						tank.setDirection(Direction.LEFT);
						processAction(Action.MOVE, tank);
					}
					if (isPressedKey(right)) {
						tank.setDirection(Direction.RIGHT);
						processAction(Action.MOVE, tank);
					}
					if (isPressedKey(fire)) {
						processAction(Action.FIRE, tank);
					}
					sleep(50);
				}
			}
		};
	}

	private Thread tankTwoPlayersThread(Tank tank, int up, int down, int left, int right, int fire) {
		return new Thread(twoPlayersThread(tank, up, down, left, right, fire));
	}

	public Tank lastTank(String player1, String player2) {
		if ((player1.equals(defender.getClass().getSimpleName()) && player2.equals(tiger.getClass().getSimpleName())) ||
				(player2.equals(defender.getClass().getSimpleName()) && player1.equals(tiger.getClass().getSimpleName()))) {
			return aggressor;
		}else if ((player1.equals(defender.getClass().getSimpleName()) && player2.equals(aggressor.getClass().getSimpleName())) ||
				(player2.equals(defender.getClass().getSimpleName()) && player1.equals(aggressor.getClass().getSimpleName()))) {
			return tiger;
		} else return defender;
	}

	private boolean onlyOne(Tank t1, Tank t2, Tank t3) {
		if (!t1.isDestroyed() && t2.isDestroyed() && t3.isDestroyed()) {
			if (!recording) {
				ui.winnerFrame(t1.getClass().getSimpleName() + " win!");
			}
			return true;
		}
		if (!t2.isDestroyed() && t1.isDestroyed() && t3.isDestroyed()) {
			if (!recording) {
				ui.winnerFrame(t2.getClass().getSimpleName() + " win!");
			}
			return true;
		}
		if (!t3.isDestroyed() && t1.isDestroyed() && t2.isDestroyed()) {
			if (!recording) {
				ui.winnerFrame(t3.getClass().getSimpleName() + " win!");
			}
			return true;
		}
		if (t3.isDestroyed() && t1.isDestroyed() && t2.isDestroyed()) {
			if (!recording) {
				ui.winnerFrame("All tanks destroyed!");
			}
			return true;
		}
		if (eagle.isDestroyed()) {
			if (!recording) {
				ui.winnerFrame("Eagle captured. BT-7 win!");
			}
			return true;
		}
		return false;
	}

	private Runnable attackThread(Tank tank, Destroyable destroyable) {
		return new Runnable() {
			@Override
			public void run() {
				while (!tank.isDestroyed() && !destroyable.isDestroyed() && !eagle.isDestroyed()) {

					processAction(tank.setUp(), tank);
					sleep(50);
				}
				System.out.println(tank.getClass().getSimpleName() + " end work");
			}
		};
	}

	private Runnable attackThread(Tank tank, Destroyable destroyable1, Destroyable destroyable2) {
		return new Runnable() {
			@Override
			public void run() {
				while (!tank.isDestroyed() && (!destroyable1.isDestroyed() || !destroyable2.isDestroyed()) && !eagle.isDestroyed()) {
					processAction(tank.setUp(), tank);
					sleep(50);
				}
				System.out.println(tank.getClass().getSimpleName() + " end work");
			}
		};
	}

	private Thread tankThread(Tank tank) {
		if (tank instanceof T34) return new Thread(attackThread(tank, aggressor, tiger));
		else if (tank instanceof Tiger) return new Thread(attackThread(tank, defender));
		return new Thread(attackThread(tank, eagle));
	}


	public void processAction(Action a, Tank t) {

		if (a == Action.MOVE) {
			processMove(t);
		} else if (a == Action.FIRE) {
             processFire(t.fire(), t);
		} else if (a == Action.TURN_DOWN) {
			t.turn(Direction.DOWN);
		} else if (a == Action.TURN_UP) {
			t.turn(Direction.UP);
		} else if (a == Action.TURN_RIGHT) {
			t.turn(Direction.RIGHT);
		} else if (a == Action.TURN_LEFT) {
			t.turn(Direction.LEFT);
		}
	}


	private void processMove(Tank tank) {

		Direction direction = tank.getDirection();
		int step = 1;
		int covered = 0;

		   if (canMoveTank(tank)) {
			   return;
		   }

			while (covered < 64) {

				if (direction == Direction.UP) {
					tank.updateY(-step);
				} else if (direction == Direction.DOWN) {
					tank.updateY(step);
				} else if (direction == Direction.LEFT) {
					tank.updateX(-step);
				} else {
					tank.updateX(step);
				}
				ps.println(tank.getBullet().getX() + "_" + tank.getBullet().getY() + ":" + tank.getBullet().getDirection() + ":" + tank.getBullet().isDestroyed() + ":" + tank.getX() + "_" + tank.getY() + ":" + tank.getDirection() + ":" + tank.getClass().getSimpleName());
				covered += step;
				sleep(tank.getSpeed());

			}

	}

	private boolean canMoveTank(Tank tank) {
		Direction direction = tank.getDirection();

		if ((direction == Direction.UP && tank.getY() == 0) || (direction == Direction.DOWN && tank.getY() >= 512)
				|| (direction == Direction.LEFT && tank.getX() == 0) || (direction == Direction.RIGHT && tank.getX() >= 512)) {
			System.out.println("[illegal move] direction111: " + direction
					+ " tankX: " + tank.getX() + ", tankY: " + tank.getY());
			return true;
		}

		BFObject bfobject = battleField.nextBfObject(tank);
		if ((bfobject instanceof Rock || bfobject instanceof Brick || bfobject instanceof Eagle) && !bfobject.isDestroyed()) {
			System.out.println("[illegal move] direction1: " + direction
					+ " tankX: " + tank.getX() + ", tankY: " + tank.getY());
			return true;

		}

		if (tank instanceof T34) {

			if (checkTankIntecception(tank, tiger) || checkTankIntecception(tank, aggressor)) {
				return true;
			}

		} else if (tank instanceof Tiger) {

			if (checkTankIntecception(tank, defender) || checkTankIntecception(tank, aggressor)) {
				return true;
			}

		} else {

			if (checkTankIntecception(tank, tiger) || checkTankIntecception(tank, defender)) {
				return true;
			}

		}
		return false;
	}


	private boolean checkTankIntecception(Tank main, Tank adversaly) {

		if (!adversaly.isDestroyed()
				&& main.getDirection() == Direction.DOWN && (checkInterception(getQuadrant(main.getX(), main.getY() + 64),
				getQuadrant(adversaly.getX(), adversaly.getY())) || checkInterception(getQuadrant(main.getX(), main.getY() + 64),
				getQuadrant(adversaly.getX()+63, adversaly.getY())) )) {
			return true;
		}
		if (!adversaly.isDestroyed()
				&& main.getDirection() == Direction.LEFT && (checkInterception(getQuadrant(main.getX() - 64, main.getY()),
				getQuadrant(adversaly.getX(), adversaly.getY())) || checkInterception(getQuadrant(main.getX()-64, main.getY() ),
				getQuadrant(adversaly.getX(), adversaly.getY()+63)) || checkInterception(getQuadrant(main.getX()-64, main.getY() ),
				getQuadrant(adversaly.getX()+63, adversaly.getY())))) {
			return true;
		}
		if (!adversaly.isDestroyed()
				&& main.getDirection() == Direction.RIGHT && (checkInterception(getQuadrant(main.getX() + 64, main.getY()),
				getQuadrant(adversaly.getX(), adversaly.getY())) || checkInterception(getQuadrant(main.getX()+64, main.getY() ),
				getQuadrant(adversaly.getX(), adversaly.getY()+63)))) {
			return true;
		}
		if (!adversaly.isDestroyed()
				&& main.getDirection() == Direction.UP && (checkInterception(getQuadrant(main.getX(), main.getY() - 64),
				getQuadrant(adversaly.getX(), adversaly.getY())) || checkInterception(getQuadrant(main.getX(), main.getY() - 64),
				getQuadrant(adversaly.getX(), adversaly.getY()+63)) || checkInterception(getQuadrant(main.getX(), main.getY() - 64),
				getQuadrant(adversaly.getX()+63, adversaly.getY())))) {
			return true;
		}
		return false;
	}


	public void processFire(Bullet bullet, Tank tank) {

        new Thread(new Runnable() {
			@Override
			public synchronized void run() {
				if (tank.getBullet().isDestroyed()) {

					tank.setBullet(bullet);
					int step = 1;
					while ((tank.getBullet().getX() > -14 && tank.getBullet().getX() < 590)
							&& (tank.getBullet().getY() > -14 && tank.getBullet().getY() < 590))
					{
						if (bullet.getDirection() == Direction.UP) {
							tank.getBullet().updateY(-step);
						} else if (bullet.getDirection() == Direction.DOWN) {
							tank.getBullet().updateY(step);
						} else if (bullet.getDirection() == Direction.LEFT) {
							tank.getBullet().updateX(-step);
						} else {
							tank.getBullet().updateX(step);
						}

						ps.println(tank.getBullet().getX() + "_" + tank.getBullet().getY() + ":" + tank.getBullet().getDirection() + ":" + tank.getBullet().isDestroyed() + ":" + tank.getX() + "_" + tank.getY() + ":" + tank.getDirection() + ":" + tank.getClass().getSimpleName());
						if (processInterception(tank.getBullet())) {
							tank.getBullet().destroy();
						}

						sleep(bullet.getSpeed());

						if (tank.getBullet().isDestroyed()) {
							break;
						}
					}
					if (!tank.getBullet().isDestroyed()) {
						tank.getBullet().destroy();
					}

			}
		}}).start();

	}

	private boolean processInterception(Bullet bullet) {
		String coordinates = getQuadrant(bullet.getX(), bullet.getY());
		int y = Integer.parseInt(coordinates.split("_")[0]);
		int x = Integer.parseInt(coordinates.split("_")[1]);

		if (y >= 0 && y < 9 && x >= 0 && x < 9) {
			BFObject bfObject = battleField.scanQuadrant(y, x);
			if (!bfObject.isDestroyed() && !(bfObject instanceof Blank) && !(bfObject instanceof Water)) {
				if ((!(bfObject instanceof Rock) || bullet.equals(tiger.getBullet())) &&  (!(bfObject instanceof Eagle) || bullet.equals(aggressor.getBullet())))
					battleField.destroyObject(y, x);
				return true;
			}

			if (!aggressor.isDestroyed() && checkInterception(getQuadrant(aggressor.getX(), aggressor.getY()), coordinates)) {
				if (bullet.equals(aggressor.getBullet())) {
					return false;
				}
				aggressor.destroy();
				return true;
			}

			if (!defender.isDestroyed() && checkInterception(getQuadrant(defender.getX(), defender.getY()), coordinates)) {
				if (bullet.equals(defender.getBullet())) {
					return false;
				}
				defender.destroy();
				return true;
			}

			if (!tiger.isDestroyed() && checkInterception(getQuadrant(tiger.getX(), tiger.getY()), coordinates)) {
				if (bullet.equals(tiger.getBullet())) {
					return false;
				}
				tiger.destroy();
				return true;
			}

			if (!bullet.isDestroyed() && checkBulletInterception(bullet)) {
				return true;
			}

		}
		return false;
	}

	private boolean checkBulletInterception(Bullet bullet) {

		String coordinates = getQuadrant(bullet.getX(), bullet.getY());

		Bullet tigerBullet = tiger.getBullet();
		Bullet aggressorBullet = aggressor.getBullet();
		Bullet defenderBullet = defender.getBullet();

		if (bullet.equals(defender.getBullet())) {

			if ((checkInterception(coordinates, getQuadrant(tigerBullet.getX(), tigerBullet.getY())) && !tigerBullet.isDestroyed())) {
				return true;
			}
			if ((checkInterception(coordinates, getQuadrant(aggressorBullet.getX(), aggressorBullet.getY())) && !aggressorBullet.isDestroyed())) {
				return true;
			}
		} else if (bullet.equals(tiger.getBullet())) {

			if ((checkInterception(coordinates, getQuadrant(defenderBullet.getX(), defenderBullet.getY())) && !defenderBullet.isDestroyed())) {
				return true;
			}
			if ((checkInterception(coordinates, getQuadrant(aggressorBullet.getX(), aggressorBullet.getY()))) && !aggressorBullet.isDestroyed()) {
				return true;
			}
		} else {

			if ((checkInterception(coordinates, getQuadrant(tigerBullet.getX(), tigerBullet.getY())) && !tigerBullet.isDestroyed())) {
				return true;
			}
			if ((checkInterception(coordinates, getQuadrant(defenderBullet.getX(), defenderBullet.getY())) && !defenderBullet.isDestroyed())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkInterception(String object, String quadrant) {
		int oy = Integer.parseInt(object.split("_")[0]);
		int ox = Integer.parseInt(object.split("_")[1]);

		int qy = Integer.parseInt(quadrant.split("_")[0]);
		int qx = Integer.parseInt(quadrant.split("_")[1]);

		if (oy >= 0 && oy < 9 && ox >= 0 && ox < 9) {
			if (oy == qy && ox == qx) {
				return true;
			}
		}
		return false;
	}

	public Eagle getEagle() {
		return eagle;
	}

	public Tank getDefender() {
		return defender;
	}

	public Tank getAggressor() {
		return aggressor;
	}

	public Tank getTiger() {
		return tiger;
	}

	public String getQuadrant(int x, int y) {
		return y / 64 + "_" + x / 64;
	}

	public void repeat() {

		recording = true;
		initialObjectsToButtleField();
		ui.newPanel(this);
		graficThread().start();

        new Thread(new Runnable() {
			@Override
			public void run() {
				try (FileInputStream fis = new FileInputStream("cordinate.txt");
					 Reader reader = new InputStreamReader(fis);
					 BufferedReader bufferedReader = new BufferedReader(reader)) {

					String str;
					while ((str = bufferedReader.readLine()) != null) {
						System.out.println(str);
						String tankName = str.split(":")[5];
						Direction tankDirection = Direction.valueOf(str.split(":")[4]);
						String tankCordinates = str.split(":")[3];
						int tankX = Integer.parseInt(tankCordinates.split("_")[0]);
						int tankY = Integer.parseInt(tankCordinates.split("_")[1]);
						boolean bulletDestroyed = Boolean.valueOf(str.split(":")[2]);
						String bulletCordinates = str.split(":")[0];
						Direction bulletDirection = Direction.valueOf(str.split(":")[1]);
						int bulletX = Integer.parseInt(bulletCordinates.split("_")[0]);
						int bulletY = Integer.parseInt(bulletCordinates.split("_")[1]);

						getTank(tankName).setDirection(tankDirection);
						getTank(tankName).setX(tankX);
						getTank(tankName).setY(tankY);

						getTank(tankName).getBullet().setDestroyed(bulletDestroyed);
						if (!getTank(tankName).getBullet().isDestroyed()) {
							getTank(tankName).getBullet().setX(bulletX);
							getTank(tankName).getBullet().setY(bulletY);
							getTank(tankName).getBullet().setDirection(bulletDirection);
							processInterception(getTank(tankName).getBullet());
						}

						sleep(getTank(tankName).getSpeed() / 2);

					}
					ui.newPanel(ui.startAgain());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	public Tank getTank(String tankName) {
		if (tankName.equals(defender.getClass().getSimpleName())) {
			return defender;
		} else if (tankName.equals(tiger.getClass().getSimpleName())) {
			return tiger;
		}
		return aggressor;
	}

	public void initialObjectsToButtleField() {
		battleField = new BattleField(maps.getMaps().get(choseMap));
		Map<String, String> locations = maps.getLocations();
		String defenderLocation = locations.get(choseMap).split(":")[0];
		String tigerLocation = locations.get(choseMap).split(":")[1];
		String aggressorLocation = locations.get(choseMap).split(":")[2];
		defender = new T34(battleField, Integer.parseInt(defenderLocation.split("_")[1]),
				Integer.parseInt(defenderLocation.split("_")[0]), Direction.UP ,this);
		tiger = new Tiger(battleField, Integer.parseInt(tigerLocation.split("_")[1]),
				Integer.parseInt(tigerLocation.split("_")[0]), Direction.LEFT, this);
		aggressor = new BT7(battleField, Integer.parseInt(aggressorLocation.split("_")[1]),
				Integer.parseInt(aggressorLocation.split("_")[0]), Direction.RIGHT, this);
		eagle = battleField.getEagle();
	}

	private Thread graficThread() {
		return new Thread(new Runnable() {
			@Override
			public void run() {
				while (!onlyOne(defender, tiger, aggressor)) {
					repaint();
					sleep(1000/60);
				}
				System.out.println("Grafic Thread end his working");
			}
		});
	}

	public ActionField(UI ui) {

		try {
			ps = new PrintStream("cordinate.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.ui = ui;
		maps = new Maps();
		keyCode = new ArrayList<>();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		battleField.draw(g);

		tiger.draw(g);
		defender.draw(g);
		aggressor.draw(g);

		tiger.getBullet().draw(g);
		defender.getBullet().draw(g);
		aggressor.getBullet().draw(g);

	}

	private void sleep(long timeout) {
		try {
			Thread.currentThread().sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private javax.swing.Action actionKeyMove(Direction direction, Tank tank) {

		javax.swing.Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello, I'm " + tank.getClass().getSimpleName() + " and I am moving " + direction.toString() + ":)");

				if (threadKeyboard == null) {
					threadKeyboard = new Thread() {
						@Override
						public void run() {
							if (!tank.isDestroyed()) {
								tank.setDirection(direction);
								processAction(Action.MOVE, tank);
							}
							try {
								sleep(5);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							threadKeyboard = null;
						}
					};
					threadKeyboard.start();
				}
			}
		};
		return action;
	}

	private boolean isPressedKey(int key) {
		if (keyCode.contains(key)) return true;
		return false;
	}

	private javax.swing.Action actionKeyFire(Tank tank) {
		javax.swing.Action fire = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello, I'm " + tank.getClass().getSimpleName() + " and I am FIRE :)");

				if (!tank.isDestroyed()) {
					processAction(Action.FIRE, tank);
				}
			}
		};
		return fire;
	}



	private void putKeyBlinding(Direction direction, Tank tank) {
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(direction.toString()), direction.toString());
		this.getActionMap().put(direction.toString(), actionKeyMove(direction, tank));
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "Fire");
		this.getActionMap().put("Fire", actionKeyFire(tank));
	}

	public void setGame(Tank t1, Tank t2, Tank t3) {
		putKeyBlinding(Direction.UP, t1);
		putKeyBlinding(Direction.DOWN, t1);
		putKeyBlinding(Direction.LEFT, t1);
		putKeyBlinding(Direction.RIGHT, t1);

		ui.newPanel(this);
		runTheGame(t2, t3);
	}

	public ActionListener getStartGame() {
		return new Start();
	}

	public KeyListener getTwoPlayers() {
		return new TwoPlayersLisener();
	}

	private class Start implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String tankName = e.getActionCommand();

			if (tankName.equals("T34")) {
				setGame(defender, aggressor, tiger);
			} else if (tankName.equals("BT7")) {
				setGame(aggressor, defender, tiger);
			} else {
				setGame(tiger, aggressor, defender);
			}
		}

	}

	private class TwoPlayersLisener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			System.out.println("Pressed " + key);
            keyCode.add(key);
		}

		@Override
		public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
			while (keyCode.contains(key)) {
				keyCode.remove(keyCode.indexOf(key));
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}
	}

}