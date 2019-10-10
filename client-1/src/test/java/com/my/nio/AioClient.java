package com.my.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-10-10 20:20
 **/
public class AioClient implements Runnable {
    private AsynchronousSocketChannel asc;

    public AioClient() {
        try {
            asc = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        asc.connect(new InetSocketAddress("127.0.0.1", 8765));
    }

    public void write(String request) {
        try {
            asc.write(ByteBuffer.wrap(request.getBytes())).get();
            read();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        ByteBuffer readBuf = ByteBuffer.allocate(1024);

        try {
            asc.read(readBuf).get();

            readBuf.flip();

            byte[] bytes = new byte[readBuf.remaining()];
            readBuf.get(bytes);

            System.out.println(new String(bytes).trim());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {

        }
    }

    public static void main(String[] args) throws InterruptedException {
        AioClient client1 = new AioClient();
        AioClient client2 = new AioClient();
        AioClient client3 = new AioClient();

        client1.connect();
        client2.connect();
        client3.connect();


        new Thread(client1, "client1").start();
        new Thread(client1, "client2").start();
        new Thread(client1, "client3").start();

        Thread.sleep(1000);

        client1.write("aaaa");
        client2.write("bbbb");
        client3.write("cccc");
    }
}
