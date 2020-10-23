package com.example.leetcode;

import java.util.Stack;

/**
 * 回文链表判断
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class LeetCode234 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(0);
        ListNode l3 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;

    }

    // 使用快、慢链表实现
    class Solution {
        public boolean isPalindrome(ListNode head) {
            // 创建快、慢指针
            ListNode fast = head, slow = head;
            while (fast != null) {
                // 快指针走到头了
                if (fast.next == null) {
                    break;
                }
                // 快指针走两步
                fast = fast.next.next;
                // 慢指针走一步
                slow = slow.next;
            }
            // 反转链表后半段
            ListNode prev = null; // 反转的链表
            while (slow != null) {
                ListNode next = slow.next;
                // 反转节点
                slow.next = prev; // 当前节点的 next 指向前驱节点
                prev = slow; // 前驱节点指向当前节点
                // 设置循环下一次的指针
                slow = next;
            }
            // 循环比对
            while (prev != null) {
                if (prev.val != head.val) return false;
                prev = prev.next;
                head = head.next;
            }
            return false;
        }
    }

    // 使用栈进行回文判断，性能比较低
    class Solution2 {
        public boolean isPalindrome(ListNode head) {
            if (head == null) return true;
            // 链表元素存入栈
            Stack<Integer> stack = new Stack();
            ListNode node = head;
            while (node != null) {
                stack.push(node.val);
                node = node.next;
            }
            // 将反转的栈和链表对比
            while (head != null) {
                if (head.val != stack.pop()) return false;
                head = head.next;
            }
            return true;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
