package com.gof23.flyweight;

/**
 * 享元模式的测试
 * 主要目的是实现对象的共享，即共享池，当系统中对象多的时候可以减少内存的开销
 */
public class Test {

	FlyweightFactory factory = new FlyweightFactory();   
	Flyweight fly1;  
	Flyweight fly2;  
	Flyweight fly3;    

	public Test(){  
		fly1 = factory.getFlyWeight("obj1");  
		fly2 = factory.getFlyWeight("obj2");  
		fly3 = factory.getFlyWeight("obj1");    
	}  
	
	public void showFlyweight(){  
		fly1.operation();  
		fly2.operation();  
		fly3.operation();   
		int objSize = factory.getFlyweightSize();  
		System.out.println("objSize = " + objSize);  
	}  
	
	public static void main(String[] args){   
		/*
		 * 定义了3个对象，其中有2个是相同的，按照享元模式的定义“obj1”应该共享一个对象，
		 * 在实际的对象数中我们可以看出实际的对象却是只有2个
		 */
		Test test = new Test();  
		test.showFlyweight();  
	}
}
