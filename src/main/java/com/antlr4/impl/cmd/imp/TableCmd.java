package com.antlr4.impl.cmd.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.antlr.v4.runtime.tree.TerminalNode;

import com.antlr4.impl.cmd.abs.AbstractCmd;
import com.antlr4.source.DplParser.Table_dplContext;

public class TableCmd extends AbstractCmd{

	private List<JSONObject> list = new ArrayList<JSONObject>();
	private JSONObject result = new JSONObject();
	
	@Override
	public List<JSONObject> getResult() {
		// TODO Auto-generated method stub
		list.add(result);
		return list;
	}

	@Override
	public Object visitTable_dpl(Table_dplContext ctx) {
		// TODO Auto-generated method stub
		result.put("cmd", "table");
		List<String> ids = new ArrayList<String>();
		for(TerminalNode s : ctx.ID()){
			ids.add(s.getText());
		}
		result.put("ids", ids);
		return null;
	}
}
