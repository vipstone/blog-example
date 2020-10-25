package com.example.leetcode;

import java.util.Stack;

/**
 * 剑指 Offer 24. 反转链表
 * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 */
public class Offer24 {

    /**
     * 链表节点
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null) return null;
            // 最终排序的倒序链表
            ListNode prev = null;
            while (head != null) {
                // 存储下一个指针
                ListNode next = head.next;
                // 反转节点
                head.next = prev;
                // 存储反转的节点
                prev = head;
                // 移动到下一个指针
                head = next;
            }
            return prev;
        }
    }

    /**
     * 反转链表（借助栈）
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        Stack<ListNode> stack = new Stack<>();
        stack.push(head); // 存入第一个节点
        while (head.next != null) {
            stack.push(head.next); // 存入其他节点
            head = head.next;
        }
        // 反转链表
        ListNode listNode = stack.pop(); // 反转第一个元素
        ListNode lastNode = listNode; // 临时节点，在下面的 while 中记录上一个节点
        while (!stack.isEmpty()) {
            ListNode item = stack.pop(); // 当前节点
            lastNode.next = item;
            lastNode = item;
        }
        lastNode.next = null; // 最后一个节点赋为null（不然会造成死循环）
        return listNode;
    }


    /**
     * 测试代码
     * @param args
     */
    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode list3 = new ListNode(3);
        ListNode list4 = new ListNode(4);
        list.next = list2;
        list2.next = list3;
        list3.next = list4;
        // 打印信息
        ListNode newList = reverseList(list);
        while (newList.next != null) {
            System.out.println(newList.val);
            newList = newList.next;
        }
        System.out.println(newList.val);
    }

}
