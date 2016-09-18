package com.concurrent.thread;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * fork es <ThreadPool>  
 * 封装了线程池，用于多种线程池的管理
 * @author machao
 *
 */
public class ThreadPool {

	public static class Named {
		public static final String common = "COMMON";
		public static final String index = "INDEX";
		public static final String format = "FOTMAT";
		public static final String read = "READ";
	}

	public static final String THREADPOOL_GROUP = "threadpool.";
	private final ScheduledThreadPoolExecutor scheduler;
	private final Map<String, ExecutorHolder> executorHolderMap = new ConcurrentHashMap<String, ExecutorHolder>();
	private final Map<String, Info> executorInfoMap = new ConcurrentHashMap<String, Info>();

	public ThreadPool(Properties prop) {
		executorInfoMap.put(Named.index, builderInfo().setName("common").setType("fixed").setMinThread(20).setMaxThread(20));
		executorInfoMap.put(Named.index, builderInfo().setName("index").setType("fixed").setMinThread(5).setMaxThread(5));
		executorInfoMap.put(Named.format, builderInfo().setName("format").setType("fixed").setMinThread(5).setMaxThread(5));
		executorInfoMap.put(Named.read, builderInfo().setName("read").setType("fixed").setMinThread(5).setMaxThread(5));
		
		for(Entry<String, Info> entry : executorInfoMap.entrySet()) {
			String name = entry.getKey();
			Info info = entry.getValue();
			Executor exexutor = Executors.newFixedThreadPool(info.getMinThread(), new NamedThreadFactory(name));
			ExecutorHolder executorHolder = new ExecutorHolder(exexutor, info);
			executorHolderMap.put(name, executorHolder);
		}
		
		this.scheduler = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("scheduler"));
	}

	public Executor executor(String name) throws Exception {
		Executor executor = executorHolderMap.get(name).executor();
		if(executor == null) {
			throw new Exception("No executor found for [" + name + "]");
		}
		return executor;
	}
	
    public ScheduledExecutorService scheduler() {
        return this.scheduler;
    }
    
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long interval) {
        return scheduler.scheduleWithFixedDelay(command, interval, interval, TimeUnit.MILLISECONDS);
    }

    public ScheduledFuture<?> schedule(long delay, String name, Runnable command) {
        return scheduler.schedule(new NamedThreadFactory(name).newThread(command), delay, TimeUnit.MILLISECONDS);
    }
	
    public void shutdown() {
    	scheduler.shutdown();
    	for(Entry<String, ExecutorHolder> entry : executorHolderMap.entrySet()) {
			ExecutorHolder executorHolder = entry.getValue();
			if (executorHolder.executor() instanceof ThreadPoolExecutor) {
                ((ThreadPoolExecutor) executorHolder.executor()).shutdown();
            }
		}
    }
    
    public void shutdownNow() {
    	scheduler.shutdownNow();
    	for(Entry<String, ExecutorHolder> entry : executorHolderMap.entrySet()) {
			ExecutorHolder executorHolder = entry.getValue();
			if (executorHolder.executor() instanceof ThreadPoolExecutor) {
                ((ThreadPoolExecutor) executorHolder.executor()).shutdownNow();
            }
		}
    }
    
	private static class ExecutorHolder {
		private Executor executor;
		private Info info;

		public ExecutorHolder(Executor executor, Info info) {
			this.executor = executor;
			this.info = info;
		}

		public Executor executor() {
			return executor;
		}
		
		public Info info() {
			return info;
		}
	}

	private Info builderInfo() {
		return new Info();
	}
	
	private static class Info {
		private String name;
		private String type;
		private int minThread;
		private int maxThread;

		public int getMinThread() {
			return minThread;
		}

		public Info setMinThread(int minThread) {
			this.minThread = minThread;
			return this;
		}

		public int getMaxThread() {
			return maxThread;
		}

		public Info setMaxThread(int maxThread) {
			this.maxThread = maxThread;
			return this;
		}

		public Info() {
		}

		public Info(String name, String type) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public Info setName(String name) {
			this.name = name;
			return this;
		}

		public String getType() {
			return type;
		}

		public Info setType(String type) {
			this.type = type;
			return this;
		}

	}
}
