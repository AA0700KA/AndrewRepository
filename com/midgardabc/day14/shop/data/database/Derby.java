package com.midgardabc.day14.shop.data.database;

/**
 * Created by user on 08.11.2015.
 */
public class Derby implements DataBase {

    public final String URL = "jdbc:derby:birds";
    private final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    private final int INITIAL_POOL_SIZE = 10;
    private final int MIN_POOL_SIZE = 10;
    private final int ACQUIRE_INCREMENT = 10;
    private final int MAX_POOL_SIZE = 200;
    private final int MAX_STATEMENTS = 200;

    public Derby() {

    }

    @Override
    public String getURL() {
        return URL;
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

    @Override
    public String getPASSWORD() {
        return "";
    }

    @Override
    public String getUSER() {
        return "";
    }

}
