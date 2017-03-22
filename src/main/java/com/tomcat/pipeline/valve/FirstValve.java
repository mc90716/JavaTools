package com.tomcat.pipeline.valve;

public class FirstValve extends ValveBase{

	@Override
	public void invoke(String line) {
		System.out.println("I am The First Valve==>" + line);
		this.getNext().invoke(line);
	}
}
