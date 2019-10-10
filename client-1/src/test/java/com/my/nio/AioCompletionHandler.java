/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.nio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年10月09日 13:15
 */
public class AioCompletionHandler
    implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
    @Override
    public void completed(AsynchronousSocketChannel result, AioServer attachment) {
        attachment.getAssc().accept(attachment, this);
        read(result);
    }

    private void read(AsynchronousSocketChannel asc) {
        ByteBuffer readBuf = ByteBuffer.allocate(1024);
        asc.read(readBuf, readBuf, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                byte[] bytes = new byte[attachment.remaining()];
                attachment.get(bytes);

                String body = new String(bytes);
                System.out.println("服务器收到消息:" + body);


                write(asc, "确认收到消息【" + body + "】");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println(exc.getCause());
            }
        });
    }

    private void write(AsynchronousSocketChannel asc, String response) {
        byte[] bytes = response.getBytes();
        ByteBuffer writeBuf = ByteBuffer.allocate(bytes.length);

        writeBuf.put(bytes);

        writeBuf.flip();

        asc.write(writeBuf, writeBuf, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (writeBuf.hasRemaining()) {
                    asc.write(writeBuf, writeBuf, this);
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println(exc.getCause());
            }
        });
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        System.out.println(exc.getCause());
    }
}
