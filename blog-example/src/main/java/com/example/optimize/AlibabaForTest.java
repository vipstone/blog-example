package com.example.optimize;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * for 循环内创建变量性能测试
 */
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
public class AlibabaForTest {
    private static final int forSize = 100000; // 循环次数

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(AlibabaForTest.class.getSimpleName()) // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }

    @Benchmark
    public int forTest() {
        int count = 0;
        for (int i = 0; i < forSize; i++) {
            MyAlibabaForTest test = new MyAlibabaForTest();
            if (i % test.getNumber() == 0) {
                count++;
            }
        }
        return count;
    }

    @Benchmark
    public int test() {
        int count = 0;
        MyAlibabaForTest test = new MyAlibabaForTest();
        for (int i = 0; i < forSize; i++) {
            if (i % test.getNumber() == 0) {
                count++;
            }
        }
        return count;
    }


}

class MyAlibabaForTest {
    public MyAlibabaForTest() {
        // 此处模拟一些耗时的操作
        for (int i = 0; i < 10; i++) {
            if (i / 3 == 0) {
                System.out.println(i);
            }
        }
    }

    public int getNumber() {
        return 2;
    }
}