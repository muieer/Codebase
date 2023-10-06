package org.muieer.java.leetcode;

/*
* 判断子序列：https://leetcode.cn/problems/is-subsequence/
* 时间复杂度 O(n)
* 空间复杂度 O(1)
* 思路：双指针
* */
public class LeetCode392 {

    public static void main(String[] args) {
        var demo = new LeetCode392();
        System.out.println(demo.isSubsequence("ac", "ax"));
    }

    public boolean isSubsequence(String s, String t) {

        if (s.length() == 0) return true;
        if (t.length() == 0) return false;

        int ls = 0, rs = s.length() - 1;
        int lt = 0, rt = t.length() - 1;

        while(ls <= rs) {

            // 找到 s 字符串左指针在 t 中对应位置
            while (lt <= rt && t.charAt(lt) != s.charAt(ls)) {
                lt++;
            }
            // 找到了
            if (lt <= rt) {
                ls++;
                lt++;
            }

            while (lt <= rt && t.charAt(rt) != s.charAt(rs)) {
                rt--;
            }
            // 找到了
            if (lt <= rt) {
                rs--;
                rt--;
            }

            if (lt > rt) break;
        }

        return ls > rs;
    }
}
