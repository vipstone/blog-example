package com.example.leetcode;

/**
 * 18. 删除链表的节点
 * https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 */
public class Offer18 {
    class Solution {
        public ListNode deleteNode(ListNode head, int val) {
            // 非空判断
            if (head == null) return null;
            // 判断首个节点
            if (head.val == val) {
                return head.next;
            }
            // 定义辅助指针(原头指针不移动)
            ListNode temp = head;
            while (temp.next != null) {
                // 判断当前节点(是否为待删除节点)
                if (val == temp.next.val) {
                    // 移除节点,直接将当前节点跳过就等于删除当前节点
                    temp.next = temp.next.next;
                    break;
                }
                // 指针移动到下一位
                temp = temp.next;

            }
            return head;
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
