package com.tomcat.pipeline.valve;

public interface Valve {
	
	public void setNext(Valve valve);
	
	public Valve getNext();
	
	public void invoke(String line);
	
}
