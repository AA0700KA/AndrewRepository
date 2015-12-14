package com.midgardabc.day14.shop.logic;

import com.midgardabc.day14.shop.domain.Customer;

/**
 * Created by user on 28.10.2015.
 */
public interface TableData {

    boolean checkToElement(String query);

    int lastId(String tableName, String idName);

    String getStringToId(String tableName, String value, int id, String idName);

    int tableLength(String tableName);

    int getIdToString(String tableName, String value, String valueName, String idName);

    boolean checkToLoginAndPassword(String login, String password);

    Customer getCurrentCustomer(String login, String password);

}
