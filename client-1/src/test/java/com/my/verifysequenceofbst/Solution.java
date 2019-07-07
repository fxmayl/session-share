/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.verifysequenceofbst;

import java.util.ArrayList;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年07月05日 14:57
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.verifySequenceOfBST(new int[]{7, 4, 6, 5}));
    }

    public boolean verifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length <= 0) {
            return false;
        }
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> greatList = new ArrayList<>();
        ArrayList<Integer> lessList = new ArrayList<>();

        for (int i = sequence.length - 1; i >= 0; i--) {
            list.add(sequence[i]);
        }

        if (list.size() == 1 || list.size() == 2) {
            return true;
        }

        int root = list.get(0);

        return verify(list, root, greatList, lessList);
    }

    public boolean verify(ArrayList<Integer> list, int root, ArrayList<Integer> greatList, ArrayList<Integer> lessList) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > root) {
                if (greatList.size() != (i - 1)) {
                    return false;
                }
                greatList.add(list.get(i));
            } else {
                if (lessList.size() != i - greatList.size() - 1) {
                    return false;
                }
                lessList.add(list.get(i));
            }
        }
        if (greatList != null && greatList.size() > 0) {
            return verify(greatList, greatList.get(0), new ArrayList<>(), new ArrayList<>());
        }
        if (lessList != null && lessList.size() > 0) {
            return verify(lessList, lessList.get(0), new ArrayList<>(), new ArrayList<>());
        }

        return true;
    }
}
