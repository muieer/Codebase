package org.muieer.java.leetcode;

public class LeetCode110 {

    boolean balanced = true;

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        else {
            fun(root);
            return balanced;
        }
    }

    public void fun(TreeNode root) {
        if (root == null) {}
        else {
            if (balanced) {
                balanced = Math.abs(compute(root.left) - compute(root.right)) <= 1;
            }
            if (!balanced) {
                return;
            }
            fun(root.left);
            fun(root.right);
        }
    }

    public int compute(TreeNode root) {
        if (root == null) { return 0; }
        else {
            return Math.max(compute(root.left), compute(root.right)) + 1;
        }
    }
}
