package com.midgardabc.day10.frame2;

/**
 * Created by user on 29.08.2015.
 */

@Service(name = "Very lazy service")
public class LazyService {


    @initService
    public void lazyInit() throws Exception
    {
        System.out.println("lazyInit()");

    }

    public void noInit()
    {
        System.out.print("noInit()");
    }
}
