package com.antlr4.impl.cmd.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.antlr4.impl.cmd.abs.AbstractCmd;
import com.antlr4.source.DplParser.Statistic_dplContext;

public class StatisticCmd extends AbstractCmd {

    List<JSONObject> result = new ArrayList<JSONObject>();

    @Override
    public List<JSONObject> getResult() {
        // TODO Auto-generated method stub
        return result;
    }

    @Override
    public Object visitStatistic_dpl(Statistic_dplContext ctx) {
        // TODO Auto-generated method stub
        JSONObject jo = new JSONObject();
        String cmd = ctx.STATISTIC().getText();
        jo.put("cmd", cmd);
        result.add(jo);
        return null;
    }

}