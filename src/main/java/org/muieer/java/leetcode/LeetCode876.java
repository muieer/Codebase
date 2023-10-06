package org.muieer.java.leetcode;

/*
 * 876. 链表的中间结点 https://leetcode.cn/problems/middle-of-the-linked-list/
 * */
public class LeetCode876 {

    public ListNode middleNode(ListNode head) {

        if (head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
