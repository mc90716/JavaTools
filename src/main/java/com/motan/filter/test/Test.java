package com.motan.filter.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.WeakHashMap;

import com.motan.filter.filter.AccessLogFilter;
import com.motan.filter.filter.ActiveLimitFilter;
import com.motan.filter.filter.Filter;
import com.motan.filter.provider.DefaultProvider;
import com.motan.filter.provider.Provider;

public class Test {
	public static void main(String[] args) {
		List<Filter> filterList = new ArrayList<>();
		filterList.add(new AccessLogFilter());
		filterList.add(new ActiveLimitFilter());
		Provider lastProvider = new DefaultProvider();
		for(Filter filter : filterList) {
			final Filter f = filter;
			final Provider lp = lastProvider;
			lastProvider = new Provider() {
				@Override
				public String call(String request) {
					return f.filter(lp, request);
				}
			};
		}
		System.out.println(lastProvider.call("call chain"));
//		HashMap<K, V>
//		LinkedHashMap<K, V>
//		WeakHashMap<K, V>
	}
}
