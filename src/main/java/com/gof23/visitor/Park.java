package com.gof23.visitor;

public class Park implements ParkElement {

	private ParkA parkA;
	private ParkB parkB;
	
	public Park(){
		this.parkA = new ParkA();
		this.parkB = new ParkB();
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		parkA.accept(visitor);
		parkB.accept(visitor);
	}

}
