package com.example.leetcode;

/**
 * 328. 奇偶链表
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 */
public class LeetCode328 {
    // 改变链表自身为奇数节点,重新创建偶数节点之后拼接
    class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) return head;
            ListNode ans = head;
            // 奇数节点
            ListNode sNode = ans;
            // 偶数节点
            ListNode dNode = new ListNode(0);
            ListNode dPointer = dNode;
            while (sNode.next != null && sNode.next.next != null) {
                // 存储偶数节点
                dPointer.next = sNode.next;
                dPointer = dPointer.next;
                // 调整奇数节点
                sNode.next = sNode.next.next;
                sNode = sNode.next;
            }
            // 处理最后一位节点
            if (sNode.next != null) {
                dPointer.next = sNode.next;
                dPointer = dPointer.next;
            }
            // 防止成环
            dPointer.next = null;
            // 合并奇、偶节点
            sNode.next = dNode.next;
            return ans;
        }
    }

    // 分治法
    class Solution2 {
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) return head;
            // 奇数节点
            ListNode s = head;
            // 偶数节点
            ListNode d = head.next;
            // 偶数节点的指针节点
            ListNode dt = d;
            while (dt != null && dt.next != null) {
                // 奇数节点赋值
                s.next = dt.next;
                s = s.next;
                // 偶数节点赋值
                dt.next = s.next;
                dt = dt.next;
            }
            // 合并节点
            s.next = d;
            return head;
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
