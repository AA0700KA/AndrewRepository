package com.midgardabc.day5.Frame3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by user on 30.07.2015.
 */
public class BirdsUI {

    private JFrame frame;
    private JPanel transactionsUI;
    private JPanel shopUI;

    public JPanel getShopUI() {
        return shopUI;
    }

    public JPanel getTransactionsUI() {
        return transactionsUI;
    }

    private Shop shop;
    private Stock stock;
    private String choseProduct;

    public BirdsUI(Shop shop, Stock stock)
    {
        this.shop = shop;
        this.stock = stock;
        transactionsUI = new JPanel();
        frame = new JFrame("Shop birds");
        frame.setMinimumSize(new Dimension(576,576));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(300, 100);
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem buyBird = new JMenuItem("Buy Bird");
        JMenuItem transactions = new JMenuItem("Transactions");
        JMenuItem birdsStaticstic = new JMenuItem("Bird Statistic");
        buyBird.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopMenu();
            }
        });
        transactions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transactionsUI.removeAll();
                printTransactions();
            }
        });
        birdsStaticstic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                birdsStatistic();
            }
        });
        menu.add(buyBird);
        menu.add(transactions);
        menu.add(birdsStaticstic);
        mb.add(menu);
        frame.setJMenuBar(mb);
        frame.pack();
        frame.setVisible(true);
    }

    public void printTransactions()
    {
        String[] colums = {"#", "Name", "Bird", "Amount", "Date"};
        String[][] data = new String[stock.getTransactions().size()][];
        List<Transaction> transactions = stock.getTransactions();
        for (int i = 0; i < data.length; i++) {
            Transaction t = transactions.get(i);
            data[i] = new String[]{String.valueOf(i+1), t.getCustomer().getName(), t.getBird().getName(),
                    String.valueOf(t.getCount()),  t.getDate()};
        }

        JTable table = new JTable(data, colums);
        table.getColumnModel().getColumn(4).setPreferredWidth(110);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        JScrollPane sp = new JScrollPane(table);
        transactionsUI.add(sp);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(transactionsUI);
        frame.pack();
        frame.setVisible(true);
    }

    private void shopMenu()
    {
        shopUI = new JPanel();
        JLabel label = new JLabel("User name: ");
        JTextField textField = new JTextField(10);
        JTextField count = new JTextField(3);
        ActionListener al = new RBLisener();
        JRadioButton rb1 = new JRadioButton("Eagle");
        rb1.setActionCommand(rb1.getText());
        rb1.addActionListener(al);
        JRadioButton rb2 = new JRadioButton("Duck");
        rb2.setActionCommand(rb2.getText());
        rb2.addActionListener(al);
        JButton button = new JButton("Buy");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);

        shopUI.setLayout(new GridBagLayout());
        shopUI.add(label, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        shopUI.add(textField, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        shopUI.add(rb1, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        shopUI.add(rb2, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        shopUI.add(new JLabel("How mach: "), new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        shopUI.add(count, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        shopUI.add(button, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                transactionsUI.removeAll();

                int countBirds = Integer.parseInt(count.getText());

                JLabel noBirds = new JLabel("No " + countBirds + " " + choseProduct + "s!!!");
                noBirds.setForeground(Color.RED);
                JLabel uncorrect = new JLabel("Write corect count");
                uncorrect.setForeground(Color.RED);

                Bird b;
                if (choseProduct == rb1.getText()) {
                     b = new Eagle();
                } else b = new Duck();

                int eagleIndex = stock.getEAGLE();
                int duckIndex = stock.getDUCK();

                int aviableEagle = stock.getProducts().get(eagleIndex).getAviableBird();
                int aviableDuck =  stock.getProducts().get(duckIndex).getAviableBird();

                if ((countBirds != 0) &&((b instanceof Eagle && aviableEagle >= countBirds) || (b instanceof Duck && aviableDuck >= countBirds))) {
                    shop.buyBird(textField.getText(), b, Integer.parseInt(count.getText()));
                } else if (countBirds == 0) {
                    transactionsUI.add(uncorrect);
                }
                else {
                    transactionsUI.add(noBirds);
                }
                shop.getTransactionInfo();
                shop.getStock().printCustomers();

            }
        });

        frame.getContentPane().removeAll();
        frame.getContentPane().add(shopUI);

        frame.pack();
        frame.setVisible(true);
    }

    public void birdsStatistic()
    {
        JPanel birdsOnStack = new JPanel();
        String[] colums = {"bird", "aviable birds", "bought birds", "price, grn", "profits, grn"};
        String[][] data = new String[stock.getProducts().size()][];
        List<Bird> products = stock.getProducts();

        for (int i = 0; i < products.size(); i++) {
            Bird b = products.get(i);
            data[i] = new String[]{b.getName(), String.valueOf(b.getAviableBird()), String.valueOf(b.getSoldBird()),
            String.valueOf(b.getPrice()), String.valueOf(b.getPrice()*b.getSoldBird())};
        }

        JTable table = new JTable(data, colums);
        JScrollPane sp = new JScrollPane(table);
        birdsOnStack.add(sp);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(birdsOnStack);
        frame.pack();
        frame.setVisible(true);


    }


    private class RBLisener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            choseProduct = e.getActionCommand();
        }
    }
}
