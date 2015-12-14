package com.midgardabc.day7.Frame6;

/**
 * Created by user on 15.07.2015.
 */
public class Launcher {

    public static void main(String[] args)
    {
        Stack stack = new Stack();

        stack.push("Andrew");
        stack.push("Wendra");
        stack.push("TLC");
        stack.push("Kozub");

        stack.printStackInfo();

        System.out.println(stack.peak());

        stack.printStackInfo();

        System.out.println(stack.pop());

        stack.printStackInfo();

        System.out.println(stack.pop());

        stack.printStackInfo();
    }

}

