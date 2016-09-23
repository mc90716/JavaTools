package com.antlr4.impl.cmd.imp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.antlr.v4.runtime.tree.TerminalNode;

import com.antlr4.impl.cmd.abs.AbstractCmd;
import com.antlr4.source.DplParser;
import com.antlr4.source.DplParser.Boolean_expr_dpl_containsContext;
import com.antlr4.source.DplParser.Boolean_expr_dpl_opContext;
import com.antlr4.source.DplParser.ByfContext;
import com.antlr4.source.DplParser.Capslk_expContext;
import com.antlr4.source.DplParser.Contains_expContext;
import com.antlr4.source.DplParser.Count_eval_booleanContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_capslkContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_idContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_ifContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_iplocationContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_jsonparserContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_lenContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_numberContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_op_addsubContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_op_muldivContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_op_powerContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_powContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_regexContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_replaceContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_roundContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_splitContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_stringContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_substrContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_trimContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_uaparserContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_urldecodeContext;
import com.antlr4.source.DplParser.Eval_func_expContext;
import com.antlr4.source.DplParser.If_expContext;
import com.antlr4.source.DplParser.Iplocation_expContext;
import com.antlr4.source.DplParser.Jsonparser_expContext;
import com.antlr4.source.DplParser.Len_expContext;
import com.antlr4.source.DplParser.Pow_expContext;
import com.antlr4.source.DplParser.Regex_expContext;
import com.antlr4.source.DplParser.Rename_dplContext;
import com.antlr4.source.DplParser.Replace_expContext;
import com.antlr4.source.DplParser.Round_expContext;
import com.antlr4.source.DplParser.Split_expContext;
import com.antlr4.source.DplParser.Stats_dplContext;
import com.antlr4.source.DplParser.Stats_exp_dpl_evalContext;
import com.antlr4.source.DplParser.Stats_exp_dpl_statsContext;
import com.antlr4.source.DplParser.Stats_funcContext;
import com.antlr4.source.DplParser.Stats_func_expContext;
import com.antlr4.source.DplParser.Substr_expContext;
import com.antlr4.source.DplParser.Trim_expContext;
import com.antlr4.source.DplParser.Uaparser_expContext;
import com.antlr4.source.DplParser.Urldecode_expContext;

public class StatsCmd extends AbstractCmd{

	List<JSONObject> result = new ArrayList<JSONObject>();
	List<JSONObject> pre = new ArrayList<JSONObject>();
	List<JSONObject> later = new ArrayList<JSONObject>();
	JSONObject preJo = new JSONObject();
	JSONObject coreJo = new JSONObject();
	JSONObject laterJo = new JSONObject();
	JSONObject memory = new JSONObject();
	// the info of last eval func before stats 
//	JSONObject before_stats_info = new JSONObject();
	String cmd = null;
	String stage = null;
	String memory_name = null;
	
	@Override
	public List<JSONObject> getResult(){
		if(! pre.isEmpty()){
			result.addAll(pre);
		}
		result.add(coreJo);
		if(! later.isEmpty()){
			result.addAll(later);
		}
		
		return result;
	}
	
