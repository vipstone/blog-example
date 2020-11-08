package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 346. 数据流中的移动平均值
 * https://leetcode-cn.com/problems/moving-average-from-data-stream/
 */
public class LeetCode346 {
    class MovingAverage {

        List<Integer> list = new ArrayList();
        int max = 0;

        public MovingAverage(int size) {
            this.max = size;
        }

        public double next(int val) {
            if (list.size() >= this.max) {
                // 超出最大值移除一个
                list.remove(0);
            }
            list.add(val);
            double res = 0d;
            for (int n : list) {
                res += n;
            }
            res /= list.size();
            return res;
        }

    }
}
