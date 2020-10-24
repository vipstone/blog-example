package com.example.leetcode;

/**
 * 找到链表的中间结点
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class LeetCode876 {
    class Solution {
        public ListNode middleNode(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
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
