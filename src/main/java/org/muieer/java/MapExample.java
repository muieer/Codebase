package org.muieer.java;

import java.util.HashMap;
import java.util.Map;

public class MapExample {

    public static void main(String[] args) {

        showComputeFunction();
    }

    public static void showComputeFunction() {

        Map<String, String> map = new HashMap<>();
        map.computeIfAbsent("city", key -> "Beijing");
        map.computeIfPresent("city", (key, oldValue) -> "Hangzhou" + "," + oldValue);
        map.compute("brand", (key, oldValue) -> {
            if (oldValue == null) {
                return "Mac";
            } else {
                return "iPhone" + "," + oldValue;
            }
        });
        map.compute("brand", (key, oldValue) -> {
            if (oldValue == null) {
                return "Mac";
            } else {
                return "iPhone" + "," + oldValue;
            }
        });
        System.out.println(map);
    }
}
