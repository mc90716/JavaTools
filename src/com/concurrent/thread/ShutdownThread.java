package com.concurrent.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 一直不停的做doWork定义的逻辑，直到调用shutdown方法或者遇到异常退出
 * @author 云袭
 *
 */
public abstract class ShutdownThread extends Thread {
	
	private final AtomicBoolean isRunning = new AtomicBoolean(true);
	private final CountDownLatch shutDownLatch = new CountDownLatch(1);
	private final boolean isInterruptible;
	private final String name;
	
	public ShutdownThread(String threadName, boolean isInterruptible) {
		this.name = threadName;
		this.isInterruptible = isInterruptible;
	}
	
	public void shutdown() throws InterruptedException {
		initShutdown();
		awaitShutdown();
	}
	
	private void initShutdown() {
		if(isRunning.compareAndSet(true, false)) {
			isRunning.set(false);
			if(isInterruptible) {
				interrupt();
			}
		}
	}
	
	private void awaitShutdown() throws InterruptedException {
		shutDownLatch.await();
	}
	
	public abstract void doWork();
	
	/**
	 * 直到线程shutdown或者遇到抛出异常才终止
	 */
	@Override
	public void run() {
		try {
			while(isRunning.get()) {
				doWork();
			}
		} catch (Exception e) {
			if(isRunning.get()) {
				e.printStackTrace();
			}
		}
		shutDownLatch.countDown();
		System.out.println("Thread [" + this.name + "] Shutdown");
	}
}
