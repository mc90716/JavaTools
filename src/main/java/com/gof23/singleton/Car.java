package com.gof23.singleton;

/**
 * 汽车类
 * 只生产一辆汽车
 * 定义一个getInstance方法，只允许访问它的唯一实例
 *
 */
public class Car {
	
	// 静态方法为私有的，不能直接在外面new
	private Car(){}
	
	private static Car car = new Car();  // 单例
	
	// private static List<Car> cars = new ArrayList<Car>();  // 多例(在一个容器中有多辆车)
	
	/**
	 * 静态工厂
	 * @return
	 */
	public static Car getInstance(){
		return car;
	}
}
