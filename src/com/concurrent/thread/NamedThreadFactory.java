package com.concurrent.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {

	//全局就一个变量，static修饰
	private static final AtomicInteger poolId = new AtomicInteger();
	
	private final AtomicInteger nextId = new AtomicInteger();
	//线程的名字前缀
	private final String prefix;
	private boolean daemon;
	private final ThreadGroup group;
	
	public NamedThreadFactory() {
		this("pool-" + poolId.incrementAndGet(), false);
	}
	
	public NamedThreadFactory(String prefix) {
		this(prefix, false);
	}
	
	public NamedThreadFactory(String prefix, boolean daemon) {
        this.prefix = prefix + " #";
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        group = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }
	
	@Override
	public Thread newThread(Runnable r) {
		String name = this.prefix + nextId.getAndIncrement();
		Thread thread = new Thread(this.group, r, name, 0);
		if(this.daemon) {
			thread.setDaemon(true);
		} else {
			thread.setDaemon(false);
		}
		return thread;
	}

}
