package org.muieer.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode100095 {

    public List<Integer> lastVisitedIntegers(List<String> words) {

        int index = 0;
        var nums = new int[0];
        List<Integer> res = new ArrayList<>();
        int pCount = 0;

        for(String str: words) {
            if (str.charAt(0) != 'p') {
                nums = Arrays.copyOf(nums, nums.length + 1);
                nums[nums.length -1] = Integer.parseInt(str);
                pCount = 0;
            } else {
                String preStr = "a";
                if (index > 0) {
                    preStr = words.get(index - 1);
                }

                if (preStr.charAt(0) == 'p') {
                    pCount++;
                } else {
                    pCount = 1;
                }
                if (nums.length -pCount >= 0) {
                    res.add(nums[nums.length - pCount]);
                } else {
                    res.add(-1);
                }
            }
            index++;
        }

        return res;
    }
}
