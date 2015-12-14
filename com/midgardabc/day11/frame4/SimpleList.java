package com.midgardabc.day11.frame4;

import java.util.Iterator;

/**
 * Created by user on 15.09.2015.
 */
public interface SimpleList<T> {

    void add(T object);

    boolean contains(T object);

    void remove(T object);

    int size();

    Iterator<T> iterator();

    T get(int index);

}
