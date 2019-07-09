package com.my.treepath;

import java.util.ArrayList;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-07-07 13:12
 **/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        System.out.println(solution.findPath(root, 22));
    }

    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        if (root == null) {
            return arr;
        }
        ArrayList<Integer> a1 = new ArrayList<>();
        int sum = 0;
        path(root, target, arr, a1, sum);
        return arr;
    }

    private void path(TreeNode root, int target, ArrayList<ArrayList<Integer>> arr,
        ArrayList<Integer> a1, int sum) {
        if (root == null) {
            return;
        }
        sum += root.val;

        if (root.left == null && root.right == null) {
            if (sum == target) {
                a1.add(root.val);
                arr.add(new ArrayList<>(a1));
                a1.remove(a1.size() - 1);

            }
            return;
        }

        a1.add(root.val);
        path(root.left, target, arr, a1, sum);
        path(root.right, target, arr, a1, sum);
        a1.remove(a1.size() - 1);
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
