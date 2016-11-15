package com.jtoolkit;

import static java.lang.Thread.currentThread;

/**
 * {@link NamedThreadRunnable} for debug.
 *
 */
public final class NamedThreadRunnable implements Runnable {

  public static final Runnable named(final Runnable runnable, final String name) {
    return new NamedThreadRunnable(runnable, name);
  }

  private NamedThreadRunnable(final Runnable runnable, final String name) {
    this.runnable = runnable;
    this.name = name;
  }

  @Override
  public void run() {
    final String old = currentThread().getName();
    currentThread().setName(name);
    runnable.run();
    currentThread().setName(old);
  }

  private final Runnable runnable;
  private final String name;
}
