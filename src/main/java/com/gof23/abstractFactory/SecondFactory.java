package com.gof23.abstractFactory;

/**
 * 第二个具体工厂
 * 相当于ConcreteFactory1
 * 生产“飞机”和“水”
 *
 */
public class SecondFactory extends AbstractFactory{

	@Override
	public Vehicle createVehicle() {
		return new Plane();
	}

	@Override
	public Food createFood() {
		return new Water();
	}

}
