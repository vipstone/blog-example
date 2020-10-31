package com.example.leetcode;

import java.util.HashMap;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 */
public class Offer10_I {
    class Solution {
        // 将之前的结果进行缓存
        HashMap<Integer, Integer> map = new HashMap<>();

        public int fib(int n) {
            if (n < 2) return n;
            if (map.containsKey(n)) return map.get(n);
            int f = fib(n - 1) % 1000000007;
            map.put(n - 1, f);
            int s = fib(n - 2) % 1000000007;
            map.put(n - 2, s);
            return (f + s) % 1000000007;
        }
    }
}
