package com.motan.classloader.test;

import java.util.concurrent.atomic.AtomicLong;

import com.motan.classloader.annotation.SpiMeta;


@SpiMeta(name = "spitest")
public class SpiTestImpl implements SpiTestInterface {
    private static AtomicLong counter = new AtomicLong(0);
    private long index = 0;

    public SpiTestImpl() {
        index = counter.incrementAndGet();
    }

    @Override
    public long spiHello() {
    	System.out.println("fsldfjk");
        return index;
    }

}
