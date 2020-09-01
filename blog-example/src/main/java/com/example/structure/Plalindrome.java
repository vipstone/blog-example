package com.example.structure;

/**
 * 回文判断
 */
public class Plalindrome {
    public static void main(String[] args) {
        String str = "123321";
        String str2 = "1231";
        String str3 = "123321";
        System.out.println(isPlalindrome_3(str));
        System.out.println(isPlalindrome_3(str2));
        System.out.println(isPlalindrome_3(str3));
    }

    /**
     * 使用 for 循环判断
     * @param str 待对比字符串
     * @return
     */
    private static boolean isPlalindrome_3(String str) {
        char[] chars = str.toCharArray();
        int maxSize = chars.length / 2; // 循环比较次数
        for (int i = 0; i < maxSize; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                // 最前面的和最后的依次对比，如果有一个不相同，则返回 false
                return false;
            }
        }
        return true;
    }

    /**
     * 使用递归实现
     * @param str  待对比字符串
     * @param low  前面字符的下标
     * @param high 后面字符的下标
     * @return
     */
    private static boolean isPlalindrome_2(String str, int low, int high) {
        if (high == 0 || high == 1) {
            return true;
        } else {
            char c1 = str.charAt(low), c2 = str.charAt(high);
            if (c1 == c2) { // 字符对比相等
                return isPlalindrome_2(str, low + 1, high - 1);
            } else { // 字符对比不相等
                return false;
            }
        }
    }


    /**
     * 借助内置函数实现回文串判断
     * @param str 待判断字符串
     * @return
     */
    private static boolean isPlalindrome_1(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString().equals(str);
    }
}
