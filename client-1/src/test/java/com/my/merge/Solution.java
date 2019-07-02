/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.merge;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年07月02日 13:59
 */
public class Solution {
    public static ListNode merge(ListNode list1, ListNode list2) {
        ListNode head = null;

        ListNode temp = list1;
        ListNode temp2 = list2;

        ListNode l = head;
        while (temp.next != null) {
            int i1 = temp.val;

            while (temp2.next != null) {
                int i2 = temp2.val;
                if (i1 > i2) {
                    l = new ListNode(i1);
                } else {
                    l = new ListNode(i2);
                }
                if (head == null) {
                    head = l;
                } else {
                    head.next = l;
                }
                l = l.next;
                temp2 = temp2.next;
                break;
            }
            temp = temp.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(5);
        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(4);
        list2.next.next = new ListNode(6);

        merge(list1, list2);
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
