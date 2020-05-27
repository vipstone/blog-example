package com.example.optimize;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 阿里巴巴《Java开发手册》toArray 性能测试
 */
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
public class AlibabaToArrayTest {
    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(AlibabaToArrayTest.class.getSimpleName()) // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }

    // size 等于 0
    @Benchmark
    public void listTest(Blackhole blackhole) {
        List<String> list = new ArrayList<>(2);
        list.add("Java");
        list.add("Redis");
        String[] array = list.toArray(new String[0]);
        // 为了避免 JIT 忽略未被使用的结果计算，可以使用 Blackhole#consume 来保证方法被正常执行
        blackhole.consume(array);
    }

    // 等于 size
    @Benchmark
    public void listEqualTest(Blackhole blackhole) {
        List<String> list = new ArrayList<>(2);
        list.add("Java");
        list.add("Redis");
        String[] array = list.toArray(new String[list.size()]);
        // 为了避免 JIT 忽略未被使用的结果计算，可以使用 Blackhole#consume 来保证方法被正常执行
        blackhole.consume(array);
    }

    // 大于 size
    @Benchmark
    public void listGreaterTest(Blackhole blackhole) {
        List<String> list = new ArrayList<>(2);
        list.add("Java");
        list.add("Redis");
        String[] array = list.toArray(new String[1000]);
        // 为了避免 JIT 忽略未被使用的结果计算，可以使用 Blackhole#consume 来保证方法被正常执行
        blackhole.consume(array);
    }

    // 小于 size
    @Benchmark
    public void listLessTest(Blackhole blackhole) {
        List<String> list = new ArrayList<>(2);
        list.add("Java");
        list.add("Redis");
        String[] array = list.toArray(new String[1]);
        // 为了避免 JIT 忽略未被使用的结果计算，可以使用 Blackhole#consume 来保证方法被正常执行
        blackhole.consume(array);
    }
}