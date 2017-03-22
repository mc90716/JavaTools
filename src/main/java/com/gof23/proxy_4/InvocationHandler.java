package com.gof23.proxy_4;

import java.lang.reflect.Method;

/**
 * 处理器接口
 *
 */
public interface InvocationHandler {
	
	/**
	 * 
	 * @param o  代理对象
	 * @param m  
	 */
	public void invoke(Object o, Method m);
}
