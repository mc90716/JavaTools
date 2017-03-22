package com.gof23.visitor;

/**
 * 公园的B部分
 *
 */
public class ParkB implements ParkElement {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
