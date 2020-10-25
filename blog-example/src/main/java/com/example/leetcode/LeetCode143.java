package com.example.leetcode;

/**
 * 重排链表
 * https://leetcode-cn.com/problems/reorder-list/
 */
public class LeetCode143 {
    class Solution {
        public void reorderList(ListNode head) {
            if (head == null) return;

            ListNode fast = head;
            ListNode slow = head;

            // 拿到中值的上一个元素
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            ListNode l1 = head; // 链表前半段
            ListNode l2 = slow.next;  // 链表后半段
            slow.next = null; // 截取链表的前半段（更改中值之后为 null）

            // 反转链表
            ListNode prev = null;
            ListNode curr = l2;
            while (curr != null) {
                ListNode next = curr.next; // 下一个节点
                curr.next = prev; // 把 next 设置为前一个节点
                prev = curr;
                // 指针移动下一位
                curr = next;
            }

            l2 = prev;

            // 合并链表
            ListNode l1Temp, l2Temp;
            while (l1 != null && l2 != null) {
                l1Temp = l1.next;
                l2Temp = l2.next;

                l1.next = l2;
                l1 = l1Temp;

                l2.next = l1;
                l2 = l2Temp;
            }
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
