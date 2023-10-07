package org.muieer.scala.leetcode

/*
* 21. 合并两个有序链表：https://leetcode.cn/problems/merge-two-sorted-lists/
* */
object LeetCode21 {

  def mergeTwoLists(list1: ListNode, list2: ListNode): ListNode = {

    var dummyNode = new ListNode()
    var _list1 = list1
    var _list2 = list2
    val res = dummyNode

    while (_list1 != null && _list2 != null) {
      if (_list1.x <= _list2.x) {
        dummyNode.next = _list1
        _list1 = _list1.next
      } else {
        dummyNode.next = _list2
        _list2 = _list2.next
      }
      dummyNode = dummyNode.next
    }

    if (_list1 != null) {
      dummyNode.next = _list1
    }
    if (_list2 != null) {
      dummyNode.next = _list2
    }

    res.next
  }

}
