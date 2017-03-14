package com.motan.filter.filter;

import com.motan.filter.caller.Caller;

public interface Filter {
	String filter(Caller caller, String request);
}
