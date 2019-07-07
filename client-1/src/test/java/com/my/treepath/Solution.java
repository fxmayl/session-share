package com.my.treepath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-07-07 13:12
 **/
public class Solution {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        path(root, root.val);

        return result;
    }

    private void path(TreeNode root, int current) {
        if (root == null) {
            return;
        }
        root.val = current;
        if (root.left != null) {
            path(root.left, current + root.left.val);
        }
        if (root.right != null) {
            path(root.right, current + root.right.val);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        solution.findPath(root, 22);
    }
}
