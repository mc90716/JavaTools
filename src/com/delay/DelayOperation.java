package com.delay;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class DelayOperation {
	
	private AtomicBoolean completed = new AtomicBoolean(false);
	
	public abstract boolean tryComplete();
	public abstract void onComplete();
	
	public boolean isComplete() {
		return completed.get();
	}
	public boolean forceComplete() {
		if(completed.compareAndSet(false, true)) {
			onComplete();
			return true;
		} else {
			return false;
		}
	}
}
