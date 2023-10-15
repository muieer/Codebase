package org.muieer.scala.leetcode

import math.max

/*
* 二叉树最大深度
* */
object LeetCode104 {

  def maxDepth(root: TreeNode): Int = {
    fun(root)
  }

  def fun(root: TreeNode): Int = {
    if (root == null) return 0
    val left = fun(root.left)
    val right = fun(root.right)
    max(left, right) + 1
  }

}
