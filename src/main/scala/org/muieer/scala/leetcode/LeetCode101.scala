package org.muieer.scala.leetcode

object LeetCode101 {

  def isSymmetric(root: TreeNode): Boolean = {
    compute(root.left, root.right)
  }

  def compute(left: TreeNode, right: TreeNode): Boolean = {
    if (left == null && right == null) {
      return true
    }

    if (left == null || right == null ||left.value != right.value) {
      return false
    }

    compute(left.left, right.right) && compute(left.right, right.left)
  }

}
