package com.example.leetcode;

/**
 * 860. 柠檬水找零
 * https://leetcode-cn.com/problems/lemonade-change/
 */
public class LeetCode860 {
    // 方法一：不管钱够不够，先扣了再判断
    class Solution {
        public boolean lemonadeChange(int[] bills) {
            int[] w = {0, 0}; // 钱包零钱的个数，w[0]=10 美元个数，w[1]=5 美元个数
            for (int i = 0; i < bills.length; i++) {
                if (bills[i] == 20) {
                    if (w[1] <= 0) {
                        // 没有 10 美元，直接减 5 美金个数
                        w[0] = w[0] - 3;
                    } else {
                        // 有 10 美金，一起减
                        w[0] = w[0] - 1;
                        w[1] = w[1] - 1;
                    }
                } else if (bills[i] == 10) {
                    // 找零 5 美金
                    w[0] = w[0] - 1;
                    // 10 美金个数+1
                    w[1] = w[1] + 1;
                } else {
                    w[0] = w[0] + 1;
                }

                // 判断是否能够找零
                if (w[0] < 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // 方法二：推荐解法，有零钱再找零，否则就返回 false
    class Solution2 {
        public boolean lemonadeChange(int[] bills) {
            int five = 0, ten = 0;
            for (int bill : bills) {
                if (bill == 5)
                    five++;
                else if (bill == 10) {
                    if (five == 0) return false;
                    five--;
                    ten++;
                } else {
                    if (five > 0 && ten > 0) {
                        five--;
                        ten--;
                    } else if (five >= 3) {
                        five -= 3;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }


}
