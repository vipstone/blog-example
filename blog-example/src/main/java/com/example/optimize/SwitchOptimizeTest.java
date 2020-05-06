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
public class SwitchOptimizeTest {

    static Integer _NUM = -1;

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(SwitchOptimizeTest.class.getSimpleName()) // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }

    @Benchmark
    public void tableSwitchTest() {
        int num1;
        switch (_NUM) {
            case 1:
                num1 = 1;
                break;
            case 2:
                num1 = 2;
                break;
            case 3:
                num1 = 3;
                break;
            case 4:
                num1 = 4;
                break;
            case 5:
                num1 = 5;
                break;
            case 6:
                num1 = 6;
                break;
            case 7:
                num1 = 7;
                break;
            case 8:
                num1 = 8;
                break;
            case 9:
                num1 = 9;
                break;
            default:
                num1 = -1;
                break;
        }
    }

    @Benchmark
    public void lookupSwitchTest() {
        int num1;
        switch (_NUM) {
            case 1:
                num1 = 1;
                break;
            case 11:
                num1 = 2;
                break;
            case 3:
                num1 = 3;
                break;
            case 4:
                num1 = 4;
                break;
            case 19:
                num1 = 5;
                break;
            case 6:
                num1 = 6;
                break;
            case 33:
                num1 = 7;
                break;
            case 8:
                num1 = 8;
                break;
            case 999:
                num1 = 9;
                break;
            default:
                num1 = -1;
                break;
        }
    }


//    @Benchmark
//    public void switchTest() {
//        int num1;
//        switch (_NUM) {
//            case 1:
//                num1 = 1;
//                break;
//            case 2:
//                num1 = 2;
//                break;
//            case 3:
//                num1 = 3;
//                break;
//            case 4:
//                num1 = 4;
//                break;
//            case 5:
//                num1 = 5;
//                break;
//            case 6:
//                num1 = 6;
//                break;
//            case 7:
//                num1 = 7;
//                break;
//            case 8:
//                num1 = 8;
//                break;
//            case 9:
//                num1 = 9;
//                break;
//            case 10:
//                num1 = 10;
//                break;
//            case 11:
//                num1 = 11;
//                break;
//            case 12:
//                num1 = 12;
//                break;
//            case 13:
//                num1 = 13;
//                break;
//            case 14:
//                num1 = 14;
//                break;
//            case 15:
//                num1 = 15;
//                break;
//            default:
//                num1 = -1;
//                break;
//        }
//    }

//    @Benchmark
//    public void ifTest() {
//        int num1;
//        if (_NUM == 1) {
//            num1 = 1;
//        } else if (_NUM == 2) {
//            num1 = 2;
//        } else if (_NUM == 3) {
//            num1 = 3;
//        } else if (_NUM == 4) {
//            num1 = 4;
//        } else if (_NUM == 5) {
//            num1 = 5;
//        } else if (_NUM == 6) {
//            num1 = 6;
//        } else if (_NUM == 7) {
//            num1 = 7;
//        } else if (_NUM == 8) {
//            num1 = 8;
//        } else if (_NUM == 9) {
//            num1 = 9;
//        } else if (_NUM == 10) {
//            num1 = 10;
//        } else if (_NUM == 666) {
//            num1 = 666;
//        } else if (_NUM == 12) {
//            num1 = 12;
//        } else if (_NUM == 13) {
//            num1 = 13;
//        } else if (_NUM == 14) {
//            num1 = 14;
//        } else if (_NUM == 999) {
//            num1 = 999;
//        } else {
//            num1 = -1;
//        }
//    }
}
