package org.muieer.java.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode138 {

    public Node copyRandomList(Node head) {

        Node res = new Node(-1);
        Node cur = res;
        Node dummyHead = head;

        Map<Node, Integer> map1 = new HashMap<>();
        Map<Integer, Node> map2 = new HashMap<>();
        int index = 0;

        while(dummyHead != null) {
            map1.put(dummyHead, index);
            cur.next = new Node(dummyHead.val);
            map2.put(index, cur.next);
            cur = cur.next;
            dummyHead = dummyHead.next;
            index++;
        }

        dummyHead = head;
        cur = res.next;
        while(dummyHead != null) {
            Integer value = map1.getOrDefault(dummyHead.random, -1);
            if (value != -1) {
                cur.random = map2.get(value);
            }
            cur = cur.next;
            dummyHead = dummyHead.next;
        }

        return res.next;
    }
}
