package com.midgardabc.day10.frame2;

import java.lang.annotation.*;

/**
 * Created by user on 29.08.2015.
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    String name();

    boolean lazyLoad() default false;

}
