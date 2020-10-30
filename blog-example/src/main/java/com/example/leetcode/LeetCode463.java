package com.example.leetcode;

/**
 * 463. 岛屿的周长
 * https://leetcode-cn.com/problems/island-perimeter/
 */
public class LeetCode463 {
    class Solution {
        public int islandPerimeter(int[][] grid) {
            if (grid == null) return 0;
            int w = grid[0].length; // 二维地图的宽
            int h = grid.length;    // 二维地图的高
            int res = 0;            // 最终周长
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (grid[i][j] == 1) {
                        // 陆地格子
                        res += 4;
                        // 检查左边是否有相连的陆地
                        if (j != 0 && grid[i][j - 1] == 1) {
                            res -= 2;
                        }
                        // 检查正上方是否有相连的陆地
                        if (i != 0 && grid[i - 1][j] == 1) {
                            res -= 2;
                        }
                    }
                }
            }
            return res;
        }
    }
}
