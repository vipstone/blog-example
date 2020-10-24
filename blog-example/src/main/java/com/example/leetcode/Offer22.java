package com.example.leetcode;

/**
 * 链表中倒数第k个节点
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 */
public class Offer22 {
    class Solution {
        public ListNode getKthFromEnd(ListNode head, int k) {
            if (head == null || k <= 0) return null;
            // 声明快慢指针
            ListNode fast = head, slow = head;
            for (int i = 0; i < k; i++) {
                // 让快指针先移动 k 个位置
                fast = fast.next;
            }
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
