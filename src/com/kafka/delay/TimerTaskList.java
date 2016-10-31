package com.kafka.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TimerTaskList是一个双向环状链表，root节点的pre节点是tail节点，tail节点的next节点是root节点
 * 当调用add方法往list中添加新节点的时候，新节点被添加到尾部
 * @author 云袭
 *
 */
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
	
	public boolean setExpiration(long expirationMs) {
		return this.expiration.getAndSet(expirationMs) != expirationMs;
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
	
	/**
	 * 把一个Timer Task添加到List中
	 * @param timerTaskEntry
	 */
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
	
	public void remove(TimerTaskEntry timerTaskEntry) {
		synchronized (this) {
			synchronized (timerTaskEntry) {
				if(timerTaskEntry.list == this) {
					timerTaskEntry.next.prev = timerTaskEntry.prev;
					timerTaskEntry.prev.next = timerTaskEntry.next;
					timerTaskEntry.next = null;
					timerTaskEntry.prev = null;
					timerTaskEntry.list = null;
					taskCounter.decrementAndGet();
				}
			}
		}
	}
	
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(Math.max(getExpiration() - TimeUnit.NANOSECONDS.toMillis(System.nanoTime()), 0), 
				TimeUnit.MILLISECONDS);
	}

	
	public long getExpiration() {
		return expiration.get();
	}
	
	@Override
	public int compareTo(Delayed o) {
		TimerTaskList other = (TimerTaskList)o;
		if(getExpiration() > other.getExpiration()) {
			return -1;
		} else if(getExpiration() > other.getExpiration()) {
			return 1;
		} else {
			return 0;
		}
	}

}
