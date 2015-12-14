package com.midgardabc.day8.Frame3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

/**
 * Created by user on 25.07.2015.
 */
public class Quadrant {

    private Color c;
    private int x;
    private int y;
    private final int RGB = 255;


    public Quadrant()
    {
        x = 128;
        y = 128;
        c = Color.RED;
    }

    public void run()
    {
        JFrame frame = new JFrame("Qudrant change his color to click");
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setLocation(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(){

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(c);
                g.fillRect(x,y, 64, 64);
            }
        };

        panel.setMinimumSize(new Dimension(64, 64));
        panel.setLocation(0, 0);



        frame.getContentPane().add(panel);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX()/64 == x/64 && e.getY()/64 == y/64) {
                    Random r = new Random();
                    c = new Color(r.nextInt(RGB),r.nextInt(RGB), r.nextInt(RGB));
                    panel.repaint();
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
        frame.pack();
        frame.setVisible(true);
    }

}
