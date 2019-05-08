/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年04月28日 10:47
 */
public class FastFailTest {
    private static List<String> list = new ArrayList<>();
//    private static List<String> list = new CopyOnWriteArrayList<>();

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Thread threadOne = new Thread(() -> {
            int i = 0;
            while (i < 6) {
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        });

        Thread threadTwo = new Thread(() -> {
            int i = 10;
            while (i < 16) {
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        });
        executorService.execute(threadOne);
        executorService.execute(threadTwo);

        executorService.shutdown();
        while (!executorService.awaitTermination(1, TimeUnit.DAYS)) {
        }
    }

    @Test
    public void test1() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Thread threadOne = new Thread(() -> {
            int i = 0;
            while (i < 6) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        });

        Thread threadThree = new Thread(() -> {
            int i = 4;
            while (i < 8) {
                String remove = list.remove(i);
                System.out.println(remove);
                printAll();
                i++;
            }
        });

        Thread threadTwo = new Thread(() -> {
            int i = 10;
            while (i < 16) {
                list.add(String.valueOf(i));
                printAll();
                i++;

            }
        });

        executorService.execute(threadThree);
        executorService.execute(threadOne);
        executorService.execute(threadTwo);

        executorService.shutdown();
        while (!executorService.awaitTermination(1, TimeUnit.DAYS)) {
        }
    }

    @Test
    public void test2() {
        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list);
        int i = 2;
        while (i < 7) {
            list.remove(i);
            i++;
        }
        System.out.println(list);
    }

    private void printAll() {
        System.out.println("");

        String value = null;
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            value = iterator.next();
            System.out.print(value + ", ");
        }
    }

}
