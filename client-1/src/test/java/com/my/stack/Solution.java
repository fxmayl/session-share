/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.stack;

import java.util.ArrayList;

/**
 * Description:输入两个整数序列，第一个序列表示栈的压入顺序，<br/>
 * 请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。<br/>
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，<br/>
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年07月03日 17:23
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean popOrder = solution.isPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
        System.out.println(popOrder);
        boolean popOrder1 = solution.isPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2});
        System.out.println(popOrder1);
    }

    public boolean isPopOrder(int[] pushA, int[] popA) {
        ArrayList<Integer> popList = new ArrayList<>();
        for (int i = 0; i < popA.length; i++) {
            popList.add(popA[i]);
        }

        ArrayList<Integer> pushList = new ArrayList<>();
        for (int i = 0; i < pushA.length; i++) {
            pushList.add(pushA[i]);
            while (true) {
                if (popList.size() <= 0 || pushList.size() <= 0) {
                    break;
                }

                if (popList.get(0).equals(pushList.get(pushList.size() - 1))) {
                    pushList.remove(pushList.size() - 1);
                    popList.remove(0);
                } else {
                    break;
                }
            }
        }

        if (pushList.size() <= 0) {
            return true;
        } else {
            return false;
        }

    }
}
