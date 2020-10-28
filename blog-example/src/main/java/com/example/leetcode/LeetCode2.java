package com.example.leetcode;

/**
 * 两数相加/链表求和
 * https://leetcode-cn.com/problems/add-two-numbers/
 * https://leetcode-cn.com/problems/sum-lists-lcci/
 */
public class LeetCode2 {

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = new ListNode(-1);
            ListNode temp = result;
            int carry = 0; // 进位
            while (l1 != null || l2 != null) {
                int c1 = l1 == null ? 0 : l1.val;
                int c2 = l2 == null ? 0 : l2.val;
                int sum = c1 + c2 + carry;
                carry = sum / 10; // 获取下次进位
                sum = sum % 10; // 获取本位值（小于10）
                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
                temp.next = new ListNode(sum);
                temp = temp.next;
            }
            if (carry > 0) {
                temp.next = new ListNode(carry);
            }
            return result.next == null ? null : result.next;
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
