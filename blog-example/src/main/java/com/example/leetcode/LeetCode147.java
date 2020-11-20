package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 147. 对链表进行插入排序
 * https://leetcode-cn.com/problems/insertion-sort-list/
 */
public class LeetCode147 {
    class Solution {
        public ListNode insertionSortList(ListNode head) {
            List<Integer> list = new ArrayList<>();
            while (head != null && head.next != null) {
                list.add(head.val);
                head = head.next;
            }
            int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
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

        ListNode(int x) {
            val = x;
        }
    }
}
