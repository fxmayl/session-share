/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.SelectionKey;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年10月09日 12:45
 */
public class AioServer {
    private AsynchronousServerSocketChannel assc;

    private AsynchronousChannelGroup acg;

    private ExecutorService execute = Executors.newCachedThreadPool();

    public AioServer(int port) {
        try {
            acg = AsynchronousChannelGroup.withThreadPool(execute);
            assc = AsynchronousServerSocketChannel.open(acg);

            assc.bind(new InetSocketAddress("127.0.0.1", port));
            assc.accept(this, new AioCompletionHandler());

            Thread.sleep(Integer.MAX_VALUE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel getAssc() {
        return assc;
    }

    public static void main(String[] args) {
        new AioServer(8765);
    }
}
