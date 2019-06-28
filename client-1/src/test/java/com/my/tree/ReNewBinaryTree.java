/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.tree;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年06月28日 17:01
 */
public class ReNewBinaryTree {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || pre.length <= 0) {
            return new TreeNode(0);
        }
        if (in == null || in.length <= 0) {
            return new TreeNode(0);
        }

        if (pre.length != in.length) {
            return new TreeNode(0);
        }

        int rootValue = pre[0];
        TreeNode root = new TreeNode(rootValue);

        for (int k = 1; k < pre.length; k++) {
            int rootValueIndex = 0;
            for (int i = 0; i < in.length; i++) {
                if (in[i] == rootValue) {
                    rootValueIndex = i;
                    break;
                }
            }
        }
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;

        TreeNode(int x) {
            val = x;
        }
    }
}
