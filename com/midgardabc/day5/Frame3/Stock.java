package com.midgardabc.day5.Frame3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by user on 24.07.2015.
 */
public class Stock {

    private final int DUCK = 0;
    private final int EAGLE = 1;

    private List<Bird> products;
    private HashSet<Customer> customers;
    private List<Transaction> transactions;

    public HashSet<Customer> getCustomers() {
        return customers;
    }

    public List<Bird> getProducts() {
        return products;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Stock()
    {
        Bird duck = new Duck();
        Bird e = new Eagle();
        products = new ArrayList<>();
        products.add(duck);
        products.add(e);
        transactions = new ArrayList<>();
        customers = new HashSet<>();
    }

    public int getDUCK() {
        return DUCK;
    }

    public int getEAGLE() {
        return EAGLE;
    }

    public void changePrice(String s, int price)
    {
        if (s.toLowerCase().equals("eagle")) products.get(EAGLE).setPrice(price);
        else products.get(DUCK).setPrice(price);
    }

    public void printCustomers()
    {
        int counter = 1;
        for (Customer c: customers) {
            System.out.println(counter +"." + c.getName());
            counter++;
        }
        System.out.println(customers.size());
    }


}
