package com.tomcat.pipeline.valve;

public abstract class ValveBase implements Valve {

	public Valve next = null;
	
	@Override
	public void setNext(Valve valve) {
		this.next = valve;
	}

	@Override
	public Valve getNext() {
		return next;
	}
}
