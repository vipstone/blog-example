package com.example.leetcode;

/**
 * 查询环形链表的入环元素
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class LeetCode142 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null) return null;
            ListNode fast = head; // 快指针（每次移动两步）
            ListNode slow = head; // 慢指针（每次移动一步）
            while (fast != null) { // 快指针的下一个不为空
                if (null != fast.next) {
                    fast = fast.next.next; // 移动两步
                } else {
                    return null;
                }
                slow = slow.next;
                // 第一次相遇
                if (fast == slow) {
                    ListNode newHead = head; // 从新开启一个指针，每次一步
                    while (slow != newHead) {
                        newHead = newHead.next;
                        slow = slow.next;
                    }
                    return newHead;
                }
            }
            return null;
        }
    }

}
