package org.muieer.scala.leetcode

/*
* 876. 链表的中间结点 https://leetcode.cn/problems/middle-of-the-linked-list/
* */
object LeetCode876 {

  def middleNode(head: ListNode): ListNode = {

    if (head.next == null) return head

    var slow = head
    var fast = head

    while(fast != null && fast.next != null) {
      slow = slow.next
      fast = fast.next.next
    }

    slow
  }

}
