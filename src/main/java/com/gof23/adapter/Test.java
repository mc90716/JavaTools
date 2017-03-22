package com.gof23.adapter;

/**
 * 适配器模式的测试
 * 核心思想就是：有一个Adapee类，拥有一个方法，待适配，目标接口是Target，通过Adapter类，将Adapee的功能扩展到Target里
 * 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式
 */
public class Test {

	public static void main(String[] args) {
		Target target = new Adapter();  
        target.method1();  
        target.method2();
	}
}
