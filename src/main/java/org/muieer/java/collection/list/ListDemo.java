package org.muieer.java.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) {

        twoDimensionalListToTwoDimensionalArray();
    }

    // 二维 List 转二维 int 数组
    public static void twoDimensionalListToTwoDimensionalArray() {

        List<List<Integer>> list = new ArrayList<>(){{
            add(List.of(1));
            add(List.of(2, 3));
            add(List.of(4, 5, 6));
        }};

        int[][] twoDimensionalArray = list.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .toArray(x -> new int[x][]);
        System.out.println(Arrays.deepToString(twoDimensionalArray));

        twoDimensionalArray = list.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
        System.out.println(Arrays.deepToString(twoDimensionalArray));

        twoDimensionalArray = list.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray()).toList()
                .toArray(new int[0][0]);
        System.out.println(Arrays.deepToString(twoDimensionalArray));

    }
}
