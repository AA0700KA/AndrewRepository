package com.midgardabc.day14.shop.requests;

import com.midgardabc.day14.shop.Category;
import com.midgardabc.day14.shop.PurchaseType;
import com.midgardabc.day14.shop.domain.Bird;
import com.midgardabc.day14.shop.domain.Customer;

import java.util.Date;

/**
 * Created by user on 20.11.2015.
 */
public interface ShopOperations {

    void addProduct(String productName, Category category, int howMach, double price);

    void addTransaction(long idCustomer, String productName, int countBirds, Date date);

    double getBalance();

    void updateBalance(double sum);

    void buyBird(Customer customer, Bird bird, int count);

    void selfBird(String str, int count);

    void putPayment(PurchaseType purchaseType, double sumPurchase, String typePayment);

    void changePrice(String s, double price);

    void deleteBird(String bird);

    void removeTransactions();

    void register(String nameUser, String nam, String pass);

}
