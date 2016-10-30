package com.kafka.delay;

import java.beans.Expression;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TimingWheel {
	private final long tickMs;
	private final int wheelSize;
	private final long startMs;
	private final AtomicInteger taskCounter;
	private final DelayQueue<TimerTaskList> queue;
	private final long interval;
	private final TimerTaskList[] buckets;
	private long currentTime;
	
	private volatile TimingWheel overflowWheel = null;
	
	public TimingWheel(long tickMs, int wheelSize, long startMs, AtomicInteger taskCounter,
			DelayQueue<TimerTaskList> queue) {
		this.tickMs = tickMs;
		this.wheelSize = wheelSize;
		this.startMs = startMs;
		this.taskCounter = taskCounter;
		this.queue = queue;
		this.interval = tickMs * wheelSize;
		this.buckets = new TimerTaskList[taskCounter.get()];
		this.currentTime = startMs - (startMs % tickMs);
	}
	
	private void addOverflowWheel() {
		synchronized (this) {
			if (this.overflowWheel == null) {
				this.overflowWheel = new TimingWheel(interval, wheelSize,
						startMs, taskCounter, queue);
			}
		}
	}
	
	public boolean add(TimerTaskEntry timerTaskEntry) {
		if(timerTaskEntry.cancelled()) {
			return false;
		}
		long expirationMs = timerTaskEntry.expirationMs;
		if(expirationMs < currentTime + tickMs) {
			//already expired
			return false;
		} else if(expirationMs < currentTime + interval) {
			long virtualId = expirationMs / tickMs;
			int index = (int)(virtualId % wheelSize);
			TimerTaskList bucket = buckets[index];
			return true;
		} else {
			if(this.overflowWheel == null) {
				addOverflowWheel();
			}
			return this.overflowWheel.add(timerTaskEntry);
		}
	}
	
	public void advanceClock(long timeMs) {
		if(timeMs >= currentTime + tickMs) {
			currentTime = timeMs - (timeMs % tickMs);
			if(overflowWheel != null) {
				overflowWheel.advanceClock(currentTime);
			}
		}
	}
}
