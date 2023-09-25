package org.muieer.java;

import java.util.HashMap;
import java.util.Map;

public class MapExample {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.putIfAbsent("city", "北京");
        map.computeIfPresent("city", (key, oldValue) -> oldValue + "," + "上海");
        System.out.println(map);

    }
}
