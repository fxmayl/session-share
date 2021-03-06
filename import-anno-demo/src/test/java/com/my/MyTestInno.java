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
        ((AnnotationConfigApplicationContext) context).close();
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

        for (int gap = (int) Math.floor(size / 2); gap > 0; gap = (int) Math.floor(gap / 2)) {
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
        int array[] =
                new int[]{3, 44, 38, 2, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48, 6, 12, 1, 0, 28};
        print(array);
        print(mergeSort(array));

        //        2	2	3	4	5	15	19	26	27	36	38	44	46	47	48	50
    }

    /**
     * 归并排序时对数组进行二分
     *
     * @param array 数组
     * @return
     */
    private int[] mergeSort(int[] array) {
        int size = array.length;
        if (size < 2) {
            return array;
        }
        int middle = (int) Math.floor(size / 2);
        int left[] = new int[middle];
        int right[] = new int[size - middle];

        for (int i = 0; i < size; i++) {
            if (i < middle) {
                left[i] = array[i];
            } else {
                right[i - middle] = array[i];
            }
        }
        System.out.println("left数组:");
        print(left);
        System.out.println("right数组:");
        print(right);
        //        print(merge(left, right));
        //对二分后的数组进行排序、合并
        int[] merge = merge(mergeSort(left), mergeSort(right));
        System.out.println("merge数组:");
        print(merge);
        return merge;
        //        return array;
    }

    /**
     * 归并排序时对二分后的数组进行排序、合并
     *
     * @param left  第一部分数组
     * @param right 第二部分数组
     * @return
     */
    private int[] merge(int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;
        int result[] = new int[left.length + right.length];

        int length = result.length;
        //对两部分数组进行比较大小然后排序，得到新的结果集，但是此时可能存在没有完全遍历完的数组
        while (index < length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[index++] = left[leftIndex];
                leftIndex++;
                if (leftIndex >= left.length) {
                    break;
                }
            } else {
                result[index++] = right[rightIndex];
                rightIndex++;
                if (rightIndex >= right.length) {
                    break;
                }
            }
        }
        //对于没有遍历完的数组，直接将剩余元素添加到结果集末尾，如果leftIndex达到left.length，表明left数组遍历完成
        //则只需要将right数组未遍历完的元素添加到结果集末尾
        if (leftIndex >= left.length && index < length) {
            for (int i = index; i < length; i++) {
                result[i] = right[rightIndex];
                rightIndex++;
            }
            return result;
        }

        //否则如果rightIndex达到right.length，表明right数组遍历完成，则只需要将left数组未遍历完的元素添加到结果集末尾
        if (rightIndex >= right.length && index < length) {
            for (int i = index; i < length; i++) {
                result[i] = left[leftIndex];
                leftIndex++;
            }
            return result;
        }
        return result;

    }

    /**
     * 快速排序:通过一趟排序将待排记录分隔成独立的两部分，
     * 其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
     * --------->算法:
     * --------->从数列中挑出一个元素，称为 “基准”（pivot）；
     * --------->重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面
     * （相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * --------->递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     */
    @Test
    public void test6() {
        //        int array[] = new int[]{3, 44, 38, 2, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        int array[] =
                new int[]{7, 3, 44, 38, 2, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48, 6, 12, 1, 0, 28};

        int size = array.length;
        print(quickSort(array, 0, size));
    }

    /**
     * 快速排序
     *
     * @param array      数组
     * @param leftIndex  最左下标
     * @param rightIndex 最右下标
     * @return
     */
    private int[] quickSort(int[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int partitionIndex = partition(array, leftIndex, rightIndex);
            quickSort(array, leftIndex, partitionIndex);
            quickSort(array, partitionIndex + 1, rightIndex);
        }
        return array;
    }

    /**
     * 获取数组中最小值下标，并与基准交换
     *
     * @param array      数组
     * @param leftIndex  最左下标
     * @param rightIndex 最右下标
     * @return
     */
    private int partition(int[] array, int leftIndex, int rightIndex) {
        int pivot = leftIndex;
        int index = pivot + 1;

        for (int i = index; i < rightIndex; i++) {
            //获取数组中最小值下标，index表示最小值下标
            if (array[i] < array[pivot]) {
                int temp = array[i];
                array[i] = array[index];
                array[index] = temp;
                index++;
            }
        }
        //将最小值与基准做交换
        int temp = array[pivot];
        array[pivot] = array[index - 1];
        array[index - 1] = temp;
        return index - 1;
    }

    /**
     * 堆排序:指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，
     * 并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
     * ------------>算法:
     * ------------>将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
     * ------------>将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；
     * ------------>由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，然后再次将R[1]与无序区最后一个元素交换，
     * 得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。
     */
    @Test
    public void test7() {
        int array[] = new int[]{3, 44, 38, 2, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        int size = array.length;

        //建立大堆顶
        for (int i = (int) Math.floor(size / 2); i >= 0; i--) {
            heapify(array, i, size);
        }
        print(array);

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            size--;
            heapify(array, 0, size);
        }

        print(array);

    }

    /**
     * 堆调整
     *
     * @param array 数组
     * @param i     下标
     */
    private void heapify(int[] array, int i, int size) {
        int lefft = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (lefft < size && array[lefft] > array[largest]) {
            largest = lefft;
        }

        if (right < size && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(array, i, largest);
            heapify(array, largest, size);
        }
    }


    /**
     * 交换两个下标的值
     *
     * @param array 数组
     * @param i     下标i
     * @param j     下标j
     */
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    private void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }
}
