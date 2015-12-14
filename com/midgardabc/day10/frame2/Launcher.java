package com.midgardabc.day10.frame2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by user on 02.09.2015.
 */
public class Launcher {

    public static void main(String[] args)
    {
        ApplicationManager app = new ApplicationManager();

        //app.insprectSevice(ServiceClass.class);
        //app.insprectSevice(LazyService.class);
        //app.insprectSevice(String.class);

        Method m = app.getService(new ServiceClass());

        try {
            m.invoke(new ServiceClass());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
