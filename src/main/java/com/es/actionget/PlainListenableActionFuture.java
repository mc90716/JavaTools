package com.es.actionget;

public class PlainListenableActionFuture<T> extends AdapterActionFuture<T, T> {

	@Override
	protected T convert(T response) {
		return response;
	}

}
