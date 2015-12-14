package com.midgardabc.day11.frame4;

import com.midgardabc.day10.frame4.Birds;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by user on 15.09.2015.
 */
public class Demo1 {

    public static void main(String[] args) throws IOException
    {
        SimpleList<String> stringList = new FileList<>();

        stringList.add("Andrew");
        stringList.add("Wendra");
        stringList.add("Kirill");
        stringList.add("Artemiy");
        stringList.add("Petro");
        stringList.add("Sergey");
        stringList.add("Vlad");
        stringList.add("Arkadiy");
        stringList.add("Serg");
        stringList.add("Petruha");
        stringList.add("TDU");
        stringList.add("Kilya");
        stringList.add("TT");
        stringList.add("RR");
        stringList.add("Lutik");
        stringList.remove("TT");
        stringList.remove("TDU");
        stringList.add("NewString");
        stringList.add("Hello World!");
        stringList.add("Last String!!! Horeyyy!!!");
        stringList.remove("Artemiy");
        stringList.add("On Artemiy's place, 4th String");

        stringList.remove("Hello World!");
        stringList.remove("Sergey");

        stringList.add("Today string");
        stringList.add("Str");
        stringList.add("Rix");
        stringList.remove("Rix");



        System.out.println(stringList.contains("Hello World!"));
        System.out.println(stringList.size());

       Iterator it = stringList.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

}
