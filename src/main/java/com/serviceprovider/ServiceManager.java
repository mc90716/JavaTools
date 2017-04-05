package com.serviceprovider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理者
 *
 */
public class ServiceManager {
	
	private ServiceManager(){}
	
	//把服务接口的名字映射到Service中
	private static final Map<String, Provider> providers = new ConcurrentHashMap<String, Provider>();
	
	public static final String DEFAULT_PROVIDER_NAME = "DEFAULT_SERVICE";
	
	public static void registerDefaultProvider(Provider p) {
		registerProvider(DEFAULT_PROVIDER_NAME, p);
	}
	
	public static void registerProvider(String name, Provider p) {
		providers.put(name, p);
	}
	
	public static Service newInstance() {
		return newInstance(DEFAULT_PROVIDER_NAME);
	}
	
	public static Service newInstance(String name) {
		Provider p = providers.get(name);
		if(p == null) {
			throw new IllegalArgumentException("No Provider Registered for Name [" + name + "]");
		}
		return p.newService();
	}
}
