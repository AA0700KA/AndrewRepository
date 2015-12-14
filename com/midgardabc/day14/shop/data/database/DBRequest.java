package com.midgardabc.day14.shop.data.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by user on 23.11.2015.
 */
public class DBRequest {

    @Deprecated
    private ComboPooledDataSource cpds;
    private Queue<Connection> connectionPool;

    public DBRequest(DataBase db) {
        connectionPool = getConnectionPool(db);
    }

    /**
     * I'm try'ing to use connection pool c3p0,
     * but it is buggy sooner or later and I use simple
     * connection pool
     */

    @Deprecated
    private ComboPooledDataSource getConnectionPoolc3p0(DataBase db) {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(db.getDRIVER());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(db.getURL());
        cpds.setUser(db.getUSER());
        cpds.setPassword(db.getPASSWORD());
        cpds.setInitialPoolSize(db.getINITIAL_POOL_SIZE());
        cpds.setMinPoolSize(db.getMIN_POOL_SIZE());
        cpds.setAcquireIncrement(db.getACQUIRE_INCREMENT());
        cpds.setMaxPoolSize(db.getMAX_POOL_SIZE());
        cpds.setMaxStatements(db.getMAX_STATEMENTS());
        return cpds;
    }

    private Queue<Connection> getConnectionPool(DataBase db) {
        Queue<Connection> connectionPool = new LinkedBlockingQueue<>();
        try {
            Class.forName(db.getDRIVER());
            for (int i = 0; i < 5; i++) {
                Connection connection = DriverManager.getConnection(db.getURL(), db.getUSER(), db.getPASSWORD());
                connectionPool.add(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connectionPool;
    }

    public void cud(String query) {

          Connection connection = connectionPool.poll();
        try {

            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(query);
            System.out.println(result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             connectionPool.add(connection);
        }
    }


    public ResultSet select(String query) {
        ResultSet res = null;
        Connection connection = connectionPool.poll();
        try  {
            Statement statement = connection.createStatement();
            res = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.add(connection);
        }
        return res;
    }

}

