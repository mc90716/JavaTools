package com.guava.limit;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {
	
	/**
	 * 1秒钟最多允许1000次访问
	 * @throws ExecutionException
	 */
	private static void limit() throws ExecutionException {
		LoadingCache<Long, AtomicLong> counter =
		        CacheBuilder.newBuilder()
		                .expireAfterWrite(2, TimeUnit.SECONDS)
		                .build(new CacheLoader<Long, AtomicLong>() {
		                    @Override
		                    public AtomicLong load(Long seconds) throws Exception {
		                        return new AtomicLong(0);
		                    }
		                });
		long limit = 1000;
		while(true) {
		    //得到当前秒
		    long currentSeconds = System.currentTimeMillis() / 1000;
		    if(counter.get(currentSeconds).incrementAndGet() > limit) {
		        System.out.println("限流了:" + currentSeconds);
		        continue;
		    }
		    //业务处理
		}
	}
	
	public static void main(String[] args) {
//		CountDownLatch
//		ReentrantReadWriteLock
		RateLimiter limiter = RateLimiter.create(100);
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
	}
}
