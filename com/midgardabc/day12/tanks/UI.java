package com.midgardabc.day12.tanks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by user on 06.12.2015.
 */
public class UI {

    private JFrame frame;
    private ActionField af;
    private ImageIcon mainTankMenu;
    private ImageIcon choseMapIcon;

    public UI() {
        choseMapIcon = new ImageIcon(ActionField.class.getResource("/src/tanksimages/CreateOrChoseMap.jpg"));
        mainTankMenu = new ImageIcon(ActionField.class.getResource("/src/tanksimages/tank.png"));
        af = new ActionField(this);
        frame = new JFrame("TANKS 2D");
        frame.setLocation(400, 100);
        frame.setMinimumSize(new Dimension(576, 598));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(choseOrCreateMap());

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel choseTankMenu() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(mainTankMenu.getImage(), 0, 0, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            }
        };
        panel.setLayout(null);
        ActionListener startGame = af.getStartGame();
        JLabel choseOfTank = new JLabel("Chose tank: ");
        JButton tiger = new JButton("Tiger");
        tiger.setBounds(224, 232, 90, 30);
        tiger.addActionListener(startGame);
        JButton bt7 = new JButton("BT7");
        bt7.setBounds(224, 272, 90, 30);
        bt7.addActionListener(startGame);
        JButton def = new JButton("T34");
        def.setBounds(224, 192, 90, 30);
        def.addActionListener(startGame);
        JButton back = new JButton("Back");
        back.setBounds(224, 312, 90, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel(gameMode());
            }
        });
        panel.add(choseOfTank);
        panel.add(tiger);
        panel.add(bt7);
        panel.add(def);
        panel.add(back);
        return panel;
    }

    public JPanel startAgain() {
        ImageIcon startAgainMenu = new ImageIcon(ActionField.class.getResource("/src/tanksimages/worldoftanksiconbygimil.png"));
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(startAgainMenu.getImage(), 128, 128, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            }
        };
        panel.setLayout(null);
        JButton newGame = new JButton("New Game");

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new UI();
            }
        });
        JButton repeat = new JButton("Replay");
        repeat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.repeat();
            }
        });
        repeat.setBounds(224, 215, 100, 30);
        newGame.setBounds(224, 252, 100, 30);

        panel.add(repeat);
        panel.add(newGame);

        return panel;
    }

    private void errorFrame(String error) {
        JFrame frame = new JFrame();
        frame.setLocation(550, 350);
        frame.setMinimumSize(new Dimension(400, 150));
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel winner = new JLabel(error);
        winner.setBounds(105, 15, 100, 20);
        JButton button = new JButton("OK");
        button.setBounds(110, 60, 60, 40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        panel.add(winner);
        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createMap() {
        JPanel createPanel = new JPanel();
        createPanel.setLayout(new GridBagLayout());
        JTextField[][] textFields = new JTextField[9][9];
        for (int i = 0; i < textFields.length; i++) {
            for (int j = 0; j < textFields[i].length; j++) {
                textFields[i][j] = new JTextField(3);
                createPanel.add(textFields[i][j], new GridBagConstraints(j, i, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
            }
        }
        JPanel panel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel(choseOrCreateMap());
            }
        });
        panel.setLayout(new GridBagLayout());
        JLabel instructions = new JLabel("Water - W, Eagle - E, Brick - B, Rock - R, Blank - any latter or empty space.");
        JLabel warning = new JLabel("WARNING: Eagle must be one on battlefield!");
        JButton createMap = new JButton("Create map");
        createMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (af.getMaps().chackCreatedMap(textFields) > 1) {
                    errorFrame("Eagle must be only one");
                } else if (af.getMaps().chackCreatedMap(textFields) == 0) {
                    errorFrame("On battlefield must be only one eagle");
                } else {
                    af.getMaps().createNewMap(textFields);
                    af.setChoseMap(af.getMaps().getNewMap());
                    af.initialObjectsToButtleField();
                    newPanel(gameMode());
                }

            }
        });
        panel.add(instructions, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(warning, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(createPanel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(createMap, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 160, 5, 5), 0, 0));
        panel.add(back, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, -150, 5, 5), 0, 0));
        return panel;
    }

    private JPanel choseOrCreateMap() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(choseMapIcon.getImage(), 0, 0, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            }
        };
        panel.setLayout(null);
        JButton choseMap = new JButton("Chose Map");
        JButton createMap = new JButton("Create Map");
        JButton randomMap = new JButton("Random Map");
        choseMap.setBounds(224, 192, 130, 30);
        choseMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel(choseMap());
            }
        });
        createMap.setBounds(224, 232, 130, 30);
        createMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel(createMap());
            }
        });
        randomMap.setBounds(224, 272, 130, 30);
        randomMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.getMaps().randomMap();
                af.setChoseMap(af.getMaps().getRandomMap());
                af.initialObjectsToButtleField();
                newPanel(gameMode());
            }
        });
        panel.add(choseMap);
        panel.add(createMap);
        panel.add(randomMap);
        return panel;
    }

    private JPanel choseTankTwoPlayers() {
        JPanel panel = new JPanel();
        JLabel labelP1 = new JLabel("Tank for player1:");
        JLabel labelP2 = new JLabel("Tank for player2:");

        ButtonGroup bgPlayer1 = new ButtonGroup();
        JRadioButton defenderP1 = new JRadioButton(af.getDefender().getClass().getSimpleName());
        defenderP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.setPlayer1(defenderP1.getText());
            }
        });
        JRadioButton bt7P1 = new JRadioButton(af.getAggressor().getClass().getSimpleName());
        bt7P1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.setPlayer1(bt7P1.getText());
            }
        });
        JRadioButton tigerP1 = new JRadioButton(af.getTiger().getClass().getSimpleName());
        tigerP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.setPlayer1(tigerP1.getText());
            }
        });
        bgPlayer1.add(defenderP1);
        bgPlayer1.add(bt7P1);
        bgPlayer1.add(tigerP1);

        ButtonGroup bgPlayer2 = new ButtonGroup();
        JRadioButton defenderP2 = new JRadioButton(af.getDefender().getClass().getSimpleName());

        defenderP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.setPlayer2(defenderP2.getText());
            }
        });
        JRadioButton bt7P2 = new JRadioButton(af.getAggressor().getClass().getSimpleName());

        bt7P2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.setPlayer2(bt7P2.getText());
            }
        });
        JRadioButton tigerP2 = new JRadioButton(af.getTiger().getClass().getSimpleName());

        tigerP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.setPlayer2(tigerP2.getText());
            }
        });
        bgPlayer2.add(defenderP2);
        bgPlayer2.add(bt7P2);
        bgPlayer2.add(tigerP2);

        JPanel panelBg1 = new JPanel();
        panelBg1.setLayout(new GridLayout(0, 1));
        panelBg1.add(defenderP1);
        panelBg1.add(bt7P1);
        panelBg1.add(tigerP1);

        JPanel panelBg2 = new JPanel();
        panelBg2.setLayout(new GridLayout(0, 1));
        panelBg2.add(defenderP2);
        panelBg2.add(bt7P2);
        panelBg2.add(tigerP2);

        JButton startGame = new JButton("Start game");
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel(gameMode());
            }
        });
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!af.getPlayer1().equals(af.getPlayer2())) {
                    newPanel(af);
                    frame.requestFocusInWindow();
                    frame.addKeyListener(af.getTwoPlayers());
                    af.twoPlayersMode(af.getTank(af.getPlayer1()), af.getTank(af.getPlayer2()), af.lastTank(af.getPlayer1(), af.getPlayer2()));
                } else {
                    errorFrame("player1 and player2 must played another tanks");
                }
            }
        });

        panel.setLayout(new GridBagLayout());
        panel.add(labelP1, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(panelBg1, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(labelP2, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(panelBg2, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(startGame, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(back, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        return panel;
    }

    public void winnerFrame(String whoWin) {
        JFrame frame = new JFrame();
        frame.setLocation(550, 350);
        frame.setMinimumSize(new Dimension(300, 150));
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel winner = new JLabel(whoWin);
        winner.setBounds(105, 15, 100, 20);
        JButton button = new JButton("OK");
        button.setBounds(110, 60, 60, 40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel(startAgain());
                frame.setVisible(false);
            }
        });
        panel.add(winner);
        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void newPanel(JPanel panel) {

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public JPanel choseMap() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(choseMapIcon.getImage(), 0, 0, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            }
        };
        panel.setLayout(new GridBagLayout());
        ButtonGroup bg = new ButtonGroup();
        JPanel rbMapsGroup = new JPanel();
        rbMapsGroup.setLayout(new GridLayout(0, 1));
        for (String map : af.getMaps().getMaps().keySet()) {
            JRadioButton rb = new JRadioButton(map);
            rb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    af.setChoseMap(map);
                }
            });
            bg.add(rb);
            rbMapsGroup.add(rb);
        }
        JButton choseMap = new JButton("Chose map");
        JButton back = new JButton("Back");
        choseMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.initialObjectsToButtleField();
                newPanel(gameMode());
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel(choseOrCreateMap());
            }
        });
        panel.add(rbMapsGroup, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(choseMap, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(back, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        return panel;
    }

    private JPanel gameMode() {
        ImageIcon gameModeIcon = new ImageIcon(ActionField.class.getResource("/src/tanksimages/gameMode.jpg"));
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(gameModeIcon.getImage(), 0, 0, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            }
        };
        panel.setLayout(null);
        JButton onePlayer = new JButton("Single player");
        onePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel(choseTankMenu());
            }
        });
        JButton twoPlayers = new JButton("Two players");
        twoPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel(choseTankTwoPlayers());
            }
        });
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel(choseMap());
            }
        });
        onePlayer.setBounds(224, 192, 120, 30);
        twoPlayers.setBounds(224, 232, 120, 30);
        back.setBounds(224, 272, 90, 30);
        panel.add(onePlayer);
        panel.add(twoPlayers);
        panel.add(back);
        return panel;
    }


}
