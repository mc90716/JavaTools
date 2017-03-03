package com.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class StackTraceUtil {

	/**
	 * 把异常转化为字符串返回
	 * @param t
	 * @return
	 */
    public static String stackTrace(Throwable t) {
        if (t == null) {
            return "null";
        }

        PrintWriter pw = null;
        try {
            StringWriter sw = new StringWriter();
            pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    private StackTraceUtil() {}
}
