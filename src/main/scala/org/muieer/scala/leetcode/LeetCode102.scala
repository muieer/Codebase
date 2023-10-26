package org.muieer.scala.leetcode

import scala.collection.mutable

object LeetCode102 {

  def levelOrder(root: TreeNode): List[List[Int]] = {

    if (root == null) return List.empty[List[Int]]

    var res = List.empty[List[Int]]
    var queue = mutable.Queue.apply(root)

    while(queue.nonEmpty) {
      var list = List.empty[Int]
      val newQueue = mutable.Queue.empty[TreeNode]
      while (queue.nonEmpty) {
        val node = queue.dequeue
        list = list.appended(node.value)
        if (node.left != null) newQueue.enqueue(node.left)
        if (node.right != null) newQueue.enqueue(node.right)
      }
      queue = newQueue
      res = res.appended(list)
    }

    res
  }


}
