/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月15日 13:14
 */
public class NioDemo {
    @Test
    public void test1() throws IOException {
        File file = new File("D:\\logs\\demo.log");

        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        byte[] bytes = new byte[(int)file.length()];
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, bytes.length);
        for (int offset = 0; offset < bytes.length; offset++) {
            byte b = buffer.get();
            bytes[offset] = b;
        }
        Scanner scanner = new Scanner(new ByteArrayInputStream(bytes)).useDelimiter("\n\r");
        while (scanner.hasNext()) {
            System.out.println(scanner.next() + " ");
        }

    }
}
