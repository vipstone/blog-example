package com.example.file;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Socket 粘包和半包演示示例
 */
public class SocketErrorExample {

    private static final int PORT = 9999;       // 端口号
    private static final int BYTE_LENGTH = 20;  // 字节读取长度

    /**
     * 服务器端:负责收消息
     */
    static class ServSocket {

        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(PORT);
            // 获取到连接
            Socket clientSocket = serverSocket.accept();
            try (InputStream inputStream = clientSocket.getInputStream()) {
                while (true) {
                    byte[] bytes = new byte[BYTE_LENGTH];
                    // 读取客户端发送的信息
                    int count = inputStream.read(bytes, 0, BYTE_LENGTH);
                    if (count > 0) {
                        // 接收到消息打印
                        System.out.println("接收到客户端的信息是:" + new String(bytes));
                    }
                    count = 0;
                }
            }
        }
    }

    /**
     * 客户端:负责发消息
     */
    static class ClientSocket {
        public static void main(String[] args) throws IOException {
            Socket socket = new Socket("127.0.0.1", PORT);
            final String message = "Hi,Java."; // 发送消息
            try (OutputStream outputStream = socket.getOutputStream()) {
                for (int i = 0; i < 10; i++) {
                    outputStream.write(message.getBytes());
                }
            }
        }
    }

    /**
     * 服务器端改进版 1(只负责收消息)
     */
    static class ServSocketV1 {
        private static final int BYTE_LENGTH = 1024;  // 字节长度

        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(9091);
            // 获取到连接
            Socket clientSocket = serverSocket.accept();
            try (InputStream inputStream = clientSocket.getInputStream()) {
                while (true) {
                    byte[] bytes = new byte[BYTE_LENGTH];
                    // 读取客户端发送的信息
                    int count = inputStream.read(bytes, 0, BYTE_LENGTH);
                    if (count > 0) {
                        // 接收到消息打印
                        System.out.println("接收到客户端的信息是:" + new String(bytes).trim());
                    }
                    count = 0;
                }
            }
        }
    }

    /**
     * 客户端改进版 1(只负责发消息)
     */
    static class ClientSocketV1 {
        private static final int BYTE_LENGTH = 1024;  // 字节长度

        public static void main(String[] args) throws IOException {
            Socket socket = new Socket("127.0.0.1", 9091);
            final String message = "Hi,Java."; // 发送消息
            try (OutputStream outputStream = socket.getOutputStream()) {
                // 将数据组装成定长字节数组
                byte[] bytes = new byte[BYTE_LENGTH];
                int idx = 0;
                for (byte b : message.getBytes()) {
                    bytes[idx] = b;
                    idx++;
                }
                // 给服务器端发送 10 次消息
                for (int i = 0; i < 10; i++) {
                    outputStream.write(bytes, 0, BYTE_LENGTH);
                }
            }
        }
    }

    /**
     * 服务器端改进版 3(只负责收消息)
     */
    static class ServSocketV3 {
        public static void main(String[] args) throws IOException {
            // 创建 Socket 服务器端
            ServerSocket serverSocket = new ServerSocket(9092);
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
            // 获取客户端发送的消息流对象
            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()))) {
                while (true) {
                    // 按行读取客户端发送的消息
                    String msg = bufferedReader.readLine();
                    if (msg != null) {
                        // 成功接收到客户端的消息并打印
                        System.out.println("接收到客户端的信息:" + msg);
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 客户端改进版 3(只负责发消息)
     */
    static class ClientSocketV3 {
        public static void main(String[] args) throws IOException {
            // 启动 Socket 并尝试连接服务器
            Socket socket = new Socket("127.0.0.1", 9092);
            final String message = "Hi,Java."; // 发送消息
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()))) {
                // 给服务器端发送 10 次消息
                for (int i = 0; i < 10; i++) {
                    // 注意:结尾的 \n 不能省略,它表示按行写入
                    bufferedWriter.write(message + "\n");
                    // 刷新缓冲区(此步骤不能省略)
                    bufferedWriter.flush();
                }
            }
        }
    }


}
