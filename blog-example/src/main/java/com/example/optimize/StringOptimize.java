package com.example.optimize;

/**
 * String 优化
 */
public class StringOptimize {
    public static void main(String[] args) {
        // 性能测试
        for (int i = 0; i < 5; i++) {
            // String
            long st1 = System.currentTimeMillis(); // 开始时间
            doAdd();
            long et1 = System.currentTimeMillis(); // 开始时间
            System.out.println("String 拼加，执行时间：" + (et1 - st1));
            // StringBuilder
            long st2 = System.currentTimeMillis(); // 开始时间
            doAppend();
            long et2 = System.currentTimeMillis(); // 开始时间
            System.out.println("StringBuilder 拼加，执行时间：" + (et2 - st2));
            System.out.println();
        }

        // intern 使用
        String s1 = new String("Java中文社群").intern();
        String s2 = new String("Java中文社群").intern();
        System.out.println(s1 == s2);

        // 回溯导致 CPU 100%
        String badRegex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";
        String bugUrl = "http://www.apigo.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";
        if (bugUrl.matches(badRegex)) {
            System.out.println("match!!");
        } else {
            System.out.println("no match!!");
        }
    }

    public static String doAdd() {
        String result = "";
        for (int i = 0; i < 50000; i++) {
            result += (" i:" + i);
        }
        return result;
    }

    public static String doAppend() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append(" i:" + i);
        }
        return sb.toString();
    }


}
