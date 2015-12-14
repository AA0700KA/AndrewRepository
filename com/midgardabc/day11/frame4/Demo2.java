package com.midgardabc.day11.frame4;

import com.midgardabc.day10.frame4.Birds;

import java.io.IOException;

/**
 * Created by user on 18.09.2015.
 */
public class Demo2 {

    public static void main(String[] args) throws IOException
    {
       SimpleList<Birds> birds = new FileList<>();

        Birds duck = new Birds("Duck", 4, 5);
        Birds eagle = new Birds("Eagle", 7, 2);
        Birds cannary = new Birds("Cannary", 9, 9);

        birds.add(duck);
        birds.add(eagle);
        birds.add(cannary);

        birds.remove(eagle);

        Birds turkey = new Birds("Turkey", 4, 10);
        birds.add(turkey);

        Object o = birds.get(1);
        System.out.println(o);
    }
}
