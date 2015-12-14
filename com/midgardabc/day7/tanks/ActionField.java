package com.midgardabc.day7.tanks;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

import com.midgardabc.day7.tanks.bf.*;
import com.midgardabc.day7.tanks.bf.tanks.*;
import com.midgardabc.day7.tanks.bf.tanks.Action;

/**
 * Updated to object oriented style.
 * 
 * @version 3.0
 */
public class ActionField extends JPanel {

	private boolean COLORDED_MODE = false;

	private BattleField battleField;
	private Tank defender;
	private Tank aggressor;
	private Tank tiger;
	private Bullet bullet;
	private Eagle eagle;
	private JFrame frame;
	private JPanel choseTank;
	private JPanel gamePanel;
	private JPanel startAgain;
	private int counter;
	private Image choseTankMenu;
	private Image startAgainMenu;
	private Record rec;
	private PrintStream ps;


	/**
	 * Write your code here.
	 */
	void runTheGame()  throws Exception {
		//newWindow(intefaceChoseOfTank());
		//gamePanel.repaint();


		while (counter < 15) {
			attack(aggressor, eagle);
		    attack(tiger, defender);
			defendEagle();
			/*if (!aggressor.isDestroyed() && !defender.isDestroyed()) {
				processAction(aggressor.setUp(), aggressor);
			}
			if (!aggressor.isDestroyed() && !defender.isDestroyed()) {
				processAction(defender.setUp(), defender);
			}*/
		  }
		System.out.println("Close to loop:)");
		counter = 0;

		//Thread.sleep(1000);
		newPanel(startAgain);

		}


	public void processAction(Action a, Tank t) throws Exception {
		if (a == Action.MOVE) {
			processMove(t);
			//System.err.println("MOVE_"+ t.getClass().getSimpleName());
		} else if (a == Action.FIRE) {
			processTurn(t);
			processFire(t.fire());
			//System.err.println("FIRE_" + t.getClass().getSimpleName());
		} else if (a == Action.TURN_DOWN) {
			t.turn(Direction.DOWN);
			processTurn(t);
		} else if (a == Action.TURN_UP) {
			t.turn(Direction.UP);
			processTurn(t);
		} else if (a == Action.TURN_RIGHT) {
			t.turn(Direction.RIGHT);
			processTurn(t);
		} else if (a == Action.TURN_LEFT) {
			t.turn(Direction.LEFT);
			processTurn(t);
		}
	}

	private void processTurn(Tank tank) throws Exception {
		repaint();
	}

