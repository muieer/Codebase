package org.muieer.scala.leetcode

object LeetCode160 {

  //找相等可以用哈希表
  def getIntersectionNode(headA: ListNode, headB: ListNode): ListNode = {

    var dummyA = headA
    var dummyB = headB

    while(dummyA != dummyB) {
      if (dummyA == null) {
        dummyA = headB
      } else {
        dummyA = dummyA.next
      }
      if (dummyB == null) {
        dummyB = headA
      } else {
        dummyB = dummyB.next
      }
    }

    dummyA
  }
}
