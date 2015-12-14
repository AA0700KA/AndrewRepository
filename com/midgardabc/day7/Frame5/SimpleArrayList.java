package com.midgardabc.day7.Frame5;

import java.util.IllegalFormatCodePointException;
import java.util.Iterator;

/**
 * Created by user on 14.07.2015.
 */
public class SimpleArrayList implements Iterable<Object>{

    Node root;
    int size;
    int nextIndex;
    Object[] objects;


    public SimpleArrayList()
    {
        size = 0;
        nextIndex = -1;
    }

    public int getSize()
    {
        return size;
    }


    public Iterator<Object> iterator()
    {
       return new SALIterator();
    }


    public void add(Object object)
    {
        Node n = new Node();
        n.object = object;

        if (root == null)
        {
            n.index = ++nextIndex;
            root = n;
        }else {
            Node previous = root;
            Node cp = root;
            while (cp.ref != null)
            {
                cp = cp.ref;
            }
            n.index = ++nextIndex;
            cp.ref = n;
        }
        size++;
    }



    public void remove(Object object)
    {
        Node presentObject = null;
        Node cp = root;
        Node previous = root;

        do{
            if (cp.object == object)
            {
                presentObject = cp;
                break;
            }
            previous = cp;
            cp = cp.ref;
        }while (cp != null);

        if (presentObject == null)
        {
            return;
        }

        if (presentObject.ref != null)
        {
            cp = presentObject;
            cp = cp.ref;

            do {
                cp.index--;
                cp = cp.ref;
            }while (cp != null);
        }
        if (previous == root && previous == presentObject) {
            root = presentObject.ref;
        }
        else {
            previous.ref = presentObject.ref;
        }
        size--;
    }

    public boolean contains(Object object)
    {
        Node cp = root;

        do {
            if (cp.object == object) {
                return true;
            }
            cp = cp.ref;
        }while(cp != null);

        return false;
    }


    public Object get(int index)
    {
        if (index >= 0 && index < size)
        {
            Node cp = root;
            while (cp != null)
            {
                if (cp.index == index)
                {
                    return cp.object;
                }
                cp = cp.ref;
            }
        }
        throw new IllegalStateException("Out of index:)");
    }

    public Object[] toArray()
    {
        objects = new Object[size];
        Node cp = root;
        while (cp != null)
        {
            objects[cp.index] = cp.object;
            cp = cp.ref;
        }
        return objects;
    }


    public void printListInfo()
    {
        Node cp = root;
        while (cp != null)
        {
            cp.printNodeInfo();
            cp = cp.ref;
        }
    }

    private class Node{

        Node ref;
        Object object;
        int index;

        private void printNodeInfo()
        {
            System.out.println("{" + object + "," + index + "}");
        }


    }

    private class SALIterator implements Iterator<Object>{

        Node cp;

        @Override
        public boolean hasNext() {
            return (cp == null && root != null)||(cp != null && cp.ref != null);
        }

        @Override
        public Object next() {
            if (cp == null && root != null)
            {
                cp = root;
                return cp.object;
            }
            if (hasNext())
            {
                cp = cp.ref;
                return cp.object;
            }
            throw new IllegalStateException("No objects here");
        }
    }
}
