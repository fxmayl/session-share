/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年10月12日 14:33
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        // 用于接收客户端连接的工作组
        EventLoopGroup boss = new NioEventLoopGroup();
        // 用于对 被接收到的客户端的读写操作进行处理的  工作组
        EventLoopGroup worker = new NioEventLoopGroup();

        // Nio启动辅助类，用于帮助创建Netty服务
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChannelFuture cf = bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    // 接收客户端连接，并进行处理
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        /**
                         * 解决粘包：
                         * 1、分隔符
                         * ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                         * //maxFrameLength参数表示消息长度
                         * socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                         * 2、固定长度
                         * socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(5));
                         * 发送字符串：
                         * socketChannel.pipeline().addLast(new StringEncoder());
                         *                         socketChannel.pipeline().addLast(new StringDecoder());
                         */
                        // 为通道进行初始化，数据传输过来时会进行拦截和执行
                        socketChannel.pipeline().addLast(new ServerHandler());
                    }
                })
                .bind(new InetSocketAddress("127.0.0.1", 8765)).sync();

        cf.channel().closeFuture().sync();

        boss.shutdownGracefully();
        worker.shutdownGracefully();

    }

    public static class ServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("==============通道被激活==============");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                //读
                ByteBuf buf = (ByteBuf) msg;

                byte[] bytes = new byte[buf.readableBytes()];

                buf.readBytes(bytes);

                System.out.println("服务器收到数据: " + new String(bytes));

                //写
                String response = "服务器发送消息";
                ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
//                        .addListener(ChannelFutureListener.CLOSE);// 添加通道连接监听，可以判断当服务器将相应成功给客户端后进行通道关闭，以及客户端程序关闭
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("==============通道读取完成==============");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("==============通道上下文关闭==============");
            ctx.close();
        }
    }
}
