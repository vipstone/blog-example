package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 148. 排序链表
 * https://leetcode-cn.com/problems/sort-list/
 */
public class LeetCode148 {
    class Solution {
        public ListNode sortList(ListNode head) {
            List<Integer> list = new ArrayList<>();
            while (head != null) {
                list.add(head.val);
                head = head.next;
            }
            // list to array
            int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
            // 排序
            Arrays.sort(arr);
            ListNode ans = new ListNode(0);
            ListNode t = ans;
            for (int i : arr) {
                t.next = new ListNode(i);
                t = t.next;
            }
            return ans.next;
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
