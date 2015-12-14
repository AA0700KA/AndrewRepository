package com.midgardabc.day14.shop.data;

import com.midgardabc.day14.shop.Category;
import com.midgardabc.day14.shop.PurchaseType;
import com.midgardabc.day14.shop.data.database.DBRequest;
import com.midgardabc.day14.shop.domain.Bird;
import com.midgardabc.day14.shop.domain.Customer;
import com.midgardabc.day14.shop.domain.Payment;
import com.midgardabc.day14.shop.domain.Transaction;
import com.midgardabc.day14.shop.logic.TableData;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 24.07.2015.
 */
public class Stock implements ShopData {

    private Map<String,Bird> products;
    private List<Transaction> transactions;
    private Map<Long, Customer> customers;

    private DBRequest requests;
    private TableData tableInfo;


    public Map<String,Bird> getProducts() {
        return products;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Stock(DBRequest requests, TableData tableInfo)
    {
        this.requests = requests;
        this.tableInfo = tableInfo;
        products = new HashMap<>();
        customers = new HashMap<>();
        transactions = new ArrayList<>();

        if (tableInfo.tableLength("products") > 0) {
            products = getAll();
            System.out.println("All products on stock: " + products.size());
        }

        if (tableInfo.tableLength("transactions") > 0) {
            transactions = getAllTransactions();
        }

        if (tableInfo.tableLength("customers") > 0) {
            customers = getAllCustomers();
        }

    }

    public Map<Long, Customer> getCustomers() {
        return customers;
    }

    public TableData getTableInfo() {
        return tableInfo;
    }

    @Override
    public List<Transaction> getTransactionsFilter(String filter) {

        String join = "select c.customer_id, c.name, t.date_transaction, p.product, tp.product_count " +
                "from customers c inner join transactions t \n" +
                "on c.customer_id = t.customer_id inner join transactions_products tp \n" +
                "on t.transaction_id = tp.transaction_id inner join products p \n" +
                "on p.product_id = tp.product_id where " + filter;
        List<Transaction> transactions = new ArrayList<>();

        ResultSet res = requests.select(join);

        try {
            while (res.next()) {
                long id = res.getInt("customer_id");
                String product = res.getString("product");
                int productsCount = res.getInt("product_count");
                String customerName = res.getString("name");
                Timestamp date = res.getTimestamp("date_transaction");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = sdf.format(date);
                Transaction t = new Transaction();
                Customer customer = new Customer(customerName, id);
                Bird bird = new Bird(product);
                t.setCustomer(customer);
                t.setBird(bird);
                t.setDate(dateString);
                t.setCount(productsCount);
                transactions.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        String join = "select c.customer_id, c.name, t.date_transaction, p.product, tp.product_count " +
                "from customers c inner join transactions t \n" +
                "on c.customer_id = t.customer_id inner join transactions_products tp \n" +
                "on t.transaction_id = tp.transaction_id inner join products p \n" +
                "on p.product_id = tp.product_id ";
        List<Transaction> transactions = new ArrayList<>();

            ResultSet res = requests.select(join);

        try {
            while (res.next()) {
                long id = res.getInt("customer_id");
                String product = res.getString("product");
                int productsCount = res.getInt("product_count");
                String customerName = res.getString("name");
                Timestamp date = res.getTimestamp("date_transaction");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = sdf.format(date);
                Transaction t = new Transaction();
                Customer customer = new Customer(customerName, id);
                Bird bird = new Bird(product);
                t.setCustomer(customer);
                t.setBird(bird);
                t.setDate(dateString);
                t.setCount(productsCount);
                transactions.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return transactions;
    }

    @Override
    public List<Payment> getPayments(Customer customer) {
        List<Payment> payments = new ArrayList<>();
        String query = "select type_payment, purchase_type, balance_payment from customer_payment where customer_id = "
                + customer.getId();

            ResultSet res = requests.select(query);

        try {
            while (res.next()) {
                String typePayment = res.getString("type_payment");
                PurchaseType purchaseType = PurchaseType.valueOf(res.getString("purchase_type"));
                double balance = res.getDouble("balance_payment");
                Payment payment = new Payment();
                payment.setTypePayment(typePayment);
                payment.setPurchaseType(purchaseType);
                payment.setNumberPayment(balance);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return payments;
    }


    @Override
    public Map<String, Bird> getAll() {
        Map<String,Bird> products = new HashMap<>();
        String join = "select p.product_id, p.product, p.category, s.how_match, s.price, s.profit, s.how_match_sold from products p INNER join product_statistic s on p.product_id = s.product_id ";

            ResultSet res = requests.select(join);

        try {
            while (res.next()) {
                long id = res.getInt("product_id");
                String product = res.getString("product");
                Category category = Category.valueOf(res.getString("category"));
                int howMatch = res.getInt("how_match");
                double price = res.getDouble("price");
                double profit = res.getDouble("profit");
                int soldBid = res.getInt("how_match_sold");
                Bird b = new Bird(id, product, category, howMatch, price, profit, soldBid);
                products.put(product, b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Call get all products, products size: " + products.size());
        return products;
    }




    @Override
    public Map<Long,Customer> getAllCustomers() {
        Map<Long,Customer> customers = new HashMap<>();

            ResultSet res = requests.select("select * from customers ");

        try {
            while (res.next()) {
                long id = res.getInt("customer_id");
                String name = res.getString("name");
                Customer customer = new Customer(name, id);
                customers.put(id, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return customers;
    }

    @Override
    public Map<String, Bird> getToCategory(String categoryFilter) {
        Map<String,Bird> products = new HashMap<>();
        String join = "select p.product_id, p.product, p.category, s.how_match, s.price, s.profit, s.how_match_sold from products p INNER join product_statistic s on p.product_id = s.product_id where category = '" + categoryFilter + "'";

        ResultSet res = requests.select(join);

        try {
            while (res.next()) {
                long id = res.getInt("product_id");
                String product = res.getString("product");
                Category category = Category.valueOf(res.getString("category"));
                int howMatch = res.getInt("how_match");
                double price = res.getDouble("price");
                double profit = res.getDouble("profit");
                int soldBid = res.getInt("how_match_sold");
                Bird b = new Bird(id, product, category, howMatch, price, profit, soldBid);
                products.put(product, b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Call get all products, products size: " + products.size());
        return products;
    }

}
