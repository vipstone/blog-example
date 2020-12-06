package com.example.leetcode;

/**
 * 204. 计数质数
 * https://leetcode-cn.com/problems/count-primes/
 */
public class LeetCode204 {
    // 任何质数的 n 倍,n>=2 都不是一个质数
    class Solution {
        public int countPrimes(int n) {
            int ans = 0;
            // 非质数数组
            boolean[] arr = new boolean[n];
            for (int i = 2; i < n; i++) {
                if (arr[i]) {
                    // 非素数
                    continue;
                }
                ans++;
                // 将质数的 n 倍都存储到非质数数组
                for (int j = i; j < n; j += i) {
                    arr[j] = true;
                }
            }
            return ans;
        }
    }
}
