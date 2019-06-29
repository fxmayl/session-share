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
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || pre.length <= 0) {
            return new TreeNode(0);
        }
        if (in == null || in.length <= 0) {
            return new TreeNode(0);
        }

        if (pre.length != in.length) {
            return new TreeNode(0);
        }

        List<Integer> preList = new ArrayList<>();
        List<Integer> inList = new ArrayList<>();

        for (int i = 0; i < in.length; i++) {
            preList.add(pre[i]);
            inList.add(in[i]);
        }

        int rootValue = preList.get(0);
        TreeNode root = new TreeNode(rootValue);

        List<Integer> tempInList = new ArrayList<>(inList);
        List<Integer> tempPreList = new ArrayList<>(preList);

        leftRight(root, inList, preList, rootValue);

        inList = new ArrayList<>(tempInList);
        preList = new ArrayList<>(tempPreList);
        rootValue = preList.get(0);

        rightLeft(root, inList, preList, rootValue);

        return root;
    }

    private static void leftRight(TreeNode root, List<Integer> inList, List<Integer> preList,
        int rootValue) {
        int rootLeftValue = rootValue;
        int rootRightValue = rootValue;
        do {
            TreeNode temp = root;

            List<Object> left = left(temp, inList, preList, rootLeftValue);
            List<Integer> inLeft = (List<Integer>)left.get(0);
            List<Integer> preLeft = (List<Integer>)left.get(1);
            rootLeftValue = (Integer)left.get(2);
            rootRightValue = rootLeftValue;

            right(temp, inLeft, preLeft, rootRightValue);

            root = temp;
            inList = new ArrayList<>(inLeft);
            preList = new ArrayList<>(preLeft);
        } while (inList.size() > 1);
    }

    private static void rightLeft(TreeNode root, List<Integer> inList, List<Integer> preList,
        int rootValue) {
        int rootRightValue = rootValue;
        int rootLeftValue = rootValue;
        do {
            TreeNode temp = root;

            List<Object> right = right(temp, inList, preList, rootRightValue);
            List<Integer> inRight = (List<Integer>)right.get(0);
            List<Integer> preRight = (List<Integer>)right.get(1);
            rootRightValue = (Integer)right.get(2);
            rootLeftValue = rootRightValue;

            left(temp, inRight, preRight, rootLeftValue);

            root = temp;
            inList = new ArrayList<>(inRight);
            preList = new ArrayList<>(preRight);
        } while (inList.size() > 1);
    }

    private static List<Object> left(TreeNode temp, List<Integer> inList, List<Integer> preList,
        int rootValue) {
        List<Object> list = new ArrayList<>();
        int rootIndex = -1;
        List<Integer> inLeftArray = new ArrayList<>();
        for (int i = 0; i < inList.size(); i++) {
            if (inList.get(i) == rootValue) {
                rootIndex = i;
                break;
            } else {
                inLeftArray.add(inList.get(i));
            }
        }
        List<Integer> preLeftArray = new ArrayList<>();
        if (rootIndex > 0) {
            for (int i = 0; i < preList.size(); i++) {
                if (inLeftArray.contains(preList.get(i))) {
                    preLeftArray.add(preList.get(i));
                }
            }
        }

        if (preLeftArray.size() > 0) {
            TreeNode l = temp;
            while (true) {
                if (l.left == null) {
                    l.left = new TreeNode(preLeftArray.get(0));
                    rootValue = preLeftArray.get(0);
                    break;
                } else {
                    l = l.left;
                }
            }
        }
        list.add(inLeftArray);
        list.add(preLeftArray);
        list.add(rootValue);
        return list;
    }

    private static List<Object> right(TreeNode temp, List<Integer> inList, List<Integer> preList,
        int rootValue) {
        List<Object> list = new ArrayList<>();
        int rootValueIndex = 0;
        for (int i = 0; i < inList.size(); i++) {
            if (inList.get(i) == rootValue) {
                rootValueIndex = i;
                break;
            }
        }
        List<Integer> inRightArray = new ArrayList<>();
        for (int i = rootValueIndex + 1; i < inList.size(); i++) {
            inRightArray.add(inList.get(i));
        }
        List<Integer> preRightArray = new ArrayList<>();
        for (int i = 0; i < preList.size(); i++) {
            if (inRightArray.contains(preList.get(i))) {
                preRightArray.add(preList.get(i));
            }
        }

        if (preRightArray.size() > 0) {
            TreeNode l = temp;
            while (true) {
                if (l.right == null) {
                    l.right = new TreeNode(preRightArray.get(0));
                    rootValue = preRightArray.get(0);
                    break;
                } else {
                    l = l.right;
                }
            }
        }
        list.add(inRightArray);
        list.add(preRightArray);
        list.add(rootValue);
        return list;
    }

    public static void main(String[] args) {
        System.out.println(
            reConstructBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{3, 2, 4, 1, 6, 5, 7}));
    }

    @Data
    public static class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;

        TreeNode(int x) {
            val = x;
        }
    }
}
