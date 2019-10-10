/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年10月10日 11:15
 */
public class FileNio {
    @Test
    public void test1() throws IOException, InterruptedException {
        Path path = Paths.get("E:\\log\\no_user.log");
        AsynchronousFileChannel fileChannel =
            AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer readBuf = ByteBuffer.allocate(1024);
        fileChannel.read(readBuf, 1024, readBuf, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);

                attachment.flip();
                byte[] bytes = new byte[attachment.remaining()];
                attachment.get(bytes);

                System.out.println(new String(bytes));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                throw new RuntimeException(exc);
            }
        });

        Thread.sleep(2000);
    }
}
