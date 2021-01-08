package com.example.file;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NettyExample {
    // 定义服务器的端口号
    static final int PORT = 8007;

    /**
     * 服务器端
     */
    static class MyNettyServer {
        public static void main(String[] args) {

            Logger.getLogger("io.netty").setLevel(Level.OFF);

            // 创建一个线程组,用来负责接收客户端连接
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            // 创建另一个线程组,用来负责 I/O 的读写
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                // 创建一个 Server 实例(可理解为 Netty 的入门类)
                ServerBootstrap b = new ServerBootstrap();
                // 将两个线程池设置到 Server 实例
                b.group(bossGroup, workerGroup)
                        // 设置 Netty 通道的类型为 NioServerSocket(非阻塞 I/O Socket 服务器)
                        .channel(NioServerSocketChannel.class)
                        // 设置建立连接之后的执行器(ServerInitializer 是我创建的一个自定义类)
                        .childHandler(new ServerInitializer());
                // 绑定端口并且进行同步
                ChannelFuture future = b.bind(PORT).sync();
                // 对关闭通道进行监听
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 资源关闭
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        }
    }

    /**
     * 服务端通道初始化
     */
    static class ServerInitializer extends ChannelInitializer<SocketChannel> {
        // 字符串编码器和解码器
        private static final StringDecoder DECODER = new StringDecoder();
        private static final StringEncoder ENCODER = new StringEncoder();
        // 服务器端连接之后的执行器(自定义的类)
        private static final ServerHandler SERVER_HANDLER = new ServerHandler();

        /**
         * 初始化通道的具体执行方法
         */
        @Override
        public void initChannel(SocketChannel ch) {
            // 通道 Channel 设置
            ChannelPipeline pipeline = ch.pipeline();
//            pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
            // 设置(字符串)编码器和解码器
            pipeline.addLast(DECODER);
            pipeline.addLast(ENCODER);
            // 服务器端连接之后的执行器,接收到消息之后的业务处理
            pipeline.addLast(SERVER_HANDLER);
        }
    }

    /**
     * 服务器端接收到消息之后的业务处理类
     */
    static class ServerHandler extends SimpleChannelInboundHandler<String> {

        /**
         * 读取到客户端的消息
         */
        @Override
        public void channelRead0(ChannelHandlerContext ctx, String request) {
            if (!request.isEmpty()) {
                System.out.println("接到客户端的消息:" + request);
            }
        }

        /**
         * 数据读取完毕
         */
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            ctx.flush();
        }

        /**
         * 异常处理，打印异常并关闭通道
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }

    /**
     * 客户端
     */
    static class MyNettyClient {
        public static void main(String[] args) {
            // 创建事件循环线程组(客户端的线程组只有一个)
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                // Netty 客户端启动对象
                Bootstrap b = new Bootstrap();
                // 设置启动参数
                b.group(group)
                        // 设置通道类型
                        .channel(NioSocketChannel.class)
                        // 设置启动执行器(负责启动事件的业务执行,ClientInitializer 为自定义的类)
                        .handler(new ClientInitializer());

                // 连接服务器端并同步通道
                Channel ch = b.connect("127.0.0.1", PORT).sync().channel();

                // 发送消息
                ChannelFuture lastWriteFuture = null;
                // 给服务器端发送 10 条消息
                for (int i = 0; i < 10; i++) {
                    // 发送给服务器消息
                    lastWriteFuture = ch.writeAndFlush("Hi,Java.");
                }
                // 在关闭通道之前，同步刷新所有的消息
                if (lastWriteFuture != null) {
                    lastWriteFuture.sync();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放资源
                group.shutdownGracefully();
            }
        }
    }

    /**
     * 客户端通道初始化类
     */
    static class ClientInitializer extends ChannelInitializer<SocketChannel> {
        // 字符串编码器和解码器
        private static final StringDecoder DECODER = new StringDecoder();
        private static final StringEncoder ENCODER = new StringEncoder();
        // 客户端连接成功之后业务处理
        private static final ClientHandler CLIENT_HANDLER = new ClientHandler();

        /**
         * 初始化客户端通道
         */
        @Override
        public void initChannel(SocketChannel ch) {
            ChannelPipeline pipeline = ch.pipeline();
//            pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
            // 设置(字符串)编码器和解码器
            pipeline.addLast(DECODER);
            pipeline.addLast(ENCODER);
            // 客户端连接成功之后的业务处理
            pipeline.addLast(CLIENT_HANDLER);
        }
    }

    /**
     * 客户端连接成功之后的业务处理
     */
    static class ClientHandler extends SimpleChannelInboundHandler<String> {

        /**
         * 读取到服务器端的消息
         */
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) {
            System.err.println("接到服务器的消息:" + msg);
        }

        /**
         * 异常处理，打印异常并关闭通道
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
