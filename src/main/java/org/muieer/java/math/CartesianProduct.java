package org.muieer.java.math;

import com.google.common.collect.Sets;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/*
* 求笛卡尔积
* */
public class CartesianProduct {

    public static void main(String[] args) {
//        benchmark();
        customCartesianProduct();
    }

    public static void benchmark() {

        for (int i = 0; i < 10000; i++) {
            var res = cartesianProduct(new long[]{1, 2, 3, 4}, new long[]{4, 5, 6, 7}, new long[]{7, 8, 9, 0});
            if (i == 999) System.out.println(Arrays.deepToString(res));
        }
        {
            var start = Instant.now().toEpochMilli();
            var size = 0;
            for (int i = 0; i < 100000; i++) {
                var res = cartesianProduct(new long[]{1, 2, 3, 4}, new long[]{4, 5, 6, 7}, new long[]{7, 8, 9, 0});
                size += res.length;
            }
            System.out.println(Instant.now().toEpochMilli() - start);
            System.out.println(size);
        }

        for (int i = 0; i < 10000; i++) {
            var res = calculateCartesianProduct3Array(new long[]{1, 2, 3, 4}, new long[]{4, 5, 6, 7}, new long[]{7, 8, 9, 0});
            if (i == 999) System.out.println(Arrays.deepToString(res));
        }
        {
            var start = Instant.now().toEpochMilli();
            var size = 0;
            for (int i = 0; i < 100000; i++) {
                var res = calculateCartesianProduct3Array(new long[]{1, 2, 3, 4}, new long[]{4, 5, 6, 7}, new long[]{7, 8, 9, 0});
                size += res.length;
            }
            System.out.println(Instant.now().toEpochMilli() - start);
            System.out.println(size);
        }

        for (int i = 0; i < 10000; i++) {
            var res = Sets.cartesianProduct(Set.of(1L, 2L, 3L, 4L), Set.of(4L, 5L, 6L, 7L), Set.of(7L, 8L, 9L, 0L));
            if (i == 999) System.out.println(res);
        }
        {
            var start = Instant.now().toEpochMilli();
            var size = 0;
            for (int i = 0; i < 100000; i++) {
                var res = Sets.cartesianProduct(Set.of(1L, 2L, 3L, 4L), Set.of(4L, 5L, 6L, 7L), Set.of(7L, 8L, 9L, 0L));
                size += res.size();
            }
            System.out.println(Instant.now().toEpochMilli() - start);
            System.out.println(size);
        }
    }

    /*
    * 输入的是 Set，没有重复元素
    * */
    public static void guavaCartesianProduct() {
        Set<List<Long>> cartesianProduct = Sets.cartesianProduct(Set.of(1L, 2L, 3L, 4L), Set.of(4L, 5L, 6L, 7L), Set.of(7L, 8L, 9L, 0L));
        System.out.println(cartesianProduct);
    }

    /*
    * 有重复元素
    * */
    public static void customCartesianProduct() {
        var res1 = cartesianProduct(new long[]{1, 2, 3});
        System.out.println(Arrays.deepToString(res1));
        var res2 = cartesianProduct(new long[]{1, 2, 3}, new long[]{4, 5, 6});
        System.out.println(Arrays.deepToString(res2));
        var res3 = cartesianProduct(new long[]{1, 2, 3}, new long[]{4, 5, 6}, new long[]{7, 8, 9});
        System.out.println(Arrays.deepToString(res3));
        var res4 = cartesianProduct(new long[]{1, 2, 3}, new long[]{4, 5, 6}, new long[]{7, 8, 9}, new long[]{0});
        System.out.println(Arrays.deepToString(res4));
    }

    public static long[][] cartesianProduct(long[]... arrays) {

        switch (arrays.length) {
            case 1 -> {
                return calculateCartesianProduct1Array(arrays[0]);
            }
            case 2 -> {
                return calculateCartesianProduct2Array(arrays);
            }
            case 3 -> {
                return calculateCartesianProduct3Array(arrays);
            }
            default -> {
                int size = 1;
                for (long[] array: arrays) {
                    size *= array.length;
                }
                long[][] res = new long[size][arrays.length];

                calculateCartesianProduct(arrays, 0, new long[0], 0, res);
                return res;
            }
        }
    }

    private static long[][] calculateCartesianProduct1Array(long[] array) {
        long[][] res = new long[array.length][1];
        for (int i = 0; i < array.length; i++) {
                res[i] = new long[]{array[i]};
        }
        return res;
    }

    private static long[][] calculateCartesianProduct2Array(long[]... arrays) {

        int size = 1;
        for (long[] array: arrays) {
            size *= array.length;
        }
        long[][] res = new long[size][2];

        int index = 0;
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays[1].length; j++) {
                res[index++] = new long[]{arrays[0][i], arrays[1][j]};
            }
        }

        return res;
    }

    private static long[][] calculateCartesianProduct3Array(long[]... arrays) {

        int size = 1;
        for (long[] array: arrays) {
            size *= array.length;
        }
        long[][] res = new long[size][3];

        int index = 0;
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays[1].length; j++) {
                for (int l = 0; l < arrays[2].length; l++) {
                    res[index++] = new long[]{arrays[0][i], arrays[1][j], arrays[2][l]};
                }
            }
        }

        return res;
    }

    /*
     * 二维数组有数组 a1、a2...an，长度为 l1、l2...ln，size = l1 * l2 * ... * ln
     * 时间复杂度 O(size)
     * 空间复杂度 O(n)
     * */
    private static int calculateCartesianProduct(long[][] arrays, int level, long[] elements, int index, long[][] res) {

        // 遍历结束所有层的元素了
        if (level == arrays.length) {
            res[index] = elements;
            return ++index;
        }

        for (long num: arrays[level]) {
            var newElements = Arrays.copyOf(elements, elements.length + 1);
            newElements[level] = num;
            index = calculateCartesianProduct(arrays, level + 1, newElements, index, res);
        }

        return index;
    }

}
