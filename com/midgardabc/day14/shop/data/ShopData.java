package com.midgardabc.day14.shop.data;

import com.midgardabc.day14.shop.domain.Bird;
import com.midgardabc.day14.shop.domain.Customer;
import com.midgardabc.day14.shop.domain.Payment;
import com.midgardabc.day14.shop.domain.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 03.11.2015.
 */
public interface ShopData {

    List<Payment> getPayments(Customer customer);

    Map<String,Bird> getAll();

    Map<String, Bird> getToCategory(String category);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsFilter(String filter);

    Map<Long,Customer> getAllCustomers();

}
