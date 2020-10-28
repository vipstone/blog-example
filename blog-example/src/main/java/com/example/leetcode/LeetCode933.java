package com.example.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode933 {
    class RecentCounter {
        Queue<Integer> queue = new LinkedList();

        public RecentCounter() {
        }

        public int ping(int t) {
            queue.offer(t);
            int ot = t - 3000;
            while (queue.peek() < ot)
                queue.poll();
            return queue.size();
        }
    }
}
