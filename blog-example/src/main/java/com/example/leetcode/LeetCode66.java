package com.example.leetcode;

import java.util.Arrays;

/**
 * 66. 加一
 * https://leetcode-cn.com/problems/plus-one/
 */
public class LeetCode66 {
    public static void main(String[] args) {
        int[] digits = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    }

    class Solution {
        public int[] plusOne(int[] digits) {
            if (digits == null || digits.length == 0) return new int[0];
            int carry = 0; // 进位
            int[] res = new int[digits.length + 1];
            for (int i = digits.length - 1; i >= 0; i--) {
                int sum = digits[i] + carry;
                if (i == digits.length - 1)
                    sum += 1;
                carry = sum / 10;
                res[i + 1] = sum % 10;
            }
            res[0] = carry;
            if (carry == 0) {
                return Arrays.copyOfRange(res, 1, res.length);
            } else {
                return res;
            }
        }
    }

}
