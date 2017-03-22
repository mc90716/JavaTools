package com.tomcat.pipeline.valve;

public class SecondValve extends ValveBase{

	@Override
	public void invoke(String line) {
		System.out.println("I am The Second Valve==>" + line);
		this.getNext().invoke(line);
	}
}
