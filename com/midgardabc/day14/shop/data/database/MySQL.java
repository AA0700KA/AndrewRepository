package com.midgardabc.day14.shop.data.database;

/**
 * Created by user on 03.11.2015.
 */
public class MySQL implements DataBase {

    public final String URL = "jdbc:mysql://localhost:3306/birdsshop";
    public final String LOGIN = "root";
    public final String PASSWORD = "root";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final int INITIAL_POOL_SIZE = 20;
    private final int MIN_POOL_SIZE = 20;
    private final int ACQUIRE_INCREMENT = 20;
    private final int MAX_POOL_SIZE = 400;
    private final int MAX_STATEMENTS = 400;

    public MySQL() {

    }

    @Override
    public String getURL() {
        return URL;
    }

    public String getUSER() {
        return LOGIN;
    }

    @Override
    public String getPASSWORD() {
        return PASSWORD;
    }

    @Override
    public String getDRIVER() {
        return DRIVER;
    }

    @Override
    public int getINITIAL_POOL_SIZE() {
        return INITIAL_POOL_SIZE;
    }

    @Override
    public int getMIN_POOL_SIZE() {
        return MIN_POOL_SIZE;
    }

    @Override
    public int getACQUIRE_INCREMENT() {
        return ACQUIRE_INCREMENT;
    }

    @Override
    public int getMAX_POOL_SIZE() {
        return MAX_POOL_SIZE;
    }

    @Override
    public int getMAX_STATEMENTS() {
        return MAX_STATEMENTS;
    }

}
