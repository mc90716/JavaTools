package com.gof23.proxy_1;

/**
 * 被代理类
 *
 */
public class Tank implements Moveable{

	@Override
	public void move() {
		System.out.println("坦克正在开了...");
	}
}
