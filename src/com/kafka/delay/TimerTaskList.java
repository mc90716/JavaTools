package com.kafka.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TimerTaskList implements Delayed {

	/**
	 * 根节点是一个dummy节点
	 */
	private TimerTaskEntry root = new TimerTaskEntry(null, -1);
	private AtomicInteger taskCounter = null;
	private AtomicLong expiration = new AtomicLong(-1L);
	
	public TimerTaskList(AtomicInteger taskCounter) {
		this.root.next = root;
		this.root.prev = root;
		this.taskCounter = taskCounter;
	}
	
	public void setExpiration(long expirationMs) {
		this.expiration.set(expirationMs);
	}
	
	/**
	 * 循环遍历TimerTaskList中的每一个Entry元素，foreach方法中可以传递一个接口，
	 * 用于实现supply function
	 */
	public void foreach() {
		synchronized (this) {
			TimerTaskEntry entry = root.next;
			while(entry != root) {
				TimerTaskEntry nextEntry = entry.next;
				if (!entry.cancelled()) {
					//Do Something
				}
				entry = nextEntry;
			}
		}
	}
	
	public void add(TimerTaskEntry timerTaskEntry) {
		boolean done = false;
		while(!done) {
			timerTaskEntry.remove();
			
			synchronized (this) {
				synchronized (timerTaskEntry) {
					if(timerTaskEntry.list == null) {
						TimerTaskEntry tail = root.prev;
						timerTaskEntry.next = root;
						timerTaskEntry.prev = tail;
						timerTaskEntry.list = this;
						tail.next = timerTaskEntry;
						root.prev = timerTaskEntry;
						taskCounter.incrementAndGet();
						done = true;
					}
				}
			}
		}
	}
	
	public void remove(TimerTaskEntry entry) {
		
	}
	
	@Override
	public int compareTo(Delayed o) {
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return 0;
	}

}
