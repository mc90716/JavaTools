package com.motan.filter.filter;

import com.motan.filter.caller.Caller;

public class ActiveLimitFilter implements Filter {

	@Override
	public String filter(Caller caller, String request) {
		System.out.println("Before ActiveLimitFilter");
		String response = caller.call(request);
		System.out.println("After ActiveLimitFilter");
		return response;
	}

}
