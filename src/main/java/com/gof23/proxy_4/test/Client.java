package com.gof23.proxy_4.test;

import com.gof23.proxy_4.InvocationHandler;
import com.gof23.proxy_4.Proxy;

/**
 * 动态代理模式的测试_4 (可以对任意的对象、任意的接口方法，实现任意的代理)
 * (目前只能实现一层代理，还没完成叠加的代理)
 * （在静态代理中，代理对象是要写出来的；而在动态代理中，不用知道代理对象的名字，要的那个代理对象直接帮你产生）
 */
public class Client {
	public static void main(String[] args) throws Exception {
		UserMgr mgr = new UserMgrImpl();  // 被代理对象
		InvocationHandler h = new TransactionHandler(mgr);  // 事务上的代理
		//InvocationHandler h = new TimeHandler(mgr);  // 时间上的代理
		UserMgr u = (UserMgr)Proxy.newProxyInstance(UserMgr.class, h);
		u.addUser();
		
//      生成的$proxy1.java在com.hin.proxy_4包中		
	}
}
