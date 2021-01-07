package com.example.file;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 将消息封装为:消息头和消息体
 */
public class SocketPacketExample {

    /**
     * Socket 协议封装类
     */
    static class SocketPacket {

        // 消息头存储的长度(8 字节)
        static final int HEAD_SIZE = 8;

        /**
         * 将协议封装为:协议头 + 协议体
         * @param context 消息体(String 类型)
         * @return byte[]
         */
        public byte[] toBytes(String context) {
            // 协议体 byte 数组
            byte[] bodyByte = context.getBytes();
            int bodyByteLength = bodyByte.length;
            // 最终封装对象
            byte[] result = new byte[HEAD_SIZE + bodyByteLength];
            // 借助 NumberFormat 将 int 转换为 byte[]
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setMinimumIntegerDigits(HEAD_SIZE);
            numberFormat.setGroupingUsed(false);
            // 协议头 byte 数组
            byte[] headByte = numberFormat.format(bodyByteLength).getBytes();
            // 封装协议头
            System.arraycopy(headByte, 0, result, 0, HEAD_SIZE);
            // 封装协议体
            System.arraycopy(bodyByte, 0, result, HEAD_SIZE, bodyByteLength);
            return result;
        }

        /**
         * 获取消息头的内容(也就是消息体的长度)
         * @param inputStream
         * @return
         */
        public int getHeader(InputStream inputStream) throws IOException {
            int result = 0;
            byte[] bytes = new byte[HEAD_SIZE];
            inputStream.read(bytes, 0, HEAD_SIZE);
            // 得到消息体的字节长度
            result = Integer.valueOf(new String(bytes));
            return result;
        }
    }

    /**
     * 客户端
     */
    static class MySocketClient {

        public static void main(String[] args) throws IOException {
            // 启动 Socket 并尝试连接服务器
            Socket socket = new Socket("127.0.0.1", 9093);
            // 发送消息合集
            final String[] message = {"Hi,Java.", "Hi,SQL~", "关注公众号|Java中文社群."};
            // 创建协议封装对象
            SocketPacket socketPacket = new SocketPacket();
            try (OutputStream outputStream = socket.getOutputStream()) {
                // 给服务器端发送 10 次消息
                for (int i = 0; i < 10; i++) {
                    // 随机发送消息
                    String msg = message[new Random().nextInt(message.length)];
                    // 将内容封装为:协议头+协议体
                    byte[] bytes = socketPacket.toBytes(msg);
                    // 发送消息
                    outputStream.write(bytes, 0, bytes.length);
                    outputStream.flush();
                }
            }
        }
    }

    /**
     * 服务器端
     */
    static class MySocketServer {
        public static void main(String[] args) throws IOException {
            // 创建 Socket 服务器端
            ServerSocket serverSocket = new ServerSocket(9093);
            // 获取客户端连接
            Socket clientSocket = serverSocket.accept();
            // 使用线程池处理更多的客户端
            ThreadPoolExecutor threadPool = new ThreadPoolExecutor(100, 150, 100,
                    TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000));
            threadPool.submit(() -> {
                // 客户端消息处理
                processMessage(clientSocket);
            });
        }

        /**
         * 客户端消息处理
         * @param clientSocket
         */
        private static void processMessage(Socket clientSocket) {
            // Socket 封装对象
            SocketPacket socketPacket = new SocketPacket();
            // 获取客户端发送的消息对象
            try (InputStream inputStream = clientSocket.getInputStream()) {
                while (true) {
                    // 获取消息头(也就是消息体的长度)
                    int bodyLength = socketPacket.getHeader(inputStream);
                    // 消息体 byte 数组
                    byte[] bodyByte = new byte[bodyLength];
                    // 每次实际读取字节数
                    int readCount = 0;
                    // 消息体赋值下标
                    int bodyIndex = 0;
                    // 循环接收消息头中定义的长度
                    while (bodyIndex <= (bodyLength - 1) &&
                            (readCount = inputStream.read(bodyByte, bodyIndex, bodyLength)) != -1) {
                        bodyIndex += readCount;
                    }
                    bodyIndex = 0;
                    // 成功接收到客户端的消息并打印
                    System.out.println("接收到客户端的信息:" + new String(bodyByte));
                }
            } catch (IOException ioException) {
                System.out.println(ioException.getMessage());
            }
        }
    }
}