	private void processMove(Tank tank) throws Exception {

		Direction direction = tank.getDirection();
		processTurn(tank);
		int step = 1;
		
		for (int i = 0; i < tank.getMovePath(); i++) {
			int covered = 0;

			String tankQuadrant = getQuadrant(tank.getX(), tank.getY());
			int v = Integer.parseInt(tankQuadrant.split("_")[0]);
			int h = Integer.parseInt(tankQuadrant.split("_")[1]);

			// check limits x: 0, 513; y: 0, 513
			if ((direction == Direction.UP && tank.getY() == 0) || (direction == Direction.DOWN && tank.getY() >= 512)
					|| (direction == Direction.LEFT && tank.getX() == 0) || (direction == Direction.RIGHT && tank.getX() >= 512)) {
				System.out.println("[illegal move] direction111: " + direction
						+ " tankX: " + tank.getX() + ", tankY: " + tank.getY());
				return;
			}

			// check next quadrant before move
			if (direction == Direction.UP) {
				v--;
			} else if (direction == Direction.DOWN) {
				v++;
			} else if (direction == Direction.RIGHT) {
				h++;
			} else if (direction == Direction.LEFT) {
				h--;
			}

			BFObject bfobject = battleField.scanQuadrant(v, h);

			if (!(bfobject instanceof Blank) && !bfobject.isDestroyed()) {
				System.out.println("[illegal move] direction1: " + direction
						+ " tankX: " + tank.getX() + ", tankY: " + tank.getY());
				if (bfobject instanceof Brick || bfobject instanceof Eagle || (tank instanceof Tiger && bfobject instanceof Rock))
				{
					//tank.setActoin(Action.FIRE);
					processAction(Action.FIRE, tank);
					System.err.println("FIRE_" + tank.getClass().getSimpleName());
					//processAction(Action.FIRE, tank);
				}
				else if (bfobject instanceof Water) {

				}
				else {
					//tank.setDirection(randomDirection());
					//processMove(tank);
					return;
				}
			}

			if (tank instanceof T34) {
				if (((tank.getDirection() == Direction.UP || tank.getDirection() == Direction.DOWN)
						&&  ((!tiger.isDestroyed() && (v*64 == tiger.getY() && tank.getX() == tiger.getX())) ||
						(!aggressor.isDestroyed() &&(v*64 == aggressor.getY() && tank.getX() == aggressor.getX()))))
						|| ((tank.getDirection() == Direction.LEFT || tank.getDirection() == Direction.RIGHT)
						&&  ((!tiger.isDestroyed() &&(h*64 == tiger.getX() && tank.getY() == tiger.getY())) ||
						(!aggressor.isDestroyed() &&(h*64 == aggressor.getX() && tank.getY() == aggressor.getY()))))) {
					//tank.setActoin(Action.FIRE);
					processAction(Action.FIRE, tank);
					System.err.println("FIRE_" + tank.getClass().getSimpleName());
					processAction(Action.FIRE, tank);
					System.err.println("FIRE_" + tank.getClass().getSimpleName());
				   //if (!tiger.isDestroyed()) return;
				 }
			}
			else if (tank instanceof Tiger) {
				if (((tank.getDirection() == Direction.UP || tank.getDirection() == Direction.DOWN)
						&& (!defender.isDestroyed() && (v*64 == defender.getY() && tank.getX() == defender.getX())) )
						|| ((tank.getDirection() == Direction.LEFT || tank.getDirection() == Direction.RIGHT)
						&& (!defender.isDestroyed() && (h*64 == defender.getX() && tank.getY() == defender.getY()) )))  {
					//tank.setActoin(Action.FIRE);
					processAction(Action.FIRE, tank);
					System.err.println("FIRE_" + tank.getClass().getSimpleName());

					//if (!tiger.isDestroyed()) return;
				}
				else if (((tank.getDirection() == Direction.UP || tank.getDirection() == Direction.DOWN)
						&&  (!aggressor.isDestroyed() && (v*64 == aggressor.getY() && tank.getX() == aggressor.getX())) )
						|| ((tank.getDirection() == Direction.LEFT || tank.getDirection() == Direction.RIGHT)
						&& ( !aggressor.isDestroyed() && (h*64 == aggressor.getX() && tank.getY() == aggressor.getY()) ) )) {
					return;
				}
			}
			else {
				if (((tank.getDirection() == Direction.UP || tank.getDirection() == Direction.DOWN)
						&& (!defender.isDestroyed() && (v*64 == defender.getY() && tank.getX() == defender.getX()) ))
						|| ((tank.getDirection() == Direction.LEFT || tank.getDirection() == Direction.RIGHT)
						&&  (!defender.isDestroyed() && (h*64 == defender.getX() && tank.getY() == defender.getY()) )))  {
                   // tank.setActoin(Action.FIRE);
					processAction(Action.FIRE, tank);
					System.err.println("FIRE_" + tank.getClass().getSimpleName());

					//if
					// (!tiger.isDestroyed()) return;
				}
				else if (((tank.getDirection() == Direction.UP || tank.getDirection() == Direction.DOWN)
						&& (!tiger.isDestroyed() && (v*64 == tiger.getY() && tank.getX() == tiger.getX())) )
						|| ((tank.getDirection() == Direction.LEFT || tank.getDirection() == Direction.RIGHT)
						&&  ( !tiger.isDestroyed() && (h*64 == tiger.getX() && tank.getY() == tiger.getY()) ))) {
					return;
				}
			}
			// process move
	
			while (covered < 64) {


				if (direction == Direction.UP) {
					tank.updateY(-step);
	//				System.out.println("[move up] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
				} else if (direction == Direction.DOWN) {
					tank.updateY(step);
	//				System.out.println("[move down] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
				} else if (direction == Direction.LEFT) {
					tank.updateX(-step);
	//				System.out.println("[move left] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
				} else {
					tank.updateX(step);
	//				System.out.println("[move right] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
				}
				covered += step;
	
				repaint();
				Thread.sleep(tank.getSpeed());
			}
		}
	}


	private void processFire(Bullet bullet) throws Exception {
		this.bullet = bullet;
		int step = 1;
		while ((bullet.getX() > -14 && bullet.getX() < 590)
				&& (bullet.getY() > -14 && bullet.getY() < 590)) {
			if (bullet.getDirection() == Direction.UP) {
				bullet.updateY(-step);
			} else if (bullet.getDirection() == Direction.DOWN) {
				bullet.updateY(step);
			} else if (bullet.getDirection() == Direction.LEFT) {
				bullet.updateX(-step);
			} else {
				bullet.updateX(step);
			}
			if (processInterception()) {
				bullet.destroy();
			}
			repaint();
			Thread.sleep(bullet.getSpeed());
			if (bullet.isDestroyed()) {
				break;
			}
		}
	}

