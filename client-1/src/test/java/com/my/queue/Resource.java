/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:基于阻塞队列实现生产消费者模式<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年04月26日 09:18
 */
@Slf4j
public class Resource {
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    /**
     * 生产者添加/生产资源
     *
     * @param msg 资源
     */
    public void add(String msg) {
        try {
            queue.put(msg);
            log.info("生产者{}生产一个资源,当前资源池有{}个资源!", Thread.currentThread().getName(), queue.size());
        } catch (InterruptedException e) {
            log.error("添加消息出错!", e);
        }
    }

    /**
     * 消费者消费资源
     *
     * @return 消息
     */
    public String remove() {
        try {
            String msg = queue.take();
            log.info("消费者{}消费一个资源,当前资源池有{}个资源!", Thread.currentThread().getName(), queue.size());
            return msg;
        } catch (InterruptedException e) {
            log.error("消费消息时发生错误!", e);
            return null;
        }
    }
}
