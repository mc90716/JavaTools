package com.gof23.proxy_2;

/**
 * 动态代理模式的测试_1 (只能对指定的对象，指定的接口方法，实现指定的代理；在Proxy中生成指定的接口的代理对象)
 * (在静态代理中，代理对象是要写出来的；而在动态代理中，不用知道代理对象的名字，要的那个代理对象直接帮你产生)
 */
public class Client {
	public static void main(String[] args) throws Exception {
		//Tank t = new Tank();
		Moveable m = (Moveable)Proxy.newProxyInstance();
		m.move();
	}
}
