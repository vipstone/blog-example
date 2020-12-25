package com.example.file;

import java.io.*;

public class FileExample {
    public static void main(String[] args) throws IOException {
//        mkdirTest();
//        listFileTest(new File(filePath)); // 递归打印文件

//        String filePath = "/Users/mac/Downloads/io_test/1";

//        Long stime = System.nanoTime();
//        copyImage();
//        Long etime = System.nanoTime();
//        System.out.println("无缓冲区:" + (etime - stime));
//
//
//        Long stime2 = System.nanoTime();
//        copyImage2();
//        Long etime2 = System.nanoTime();
//        System.out.println("有缓冲区:" + (etime2 - stime2));

//        stringSave();

//        Scanner scanner = new Scanner(new File("/Users/mac/Downloads/io_test/777.txt"));
//        while (scanner.hasNextLine()) {
//            System.out.println(scanner.nextLine());
//        }

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/mac/Downloads/io_test/777.txt");
        fileOutputStream.write("中文".getBytes("utf-8"));

    }

    private static void stringSave() {
        String srcPath = "/Users/mac/Downloads/io_test/777.txt";
        try (FileWriter fileWriter = new FileWriter(srcPath, true)) {
            fileWriter.append("嘎嘎.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void copyImage2() {
        String srcPath = "/Users/mac/Downloads/io_test/flower.png";
        String destPath = "/Users/mac/Downloads/io_test/flower-22.png";
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcPath));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destPath))) {
            byte[] bytes = new byte[1024];
            int count = 0;
            while ((count = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, count);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    private static void copyImage() {
        String srcPath = "/Users/mac/Downloads/io_test/flower.png";
        String destPath = "/Users/mac/Downloads/io_test/flower-23.png";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcPath);
            fileOutputStream = new FileOutputStream(destPath);
            byte[] bytes = new byte[1024];
            int count = 0;
            while (true) {
                try {
                    if ((count = fileInputStream.read(bytes)) == -1) break;
                    fileOutputStream.write(bytes, 0, count);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 递归打印文件
    private static void listFileTest(File file) {
        for (File item : file.listFiles()) {
            if (item.isFile()) {
                System.out.println("文件名:" + item.getName());
            } else {
                listFileTest(item);
            }
        }
    }

    // 创建目录
    private static void mkdirTest() {
        String filePath = "/Users/mac/Downloads/io_test/3/3_1";
        File file = new File(filePath);
        if (!file.exists()) {
            boolean result = file.mkdirs();
            if (result) {
                System.out.println("文件夹创建成功");
            } else {
                System.out.println("文件夹创建失败");
            }
        } else {
            System.out.println("目录已经存在");
        }

    }
}
