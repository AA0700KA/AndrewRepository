package com.midgardabc.day14.shop.logic;

import com.midgardabc.day14.shop.data.database.DBRequest;
import com.midgardabc.day14.shop.domain.Customer;

import java.sql.*;

/**
 * Created by user on 28.10.2015.
 */
public class DataInfo implements TableData {

    private DBRequest requests;

    public DataInfo(DBRequest requests) {
        this.requests = requests;
    }


    @Override
    public boolean checkToElement(String query) {

            ResultSet res = requests.select(query);
        try {
            if (res.next()) {
                return true;
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

        return false;
    }

    @Override
    public int lastId(String tableName, String idName) {

            ResultSet res = requests.select("select MAX(" + idName + ") from " + tableName + " ");
        try {
            if (res.next()) {
                return res.getInt(1);
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

        return 0;
    }

    @Override
    public String getStringToId(String tableName, String valueName, int id, String idName) {

            ResultSet res = requests.select("select " + valueName + " from " + tableName + " where " + idName + " = " + id);
        try {
            if (res.next()) {
                return res.getString(1);
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

        return null;
    }

    @Override
    public int tableLength(String tableName) {

        ResultSet res = requests.select("select COUNT(*) from " + tableName);
        try {
            if (res.next()) {
                return res.getInt(1);
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

        return -1;
    }

    @Override
    public int getIdToString(String tableName, String value, String valueName, String idName) {

            ResultSet res = requests.select("select " + idName + " from " + tableName + " where " + valueName + " = '" + value + "' ");
        try {
            if (res.next()) {
                return res.getInt(1);
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

        return -1;
    }

    @Override
    public boolean checkToLoginAndPassword(String login, String password) {
        ResultSet res = requests.select("select customer_id from users where login = '" + login + "' AND password = '" + password + "'");
        try {
            if (res.next()) {
                return true;
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
        return false;
    }

    @Override
    public Customer getCurrentCustomer(String login, String password) {
        ResultSet res = requests.select("select customer_id from users where login = '" + login + "' AND password = '" + password + "'");
        try {
            if (res.next()) {
                int id = res.getInt(1);
                String name = getStringToId("customers", "name", id, "customer_id");
                System.out.println(name);
                Customer customer = new Customer(name, id);
                return customer;
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
        return null;
    }

}
