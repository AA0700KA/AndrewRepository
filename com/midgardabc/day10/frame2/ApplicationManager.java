package com.midgardabc.day10.frame2;


import java.lang.reflect.Method;

/**
 * Created by user on 29.08.2015.
 */
public class ApplicationManager {



    public void insprectSevice(Class<?> service)
    {
       if (service.isAnnotationPresent(Service.class)) {
           Service annot = service.getAnnotation(Service.class);
           System.out.println("Annotation " + annot.name() + " to class " + service.getSimpleName());

           Method[] methods = service.getMethods();

           Object obj = null;
           try {
                obj = service.newInstance();
           } catch (Exception e) {
               e.printStackTrace();
           }


          if (obj != null) {
              for (Method method : methods) {
                  if (method.isAnnotationPresent(initService.class)) {
                      try {
                          method.invoke(obj);
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }
              }
          }

       }
       else {
           System.err.println("Class is't annoteted " + Service.class.getSimpleName() + " annotation");
       }
    }


    public <T>Method getService(T type)
    {
        Class<T> c = (Class<T>) type.getClass();
        if (c.isAnnotationPresent(Service.class)) {
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(initService.class)) return method;
            }
        }
        throw new IllegalStateException("Method is't Service");
    }




}
