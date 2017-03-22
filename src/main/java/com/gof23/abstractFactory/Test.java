package com.gof23.abstractFactory;

/**
 * 抽象工厂模式的测试
 * 系列产品（交通工具，食物...）
 */
public class Test {
	public static void main(String[] args) {
		AbstractFactory f = new SecondFactory();
		f.createVehicle().run();
		f.createFood().eat();
	}
}