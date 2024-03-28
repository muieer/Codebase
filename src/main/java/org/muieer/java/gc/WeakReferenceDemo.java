package org.muieer.java.gc;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object obj = new Object();
        Map<Object, String> map = new WeakHashMap<>();
        map.put(obj, "test");
        System.out.println("map content is " + map);
        System.out.println("invoke obj = null");
        obj = null;
        System.out.println("obj current is " + obj);
        System.out.println("invoke System.gc()");
        System.gc();
        System.out.printf("map content is %s after gc", map);
    }
}
