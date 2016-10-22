package com.delay;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DelayedOperationPurgatory<D> {
	
	private Map<D, Watcher<D>> watcher = new ConcurrentHashMap<D, Watcher<D>>();
	
	public boolean tryCompleteElseWatch(DelayOperation op) {
		boolean isComplete = op.tryComplete();
		if(isComplete) {
			return true;
		}
		return false;
	}
	
	private class Watcher<T> {
		private List<T> operations = new LinkedList<T>();
		
		public void watch(T t) {
			synchronized (operations) {
				operations.add(t);
			}
		}
	}
}
