package com.antlr4.impl.cmd.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.antlr4.impl.cmd.abs.AbstractCmd;
import com.antlr4.source.DplParser.Change_dplContext;

public class ChangeCmd extends AbstractCmd {

    List<JSONObject> result = new ArrayList<JSONObject>();
    
    @Override
    public List<JSONObject> getResult() {
        return result;
    }

    @Override
    public Object visitChange_dpl(Change_dplContext ctx) {
        JSONObject jo = new JSONObject();
        String cmd = ctx.CHANGE().getText();
        jo.put("cmd", cmd);
        result.add(jo);
        return null;
    }

}
