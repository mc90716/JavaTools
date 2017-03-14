package com.motan.filter.provider;

public class DefaultProvider extends AbstractProvider {

	@Override
	protected String invoke(String request) {
		return "Convert Request To Response";
	}

}
