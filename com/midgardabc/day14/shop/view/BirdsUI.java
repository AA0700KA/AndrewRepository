package com.midgardabc.day14.shop.view;



import com.midgardabc.day14.shop.*;
import com.midgardabc.day14.shop.domain.Bird;
import com.midgardabc.day14.shop.domain.Customer;
import com.midgardabc.day14.shop.domain.Payment;
import com.midgardabc.day14.shop.domain.Transaction;
import com.midgardabc.day14.shop.requests.UserRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class BirdsUI {

    private JFrame frame;
    private UserRequests userRequests;

    public BirdsUI()
    {
        userRequests = new UserRequests();
        frame = new JFrame("Shop birds");
        frame.setMinimumSize(new Dimension(576,576));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(300, 100);

        frame.setJMenuBar(munuBar());
        frame.getContentPane().add(loginPage());

        frame.pack();
        frame.setVisible(true);
    }

    private JMenuBar munuBar() {
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenu adminPanel = new JMenu("Admin panel");
        JMenuItem deleteProduct = new JMenuItem("Delete bird");
        JMenuItem logout = new JMenuItem("Logout");
        JMenuItem homePage = new JMenuItem("Home");
        JMenuItem expectures = new JMenuItem("Expectures");
        JMenuItem checkBalance = new JMenuItem("Balance");
        JMenuItem replenishTheBalance = new JMenuItem("Replanish the Balance");
        JMenuItem buyBird = new JMenuItem("Buy Bird");
        JMenuItem transactions = new JMenuItem("Transactions");
        JMenuItem birdsStaticstic = new JMenuItem("Birds Statistic");
        JMenuItem changePrice = new JMenuItem("Chane price");
        JMenuItem addBird = new JMenuItem("Add bird");
        JMenuItem selfBird = new JMenuItem("Self bird");
        deleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    deleteBird();
                } else {
                    noLogined();
                }
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userRequests.logOut();
                noLogined();
            }
        });
        homePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    home();
                } else {
                    noLogined();
                }
            }
        });
        expectures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    printExpectures();
                } else {
                    noLogined();
                }

            }
        });
        replenishTheBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    replenishTheBalance();
                } else {
                    noLogined();
                }

            }
        });
        checkBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    balanceFrame();
                } else {
                    noLogined();
                }

            }
        });

        selfBird.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    selfBird();
                } else {
                    noLogined();
                }

            }
        });

        addBird.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    addBird();
                } else {
                    noLogined();
                }

            }
        });
        changePrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    changePrice();
                } else {
                    noLogined();
                }

            }
        });
        buyBird.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    shopMenu();
                } else {
                    noLogined();
                }

            }
        });
        transactions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    printTransactions(userRequests.getStock().getTransactions());
                } else {
                    noLogined();
                }

            }
        });
        birdsStaticstic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.logIn()) {
                    birdsStatistic(userRequests.getStock().getProducts());
                } else {
                    noLogined();
                }

            }
        });
        menu.add(homePage);
        menu.add(buyBird);
        menu.add(checkBalance);
        menu.add(replenishTheBalance);
        menu.add(logout);
        adminPanel.add(transactions);
        adminPanel.add(birdsStaticstic);
        adminPanel.add(changePrice);
        adminPanel.add(addBird);
        adminPanel.add(selfBird);
        adminPanel.add(expectures);
        adminPanel.add(deleteProduct);
        mb.add(menu);
        mb.add(adminPanel);
        return mb;
    }

    private void noLogined() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(loginPage());
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel loginPage() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        JTextField textField = new JTextField(10);
        JTextField passwordField = new JPasswordField(10);
        JButton login = new JButton("Login");
        JButton register = new JButton("Register");

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!userRequests.login(textField, passwordField)) {
                    textError(new JLabel("Incorrect login or password"));
                } else {
                    home();
                }

            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
        loginPanel.add(new JLabel("Login: "), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 185, 10, 0), 0, 0));
        loginPanel.add(textField, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 120), 0, 0));
        loginPanel.add(new JLabel("Password: "), new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 160, 10, 0), 0, 0));
        loginPanel.add(passwordField, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 120), 0, 0));
        loginPanel.add(login, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 0), 0, 0));
        loginPanel.add(new JLabel("If you haven't accaunt, you can reginster"), new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(50, -10, 0, 0), 0, 0));
        loginPanel.add(register, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(50, 0, 0, 0), 0, 0));
        return loginPanel;
    }

    private void register() {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridBagLayout());
        JTextField name = new JTextField(10);
        JTextField login = new JTextField(10);
        JTextField password = new JPasswordField(10);
        JTextField repeatPassword = new JPasswordField(10);
        JButton register = new JButton("Create account");
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userRequests.checkToLogin(login.getText())) {
                    textError(new JLabel("This login is used"));
                } else {
                    String error = userRequests.register(login, password, repeatPassword, name);
                    if (error.equals("Register succefull!")) {
                       noLogined();
                    }
                    textError(new JLabel(error));
                }


            }
        });
        registerPanel.add(new JLabel("Your name:"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 0), 0, 0));
        registerPanel.add(name, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 0), 0, 0));
        registerPanel.add(new JLabel("Login:"), new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 0), 0, 0));
        registerPanel.add(login, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 0), 0, 0));
        registerPanel.add(new JLabel("Password:"), new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 0), 0, 0));
        registerPanel.add(password, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 0), 0, 0));
        registerPanel.add(new JLabel("Reapeat password:"), new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 10), 0, 0));
        registerPanel.add(repeatPassword, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 0), 0, 0));
        registerPanel.add(register, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10, 0), 0, 0));
        frame.getContentPane().removeAll();
        frame.getContentPane().add(registerPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void home() {
        JPanel welcome = new JPanel();
        welcome.add(new JLabel("Welcome, " + userRequests.getShop().getCurrentCustomer().getName()));
        frame.getContentPane().removeAll();
        frame.getContentPane().add(welcome);
        frame.pack();
        frame.setVisible(true);
    }

    private void printTransactions(List<Transaction> transactions)
    {
        JPanel transactionsUI = new JPanel();
        String[] colums = {"#", "Name", "Bird", "Amount", "Date"};
        String[][] data = new String[transactions.size()][];

        for (int i = 0; i < data.length; i++) {
            Transaction t = transactions.get(i);
            data[i] = new String[]{String.valueOf(t.getCustomer().getId()), t.getCustomer().getName(), t.getBird().getName(),
                    String.valueOf(t.getCount()),  t.getDate()};
        }

        JTable table = new JTable(data, colums);
        table.getColumnModel().getColumn(4).setPreferredWidth(110);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        String[] itemsCustomers = new String[userRequests.getStock().getAllCustomers().size() + 1];

        int i = 1;
        itemsCustomers[0] = " ";
        for (Long key : userRequests.getStock().getCustomers().keySet()) {
            Customer customer = userRequests.getStock().getCustomers().get(key);
            itemsCustomers[i] = key + "." + customer.getName();
            i++;
        }

        String[] itemsProducts = new String[userRequests.getStock().getProducts().size() + 1];

        i = 1;
        itemsProducts[0] = " ";
        for (String key : userRequests.getStock().getProducts().keySet()) {
            Bird bird = userRequests.getStock().getProducts().get(key);
            itemsProducts[i] = bird.getId() + "." + bird.getName();
            i++;
        }

        JComboBox customers = new JComboBox(itemsCustomers);
        userRequests.setChoseTransactionCustomer((String)customers.getSelectedItem());
        customers.addActionListener(userRequests.getCustomerLisener());

        JComboBox products = new JComboBox(itemsProducts);
        userRequests.setChoseProduct((String)products.getSelectedItem());
        products.addActionListener(userRequests.getComboBoxLisener());

        JButton searchTransactions = new JButton("Search");

        searchTransactions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTransactions(userRequests.printTransactions());
            }
        });

        JScrollPane sp = new JScrollPane(table);
        JButton refreshTable = new JButton("Remove");
        refreshTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTransactions(userRequests.removeTransactions());
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        panel.add(new JLabel("Search transactions: "));
        panel.add(customers);
        panel.add(products);
        panel.add(searchTransactions);
        transactionsUI.setLayout(new GridBagLayout());
        transactionsUI.add(panel, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        transactionsUI.add(sp, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        transactionsUI.add(refreshTable, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));

        frame.getContentPane().removeAll();
        frame.getContentPane().add(transactionsUI);
        frame.pack();
        frame.setVisible(true);
    }

    private void balanceFrame() {
        JFrame frame = new JFrame("Your balance");
        frame.setMinimumSize(new Dimension(100, 100));
        frame.setLocation(300, 100);
        frame.add(new JLabel("Your balance: " + userRequests.getShop().getBalance() + " grn"));
        frame.pack();
        frame.setVisible(true);
    }

    private void replenishTheBalance() {
        JFrame frame = new JFrame("Replenish the balance");
        frame.setMinimumSize(new Dimension(300, 50));
        frame.setLocation(300, 100);
        JPanel balancePanel = new JPanel();
        balancePanel.setLayout(new GridLayout());
        JTextField ftf = new JFormattedTextField();
        ftf.setColumns(3);
        balancePanel.add(ftf);
        JButton button = new JButton("Replesh");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userRequests.replanishTheBalance(ftf);
                frame.setVisible(false);
            }
        });
        balancePanel.add(button);
        frame.add(balancePanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void shopMenu()
    {
        JPanel shopUI = new JPanel();

        JFormattedTextField count = new JFormattedTextField();
        count.setColumns(3);
        count.setValue(1);
        ActionListener al = userRequests.getComboBoxLisener();

        String[] items = new String[userRequests.getStock().getProducts().keySet().size()];

        int index = 0;
        for (String key : userRequests.getStock().getProducts().keySet()) {
            items[index] = key;
            index++;
        }

        JComboBox birds = new JComboBox(items);
        userRequests.setChoseProduct((String) birds.getSelectedItem());
        birds.addActionListener(al);
        System.out.println(birds.getActionCommand());
        JButton button = new JButton("Buy");

        shopUI.setLayout(new GridBagLayout());
        shopUI.add(new JLabel("Your balance: " + userRequests.getShop().getBalance() + " grn"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        shopUI.add(birds, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        shopUI.add(new JLabel("How mach: "), new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        shopUI.add(count, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        shopUI.add(button, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JLabel noBirds = new JLabel("Error : No " + count.getText() + " " + userRequests.getChoseProduct() + "s!!!");
                JLabel uncorrect = new JLabel("Error : Write corect count");

                double result = userRequests.buyBird(count);

                if (result == 0) {
                    textError(uncorrect);
                } else if (result == -1) {
                    textError(new JLabel("Not enough money to buy this product!"));
                } else if (result == -2) {
                    textError(noBirds);
                } else {
                    printTransactions(userRequests.getStock().getTransactions());
                }

            }
        });

        frame.getContentPane().removeAll();
        frame.getContentPane().add(shopUI);

        frame.pack();
        frame.setVisible(true);
    }

    private void textError(JLabel label) {
        JFrame errorFrame = new JFrame();
        errorFrame.setLocation(300, 500);
        JPanel errorPanel = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorFrame.setVisible(false);
            }
        });
        errorPanel.setLayout(new GridBagLayout());
        errorPanel.add(ok, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        errorPanel.add(label, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));
        errorFrame.getContentPane().add(errorPanel);
        errorFrame.pack();
        errorFrame.setVisible(true);
    }

    private void birdsStatistic(Map<String, Bird> products)
    {
        JPanel birdsOnStack = new JPanel();
        String[] colums = {"bird", "aviable birds", "bought birds", "price, grn", "profits, grn"};
        String[][] data = new String[products.size()][];

        int i = 0;

        for (String key : products.keySet()) {
            Bird b = products.get(key);
            data[i] = new String[]{b.getName(), String.valueOf(b.getAviableBird()), String.valueOf(b.getSoldBird()),
            String.valueOf(b.getRealPrice()), String.valueOf(b.getProfit())};
            i++;
        }

        Category[] categories = Category.values();

        String[] itemsProducts = new String[categories.length + 1];

        i = 1;
        itemsProducts[0] = " ";

        for (Category category : categories) {
            itemsProducts[i] = category.toString();
            i++;
        }

        JComboBox productsChose = new JComboBox(itemsProducts);
        userRequests.setChoseProduct((String)productsChose.getSelectedItem());
        productsChose.addActionListener(userRequests.getComboBoxLisener());

        JButton searchToCategory = new JButton("Search");
        searchToCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                birdsStatistic(userRequests.printProducts());
            }
        });

        JTable table = new JTable(data, colums);
        JScrollPane sp = new JScrollPane(table);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        panel.add(new JLabel("Search to category: "));
        panel.add(productsChose);
        panel.add(searchToCategory);

        birdsOnStack.setLayout(new GridBagLayout());
        birdsOnStack.add(panel, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        birdsOnStack.add(sp, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        frame.getContentPane().removeAll();
        frame.getContentPane().add(birdsOnStack);

        frame.pack();
        frame.setVisible(true);

    }

    private void changePrice() {
        JFrame changePriceFrame = new JFrame("Change Price");
        changePriceFrame.setMinimumSize(new Dimension(320, 320));
        changePriceFrame.setLocation(400, 400);

        JPanel changePanel = new JPanel();
        JFormattedTextField ftfPrice = new JFormattedTextField();
        ftfPrice.setColumns(4);
        ftfPrice.setValue(10);

        ActionListener al = userRequests.getComboBoxLisener();

        String[] items = new String[userRequests.getStock().getProducts().keySet().size()];

        int index = 0;

        for (String key : userRequests.getStock().getProducts().keySet()) {
            items[index] = key;
            index++;
        }

        JComboBox comboBox = new JComboBox(items);
        userRequests.setChoseProduct((String) comboBox.getSelectedItem());
        comboBox.addActionListener(al);

        JButton button = new JButton("Change price");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userRequests.changePrice(ftfPrice);
                changePriceFrame.setVisible(false);
                birdsStatistic(userRequests.getStock().getProducts());

            }
        });

        changePanel.setLayout(new GridBagLayout());
        changePanel.add(new JLabel("Your price: "), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        changePanel.add(ftfPrice, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        changePanel.add(comboBox, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        changePanel.add(button, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        changePriceFrame.getContentPane().add(changePanel);

        changePriceFrame.pack();
        changePriceFrame.setVisible(true);
    }

    private void addBird() {
        JFrame addFrame = new JFrame("Add product");
        addFrame.setMinimumSize(new Dimension(240, 140));
        addFrame.setLocation(400, 400);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(0, 2));
        JLabel birdName = new JLabel("Bird name: ");
        JTextField nameBird = new JTextField(10);
        nameBird.setText("Bird name");
        JLabel aviableBird = new JLabel("How mach bird on Stack: ");
        JFormattedTextField howMach = new JFormattedTextField();
        JLabel category = new JLabel("Category");

        Category[] categories = Category.values();
        String[] items = new String[categories.length];

        for (int i = 0; i < items.length; i++) {
            items[i] = categories[i].toString();
        }

        JComboBox categoryItems = new JComboBox(items);

        userRequests.setChoseCategory(Category.valueOf((String)categoryItems.getSelectedItem()));
        categoryItems.addActionListener(userRequests.getCategoryLisener());
        howMach.setValue(1);
        JLabel price = new JLabel("Price");
        JFormattedTextField priceBird = new JFormattedTextField();
        priceBird.setValue(1);
        JButton addProduct = new JButton("Add product");
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double result = userRequests.addBird(nameBird, priceBird, howMach);
                if (result == -1) {
                    textError(new JLabel("Not enough money to add this product!"));
                } else {
                    birdsStatistic(userRequests.getStock().getProducts());
                    addFrame.setVisible(false);
                }

            }
        });
        addPanel.add(birdName);
        addPanel.add(nameBird);
        addPanel.add(aviableBird);
        addPanel.add(howMach);
        addPanel.add(price);
        addPanel.add(priceBird);
        addPanel.add(category);
        addPanel.add(categoryItems);
        addPanel.add(addProduct);

        addFrame.getContentPane().add(addPanel);
        addFrame.pack();
        addFrame.setVisible(true);
    }

    private void selfBird()
    {
        JPanel changePanel = new JPanel();
        JFormattedTextField ftfPrice = new JFormattedTextField();
        ftfPrice.setColumns(4);
        ftfPrice.setValue(0);

        ActionListener al = userRequests.getComboBoxLisener();

        String[] items = new String[userRequests.getStock().getProducts().keySet().size()];

        int index = 0;
        for (String key : userRequests.getStock().getProducts().keySet()) {
            items[index] = key;
            index++;
        }

        JComboBox comboBox = new JComboBox(items);
        userRequests.setChoseProduct((String) comboBox.getSelectedItem());
        comboBox.addActionListener(al);

        JButton button = new JButton("Self product");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double result = userRequests.selfBird(ftfPrice);
                if (result == -1) {
                    textError(new JLabel("Not enough money to add this product!"));
                } else {
                    birdsStatistic(userRequests.getStock().getProducts());
                }

            }
        });

        changePanel.setLayout(new GridBagLayout());
        changePanel.add(new JLabel("Your balance: " + userRequests.getShop().getBalance() + " grn"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        changePanel.add(new JLabel("How mach: "), new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        changePanel.add(ftfPrice, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        changePanel.add(comboBox, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        changePanel.add(button, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
        frame.getContentPane().removeAll();
        frame.getContentPane().add(changePanel);

        frame.pack();
        frame.setVisible(true);
    }

    private void deleteBird() {
        JFrame deleteFrame = new JFrame("Delete bird");
        deleteFrame.setMinimumSize(new Dimension(160, 160));
        deleteFrame.setLocation(400, 400);

        JPanel deletePanel = new JPanel();

        ActionListener al = userRequests.getComboBoxLisener();

        String[] items = new String[userRequests.getStock().getProducts().keySet().size()];

        int index = 0;
        for (String key : userRequests.getStock().getProducts().keySet()) {
            items[index] = key;
            index++;
        }

        JComboBox comboBox = new JComboBox(items);
        userRequests.setChoseProduct((String) comboBox.getSelectedItem());
        comboBox.addActionListener(al);

        JButton button = new JButton("Delete product");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userRequests.deleteBird();
                deleteFrame.setVisible(false);
                birdsStatistic(userRequests.getStock().getProducts());
            }
        });
        deletePanel.setLayout(new GridBagLayout());

        deletePanel.add(comboBox, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        deletePanel.add(button, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));

        deleteFrame.getContentPane().add(deletePanel);

        deleteFrame.pack();
        deleteFrame.setVisible(true);
    }

    private void printExpectures() {
        JPanel paymentPanel = new JPanel();
        String[] colums = {"Type Payment", "Type purchase", "Balance operation"};
        String[][] data = new String[userRequests.getStock().getPayments(userRequests.getShop().getCurrentCustomer()).size()][];
        List<Payment> payments = userRequests.getStock().getPayments(userRequests.getShop().getCurrentCustomer());
        for (int i = 0; i < data.length; i++) {
            Payment payment = payments.get(i);
            data[i] = new String[]{ payment.getTypePayment(),
                    payment.getPurchaseType().toString(),
                    String.valueOf(payment.getNumberPayment())};
        }

        JTable table = new JTable(data, colums);
        JScrollPane sp = new JScrollPane(table);
        paymentPanel.add(sp);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(paymentPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
