package com.midgardabc.day14.shop.data.database;

/**
 * Created by user on 03.11.2015.
 */
public interface DataBase {

     String getURL();

     String getDRIVER();

     String getUSER();

     String getPASSWORD();

     int getINITIAL_POOL_SIZE();

     int getMIN_POOL_SIZE();

     int getACQUIRE_INCREMENT();

     int getMAX_POOL_SIZE();

     int getMAX_STATEMENTS();

}
