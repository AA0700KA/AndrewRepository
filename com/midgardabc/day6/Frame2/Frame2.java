package com.midgardabc.day6.Frame2;

/**
 * Created by user on 04.07.2015.
 */
public class Frame2 {

    public static void main(String[] args) {
        try {
            return;
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("I want to be excuted!");
        }
    }

}
