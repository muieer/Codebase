package org.muieer.java.math;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/*
* 求笛卡尔积
* */
public class CartesianProduct {

    public static void main(String[] args) {
        customCartesianProduct();
//        guavaCartesianProduct();
    }

    /*
    * 输入的是 Set，没有重复元素
    * */
    public static void guavaCartesianProduct() {
        Set<List<Long>> cartesianProduct = Sets.cartesianProduct(Set.of(1L, 2L, 3L), Set.of(4L, 5L, 6L), Set.of(7L, 8L, 9L));
        System.out.println(cartesianProduct);
    }

    /*
    * 有重复元素
    * */
    public static void customCartesianProduct() {
        var res = cartesianProduct(new long[]{1, 2, 3}, new long[]{4, 5, 6}, new long[]{7, 8, 9});
        System.out.println(Arrays.deepToString(res));
        var res1 = cartesianProduct(new long[]{1, 1}, new long[]{4}, new long[]{7});
        System.out.println(Arrays.deepToString(res1));
        var res2 = cartesianProduct(new long[]{1, 1, 1}, new long[]{4, 5, 6}, new long[]{7, 8, 9}, new long[]{0, 1});
        System.out.println(Arrays.deepToString(res2));
        System.out.println(Arrays.deepToString(cartesianProduct(new long[]{})));
    }

    /*
    * 二维数组有数组 a1、a2...an，长度为 l1、l2...ln，size = l1 * l2 * ... * ln
    * 时间复杂度 O(size)
    * 空间复杂度 O(n)
    * */
    public static long[][] cartesianProduct(long[]... arrays) {
        int size = 1;
        for (long[] array: arrays) {
            size *= array.length;
        }
        long[][] res = new long[size][arrays.length];

        calculateCartesianProduct(arrays, 0, new long[0], 0, res);
        return res;
    }

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
