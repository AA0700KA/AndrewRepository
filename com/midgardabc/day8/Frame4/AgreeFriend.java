package com.midgardabc.day8.Frame4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by user on 26.07.2015.
 */
public class AgreeFriend extends JPanel{

    private Ball ball;
    private String text;
    private JFrame frame;

    public AgreeFriend()
    {
        text = "";
        ball = new Ball(this);
        frame = new JFrame("Click rad ball");
        frame.setMinimumSize(new Dimension(576, 576));
        frame.setLocation(300, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        JButton button = new JButton("Start game!!!");
        JButton stopGame = new JButton("Stop game!");
        panel.add(button);
        panel.add(stopGame);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = "";
                runTheGame();
                repaint();
            }
        });
        stopGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    public void runTheGame()
    {
        frame.getContentPane().add(this);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() / 64 == ball.getX() / 64 && e.getY() / 64 == ball.getY() / 64) {
                    text = "You win!!!";
                    repaint();
                    ball.setX(256);
                    ball.setY(192);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (text == "") {
                    if (e.getX() / 64 == ball.getX() / 64 && e.getY() / 64 == ball.getY() / 64) {

                        try {
                            ball.moveDrive();
                        } catch (Exception ex) {

                        }
                    }
                }
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN,72));
        g.drawString(text, 128, 256);
    }

}
