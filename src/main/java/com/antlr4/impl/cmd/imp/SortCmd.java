package com.antlr4.impl.cmd.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.antlr4.impl.cmd.abs.AbstractCmd;
import com.antlr4.source.DplParser;
import com.antlr4.source.DplParser.Sort_dplContext;

public class SortCmd extends AbstractCmd{

	List<JSONObject> result = new ArrayList<JSONObject>();
	
	@Override
	public List<JSONObject> getResult() {
		// TODO Auto-generated method stub
		return result;
	}
	
	@Override
	public Object visitSort_dpl(Sort_dplContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		String cmd = ctx.SORT().getText();
		if(ctx.op.getType() == DplParser.ADD){
			jo.put("op", "+");
		} else if(ctx.op.getType() == DplParser.SUB){
			jo.put("op", "-");
		}
		String field = ctx.ID().getText();
		jo.put("cmd", cmd);
		jo.put("field", field);
		result.add(jo);
		return null;
	}
}
