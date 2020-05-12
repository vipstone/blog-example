package com.example.optimize;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
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
public class SwitchOptimizeByStringTest {

    static String _STR = "Java中文社群";

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(SwitchOptimizeByStringTest.class.getSimpleName()) // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }

    @Benchmark
    public void switchHashCodeTest(Blackhole blackhole) {
        String s1;
        switch (_STR.hashCode()) {
            case 3254818:
                s1 = "java";
                break;
            case 104382626:
                s1 = "mysql";
                break;
            case -1008861826:
                s1 = "oracle";
                break;
            case 108389755:
                s1 = "redis";
                break;
            case 3492:
                s1 = "mq";
                break;
            case 101807910:
                s1 = "kafka";
                break;
            case -95168706:
                s1 = "rabbitmq";
                break;
            default:
                s1 = "default";
                break;
        }
        // 为了避免 JIT 忽略未被使用的结果计算，可以使用 Blackhole#consume 来保证方法被正常执行
        blackhole.consume(s1);
    }

    @Benchmark
    public void switchTest(Blackhole blackhole) {
        String s1;
        switch (_STR) {
            case "java":
                s1 = "java";
                break;
            case "mysql":
                s1 = "mysql";
                break;
            case "oracle":
                s1 = "oracle";
                break;
            case "redis":
                s1 = "redis";
                break;
            case "mq":
                s1 = "mq";
                break;
            case "kafka":
                s1 = "kafka";
                break;
            case "rabbitmq":
                s1 = "rabbitmq";
                break;
            default:
                s1 = "default";
                break;
        }
        // 为了避免 JIT 忽略未被使用的结果计算，可以使用 Blackhole#consume 来保证方法被正常执行
        blackhole.consume(s1);
    }

    @Benchmark
    public void ifTest(Blackhole blackhole) {
        String s1;
        if ("java".equals(_STR)) {
            s1 = "java";
        } else if ("mysql".equals(_STR)) {
            s1 = "mysql";
        } else if ("oracle".equals(_STR)) {
            s1 = "oracle";
        } else if ("redis".equals(_STR)) {
            s1 = "redis";
        } else if ("mq".equals(_STR)) {
            s1 = "mq";
        } else if ("kafka".equals(_STR)) {
            s1 = "kafka";
        } else if ("rabbitmq".equals(_STR)) {
            s1 = "rabbitmq";
        } else {
            s1 = "default";
        }
        // 为了避免 JIT 忽略未被使用的结果计算，可以使用 Blackhole#consume 来保证方法被正常执行
        blackhole.consume(s1);
    }
}
