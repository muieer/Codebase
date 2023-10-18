package org.muieer.java.leetcode;


public class LeetCode226 {

    public TreeNode invertTree(TreeNode root) {
        return compute(root);
    }

    public TreeNode compute(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        TreeNode temp =  compute(root.left);
        root.left = compute(root.right);
        root.right = temp;

        return root;
    }
}
