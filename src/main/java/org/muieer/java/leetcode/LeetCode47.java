package org.muieer.java.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode47 {

    public static void main(String[] args) {
        var demo = new LeetCode47();
        System.out.println(demo.permuteUnique(new int[]{2, 2, 1, 1}));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        compute(nums, 0, new ArrayList<>(), res);
        return res;
    }

    public void compute(int[] nums, int index, List<Integer> element, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(element));
            return;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            if (set.contains(nums[index])) {
                swap(nums, index, i);
                continue;
            }
            set.add(nums[index]);
            element.add(nums[index]);
            compute(nums, index + 1, element, res);
            element.remove(index);
            swap(nums, index, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
