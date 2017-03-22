package com.gof23.visitor;

/**
 * 具体访问者
 * 实现每个由Visitor声明的操作，每个操作实现算法的一部分，而该算法片段是应对于结构中对象的类
 *
 */
public class VisitorManager implements Visitor {

	@Override
	public void visit(Park park) {
		System.out.println("管理员负责公园卫生检查");
	}

	@Override
	public void visit(ParkA parkA) {
		System.out.println("管理员负责公园A部分卫生检查");
	}

	@Override
	public void visit(ParkB parkB) {
		System.out.println("管理员负责公园B部分卫生检查");
	}

}
