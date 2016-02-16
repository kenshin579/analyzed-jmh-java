package com.devoxx.tia.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class ProfilerDemoBench {

    private Map<Long, Boolean> map = new HashMap<Long, Boolean>();

    // private ConcurrentHashMap<Long, Boolean> map = new
    // ConcurrentHashMap<Long, Boolean>();

    @Param(value = {"0", "1000"})
    private long tokens;

    public void doSomething(long id) {

        synchronized (map) {
            if (!map.containsKey(id))
                map.put(id, Boolean.TRUE);
        }
        // map.putIfAbsent(id, Boolean.TRUE);
    }

    @Benchmark
    public void record() {

        Blackhole.consumeCPU(tokens);

        long id = Thread.currentThread().getId();

        doSomething(id);
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
