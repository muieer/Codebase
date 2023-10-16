package org.muieer.java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        compute(nums, 0, new ArrayList<>(), res);
        return res;
    }

    public void compute(int[] nums, int index, List<Integer> element, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(element);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            var newElement = new ArrayList<>(element);
            newElement.add(nums[index]);
            compute(nums, index + 1, newElement, res);
            swap(nums, index, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
