package com.antlr4.impl.cmd.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.antlr4.impl.cmd.abs.AbstractCmd;
import com.antlr4.source.DplParser.Rename_dplContext;

public class RenameCmd extends AbstractCmd{

	List<JSONObject> result = new ArrayList<JSONObject>();
	@Override
	public List<JSONObject> getResult() {
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public String visitRename_dpl(Rename_dplContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		String cmd = ctx.RENAME().getText();
		String oldname = ctx.ID(0).getText();
		String newname = ctx.ID(1).getText();
		jo.put("cmd", cmd);
		jo.put("oldname", oldname);
		jo.put("newname", newname);
		result.add(jo);
		return null;
	}
}
