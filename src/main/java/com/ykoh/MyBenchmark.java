package com.ykoh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MyBenchmark {

    private static final Logger logger = LoggerFactory.getLogger(MyBenchmark.class);

    @Benchmark
    public void testConcatenatingStrings() {

        String x = "", y = "", z = "";

        for (int i = 0; i < 100; i++) {
            x += i;
            y += i;
            z += i;

            logger.debug("Concatenating strings " + x + y + z);
        }
    }

    @Benchmark
    public void testVariableArguments() {

        String x = "", y = "", z = "";

        for (int i = 0; i < 100; i++) {
            x += i;
            y += i;
            z += i;

            logger.debug("Variable arguments {} {} {}", x, y, z);
        }
    }

    @Benchmark
    public void testIfDebugEnabled() {

        String x = "", y = "", z = "";

        for (int i = 0; i < 100; i++) {
            x += i;
            y += i;
            z += i;

            if (logger.isDebugEnabled())
                logger.debug("If debug enabled {} {} {}", x, y, z);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .warmupIterations(1)
                .threads(4)
                .forks(2)
                .measurementIterations(2)
                .build();

        new Runner(opt).run();
    }

}
