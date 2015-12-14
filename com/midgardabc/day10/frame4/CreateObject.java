package com.midgardabc.day10.frame4;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by user on 28.08.2015.
 */
public class CreateObject<T> {

    public T initClass(Class<T> c, List<Object> parameters)
    {
        Constructor[] constructors =  c.getConstructors();
        Class[] listParamType = null;

        for (Constructor constructor: constructors) {
            if (constructor.getParameterTypes().length == parameters.size()) {
                Class[] paramType = constructor.getParameterTypes();
                int count = 0;

                for (int i = 0; i < parameters.size(); i++) {
                    Class cl = returnClass(parameters.get(i).getClass());
                    System.out.println(paramType[i] + " and " + cl);
                    if (paramType[i] == cl) {
                        count++;
                    }
                }
                if (count == parameters.size()) {
                    listParamType = paramType;
                    break;
                }
            }
        }

        if (listParamType == null) {
              throw new IllegalStateException("No this constructor in this class");
        }

        Constructor constructor = null;

        try {
             constructor = c.getConstructor(listParamType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        T object = null;
        Object[] param = parameters.toArray();
        try {
            object = (T) constructor.newInstance(param);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return object;
    }


    private Class returnClass(Class clazz)
    {
        if (clazz == Integer.class) {
            clazz = int.class;
        } else if (clazz == Double.class) {
            clazz = double.class;
        } else if (clazz == Long.class) {
            clazz = long.class;
        } else if (clazz == Boolean.class) {
            clazz = boolean.class;
        }
        return clazz;
    }
}
