package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1431. 拥有最多糖果的孩子
 * https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/
 */
public class LeetCode1431 {

    // 方法一:通过两个循环查询结果
    class Solution {
        public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
            // 1.循环找到最大值
            int max = 0;
            for (int i = 0; i < candies.length; i++) {
                max = Math.max(candies[i], max);
            }
            // 2.对比是否为最大值
            List<Boolean> list = new ArrayList<>();
            for (int i = 0; i < candies.length; i++) {
                list.add((extraCandies + candies[i]) >= max);
            }
            return list;
        }
    }

    // 方法二:使用 Arrays.sort 找到最大值
    class Solution2 {
        public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
            // 1.通过排序找到最大值
            int[] t = candies.clone();
            Arrays.sort(t);
            int max = t[t.length - 1];
            // 2.对比是否为最大值
            List<Boolean> list = new ArrayList<>();
            for (int i = 0; i < candies.length; i++) {
                list.add((extraCandies + candies[i]) >= max);
            }
            return list;
        }
    }

}
