package org.muieer.java.leetcode;

public class LeetCode86 {

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        else {
            var dummyNode = new ListNode(-1);
            dummyNode.next = head;
            var prePartitionNode = dummyNode;
            var curNode = dummyNode;

            while (curNode.next != null && curNode.next.val < x ) {
                curNode = curNode.next;
            }
            if (curNode.next == null) {
                return head;
            }
            // 先找到分区节点的前一个节点
            prePartitionNode = curNode;
            // 当前指向分区节点
            curNode = curNode.next;

            while(curNode != null) {
                while(curNode.next != null && curNode.next.val < x) {
                    var partitionNode = prePartitionNode.next;
                    var nextNextNode = curNode.next.next;
                    prePartitionNode.next = curNode.next;
                    curNode.next.next = partitionNode;
                    prePartitionNode = prePartitionNode.next;
                    curNode.next = nextNextNode;
                }

                curNode = curNode.next;
            }

            return dummyNode.next;
        }
    }
}
