package com.example.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 1356. 根据数字二进制下 1 的数目排序
 * https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/submissions/
 */
public class LeetCode1356 {
    // 暴力解法
    class Solution {
        public int[] sortByBits(int[] arr) {
            if (arr == null && arr.length == 0) return new int[0];
            // 统计每个数组的二进制 1 的个数
            int[] cs = new int[10001]; // 根据备注信息可知最大值为 10^4
            ArrayList<Integer> list = new ArrayList<>(); // 用于排序
            for (int i : arr) {
                list.add(i);
                // 转换为二进制后包含 1 的个数
                cs[i] = Integer.toBinaryString(i).replaceAll("0", "").length();
            }
            // 排序数字(根据 1 的个数)
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    // 1 的个数不相等
                    if (cs[o1] != cs[o2]) {
                        return cs[o1] - cs[o2];
                    } else {
                        return o1 - o2;
                    }
                }
            });
            // 返回数组
            return list.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
