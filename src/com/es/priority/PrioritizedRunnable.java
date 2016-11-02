package com.es.priority;

/**
 * 
 * @author 云袭
 *
 */
public abstract class PrioritizedRunnable implements Runnable, Comparable<PrioritizedRunnable> {
	private final Priority priority;
	
	/**
     * 此处使用静态方法是因为该类时抽象类，不能实例化调用，因此通过使用静态方法就可以不用实例化而进行调用
     * 方法使用static修饰，所以Wrapped类也必须使用static修饰
     * @param runnable
     * @param priority
     * @return
     */
	public static PrioritizedRunnable wrap(Priority priority, Runnable runnable) {
		return new Wrapped(priority, runnable);
	}
	
	protected PrioritizedRunnable(Priority priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(PrioritizedRunnable o) {
		if(this == o) {
			return 0;
		}
		return this.priority.compareTo(o.priority);
	}
	
	public Priority priority() {
		return this.priority;
	}
	
	static class Wrapped extends PrioritizedRunnable {
		private final Runnable runnable;
		protected Wrapped(Priority priority, Runnable runnable) {
			super(priority);
			this.runnable = runnable;
		}

		@Override
		public void run() {
			this.runnable.run();
		}
		
	}
}
