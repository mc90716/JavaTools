package com.jtoolkit;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * {@link LoggingUncaughtExceptionHandler
 */
public final class LoggingUncaughtExceptionHandler implements UncaughtExceptionHandler {


  public final static UncaughtExceptionHandler SINGLETON = new LoggingUncaughtExceptionHandler();

  private LoggingUncaughtExceptionHandler() {}

  @Override
  public void uncaughtException(final Thread t, final Throwable e) {
    if (e instanceof RuntimeException && e.getCause() != null) {
      if (e.getCause() instanceof InterruptedException) Thread.currentThread().interrupt();
      return;
    }
  }
}
