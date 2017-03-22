package com.gof23.visitor;

/**
 * 公园每一部分的抽象
 * 定义一个accept操作，接收访问者
 *
 */
public interface ParkElement {
	
	public void accept(Visitor visitor);
}
