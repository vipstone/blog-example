package com.example.others;

import java.util.Scanner;

/**
 * "智能"聊天机器人
 * <p>
 * 用户输入：在吗？
 * AI：在！
 * 用户输入：会说中文吗？
 * AI：会说中文！
 * 用户输入：智能吗？
 * AI：智能！
 * 用户输入：真的吗？
 * AI：真的！
 */
public class AIChat {
    public static void main(String[] args) {
        // 获取输出对象
        Scanner scanner = new Scanner(System.in);
        // 用户输入提示信息
        System.out.print("用户输入：");
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            // 智能应答
            System.out.println("AI：" + msg.replace("吗", "")
                    .replace("？", "！"));
            System.out.print("用户输入："); // 用户输入提示信息
        }
    }
}
