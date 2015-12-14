package com.midgardabc.day10.frame2;

/**
 * Created by user on 29.08.2015.
 */

@Service(name = "Super Working Service")
public class ServiceClass {


    @initService
    public void initService()
    {
        System.out.println("initService()");
    }

    public void method() {
        System.out.print("No init");
    }

}
