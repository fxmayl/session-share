/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.tree;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

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

        int rootValueIndex = 0;
        List<Integer> leftArray = new ArrayList<>();
        List<Integer> rightArray = new ArrayList<>();
        for (int i = 0; i < in.length; i++) {
            if (in[i] == rootValue) {
                rootValueIndex = i;
                break;
            } else {
                leftArray.add(in[i]);
            }
        }
        for (int i = rootValueIndex + 1; i < in.length; i++) {
            rightArray.add(in[i]);
        }

        return root;
    }

    @Data
    public class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;

        TreeNode(int x) {
            val = x;
        }
    }
}
