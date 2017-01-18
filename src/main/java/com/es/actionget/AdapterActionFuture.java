package com.es.actionget;

import java.util.concurrent.TimeUnit;

public abstract class AdapterActionFuture<T, L> extends BaseFuture<T> 
		implements ActionFuture<T>, ActionListener<L> {

	public AdapterActionFuture() {
	}
	
	@Override
	public T actionGet() {
		try {
			return get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T actionGet(long timeoutMillis) {
		return actionGet(timeoutMillis, TimeUnit.MILLISECONDS);
	}

	@Override
	public T actionGet(long timeout, TimeUnit unit) {
		try {
			return get(timeout, unit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void onResponse(L result) {
		set(convert(result));
	}

	protected abstract T convert(L listenerResponse);

	@Override
	public void onFailure(Throwable e) {
		setException(e);
	}
}