	@Override
	public String visitLen_exp(Len_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.LEN().getText());
		jo.put("field", field);
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.NUMBER);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_trim(Eval_expr_dpl_trimContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_trim(ctx);
	}

	@Override
	public Object visitUrldecode_exp(Urldecode_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.URLDECODE().getText());
		jo.put("field", field);
		if(ctx.STRING() != null){
			jo.put("code", ctx.STRING().getText());
		}
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitCapslk_exp(Capslk_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.CAPSLKFUNC().getText());
		jo.put("field", field);
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
//			preJo.accumulate(FUNCS, jo);
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitTrim_exp(Trim_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.TRIMFUNC().getText());
		jo.put("field", field);
		if(ctx.STRING() != null){
			String s = ctx.STRING().getText();
			jo.put("trimchara", s.substring(1, s.length() - 1));
		}
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
//			preJo.accumulate(FUNCS, jo);
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_urldecode(
			Eval_expr_dpl_urldecodeContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_urldecode(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_replace(Eval_expr_dpl_replaceContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_replace(ctx);
	}

	@Override
	public Object visitSplit_exp(Split_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.SPLIT().getText());
		jo.put("field", field);
		String s = ctx.STRING().getText();
		jo.put("splitchara", s.substring(1, s.length() - 1));
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_capslk(Eval_expr_dpl_capslkContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_capslk(ctx);
	}

	@Override
	public Object visitReplace_exp(Replace_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.REPLACE().getText());
		jo.put("field", field);
		String sRegex = ctx.STRING(0).getText();
		String sReplace = ctx.STRING(1).getText();
		jo.put("regex", sRegex.substring(1, sRegex.length() - 1));
		jo.put("replacement", sReplace.substring(1, sReplace.length() - 1));
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
//			preJo.accumulate(FUNCS, jo);
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_split(Eval_expr_dpl_splitContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_split(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_len(Eval_expr_dpl_lenContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_len(ctx);
	}


	@Override
	public Object visitEval_expr_dpl_substr(Eval_expr_dpl_substrContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_substr(ctx);
	}


	@Override
	public Object visitStats_func(Stats_funcContext ctx) {
		// TODO Auto-generated method stub
		return super.visitStats_func(ctx);
	}

	@Override
	public Object visitStats_exp_dpl_stats(Stats_exp_dpl_statsContext ctx) {
		// TODO Auto-generated method stub
		stage = "pre";
		visit(ctx.stats_func_exp());
		if(ctx.ID() != null){
			memory.put(NEWNAME, ctx.ID().getText());
		} 
//		coreJo.accumulate(FUNCS, memory);
		addJA(coreJo, memory, FUNCS);
		memory.clear();
		return null;
	}


	@Override
	public Object visitByf(ByfContext ctx) {
		// TODO Auto-generated method stub
		List<TerminalNode> list = ctx.ID();
		for(TerminalNode tn : list){
			String byf = tn.getText();
//			coreJo.accumulate("byf", byf);
			addJA(coreJo, byf, "byf");
		}
		return super.visitByf(ctx);
	}


	@Override
	public Object visitEval_expr_dpl_round(Eval_expr_dpl_roundContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_round(ctx);
	}


	@Override
	public Object visitStats_func_exp(Stats_func_expContext ctx) {
		// TODO Auto-generated method stub
		String func = ctx.stats_func().getText();
		memory.put("func", func);
		Object field = null;
		if(ctx.eval_func_exp() != null){
			field = visit(ctx.eval_func_exp());
			memory.put("field", field);
		}else if(ctx.ID() != null){
			field = ctx.ID().getText();
			memory.put("field", field);
		}else if(ctx.count_eval_boolean() != null){
			field = visit(ctx.count_eval_boolean());
			memory.put("field", field);
		}
		memory.put(TYPE, Type.NUMBER);
		String newName = null;
		if(field != null){
			newName = func + "(" + field + ")";
		} else {
			newName = func;
		}
		memory.put(NEWNAME, newName);
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_op_addsub(
			Eval_expr_dpl_op_addsubContext ctx) {
		// TODO Auto-generated method stub
		Object field1 = visit(ctx.eval_func(0));
		Object field2 = visit(ctx.eval_func(1));
		JSONObject jo = new JSONObject();
		jo.put("func", "calc");
		jo.accumulate("field", field1);
		jo.accumulate("field", field2);
		if(ctx.op.getType() == DplParser.ADD){
			jo.put("op", "+");
		} else if(ctx.op.getType() == DplParser.SUB){
			jo.put("op", "-");
		}
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.NUMBER);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}


	@Override
	public Object visitRound_exp(Round_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.ROUND().getText());
		jo.put("field", field);
		jo.put("roundnum", Long.parseLong(ctx.LONG().getText()));
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.NUMBER);
		if(stage.equals("pre")){
//			preJo.accumulate(FUNCS, jo);
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
//			laterJo.accumulate(FUNCS, jo);
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}


	@Override
	public Object visitEval_expr_dpl_op_muldiv(
			Eval_expr_dpl_op_muldivContext ctx) {
		// TODO Auto-generated method stub
		Object field1 = visit(ctx.eval_func(0));
		Object field2 = visit(ctx.eval_func(1));
		JSONObject jo = new JSONObject();
		jo.put("func", "calc");
		jo.accumulate("field", field1);
		jo.accumulate("field", field2);
		if(ctx.op.getType() == DplParser.MUL){
			jo.put("op", "*");
		} else if(ctx.op.getType() == DplParser.DIV){
			jo.put("op", "/");
		}
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.NUMBER);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}


	@Override
	public Object visitEval_expr_dpl_op_power(Eval_expr_dpl_op_powerContext ctx) {
		// TODO Auto-generated method stub
		Object field1 = visit(ctx.eval_func(0));
		Object field2 = visit(ctx.eval_func(1));
		JSONObject jo = new JSONObject();
		jo.put("func", "calc");
		jo.accumulate("field", field1);
		jo.accumulate("field", field2);
		if(ctx.op.getType() == DplParser.POWER){
			jo.put("op", "^");
		}
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.NUMBER);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}


//	@Override
//	public Object visitEval_expr_dpl_stats_func(
//			Eval_expr_dpl_stats_funcContext ctx) {
//		// TODO Auto-generated method stub
//		Object newName = visit(ctx.stats_func_exp());
//		stage = "later";
//		return newName;
//	}


	@Override
	public Object visitSubstr_exp(Substr_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.SUBSTR().getText());
		jo.put("field", field);
		jo.put("startnum", Long.parseLong(ctx.LONG(0).getText()));
		if(ctx.LONG().size() == 2){
			jo.put("endnum", Long.parseLong(ctx.LONG(1).getText()));
		}
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public String visitStats_exp_dpl_eval(Stats_exp_dpl_evalContext ctx) {
		// TODO Auto-generated method stub
		stage = "pre";
		visit(ctx.eval_func_exp());
		if(ctx.ID() != null){
			memory.put(NEWNAME, ctx.ID().getText());
		}
		addJA(coreJo, memory, FUNCS);
		memory.clear();
		return null;
	}

	@Override
	public String visitEval_expr_dpl_id(Eval_expr_dpl_idContext ctx) {
		// TODO Auto-generated method stub
		return ctx.ID().getText();
	}


	@Override
	public Object visitEval_func_exp(Eval_func_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject newJo = new JSONObject();
		Object newName = visit(ctx.eval_func());
		if(stage.equals("pre")){
			newJo.put("cmd", ctx.EVAL().getText());
			JSONArray funcsJa = preJo.getJSONArray(FUNCS);
			newJo.put(FUNCS, funcsJa);
			JSONObject lastFunc = funcsJa.getJSONObject(funcsJa.size() - 1);
			newJo.put(NEWNAME, lastFunc.getString(NEWNAME));
			newJo.put(TYPE, lastFunc.getString(TYPE));
			newJo.put(RETURN, false);
			pre.add(newJo);
			// add type in before_stats_info
			preJo.clear();
		} else {
			newJo.put("cmd", ctx.EVAL().getText());
			JSONArray funcsJa = preJo.getJSONArray(FUNCS);
			newJo.put(FUNCS, funcsJa);
			JSONObject lastFunc = funcsJa.getJSONObject(funcsJa.size() - 1);
			newJo.put(NEWNAME, lastFunc.getString(NEWNAME));
			newJo.put(TYPE, lastFunc.getString(TYPE));
			later.add(newJo);
			laterJo.clear();
		}
		
		return newName;
	}


	@Override
	public String visitBoolean_expr_dpl_op(Boolean_expr_dpl_opContext ctx) {
		// TODO Auto-generated method stub
		Object field1 = visit(ctx.eval_func(0));
		Object field2 = visit(ctx.eval_func(1));
		JSONObject jo = new JSONObject();
		jo.put("func", "op_compare");
		jo.accumulate("field", field1);
		jo.accumulate("field", field2);
		// op text
		jo.put("op", ctx.op.getText());
		String newName = null;
        try {
            newName = URLEncoder.encode(ctx.getText().toString(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		jo.put(NEWNAME, newName);
		jo.put("type", Type.BOOLEAN);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitCount_eval_boolean(Count_eval_booleanContext ctx) {
		// TODO Auto-generated method stub
		stage = "pre";
		JSONObject newJo = new JSONObject();
		Object newName = visit(ctx.boolean_exp());
		if(stage.equals("pre")){
			newJo.put("cmd", ctx.EVAL().getText());
			JSONArray funcsJa = preJo.getJSONArray(FUNCS);
			newJo.put(FUNCS, funcsJa);
			JSONObject lastFunc = funcsJa.getJSONObject(funcsJa.size() - 1);
			newJo.put(NEWNAME, lastFunc.getString(NEWNAME));
			newJo.put(TYPE, lastFunc.getString(TYPE));
			newJo.put(RETURN, false);
			pre.add(newJo);
			// add type in before_stats_info
			preJo.clear();
		} else {
			newJo.put("cmd", ctx.EVAL().getText());
			JSONArray funcsJa = preJo.getJSONArray(FUNCS);
			newJo.put(FUNCS, funcsJa);
			JSONObject lastFunc = funcsJa.getJSONObject(funcsJa.size() - 1);
			newJo.put(NEWNAME, lastFunc.getString(NEWNAME));
			newJo.put(TYPE, lastFunc.getString(TYPE));
			later.add(newJo);
			laterJo.clear();
		}
		
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_jsonparser(
			Eval_expr_dpl_jsonparserContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_jsonparser(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_if(Eval_expr_dpl_ifContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_if(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_pow(Eval_expr_dpl_powContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_pow(ctx);
	}

	@Override
	public Object visitJsonparser_exp(Jsonparser_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.JSONPARSER().getText());
		jo.put("field", field);
		jo.put("return_field", ctx.STRING().getText());
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitPow_exp(Pow_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.POW().getText());
		jo.put("field", field);
		jo.put("power", Long.parseLong(ctx.LONG().getText()));
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_regex(Eval_expr_dpl_regexContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_regex(ctx);
	}

	@Override
	public Object visitIplocation_exp(Iplocation_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.IPLOCATION().getText());
		jo.put("field", field);
		jo.put("level", ctx.STRING().getText());
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitUaparser_exp(Uaparser_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.UAPARSER().getText());
		jo.put("field", field);
		jo.put("return_field", ctx.STRING().getText());
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_iplocation(
			Eval_expr_dpl_iplocationContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_iplocation(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_uaparser(Eval_expr_dpl_uaparserContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_uaparser(ctx);
	}

	@Override
	public Object visitRegex_exp(Regex_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.REGEX().getText());
		jo.put("field", field);
		jo.put("regex", ctx.STRING().getText());
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitRename_dpl(Rename_dplContext ctx) {
		// TODO Auto-generated method stub
		return super.visitRename_dpl(ctx);
	}

	@Override
	public String visitIf_exp(If_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.boolean_exp());
		jo.put("func", ctx.IF().getText());
		jo.put("field", field);
		Object sTrue = visit(ctx.eval_func(0));
		Object sFalse = visit(ctx.eval_func(1));
		jo.put("true_return", sTrue);
		jo.put("false_return", sFalse);
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.STRING);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public String visitEval_expr_dpl_string(Eval_expr_dpl_stringContext ctx) {
		// TODO Auto-generated method stub
		String s = ctx.STRING().getText();
		return s.substring(1, s.length() - 1);
	}

	@Override
	public Object visitEval_expr_dpl_number(Eval_expr_dpl_numberContext ctx) {
		// TODO Auto-generated method stub
		Object number = null;
		if(ctx.DOUBLE() != null){
			number = Double.parseDouble(ctx.DOUBLE().getText());
		} else if(ctx.LONG() != null) {
			number = Long.parseLong(ctx.LONG().getText());
		}
		return number;
	}
	
	

	@Override
	public Object visitBoolean_expr_dpl_contains(
			Boolean_expr_dpl_containsContext ctx) {
		// TODO Auto-generated method stub
		return super.visitBoolean_expr_dpl_contains(ctx);
	}

	@Override
	public Object visitContains_exp(Contains_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.CONTAINS().getText());
		jo.put("field", field);
		String s = ctx.STRING().getText();
		jo.put("contains_field", s.substring(1, s.length() - 1));
		String newName = ctx.getText().toString();
		jo.put(NEWNAME, newName);
		jo.put("type", Type.BOOLEAN);
		if(stage.equals("pre")){
			addJA(preJo, jo, FUNCS);
		} else if(stage.equals("later")){
			addJA(laterJo, jo, FUNCS);
		}
		return newName;
	}

	@Override
	public Object visitStats_dpl(Stats_dplContext ctx) {
		// TODO Auto-generated method stub
		coreJo.put("cmd", ctx.STATSCMD().getText());
		if(ctx.SPAN() != null){
			if(ctx.TIME() != null){
				coreJo.put(ctx.SPAN().getText(), ctx.TIME().getText());
			}
		}
		return super.visitStats_dpl(ctx);
	}
	
	public void addJA(JSONObject fatherJo, Object leafO, String jaName){
		if(fatherJo.containsKey(jaName)){
			fatherJo.getJSONArray(jaName).add(leafO);
		} else {
			JSONArray ja = new JSONArray();
			ja.add(leafO);
			fatherJo.put(jaName, ja);
		}
	}
}
