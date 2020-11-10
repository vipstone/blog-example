package com.example.leetcode;

/**
 * 92. 反转链表 II
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 */
public class Offer92 {
    class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            // 非空判断
            if (head == null) return null;

            // 最终链表
            ListNode res = new ListNode(0);
            res.next = head;

            // 反转开始的前一个节点
            ListNode before = null;
            // 反转结束的前一个节点
            ListNode after = null;

            // 辅助节点
            ListNode curr = res;
            // 1.找到 before 节点
            for (int i = 1; i <= m - 1; i++) {
                curr = curr.next;
            }
            before = curr;

            // 2.反转区域节点
            curr = curr.next;     // 指针移动到下一位(反转节点区间)
            ListNode prev = null; // 反转后的链表
            ListNode next = null;
            for (int i = m; i <= n; i++) {
                // 非空验证
                if (curr == null) break;
                // 找到 after 节点
                if (i == n) after = curr.next;
                // 链表反转
                next = curr.next;
                curr.next = prev;
                prev = curr;
                // 指针指向下一位
                curr = next;
            }

            // 3.before 节点和反转区域节点合并
            before.next = prev;

            // 4.移动 before 节点到最后和 after 节点合并
            while (prev.next != null) {
                prev = prev.next;
            }
            prev.next = after;
            return res.next;
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
