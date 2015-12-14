package com.midgardabc.day5.Frame3;

/**
 * Created by user on 01.07.2015.
 */
public class Customer {

    private String name;

    public Customer(String name)
    {
       this.name = name;
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
            if (name.equals(c.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 25;
        result = 37*result + name.hashCode();
        return result;
    }
}
