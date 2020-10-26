package com.example.leetcode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class LeetCode239 {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();

        queue.offer(9);
        queue.offer(1);
        queue.offer(10);
        queue.offer(7);
        System.out.println(queue.peek());

        queue.remove(1);
        System.out.println(queue.peek());
    }

    // 使用优先队列，执行超时
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || k <= 0) return new int[]{};
            int[] res = new int[nums.length - k + 1];
            PriorityQueue<Integer> queue = new PriorityQueue(res.length, new Comparator<Integer>() {
                @Override
                public int compare(Integer i1, Integer i2) {
                    return i2 - i1;
                }
            });
            int last = nums[0];
            for (int i = 0; i < k; i++) {
                // 添加元素
                queue.offer(nums[i]);
            }
            res[0] = queue.peek();
            for (int i = k; i < nums.length; i++) {
                // 移除上次的元素
                queue.remove(last);
                // 添加本次元素
                queue.offer(nums[i]);
                // 存入最大值
                res[i - k + 1] = queue.peek();
                last = nums[i - k + 1];
            }
            return res;
        }
    }

    // 使用双端队列
    class Solution2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || k <= 0) return new int[0];
            int[] res = new int[nums.length - k + 1];
            // 存储下标
            ArrayDeque<Integer> deque = new ArrayDeque();
            for (int i = 0; i < nums.length; i++) {

                // 移除左边超过滑动窗口的下标
                if (i >= k && (i - k + 1) > deque.peek()) deque.removeFirst();

                // 从最后面开始移除小于 nums[i] 的元素
                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                    deque.removeLast();

                // 下标加入队列
                deque.offer(i);

                // 添加最大值到返回的数组中
                int rindex = i - k + 1;
                if (rindex >= 0) {
                    res[rindex] = nums[deque.peek()];
                }
            }
            return res;
        }
    }


}
