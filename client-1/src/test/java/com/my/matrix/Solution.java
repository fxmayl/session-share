/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.matrix;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年07月03日 11:15
 */
public class Solution {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrix1 = new int[][]{{1, 2, 3, 4, 5, 6, 7, 8}, {9, 10, 11, 12, 13, 14, 15, 16}};
        Solution solution = new Solution();
        System.out.println(solution.printMatrix(matrix));
        System.out.println(solution.printMatrix(matrix1));
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null) {
            return new ArrayList<>();
        }

        LinkedHashMap<Integer, ArrayList<Integer>> map = new LinkedHashMap<>(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < matrix[i].length; j++) {
                list.add(matrix[i][j]);
            }
            map.put(i, list);
        }
        ArrayList<Integer> result = new ArrayList<>();

        int index = 0;
        int startIndex = 0;
        int endIndex = map.size() - 1;
        while (true) {
            //获取第一行
            if (index == startIndex) {
                ArrayList<Integer> list = map.get(index);
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                result.addAll(list);
                map.remove(index);
                startIndex = index + 1;
            }
            //获取中间行最后一个元素以及最后一行
            for (int i = index + 1; i <= endIndex; i++) {
                ArrayList<Integer> list = map.get(i);
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                if (i == endIndex) {
                    for (int j = list.size() - 1; j >= 0; j--) {
                        result.add(list.get(j));
                    }
                    map.remove(endIndex);
                    endIndex = endIndex - 1;
                    break;
                }
                result.add(list.get(list.size() - 1));
                list.remove(list.size() - 1);
            }

            //获取中间行第一个元素
            for (int i = endIndex; i >= startIndex; i--) {
                ArrayList<Integer> list = map.get(i);
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                result.add(list.get(0));
                list.remove(0);
            }

            index = index + 1;

        }

        return result;
    }
}
