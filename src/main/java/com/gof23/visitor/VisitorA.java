package com.gof23.visitor;

/**
 * 具体访问者
 * 实现每个由Visitor声明的操作，每个操作实现算法的一部分，而该算法片段是应对于结构中对象的类
 *
 */
public class VisitorA implements Visitor {

	@Override
	public void visit(Park park) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ParkA parkA) {
		System.out.println("清洁工A完成公园A部分的卫生");
	}

	@Override
	public void visit(ParkB parkB) {
		// TODO Auto-generated method stub

	}

}
