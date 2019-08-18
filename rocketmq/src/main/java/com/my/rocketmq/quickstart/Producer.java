/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.rocketmq.quickstart;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年08月18日 13:25
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("quickstart_producer");

        producer.setNamesrvAddr("192.168.189.2:9876;192.168.189.3:9876;");

        producer.start();

        for (int i = 0; i < 10; i++) {
            try {
                Message message = new Message("TopicQuickStart", "TagB", "kkk",
                    ("Hello RocketMQ " + i).getBytes());
                SendResult sendResult = producer.send(message);
                System.out.println(sendResult);
            } catch (InterruptedException | RemotingException | MQBrokerException e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        producer.shutdown();
    }
}
