package com.example.leetcode;

/**
 * 24. 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class LeetCode24 {
    class Solution {
        public ListNode swapPairs(ListNode head) {
            // 边界情况处理
            if (head == null || head.next == null) return head;
            ListNode res = new ListNode(0);
            res.next = head;
            ListNode temp = res;
            while (temp.next != null && temp.next.next != null) {
                // 交换的节点 1
                ListNode n1 = temp.next;
                // 交换的节点 2
                ListNode n2 = temp.next.next;
                // 将交接 2 交换到前面
                temp.next = n2;
                // 将节点 1 和节点 2 之后的所有节点存入放入节点 1
                n1.next = n2.next;
                // 完成节点交换
                n2.next = n1;
                // 指针移动到下一位
                temp = n1;
            }
            return res.next;
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
