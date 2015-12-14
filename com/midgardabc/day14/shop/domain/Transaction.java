package com.midgardabc.day14.shop.domain;

/**
 * Created by user on 25.07.2015.
 */
public class Transaction {

    private Customer customer;
    private Bird bird;
    private String date;
    private int count;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Transaction()
    {

    }

}
