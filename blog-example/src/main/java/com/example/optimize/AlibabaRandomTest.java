package com.example.optimize;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
public class AlibabaRandomTest {
    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(AlibabaRandomTest.class.getSimpleName()) // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }

    @Benchmark
    public void randomTest(Blackhole blackhole) {
        int count = 0;
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            count += random.nextInt(10);
        }
        // 为了避免 JIT 忽略未被使用的结果计算，可以使用 Blackhole#consume 来保证方法被正常执行
        blackhole.consume(count);
    }

    @Benchmark
    public void threadLocalRandomTest(Blackhole blackhole) {
        int count = 0;
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        for (int i = 0; i < 1000; i++) {
            count += localRandom.nextInt(10);
        }
        // 为了避免 JIT 忽略未被使用的结果计算，可以使用 Blackhole#consume 来保证方法被正常执行
        blackhole.consume(count);
    }
}