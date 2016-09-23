package com.antlr4.impl.cmd.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.antlr4.impl.cmd.abs.AbstractCmd;
import com.antlr4.source.DplParser.Limit_dplContext;

public class LimitCmd extends AbstractCmd {

    List<JSONObject> result = new ArrayList<JSONObject>();

    @Override
    public List<JSONObject> getResult() {
        // TODO Auto-generated method stub
        return result;
    }

    @Override
    public Object visitLimit_dpl(Limit_dplContext ctx) {
        // TODO Auto-generated method stub
        JSONObject jo = new JSONObject();
        String cmd = ctx.LIMIT().getText();
        int num = Integer.parseInt(ctx.LONG().getText());
        jo.put("cmd", cmd);
        jo.put("num", num);
        result.add(jo);
        return null;
    }
}
