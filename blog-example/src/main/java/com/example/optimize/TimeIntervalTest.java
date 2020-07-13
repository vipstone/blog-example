package com.example.optimize;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 统计代码执行时间的 5 种方法
 */
public class TimeIntervalTest {
    public static void main(String[] args) throws InterruptedException {
        fifthTimeInterval();
    }

    /**
     * 方法五：guava StopWatch 实现
     * @throws InterruptedException
     */
    private static void fifthTimeInterval() throws InterruptedException {
        // 创建并启动计时器
        Stopwatch stopwatch = Stopwatch.createStarted();
        // 执行时间（1s）
        Thread.sleep(1000);
        // 停止计时器
        stopwatch.stop();
        // 执行时间（单位：秒）
        System.out.printf("执行时长：%d 秒. %n", stopwatch.elapsed().getSeconds()); // %n 为换行
        // 执行时间（单位：毫秒）
        System.out.printf("执行时长：%d 豪秒.", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    /**
     * 方法四：Date 相减（单位毫秒）
     * @throws InterruptedException
     */
    private static void fourthTimeInterval() throws InterruptedException {
        // 开始时间
        Date sdate = new Date();
        // 执行时间（1s）
        Thread.sleep(1000);
        // 结束时间
        Date edate = new Date();
        //  统计执行时间（毫秒）
        System.out.println("执行时长：" + (edate.getTime() - sdate.getTime()) + " 毫秒.");
    }

    /**
     * 方法三：Common.lang3 中的 StopWatch
     * @throws InterruptedException
     */
    private static void thirdTimeInterval() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        // 开始时间
        stopWatch.start();
        // 执行时间（1s）
        Thread.sleep(1000);
        // 结束时间
        stopWatch.stop();
        // 统计执行时间（秒）
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.SECONDS) + " 秒.");
        // 统计执行时间（毫秒）
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.MILLISECONDS) + " 毫秒.");
        // 统计执行时间（纳秒）
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.NANOSECONDS) + " 纳秒.");
    }

    /**
     * 方法二：使用内置 System.nanoTime() 统计时间（单位：纳秒）
     * @throws InterruptedException
     */
    private static void secondTimeInterval() throws InterruptedException {
        // 开始时间
        long stime = System.nanoTime();
        // 执行时间（1s）
        Thread.sleep(1000);
        // 结束时间
        long etime = System.nanoTime();
        // 计算执行时间
        System.out.println("执行时长：" + (etime - stime) + " 纳秒.");
    }

    /**
     * 方法一：使用 System.currentTimeMillis() 统计时间（单位：毫秒）
     * @throws InterruptedException
     */
    private static void firstTimeInterval() throws InterruptedException {
        // 开始时间
        long stime = System.currentTimeMillis();
        // 执行时间（1s）
        Thread.sleep(1000);
        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.println("执行时长：" + (etime - stime) + " 毫秒.");
    }
}
