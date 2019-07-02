package com.my.listnode;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-07-01 22:40
 **/
public class Solution {
    public static ListNode reverseList(ListNode head) {
        LinkedHashMap<Integer, ListNode> nodesMap = new LinkedHashMap<>();
        ListNode temp = head;
        int counter = 1;
        nodesMap.put(counter, new ListNode(temp.val));
        while (temp.next != null) {
            nodesMap.put(++counter, new ListNode(temp.next.val));
            temp = temp.next;
        }

        ArrayList<ListNode> list = new ArrayList<>(nodesMap.values());
        head = list.get(list.size() - 1);
        ListNode l = head;
        for (int i = list.size() - 2; i >= 0; i--) {
            l.next = list.get(i);
            l = l.next;
        }

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode temp = head;

        for (int i = 2; i < 6; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }

        reverseList(head);
    }
}
