package com.tomcat.pipeline.valve;

public class BasicValve extends ValveBase{

	@Override
	public void invoke(String line) {
		System.out.println("End==>" + line);
	}
}
