package com.motan.classloader.test;

import java.util.concurrent.atomic.AtomicLong;

import com.motan.classloader.annotation.SpiMeta;

@SpiMeta(name = "spiPrototypeTest")
public class SpiPrototypeTestImpl implements SpiPrototypeInterface {
    private static AtomicLong counter = new AtomicLong(0);
    private long index = 0;

    public SpiPrototypeTestImpl() {
        index = counter.incrementAndGet();
    }

    @Override
    public long spiHello() {
    	System.out.println("spihello");
        return index;
    }

}
