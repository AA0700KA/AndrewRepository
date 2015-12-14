package com.midgardabc.day6.Frame3;

/**
 * Created by user on 04.07.2015.
 */
public class Frame3 {

    public static void main(String[] args) {
        try {
            throw new Exception();
        }catch(Exception e) {
            throw new IllegalStateException();
        }finally{
            System.out.println("I want to be excuted!");
        }
    }
}
