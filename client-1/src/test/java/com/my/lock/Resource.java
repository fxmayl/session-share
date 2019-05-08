/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.lock;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:基于lock实现生产消费者模式<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年04月26日 10:26
 */
@Slf4j
public class Resource {
    private List<String> queue = new LinkedList<>();
    private ReentrantLock lock;
    private Condition producerCondition;
    private Condition consumerCondition;

    public Resource(ReentrantLock lock, Condition producerCondition, Condition consumerCondition) {
        this.lock = lock;
        this.producerCondition = producerCondition;
        this.consumerCondition = consumerCondition;
    }

    /**
     * 生产者添加/生产消息
     *
     * @param msg 消息
     */
    public void add(String msg) {
        lock.lock();
        try {
            if (queue.size() <= 10) {
                queue
                    .add(Thread.currentThread().getName() + "于" + new Date() + "生产一个资源!资源为:" + msg);
                log.info("生产者{}生产一个资源,当前资源池有{}个资源!", Thread.currentThread().getName(),
                    queue.size());
                consumerCondition.signalAll();
            } else {
                producerCondition.await();
                log.info("生产者{}进入等待状态!", Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费信息
     *
     * @return 信息
     */
    public String remove() {
        lock.lock();
        try {
            if (queue.size() > 0) {
                String msg = queue.remove(0);
                log.info("消费者{}消费一个资源,当前资源池有{}个资源!", Thread.currentThread().getName(),
                    queue.size());
                producerCondition.signalAll();
                return msg;
            } else {
                consumerCondition.await();
                log.info("消费者{}进入等待状态!", Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}
