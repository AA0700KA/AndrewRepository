package com.midgardabc.day7.Frame6;

/**
 * Created by user on 15.07.2015.
 */
public class Stack {

    Node root;

    public void push(Object object)
    {
        Node n = new Node();
        n.object = object;

        if (root != null)
        {
            n.ref = root;
        }
        root = n;
    }

    public Object pop()
    {
        Node cp = root;
        if (cp != null) {
            root = cp.ref;
            return cp.object;
        }
        return null;
    }

    public Object peak()
    {
        if (root != null) {
            return root.object;
        }
        throw new IllegalStateException("There no objects in stack");
    }

    public void printStackInfo()
    {
        Node cp = root;
        System.out.print("[");
        while (cp != null)
        {
            System.out.print(cp.object + ", ");
            cp = cp.ref;
        }
        System.out.print("]");
    }


    private class Node {

        Node ref;
        Object object;
    }
}
