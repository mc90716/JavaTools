package com.kafka.delay;

/**
 * TimerTaskEntry是一个双向链表的Node，Node实现Comparable接口，Node里面放TimerTask，
 * TimerTask是一个Runnable
 * 
 * @author 云袭
 *
 */
public class TimerTaskEntry implements Comparable<TimerTaskEntry> {

	private TimerTask timerTask;
	//该任务的延时多久执行
	public long expirationMs;
	public TimerTaskEntry next = null;
	public TimerTaskEntry prev = null;
	public TimerTaskList list = null;

	public TimerTaskEntry(TimerTask timerTask, long expirationMs) {
		this.timerTask = timerTask;
		this.expirationMs = expirationMs;
	}

	public boolean cancelled() {
		return timerTask.getTimerTaskEntry() != this;
	}

	public void remove() {
		TimerTaskList currentList = list;
		while (currentList != null) {
			currentList.remove(this);
			list = currentList;
		}
	}

	@Override
	public int compareTo(TimerTaskEntry that) {
		if (this == that || this.expirationMs == that.expirationMs) {
			return 0;
		}
		return this.expirationMs > that.expirationMs ? 1 : -1;
	}

}
