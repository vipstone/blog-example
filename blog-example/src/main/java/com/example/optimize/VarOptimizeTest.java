package com.example.optimize;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
public class VarOptimizeTest {

    volatile char[] myChars = ("Oracle Cloud Infrastructure Low data networking fees and " +
            "automated migration Oracle Cloud Infrastructure platform is built for " +
            "enterprises that are looking for higher performance computing with easy " +
            "migration of their on-premises applications to the Cloud.").toCharArray();

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(VarOptimizeTest.class.getSimpleName()) // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }

    @Benchmark
    public int globalVarTest() {
        int count = 0;
        for (int i = 0; i < myChars.length; i++) {
            if (myChars[i] == 'c') {
                count++;
            }
        }
        return count;
    }

    @Benchmark
    public int localityVarTest() {
        char[] localityChars = myChars;
        int count = 0;
        for (int i = 0; i < localityChars.length; i++) {
            if (localityChars[i] == 'c') {
                count++;
            }
        }
        return count;
    }

}
