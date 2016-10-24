package com.kafka.throttler;

import java.util.Random;

public class Throttler {
	private String metricName = "throttler";
	private String units = "entries";
	private Object lock = new Object();
	private long periodStarNs = Time.nanoseconds();
	private double observedSoFar = 0.0;
	private double desiredRatePerSec;
	private long checkIntervalMs;
	private boolean throttleDown;

	/**
	 * 
	 * @param desiredRatePerSec   希望每秒的速度
	 * @param checkIntervalMs	检查的时间间隔，毫秒值
	 * @param throttleDown
	 */
	public Throttler(double desiredRatePerSec, long checkIntervalMs,
			boolean throttleDown) {
		this.desiredRatePerSec = desiredRatePerSec;
		this.checkIntervalMs = checkIntervalMs;
		this.throttleDown = throttleDown;
	}

	public void maybeThrottle(double observed) {
		synchronized (lock) {
			this.observedSoFar += observed;
			long now = Time.nanoseconds();
			long elapsedNs = now - periodStarNs;
			//如果流逝的时间大于检查速度的时间间隔，并且到目前为止观察到的值大于0
			if (elapsedNs > checkIntervalMs * Time.NsPerMs && observedSoFar > 0) {
				//算出每秒的速度
				double rateInSecs = (observedSoFar * Time.NsPerSec) / elapsedNs;
				//每秒的速度大于希望的速度了，也就是说超速了，那么就需要调整速度了
				boolean needAdjustment = !(throttleDown ^ (rateInSecs > desiredRatePerSec));
				if (needAdjustment) {
					//算出每毫秒的速度
					double desiredRateMs = desiredRatePerSec / Time.MsPerSec;
					//流逝的毫秒值
					long elapsedMs = elapsedNs / Time.NsPerMs;
					//睡眠时间的计算公式：当前观察到的累计值/每毫秒的速度-流逝的毫秒
					int sleepTime = (int) (observedSoFar / desiredRateMs - elapsedMs);
					if (sleepTime > 0) {
						System.out.println("Sleep [" + sleepTime + "],The ");
						Time.sleep(sleepTime);
					}
				}
				periodStarNs = now;
				observedSoFar = 0;
			}
		}
	}

	private static class Time {
		public static long NsPerUs = 1000;
		public static long UsPerMs = 1000;
		public static long MsPerSec = 1000;
		public static long NsPerMs = NsPerUs * UsPerMs;
		public static long NsPerSec = NsPerMs * MsPerSec;
		public static long UsPerSec = UsPerMs * MsPerSec;
		public static long SecsPerMin = 60;
		public static long MinsPerHour = 60;
		public static long HoursPerDay = 24;
		public static long SecsPerHour = SecsPerMin * MinsPerHour;
		public static long SecsPerDay = SecsPerHour * HoursPerDay;
		public static long MinsPerDay = MinsPerHour * HoursPerDay;

		public static long millisecond() {
			return System.currentTimeMillis();
		}

		private static long nanoseconds() {
			return System.nanoTime();
		}

		private static void sleep(long ms) {
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Random rand = new Random();
		Throttler throttler = new Throttler(100000, 100, true);
		int interval = 30000;
		long start = System.currentTimeMillis();
		int total = 0;
		while (true) {
			int value = rand.nextInt(1000);
			Thread.sleep(1);
			throttler.maybeThrottle(value);
			total += value;
			long now = System.currentTimeMillis();
			if (now - start >= interval) {
				System.out.println(total / (interval / 1000.0));
				start = now;
				total = 0;
			}
		}
	}
}
