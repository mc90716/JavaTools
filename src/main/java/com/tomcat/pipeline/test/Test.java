package com.tomcat.pipeline.test;

import java.util.HashMap;

import com.tomcat.pipeline.pipeline.StandardPipeline;
import com.tomcat.pipeline.valve.BasicValve;
import com.tomcat.pipeline.valve.Valve;

public class Test {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		StandardPipeline pipeline = new StandardPipeline();
		pipeline.addNext(registerMap.get("First").newInstance());
		pipeline.addNext(registerMap.get("Second").newInstance());
		pipeline.setBasic(new BasicValve());
		pipeline.run();
	}
	
	public static HashMap<String,Class<Valve>> registerMap = new HashMap<String,Class<Valve>>(){
		
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("unchecked")
		public Class<Valve> getClassApi(String className) {
			try {
				return (Class<Valve>)Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		{
			put("First",getClassApi("com.tomcat.pipeline.FirstValve"));
			put("Second",getClassApi("com.tomcat.pipeline.SecondValve"));
		}
	};
}
