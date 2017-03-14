package com.motan.filter.provider;

public abstract class AbstractProvider implements Provider {

	@Override
	public String call(String request) {
		return invoke(request);
	}

	protected abstract String invoke(String request);
	
}
