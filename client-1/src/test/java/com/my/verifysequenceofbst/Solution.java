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
        for (int i = 0; i < sequence.length; i++) {
            list.add(sequence[i]);
        }

        ArrayList<Integer> temp = new ArrayList<>(list);

        if (list.size() == 1) {
            return true;
        }

        if (list.size() == 2) {
            return list.get(0) < list.get(1) || list.get(0) > list.get(1);
        }

        int index = 0;
        boolean verify = true;
        while (true) {
            if (temp.size() - 1 - index <= 2) {
                if (temp.get(temp.size() - 2) > temp.get(temp.size() - 1)) {
                    if (temp.get(temp.size() - 3) < temp.get(temp.size() - 1)) {
                        verify = true;
                        break;
                    } else {
                        verify = false;
                        break;
                    }
                }
            }
            if (list.get(index) < list.get(index + 1) &&
                list.get(index + 1) > list.get(index + 2)) {
                verify = true;
                index = index + 2;
                list.addAll(temp.subList(index, temp.size()));
            } else if (list.get(index) < list.get(index + 1) &&
                list.get(index + 1) < list.get(index + 2)) {
                verify = true;
                index = index + 1;
                list.addAll(temp.subList(index, temp.size()));
            } else if (list.get(index) > list.get(index + 1) &&
                list.get(index + 1) > list.get(index + 2)) {
                verify = true;
                index = index + 1;
                list.addAll(temp.subList(index, temp.size()));
            } else if (list.get(index) > list.get(index + 1) &&
                list.get(index + 1) < list.get(index + 2)) {
                verify = true;
                index = index + 1;
                list.addAll(temp.subList(index, temp.size()));
            } else {
                verify = false;
            }

            if (!verify) {
                break;
            }
        }

        return verify;
    }
}
