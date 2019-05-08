/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年04月26日 11:09
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        ReentrantLock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();

        Resource resource = new Resource(lock, producerCondition, consumerCondition);
        Producer producer = new Producer(resource);

        service.submit(producer);
        for (int i = 0; i < 3; i++) {
            Consumer consumer = new Consumer(resource);
            service.submit(consumer);
        }

        service.shutdown();
        while (!service.awaitTermination(1, TimeUnit.DAYS)) {

        }

    }
}
