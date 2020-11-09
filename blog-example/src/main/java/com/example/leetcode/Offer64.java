package com.example.leetcode;

/**
 * 64.求1+2+…+n
 * https://leetcode-cn.com/problems/qiu-12n-lcof/
 */
public class Offer64 {
    // 普通递归解法
    class Solution {
        public int sumNums(int n) {
            return n <= 0 ? 0 : (n + (sumNums(n - 1)));
        }
    }

    // 高斯算法 1+2+…+n=(1+n)*n/2
    class Solution2 {
        public int sumNums(int n) {
            return ((1 + n) * n) / 2;
        }
    }
}
