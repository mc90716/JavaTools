package com.tomcat.pipeline.pipeline;

import com.tomcat.pipeline.valve.Valve;

public interface Pipeline {
	
	public void setBasic(Valve valve);
	
	public void addNext(Valve next);
	
	public Valve getFirst();
	
	//public Valve[] getValves();
	
	//public void removeValve(Valve valve);
}
