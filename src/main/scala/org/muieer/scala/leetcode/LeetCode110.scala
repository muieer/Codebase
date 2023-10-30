package org.muieer.scala.leetcode

object LeetCode110 {

  def main(args: Array[String]): Unit = {
    println(isBalanced(new TreeNode(1)))
  }

  var balanced: Boolean = true

  def isBalanced(root: TreeNode): Boolean = {
    if (root == null) { true }
    else {
      fun(root)
      balanced
    }
  }

  def fun(root: TreeNode): Unit = {
    if (root == null) {}
    else {
      if (balanced) {
        balanced = Math.abs(compute(root.left) - compute(root.right)) <= 1
      }
      fun(root.left)
      fun(root.right)
    }
  }

  def compute(root: TreeNode): Int = {
    if (root == null) { 0 }
    else {
      Math.max(compute(root.left), compute(root.right)) + 1
    }
  }

}