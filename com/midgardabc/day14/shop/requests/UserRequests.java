package com.midgardabc.day14.shop.requests;



import com.midgardabc.day14.shop.Category;
import com.midgardabc.day14.shop.PurchaseType;
import com.midgardabc.day14.shop.data.Stock;
import com.midgardabc.day14.shop.domain.Bird;
import com.midgardabc.day14.shop.domain.Customer;
import com.midgardabc.day14.shop.domain.Transaction;
import com.midgardabc.day14.shop.logic.TableData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 18.11.2015.
 */
public class UserRequests {

    private Shop shop;
    private Stock stock;

    private String choseProduct;
    private Category choseCategory;
    private String choseTransactionCustomer;

    public UserRequests() {
        shop = new Shop();
        stock = shop.getStock();
    }

    public void setChoseProduct(String choseProduct) {
        this.choseProduct = choseProduct;
    }

    public void setChoseCategory(Category choseCategory) {
        this.choseCategory = choseCategory;
    }

    public void setChoseTransactionCustomer(String choseTransactionCustomer) {
        this.choseTransactionCustomer = choseTransactionCustomer;
    }

    public String getChoseProduct() {
        return choseProduct;
    }

    public Shop getShop() {
        return shop;
    }

    public Stock getStock() {
        return stock;
    }

    public ComboBoxLisener getComboBoxLisener() {
        return new ComboBoxLisener();
    }

    public CategoryLisener getCategoryLisener() {
        return new CategoryLisener();
    }

    public ChoseCustomerLisener getCustomerLisener() {
        return new ChoseCustomerLisener();
    }

    public boolean logIn() {
        if (shop.getCurrentCustomer() != null) {
            return true;
        }
        return false;
    }

    public void logOut() {
        shop.setCurrentCustomer(null);
    }

    public boolean login(JTextField textField, JTextField passwordField) {
        String login = textField.getText();
        String password = passwordField.getText();
        if (stock.getTableInfo().checkToLoginAndPassword(login, password)) {
            Customer customer = stock.getTableInfo().getCurrentCustomer(login, password);
            shop.setCurrentCustomer(customer);
            return true;
        }
        return false;
    }

    public boolean checkToLogin(String login) {
        TableData query = stock.getTableInfo();
        if (query.checkToElement("select login from users where login = '" + login + "'")) {
            return true;
        }
        return false;
    }

    public String register(JTextField login, JTextField password, JTextField repeatPassword, JTextField name) {
        String error = "Register succefull!";
        String nam = login.getText();
        String pass = password.getText();
        String repeat = repeatPassword.getText();
        String nameUser = name.getText();

        if (nam.length() >= 4 && nameUser.length() > 0 && pass.length() >= 8 && pass.equals(repeat) ) {
                shop.register(nameUser, nam, pass);
                return error;
        }
        error = "";

        if (nam.length() < 4) {
            error += "Login is very short! ";
        }
        if (pass.length() < 8) {
            error += "Password is very simple! ";
        }
        if (name.getText().length() == 0) {
            error += "Write your name! ";
        }
        if (!pass.equals(repeat)) {
            error += "Password mismatch! ";
        }
        return error;
    }

    public List<Transaction> printTransactions() {
        List<Transaction> t;
        if (choseProduct.equals(" ") && choseTransactionCustomer.equals(" ")) {
            return stock.getTransactions();
        } else if (choseProduct.equals(" ") && !choseTransactionCustomer.equals(" ")) {
            int separatorCustomerId = choseTransactionCustomer.indexOf(".");
            long customerId = Long.valueOf(choseTransactionCustomer.substring(0, separatorCustomerId));
            t = stock.getTransactionsFilter("t.customer_id = " + customerId);
        } else if (!choseProduct.equals(" ") && choseTransactionCustomer.equals(" ")) {
            int separatorProductId = choseProduct.indexOf(".");
            long productId = Long.valueOf(choseProduct.substring(0, separatorProductId));
            t = stock.getTransactionsFilter("tp.product_id = " + productId);
        } else {
            int separatorCustomerId = choseTransactionCustomer.indexOf(".");
            long customerId = Long.valueOf(choseTransactionCustomer.substring(0, separatorCustomerId));
            int separatorProductId = choseProduct.indexOf(".");
            long productId = Long.valueOf(choseProduct.substring(0, separatorProductId));
            t = stock.getTransactionsFilter("tp.product_id = " + productId + " AND t.customer_id = " + customerId);
        }
        return t;
    }

    public List<Transaction> removeTransactions() {
        shop.removeTransactions();
        return stock.getTransactions();
    }

    public void replanishTheBalance(JTextField textField) {
        shop.putPayment(PurchaseType.PAYMENT, Double.valueOf(textField.getText()), "Replash Balance");
        shop.updateBalance(Double.valueOf(textField.getText()));
    }

    public double buyBird(JTextField count) {

        int countBirds = Integer.parseInt(count.getText());

        Bird b = stock.getProducts().get(choseProduct);

        int aviable = b.getAviableBird();
        Customer customer = shop.getCurrentCustomer();
        double balance = shop.getBalance();

        if (countBirds != 0 && aviable >= countBirds && countBirds* b.getRealPrice() <= balance) {
            shop.buyBird(customer, b, Integer.parseInt(count.getText()));
        } else if (countBirds* b.getRealPrice() > balance) {
            return -1;
        } else if (aviable < countBirds) {
            return -2;
        }
        return countBirds*b.getRealPrice();
    }

    public Map<String, Bird> printProducts() {
        Map<String, Bird> productsToCategory;
        if (choseProduct.equals(" ")) {
            return stock.getProducts();
        } else {
            productsToCategory = stock.getToCategory(choseProduct);
            return productsToCategory;
        }
    }

    public void changePrice(JFormattedTextField ftfPrice) {
        double price = Double.valueOf(ftfPrice.getText());
        shop.changePrice(choseProduct, price);
    }

    public double addBird(JTextField nameBird, JFormattedTextField priceBird, JFormattedTextField howMach) {
        double price = Double.valueOf(priceBird.getText());
        int aviable = Integer.parseInt(howMach.getText());
        String product = nameBird.getText();
        double countPrice = (price - Bird.SALES) * aviable;
        double balance = shop.getBalance();
        if (countPrice <= balance) {
            shop.addProduct(product, choseCategory, aviable, price);
            shop.updateBalance(-countPrice);
            shop.putPayment(PurchaseType.WHOLESALE, -countPrice, product);
            return countPrice;
        } else {
            return -1;
        }
    }

    public double selfBird(JFormattedTextField ftfPrice) {
        int self = Integer.parseInt(ftfPrice.getText());
        Bird b = stock.getProducts().get(choseProduct);
        double countPrice = (b.getRealPrice() - Bird.SALES) * self;
        double balance = shop.getBalance();
        if (countPrice <= balance) {
            shop.selfBird(choseProduct, self);

            shop.putPayment(PurchaseType.WHOLESALE, -countPrice, b.getName());
            shop.updateBalance(-countPrice);
            return countPrice;
        } else {
            return -1;
        }
    }

    public void deleteBird() {
        shop.deleteBird(choseProduct);
    }

    private class ComboBoxLisener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox) e.getSource();
            choseProduct = (String) box.getSelectedItem();
        }
    }

    private class CategoryLisener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox) e.getSource();
            choseCategory = Category.valueOf((String) box.getSelectedItem());
        }
    }

    private class ChoseCustomerLisener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox)e.getSource();
            choseTransactionCustomer = (String) box.getSelectedItem();
        }
    }
}
