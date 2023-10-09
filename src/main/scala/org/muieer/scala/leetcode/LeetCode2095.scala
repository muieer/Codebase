package org.muieer.scala.leetcode

object LeetCode2095 {

  def deleteMiddle(head: ListNode): ListNode = {
    val dummy = new ListNode()
    dummy.next = head
    var fast = dummy
    var slow = dummy

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next
      fast = fast.next.next
    }
    // 找到中间节点的前一个节点
    slow.next = slow.next.next

    dummy.next
  }
}
