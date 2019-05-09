/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my;

import com.my.config.Config;
import com.my.service.UserService;
import com.my.service.impl.UserServiceImpl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月07日 09:37
 */
public class MyTestInno {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = context.getBean(UserServiceImpl.class);
        userService.save(null);
        ((AnnotationConfigApplicationContext)context).close();
    }

    /**
     * 冒泡排序:它重复地走访过要排序的数列，一次比较两个元素，
     * 如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，
     * 也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
     */
    @Test
    public void test1() {
        int array[] = new int[]{3, 44, 38, 2, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        int size = array.length;
        System.out.println(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {// 相邻元素两两对比
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        print(array);

    }

    /**
     * 选择排序:首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     */
    @Test
    public void test2() {
        int array[] = new int[]{3, 44, 38, 2, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        int size = array.length;
        int minIndex, temp;
        for (int i = 0; i < size; i++) {
            minIndex = i;
            for (int j = i; j < size; j++) {
                if (array[j] < array[minIndex]) {// 寻找最小的数
                    minIndex = j;
                }
            }
            //将最小值与当前位置值交换
            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }

        print(array);
    }

    /**
     * 插入排序:通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * ------> 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
     * <p>
     * ------> 从第一个元素开始，该元素可以认为已经被排序；
     * ------> 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * ------> 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * ------> 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * ------> 将新元素插入到该位置后；
     * ------> 重复步骤2~5。
     */
    @Test
    public void test3() {
        int array[] = new int[]{3, 44, 38, 2, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        int size = array.length;
        int preIndex, current;
        for (int i = 1; i < size; i++) {
            //获取前面一个的下标
            preIndex = i - 1;
            current = array[i];
            while (preIndex >= 0 && array[preIndex] > current) {
                //将较大值向后移动
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            //插入当前值到相应位置
            array[preIndex + 1] = current;
        }

        print(array);

    }

    /**
     * 希尔排序:是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
     * ---->  算法：
     * ---->先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
     * ---->选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     * ---->按增量序列个数k，对序列进行k 趟排序；
     * ---->每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，
     * 分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     */
    @Test
    public void test4() {
        int array[] = new int[]{3, 44, 38, 2, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        int size = array.length;

        for (int gap = (int)Math.floor(size / 2); gap > 0; gap = (int)Math.floor(gap / 2)) {
            for (int i = gap; i < size; i++) {
                int j = i;
                int current = array[i];
                while (j - gap >= 0 && current < array[j - gap]) {
                    array[j] = array[j - gap];
                    j = j - gap;
                }
                array[j] = current;
            }
        }
        print(array);
    }

    /**
     * 归并排序:建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）
     * 的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，
     * 再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
     * ------->算法:
     * ------->把长度为n的输入序列分成两个长度为n/2的子序列；
     * ------->对这两个子序列分别采用归并排序；
     * ------->将两个排序好的子序列合并成一个最终的排序序列。
     */
    @Test
    public void test5() {
        int array[] = new int[]{3, 44, 38, 2, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        mergeSort(array);
    }

    private int[] mergeSort(int[] array) {
        int size = array.length;
        if (size < 2) {
            print(array);
        }
        int middle = (int)Math.floor(size / 2);
        int left[] = new int[middle];
        int right[] = new int[size - middle];

        for (int i = 0; i < size; i++) {
            if (i < middle) {
                left[i] = array[i];
            } else {
                right[i - middle] = array[i];
            }
        }
        print(left);
        System.out.println();
        print(right);
        System.out.println();
        print(merge(left, right));
        //        return merge(mergeSort(left), mergeSort(right));
        return array;
    }

    private int[] merge(int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;
        int result[] = new int[left.length + right.length];

        int min = Math.min(left.length, right.length);
        while (leftIndex <= min || rightIndex <= min) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[index] = left[leftIndex];
                leftIndex++;
            } else {
                result[index] = right[rightIndex];
                rightIndex++;
            }
            index++;
        }

        return result;
    }

    private void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
    }
}
