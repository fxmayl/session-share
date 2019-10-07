package com.my.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-10-07 14:30
 **/
public class Client {
    public static void main(String[] args) {
        //1 描述套接字ip地址 端口
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8765);

        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

        try {
            SocketChannel sc = SocketChannel.open();

            sc.connect(address);

            while (true) {
                byte[] data = new byte[1024];

                System.in.read(data);

                writeBuffer.put(data);

                writeBuffer.flip();

                sc.write(writeBuffer);

                writeBuffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
