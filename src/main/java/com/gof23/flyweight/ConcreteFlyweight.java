package com.gof23.flyweight;

/**
 * 继承Flyweight超类，并为内部状态增加存储空间
 *
 */
public class ConcreteFlyweight extends Flyweight {
	
	private String string;  
	public ConcreteFlyweight(String str){  
		string = str;  
	}

	@Override
	public void operation() {
		System.out.println("ConcreteFlyweight : " + string);
	}
}
