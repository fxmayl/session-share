package com.my.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-10-13 11:14
 **/
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(worker).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
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
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture cf = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8765)).syncUninterruptibly();

        //间隔过短会出现粘包
        cf.channel().writeAndFlush(Unpooled.copiedBuffer("Hello Netty-1".getBytes()));
        Thread.sleep(1000);//加入停顿事时间，可以防止粘包，但是这样做不行
        cf.channel().writeAndFlush(Unpooled.copiedBuffer("Hello Netty-2".getBytes()));
        Thread.sleep(1000);
        cf.channel().writeAndFlush(Unpooled.copiedBuffer("Hello Netty-3".getBytes()));
        Thread.sleep(1000);
        cf.channel().writeAndFlush(Unpooled.copiedBuffer("Hello Netty-4".getBytes()));
        Thread.sleep(1000);
        cf.channel().writeAndFlush(Unpooled.copiedBuffer("Hello Netty-5".getBytes()));
        Thread.sleep(1000);
        cf.channel().writeAndFlush(Unpooled.copiedBuffer("Hello Netty-6".getBytes()));
        Thread.sleep(1000);


        cf.channel().closeFuture().syncUninterruptibly();
        worker.shutdownGracefully();
    }

    public static class ClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("=======客户端通道激活=======");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                //读
                ByteBuf buf = (ByteBuf) msg;

                byte[] bytes = new byte[buf.readableBytes()];

                buf.readBytes(bytes);

                System.out.println("客户端收到数据: " + new String(bytes));
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("=======客户端通道读取完成=======");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("=======客户端通道读取异常=======");
            ctx.close();
        }
    }
}
