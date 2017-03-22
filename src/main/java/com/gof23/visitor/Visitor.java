package com.gof23.visitor;

/**
 * 访问者
 * 为该对象结构的ConcreteElement的每一个类(这里指Park,ParkA,ParkB)声明一个Visitor操作
 *
 */
public interface Visitor {

	public void visit(Park park);
	public void visit(ParkA parkA);
	public void visit(ParkB parkB);
}
