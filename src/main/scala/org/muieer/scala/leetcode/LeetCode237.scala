package org.muieer.scala.leetcode

/*
* 237. 删除链表中的节点，中等难度，
* 思路：不被删除的元素向左移动，移除最后一个节点
* */
object LeetCode237 {

  def deleteNode(node: ListNode): Unit = {
    var dummy = node
    while (dummy.next.next != null) {
      dummy.x = dummy.next.x
      dummy = dummy.next
    }
    // 当前节点是倒数第二节点
    dummy.x = dummy.next.x
    dummy.next = null
  }

}
