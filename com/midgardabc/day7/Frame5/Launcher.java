package com.midgardabc.day7.Frame5;

import java.util.Arrays;

/**
 * Created by user on 14.07.2015.
 */
public class Launcher {

    public static void main(String[] args)
    {
        SimpleArrayList sal = new SimpleArrayList();

        sal.add("Andrew");
        sal.add("Wendra");
        sal.add("TDU");
        sal.add("Bomzi");
        sal.add("Betsko");
        sal.add("Kozub");
        sal.remove("Andrew");
        //sal.remove("Wendra");
        //sal.remove("Bomzi");

        for (Object o: sal)
        {
            if (o instanceof String){
                String s = (String)o;
                System.out.println(s);
            }
        }

        System.out.println(sal.getSize());

        sal.printListInfo();

        System.out.println(sal.contains("Kozub"));
        System.out.println(sal.contains("TDU"));
        System.out.println(sal.contains("Andrew"));

        System.out.println(sal.get(3));

        System.out.println(Arrays.toString(sal.toArray()));

        Object[] o = sal.toArray();
        if (o[1] instanceof String) {
            System.out.println(o[1]);
        }
    }

}
