/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.tree;

import lombok.Data;

/**
 * Description:TODO<BR>
 * TODO  待完善
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年07月12日 11:31
 */
public class TreeClone {
    public static void main(String[] args) {
        RandomListNode root = new RandomListNode(1);
        root.next = new RandomListNode(2);
        root.random = new RandomListNode(3);
        root.next.next = new RandomListNode(4);
        root.next.random = new RandomListNode(5);
        root.next.next.next = new RandomListNode(3);
        root.next.next.random = new RandomListNode(5);

        root.next.next.next.random = new RandomListNode(2);

        TreeClone treeClone = new TreeClone();
        System.out.println(treeClone.clone(root));
    }

    public RandomListNode clone(RandomListNode pHead) {
        RandomListNode root = new RandomListNode(pHead.label);
        if (pHead.random != null) {
            root.random = new RandomListNode(pHead.random.label);
        }
        RandomListNode temp = pHead.next;
        while (temp != null) {
            RandomListNode tempRoot = root;
            while (true) {
                if (tempRoot == null) {
                    tempRoot = new RandomListNode(temp.label);
                    if (temp.random != null) {
                        tempRoot.random = new RandomListNode(temp.random.label);
                    }
                    break;
                } else {
                    tempRoot = tempRoot.next;
                }
            }

            temp = temp.next;
        }
        return root;
    }

    @Data
    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
}
