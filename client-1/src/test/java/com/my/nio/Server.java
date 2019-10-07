package com.my.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-10-07 13:36
 **/
public class Server implements Runnable {

    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    private Selector selector;

    public Server(int port) {
        try {
            //1  打开多路复用器
            this.selector = Selector.open();

            //2 打开服务器端的通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            //3 设置服务器通道的阻塞模式
            serverSocketChannel.configureBlocking(false);

            //4 绑定地址
            serverSocketChannel.bind(new InetSocketAddress(port));

            //5 把服务器通道注册到多路复用器上，并且监听阻塞事件
            serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                //1 让多路复用器开始监听
                this.selector.select();

                //2 返回多路复用器中所有通道key
                Set<SelectionKey> keys = this.selector.selectedKeys();

                Iterator<SelectionKey> iterator = keys.iterator();

                //3 遍历key值
                while (iterator.hasNext()) {
                    //4 接收key
                    SelectionKey key = iterator.next();

                    //5 从容器中移除被选择的key
                    iterator.remove();

                    //6 验证
                    if (key.isValid()) {

                        if (key.isAcceptable()) {
                            this.accept(key);
                        }

                        if (key.isReadable()) {
                            this.read(key);
                        }

                        if (key.isWritable()) {
                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) {
        //1 清空缓冲区
        this.readBuffer.clear();
        //2 获取之前注册的SocketChannel的通道对象
        SocketChannel sc = (SocketChannel) key.channel();

        try {
            //3 从通道中放入数据到缓冲区中
            int i = sc.read(this.readBuffer);
            if (i == -1) {
                key.channel().close();

                key.cancel();

                return;
            }
            //4 由于SocketChannel通道中的数据流入到readBuffer容器中，readBuffer里面的position一定发生了变化，必须要进行复位
            this.readBuffer.flip();

            byte[] array = new byte[this.readBuffer.remaining()];
            this.readBuffer.get(array);

            System.out.println("服务端接收到的数据为: " + new String(array).trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void accept(SelectionKey key) {
        //1 由于目前是server端，那么一定是server端启动，并且处于阻塞状态，所以获取阻塞位的key一定是ServerSocketChannel
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

        try {
            //2 获取一个具体的客户端连接句柄
            SocketChannel socketChannel = ssc.accept();

            //3 设置客户端通道为非阻塞
            socketChannel.configureBlocking(false);

            //4 设置当前获取的客户端连接句柄为可读状态位
            socketChannel.register(this.selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server(8765)).start();
    }
}