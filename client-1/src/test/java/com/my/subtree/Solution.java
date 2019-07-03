/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.subtree;

import java.util.ArrayList;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年07月03日 10:40
 */
public class Solution {
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null || root1 == null) {
            return false;
        }
        ArrayList<Integer> list1 = new ArrayList<>();
        reverse(root1, list1);

        ArrayList<Integer> list2 = new ArrayList<>();
        reverse(root2, list2);

        boolean con = list1.contains(list2.get(0));
        if (!con) {
            return false;
        }
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).equals(list2.get(0))) {
                indexList.add(i);
            }
        }

        for (int i = 0; i < indexList.size(); i++) {
            boolean contain = true;
            for (int j = 0; j < list2.size(); j++) {
                if (list1.get(indexList.get(i) + j).equals(list2.get(j))) {
                    continue;
                } else {
                    contain = false;
                    break;
                }
            }
            if (contain) {
                return contain;
            }
        }

        return false;
    }

    private void reverse(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        reverse(root.left, list);
        reverse(root.right, list);
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(8);
        node.left = new TreeNode(9);
        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(7);
        node.right = new TreeNode(2);
        node.right.right = new TreeNode(10);
        node.right.left = new TreeNode(5);

        Solution solution = new Solution();
        ArrayList<Integer> list = new ArrayList<>();
        solution.reverse(node, list);
        System.out.println(list);
    }
}
