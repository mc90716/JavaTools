package com.antlr4.impl.cmd;

import java.util.List;

import net.sf.json.JSONObject;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.antlr4.impl.cmd.abs.AbstractCmd;
import com.antlr4.impl.util.AntlrConst;
import com.antlr4.source.DplLexer;
import com.antlr4.source.DplParser;

public class PipingParser {

	public static List<JSONObject> getPiping(String s) throws Exception {
		ANTLRInputStream input = new ANTLRInputStream(s);
    	DplLexer lexer = new DplLexer(input);
    	CommonTokenStream tokens = new CommonTokenStream(lexer);
    	DplParser parser = new DplParser(tokens);
    	ParseTree tree = parser.dpl(); // parse
    	
    	String cmd = s.split(" ")[0];
    	AbstractCmd ac = null;
    	ac = AntlrConst.getCmdFunction(cmd);
		ac.visit(tree);
    	return ac.getResult();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getPiping("eval n = iplocation(ip, \"city\")").toString());
	}
}
