package com.midgardabc.day14.shop.data.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 09.11.2015.
 */
public class InitDerby {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:birds;create=true";

    public static void main(String[] args) {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            statement.execute("create table customer_payment(customer_id INT NOT NULL , " +
                    "type_payment VARCHAR(45) NOT NULL , " +
                    "purchase_type VARCHAR(45) NOT NULL , " +
                    "balance_payment DOUBLE NOT NULL )");
            statement.execute("create table customers(customer_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                              " name VARCHAR(45) NOT NULL , " +
                              "balance DOUBLE DEFAULT 0 NOT NULL )");
            statement.execute("create table product_statistic(product_id INT NOT NULL ," +
                              " how_match INT NOT NULL , " +
                              " how_match_sold INT DEFAULT 0 NOT NULL, " +
                              " profit DOUBLE DEFAULT 0 NOT NULL, " +
                              "price DOUBLE NOT NULL )");
            statement.execute("create table products(product_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                              " product VARCHAR(45) NOT NULL , " +
                              " category VARCHAR(45) NOT NULL )");
            statement.execute("create table transactions(transaction_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                    " customer_id INT NOT NULL , " +
                    " date_transaction TIMESTAMP NOT NULL )");
            statement.execute("create table transactions_products(transaction_id INT NOT NULL," +
                    " product_id INT NOT NULL, " +
                    " product_count INT NOT NULL )");
            statement.execute("create table users(customer_id INT NOT NULL," +
                    " login VARCHAR(45) NOT NULL , " +
                    " password VARCHAR(45) NOT NULL )");

            System.out.println("All tables creates correctly");

        } catch (SQLException e) {
          e.printStackTrace();
        }

    }

}
