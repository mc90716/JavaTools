package com.antlr4.impl.cmd.abs;

import java.util.List;

import net.sf.json.JSONObject;

import com.antlr4.source.DplBaseVisitor;

public abstract class AbstractCmd extends DplBaseVisitor<Object>{

	protected static final String FUNCS = "funcs";
	protected static final String NEWNAME = "newname";
	protected static final String TYPE = "type";
	protected static final String RETURN = "return";
	
	protected static class Type{
		public static final String NUMBER = "number";
		public static final String STRING = "string";
		public static final String BOOLEAN = "boolean";
	}
	
	public abstract List<JSONObject> getResult();
}
