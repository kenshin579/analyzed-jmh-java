package com.devoxx.tia.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.atomic.AtomicLong;

@State(Scope.Benchmark)
public class ConsumerProducerBench {

    private AtomicLong produce = new AtomicLong();

    private AtomicLong consume = new AtomicLong();

    @Group("consumeProduce")
    @GroupThreads(3)
    @Benchmark
    public void consume() {
        consume.incrementAndGet();
    }

    @Group("consumeProduce")
    @GroupThreads(1)
    @Benchmark
    public void produce() {
        produce.incrementAndGet();
    }

}
