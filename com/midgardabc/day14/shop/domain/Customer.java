package com.midgardabc.day14.shop.domain;

/**
 * Created by user on 01.07.2015.
 */
public class Customer {

    private String name;
    private long id;

    public Customer(String name, long id)
    {
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer) {
            Customer c = (Customer) obj;
            if (name.equals(c.getName()) && id == c.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 25;
        result = 37*result + name.hashCode();
        result = 37*result + new Long(id).hashCode();
        return result;
    }
}
