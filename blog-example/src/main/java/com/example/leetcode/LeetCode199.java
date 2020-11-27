package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 199.二叉树的右视图
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/
 */
public class LeetCode199 {
    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) return null;
            List<Integer> ans = new ArrayList<>();
            ans.add(root.val);
            doRightSideView(ans, root);
            return ans;
        }

        private void doRightSideView(List<Integer> ans, TreeNode root) {
            if (root != null) {
                ans.add(root.val);
                doRightSideView(ans, root.right);
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
