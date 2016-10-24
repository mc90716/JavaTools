package com.kafka.delay;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class DelayedOperation implements Runnable {
	
	private AtomicBoolean completed = new AtomicBoolean(false);

	public boolean forceComplete() {
		if(completed.compareAndSet(false, true)) {
			cancel();
			onComplete();
			return true;
		} else {
			return false;
		}
	}
	
	
	public void cancel() {
		//do something to cancel the timer
	}
	
	public boolean isCompleted() {
		return completed.get();
	}
	
	/**
	 * 当延时操作超时之后的callback
	 * @return
	 */
	public abstract void onExpiration();
	
	/**
	 * 延时操作完成的时候调用该方法，该方法需要在子类中实现，并且会在forceComplete()中
	 * 调用
	 */
	public abstract void onComplete(); 
	
	/**
	 * 查看该延时操作是否已经满足完成的条件，如果是，那么就调用forceComplete()方法，
	 * 并且返回true；如果不满足完成条件，返回false
	 */
	public abstract boolean tryComplete();


	@Override
	public void run() {
		if(forceComplete()) {
			onExpiration();
		}
	}
}
