package com.gof23.flyweight;

/**
 * 所有具体享元类的超类
 * 通过这个超类，Flyweight可以接受并作用于外部状态
 *
 */
public abstract class Flyweight {
	
	public abstract void operation();
}