	private boolean processInterception() {
		String coordinates = getQuadrant(bullet.getX(), bullet.getY());
		int y = Integer.parseInt(coordinates.split("_")[0]);
		int x = Integer.parseInt(coordinates.split("_")[1]);

		if (y >= 0 && y < 9 && x >= 0 && x < 9) {
			BFObject bfObject = battleField.scanQuadrant(y, x);
			if (!bfObject.isDestroyed() && !(bfObject instanceof Blank) && !(bfObject instanceof Water)) {
				if (!(bfObject instanceof Rock) || bullet instanceof TigerBullet)
				   battleField.destroyObject(y, x);
				return true;
			}

			if (!aggressor.isDestroyed() && checkInterception(getQuadrant(aggressor.getX(), aggressor.getY()), coordinates)) {
				aggressor.destroy();
				return true;
			}

			// check aggressor
			if (!defender.isDestroyed() && checkInterception(getQuadrant(defender.getX(), defender.getY()), coordinates)) {
				defender.destroy();
				return true;
			}

			if (!tiger.isDestroyed() && checkInterception(getQuadrant(tiger.getX(), tiger.getY()), coordinates)) {
				tiger.destroy();
				return true;
			}
		}
		return false;
	}
	
	private boolean checkInterception(String object, String quadrant) {
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

	public Tank getDefender() {
		return defender;
	}

	public Tank getAggressor() {
		return aggressor;
	}

	public Tank getTiger() {
		return tiger;
	}

	private void attack(Tank tank, Destroyable object) throws Exception
	{
		if (!tank.isDestroyed() && !object.isDestroyed()) {
			if ((tank.getX() == object.getX() || tank.getY() == object.getY()) && !battleField.checkToRock(tank, object)) {
				fireTo(tank, object);
				return;
			} else if (tank.getX() > object.getX()) {
				tank.move(Direction.LEFT);
			} else {
				tank.move(Direction.RIGHT);
			}

			processAction(tank.setUp(), tank);
			System.err.println("MOVE_" + tank.getClass().getSimpleName());

			if ((tank.getY() == object.getY() || tank.getX() == object.getX()) && !battleField.checkToRock(tank, object)) {
				fireTo(tank, object);
				return;
			} else if (tank.getY() > object.getY()) {
				tank.move(Direction.UP);
			} else {
				tank.move(Direction.DOWN);
			}

			processAction(tank.setUp(), tank);
			System.err.println("MOVE_" + tank.getClass().getSimpleName());

			if ((tank.getY() == object.getY() || tank.getX() == object.getX()) && !battleField.checkToRock(tank, object)) {
				fireTo(tank, object);
			}

		}else counter++;

	}

	private void defendEagle() throws Exception
	{
		if (!defender.isDestroyed()) {
			if (!aggressor.isDestroyed()&& (aggressor.getX() == eagle.getX())) {
				if (defender.getY() == eagle.getY()) {
					defender.move(Direction.UP);
					processAction(defender.setUp(), defender);
					System.err.println("MOVE_" + defender.getClass().getSimpleName());
				}
				if (defender.getX() < eagle.getX()) {
					defender.move(Direction.RIGHT);
					processAction(defender.setUp(), defender);
					System.err.println("MOVE_" + defender.getClass().getSimpleName());
				} else if (defender.getX() > eagle.getX()) {
					defender.move(Direction.LEFT);
					processAction(defender.setUp(), defender);
					System.err.println("MOVE_" + defender.getClass().getSimpleName());
				}
			}
			if (!tiger.isDestroyed() && (defender.getX() == tiger.getX() || defender.getY() == tiger.getY()))
				attack(defender, tiger);

			attack(defender, aggressor);

			if (!tiger.isDestroyed() && (defender.getX() == tiger.getX() || defender.getY() == tiger.getY()))
				attack(defender, tiger);
			if (aggressor.isDestroyed())
			attack(defender, tiger);
		} else counter++;
	}


	private void newPanel(JPanel panel) {

		frame.getContentPane().removeAll();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}


	private void fireTo(Tank tank, Destroyable object) throws Exception
	{
		if (tank.getX() > object.getX()) {
			tank.setDirection(Direction.LEFT);
		}else if (tank.getX() < object.getX()){
			tank.setDirection(Direction.RIGHT);
		}

		if (tank.getY() > object.getY()){
			tank.setDirection(Direction.UP);
		}
		else if (tank.getY() < object.getY()){
			tank.setDirection(Direction.DOWN);
		}
		tank.setActoin(Action.FIRE);
		processAction(tank.setUp(), tank);
		System.err.println("FIRE_" + tank.getClass().getSimpleName());
		if (tank instanceof T34 && object instanceof Tiger) {
			processAction(tank.setUp(), tank);
			System.err.println("FIRE_" + tank.getClass().getSimpleName());
		}
		}



	public String getQuadrant(int x, int y) {
		// input database should be correct
		return y / 64 + "_" + x / 64;
	}

	public ActionField() throws Exception {
		ps = new PrintStream(new FileOutputStream("tanksAction.txt"));
		System.setErr(ps);

		rec = new Record(this);
		battleField = new BattleField();
		defender = new T34(battleField, ps);
        tiger = new Tiger(battleField, 512, 128, Direction.LEFT, ps);
		String location = battleField.getAggressorLocation();
		aggressor = new BT7(battleField,
				Integer.parseInt(location.split("_")[1]), Integer.parseInt(location.split("_")[0]), Direction.RIGHT, ps);

		bullet = new Bullet(-100, -100, Direction.NONE);
		eagle = battleField.getEagle();

		choseTankMenu = ImageIO.read(new File("tank.png"));
		startAgainMenu = ImageIO.read(new File("worldoftanksiconbygimil.png"));

		gamePanel = this;
		choseTank = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(choseTankMenu, 0, 0, new ImageObserver() {
					@Override
					public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
						return false;
					}
				});
			}
		};
		startAgain = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(startAgainMenu, 145, 100, new ImageObserver() {
					@Override
					public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
						return false;
					}
				});
			}
		};
		JButton playAgain = new JButton("Play Again");
		playAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thr = new Thread(){
					@Override
					public void run() {
						try {
							new ActionField();
						}catch(Exception el) {

						}
					}
				};
				thr.start();
			}
		});
		choseTank.setLayout(null);
		JButton repeat = new JButton("Repeat past game");
		repeat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thr = new Thread(){
					@Override
					public void run() {
						try {
							battleField = new BattleField();
							defender = new T34(battleField, ps);
							tiger = new Tiger(battleField, 512, 128, Direction.LEFT, ps);
							String location = battleField.getAggressorLocation();
							 aggressor = new BT7(battleField,
									Integer.parseInt(location.split("_")[1]), Integer.parseInt(location.split("_")[0]), Direction.RIGHT, ps);
							newPanel(gamePanel);
							rec.readFromFile("tanksAction.txt");
						} catch(Exception ex){

						}
					}
				};
				thr.start();
			}
		});
		repeat.setBounds(195, 110, 150, 30);
		playAgain.setBounds(224, 252, 100, 30);
		startAgain.setLayout(null);
		startAgain.add(playAgain);
		startAgain.add(repeat);
		ActionListener startGame = new Start();
		JButton deffender = new JButton("Deffender");
		deffender.setBounds(224,192, 90, 30);
		deffender.addActionListener(startGame);
		JButton tiger = new JButton("Tiger");
		tiger.setBounds(224, 232, 90, 30);
		tiger.addActionListener(startGame);
		JButton bt7 = new JButton("BT-7");
		bt7.setBounds(224, 272, 90, 30);
		bt7.addActionListener(startGame);
		choseTank.add(deffender);
		choseTank.add(tiger);
		choseTank.add(bt7);
		//choseTank.add(repeat);

		frame = new JFrame("BATTLE FIELD, DAY 7");
		frame.setLocation(400, 100);
		frame.setMinimumSize(new Dimension(battleField.getBfWidth(), battleField.getBfHeight() + 22));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		frame.getContentPane().add(choseTank);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int i = 0;
		Color cc;
		for (int v = 0; v < 9; v++) {
			for (int h = 0; h < 9; h++) {
				if (COLORDED_MODE) {
					if (i % 2 == 0) {
						cc = new Color(252, 241, 177);
					} else {
						cc = new Color(233, 243, 255);
					}
				} else {
					cc = new Color(180, 180, 180);
				}
				i++;
				g.setColor(cc);
				g.fillRect(h * 64, v * 64, 64, 64);
			}
		}

		//battleField.drawBattleField();

		//battleField.addObject(aggressor.getY()/64,aggressor.getX()/64, aggressor);
		//battleField.addObject(defender.getY()/64,defender.getX()/64, defender);
		//battleField.addObject(tiger.getY()/64,tiger.getX()/64, tiger);

		battleField.draw(g);
		tiger.draw(g);
		defender.draw(g);
		aggressor.draw(g);
		bullet.draw(g);

		//battleField.battleFieldToString();
	}

	private class Start implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			newPanel(gamePanel);

			Thread thr = new Thread(){
				@Override
				public void run() {
					try {
						runTheGame();
					}catch(Exception el) {

					}
				}
			};
			thr.start();

				//newWindow(gamePanel);


		}
	}

}