package com.example.leetcode;

/**
 * 19. 删除链表的倒数第N个节点
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class LeetCode19 {
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // 非空判断
            if (head == null) return null;
            // 链表长度
            int size = 1;
            // 辅助节点
            ListNode temp = head;
            // 1.计算链表长度
            while (temp != null && temp.next != null) {
                size++;
                temp = temp.next;
            }
            // 2.计算从前往后要删除的节点下标
            int index = size - n;
            // 3.执行删除操作
            if (index == 0) {
                // 要删除的元素是第一个节点
                return head.next;
            } else {
                // 辅助节点初始化
                temp = head;
                for (int i = 0; i < index; i++) {
                    if (i == index - 1) {
                        // 删除节点
                        temp.next = temp.next.next;
                        break;
                    }
                    temp = temp.next;
                }
                return head;
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
