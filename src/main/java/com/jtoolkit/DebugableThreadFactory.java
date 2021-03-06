package com.jtoolkit;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link DebugableThreadFactory}
 *
 */
public final class DebugableThreadFactory implements ThreadFactory {

  private final boolean daemon;

  public DebugableThreadFactory(final String name) {
    this(name, false);
  }

  public DebugableThreadFactory(final String name, final boolean daemon) {
    this.daemon = daemon;
    final SecurityManager s = System.getSecurityManager();
    group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    namePrefix = name + "-thread-";
  }

  @Override
  public Thread newThread(final Runnable r) {
    final Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
    if (daemon) t.setDaemon(daemon);
    if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);
    t.setUncaughtExceptionHandler(LoggingUncaughtExceptionHandler.SINGLETON);
    return t;
  }

  private final ThreadGroup group;

  private final String namePrefix;

  private final AtomicInteger threadNumber = new AtomicInteger(1);
}
