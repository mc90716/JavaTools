package com.motan.filter.filter;

import com.motan.filter.caller.Caller;

public class AccessLogFilter implements Filter {

	@Override
	public String filter(Caller caller, String request) {
		System.out.println("Before AccessLogFilter");
		String response = caller.call(request);
		System.out.println("After AccessLogFilter");
		return response;
	}

}
