package com.es.actionget;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public interface ActionFuture<T> extends Future<T>{
	T actionGet();
	T actionGet(long timeoutMillis);
	T actionGet(long timeout, TimeUnit unit); 
}
