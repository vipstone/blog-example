package com.example.leetcode;

/**
 * 找到两个单链表相交的起始节点
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class LeetCode160 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
//        l2.next = l3;

        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l4.next = l5;
        l5.next = l6;

        Solution.getIntersectionNode(l1, l4);

    }

    static class Solution {
        public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) return null;
            ListNode pA = headA, pB = headB;
            // 不相交就是 null==null 跳出循环
            while (pA != pB) {
                pA = pA == null ? headB : pA.next;
                pB = pB == null ? headA : pB.next;
            }
            return pA;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
