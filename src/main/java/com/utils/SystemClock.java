package com.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Forked from <A>https://github.com/zhongl/jtoolkit/blob/master/common/src/main/java/com/github/zhongl/jtoolkit/SystemClock.java</A>
 */
public class SystemClock {

    private static final SystemClock millisClock = new SystemClock(1);

    private final long precision;
    private final AtomicLong now;

    public static SystemClock millisClock() {
        return millisClock;
    }

    private SystemClock(long precision) {
        this.precision = precision;
        now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }

    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {

            @Override
            public Thread newThread(Runnable runnable) {
                Thread t = new Thread(runnable, "system.clock");
                t.setDaemon(true);
                return t;
            }
        });

        scheduler.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                now.set(System.currentTimeMillis());
            }
        }, precision, precision, MILLISECONDS);
    }

    public long now() {
        return now.get();
    }

    public long precision() {
        return precision;
    }
}
