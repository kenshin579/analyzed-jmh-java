package com.devoxx.tia.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class ParamExampleBench {

    @Param({"one", "two", "three"})
    private String input;

    @Benchmark
    public int bench() {
        return input.length();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.SECONDS)
                .warmupIterations(0)
                .measurementIterations(1)
//                .measurementTime(TimeValue.seconds(1))
                .forks(1)
//                .shouldFailOnError(true)
//                .shouldDoGC(true)
                .build();

        new Runner(opt).run();
    }
}


