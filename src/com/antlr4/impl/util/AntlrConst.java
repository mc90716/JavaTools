package com.antlr4.impl.util;

import java.util.HashMap;
import java.util.Map;

import com.antlr4.impl.cmd.abs.AbstractCmd;

public class AntlrConst {

    public static AbstractCmd getCmdFunction(String cmd) throws Exception {
        AbstractCmd dplCmd = null;

        if (cmdMap.containsKey(cmd)) {
            dplCmd = cmdMap.get(cmd).getConstructor().newInstance();
        } else {
            throw new Exception(cmd + " command do not support");
        }
        return dplCmd;
    }

    private static final Map<String, Class<AbstractCmd>> cmdMap = new HashMap<String, Class<AbstractCmd>>() {
        private static final long serialVersionUID = 1L;

        /**
         * 通过类名，
         * 获取ApiOpInterface接口类对应的实例
         * 
         * @param className
         * @return
         */
        @SuppressWarnings("unchecked")
        public Class<AbstractCmd> getApiClass(
                String className) {
            try {
                return (Class<AbstractCmd>) Class
                        .forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        {
            {
                put("stats", getApiClass("com.antlr4.impl.cmd.imp.StatsCmd"));
                put("rename", getApiClass("com.antlr4.impl.cmd.imp.RenameCmd"));
                put("where", getApiClass("com.antlr4.impl.cmd.imp.WhereCmd"));
                put("eval", getApiClass("com.antlr4.impl.cmd.imp.EvalCmd"));
                put("timechart", getApiClass("com.antlr4.impl.cmd.imp.StatsCmd"));
                put("table", getApiClass("com.antlr4.impl.cmd.imp.TableCmd"));
                put("sort", getApiClass("com.antlr4.impl.cmd.imp.SortCmd"));
                put("limit", getApiClass("com.antlr4.impl.cmd.imp.LimitCmd"));
                put("statistic", getApiClass("com.antlr4.impl.cmd.imp.StatisticCmd"));
            }
        }
    };
}
