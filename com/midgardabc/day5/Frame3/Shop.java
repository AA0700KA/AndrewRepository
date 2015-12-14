package com.midgardabc.day5.Frame3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by user on 01.07.2015.
 */
public class Shop {

    private Stock stock;

    private BirdsUI birdsUI;


    public Shop()
    {
        stock = new Stock();
        birdsUI = new BirdsUI(this, stock);
    }


    public Stock getStock() {
        return stock;
    }

    public void buyBird(String name, Bird bird, int count)
   {
       int eagleIndex = stock.getEAGLE();
       int duckIndex = stock.getDUCK();

       Transaction t = new Transaction();
       Customer customer = new Customer(name);
       stock.getCustomers().add(customer);
       Date date = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd.MM.yy");
       t.setCustomer(customer);
       t.setBird(bird);
       t.setCount(count);
       t.setDate(sdf.format(date));
       stock.getTransactions().add(t);

       if (bird instanceof Eagle) {
           stock.getProducts().get(eagleIndex).updateCount(count);
       } else if (bird instanceof Duck) {
           stock.getProducts().get(duckIndex).updateCount(count);
       }

   }

    public void getTransactionInfo()
    {
        birdsUI.printTransactions();
    }

   public void birdsOnStack()
   {

   }

    public void printPriceInfo()
    {
        for (int i = 0; i < stock.getProducts().size(); i++) {
            Bird b = stock.getProducts().get(i);
            System.out.println(b.getName() + " - " + b.getPrice());
        }
    }

}
