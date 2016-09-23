package com.antlr4.impl.cmd.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.antlr4.impl.cmd.abs.AbstractCmd;
import com.antlr4.source.DplParser;
import com.antlr4.source.DplParser.Boolean_expr_dpl_containsContext;
import com.antlr4.source.DplParser.Boolean_expr_dpl_opContext;
import com.antlr4.source.DplParser.ByfContext;
import com.antlr4.source.DplParser.Capslk_expContext;
import com.antlr4.source.DplParser.Contains_expContext;
import com.antlr4.source.DplParser.Eval_dplContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_capslkContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_idContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_ifContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_jsonparserContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_lenContext;
import com.antlr4.source.DplParser.Eval_expr_dpl_nowContext;
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
import com.antlr4.source.DplParser.If_expContext;
import com.antlr4.source.DplParser.Iplocation_expContext;
import com.antlr4.source.DplParser.Jsonparser_expContext;
import com.antlr4.source.DplParser.Len_expContext;
import com.antlr4.source.DplParser.Now_expContext;
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
import com.antlr4.source.DplParser.Substr_expContext;
import com.antlr4.source.DplParser.Trim_expContext;
import com.antlr4.source.DplParser.Uaparser_expContext;
import com.antlr4.source.DplParser.Urldecode_expContext;
import com.antlr4.source.DplParser.Where_dplContext;

public class WhereCmd extends AbstractCmd{

	private List<JSONObject> list = new ArrayList<JSONObject>();
	private JSONObject result = new JSONObject();
	private JSONObject whereCmd = new JSONObject();
	
	@Override
	public List<JSONObject> getResult() {
		// TODO Auto-generated method stub
		result.put("cmd", "eval");
		JSONArray funcsJa = result.getJSONArray("funcs");
		String newname = null;
		for(Object o : funcsJa){
			JSONObject funcJo = (JSONObject) o;
			newname = funcJo.getString("newname");
		}
		result.put("newname", newname);
		result.put("return", false);
		list.add(result);
		list.add(whereCmd);
		return list;
	}

	@Override
	public Object visitEval_expr_dpl_trim(Eval_expr_dpl_trimContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_trim(ctx);
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
		jo.put("newname", newName);
		jo.put("type", Type.NUMBER);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_jsonparser(
			Eval_expr_dpl_jsonparserContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_jsonparser(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_uaparser(Eval_expr_dpl_uaparserContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_uaparser(ctx);
	}

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
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitLen_exp(Len_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.LEN().getText());
		jo.put("field", field);
		String newName = ctx.getText().toString();
		jo.put("newname", newName);
		jo.put("type", Type.NUMBER);
		addJA(result, jo, FUNCS);
		return newName;
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
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
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
		jo.put("newname", newName);
		jo.put("type", Type.NUMBER);
		addJA(result, jo, FUNCS);
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
		jo.put("newname", newName);
		jo.put("type", Type.NUMBER);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_round(Eval_expr_dpl_roundContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_round(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_urldecode(
			Eval_expr_dpl_urldecodeContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_urldecode(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_len(Eval_expr_dpl_lenContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_len(ctx);
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
		jo.put("newname", newName);
		jo.put("type", Type.NUMBER);
		addJA(result, jo, FUNCS);
		return newName;
	}
	
//	@Override
//	public Object visitEval_expr_dpl_stats_func(
//			Eval_expr_dpl_stats_funcContext ctx) {
//		// TODO Auto-generated method stub
//		return super.visitEval_expr_dpl_stats_func(ctx);
//	}

	@Override
	public Object visitEval_expr_dpl_pow(Eval_expr_dpl_powContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_pow(ctx);
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
		jo.put("newname", newName);
		jo.put("type", Type.NUMBER);
		addJA(result, jo, FUNCS);
		return newName;
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
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_number(Eval_expr_dpl_numberContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object number = null;
		if(ctx.DOUBLE() != null){
			number = Double.parseDouble(ctx.DOUBLE().getText());
			jo.put("type", "double");
		} else if(ctx.LONG() != null) {
			number = Long.parseLong(ctx.LONG().getText());
			jo.put("type", "long");
		}
		jo.put("func", "realValue");
		String newname = UUID.randomUUID().toString();
		jo.put("newname", newname);
		jo.put("value", number);
		addJA(result, jo, FUNCS);
		return newname;
	}

	@Override
	public Object visitEval_expr_dpl_substr(Eval_expr_dpl_substrContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_substr(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_if(Eval_expr_dpl_ifContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_if(ctx);
	}

	@Override
	public Object visitBoolean_expr_dpl_op(Boolean_expr_dpl_opContext ctx) {
		// TODO Auto-generated method stub
		Object field1 = visit(ctx.eval_func(0));
		Object field2 = visit(ctx.eval_func(1));
		JSONObject jo = new JSONObject();
		jo.put("func", "op_compare");
		jo.accumulate("field", field1);
		jo.accumulate("field", field2);
		jo.put("op", ctx.op.getText());
		String newName = ctx.getText().toString();
		jo.put("newname", newName);
		jo.put("type", Type.BOOLEAN);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitIf_exp(If_expContext ctx) {
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
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_string(Eval_expr_dpl_stringContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		jo.put("type", "string");
		String s = ctx.STRING().getText();
		jo.put("value", s.substring(1, s.length() - 1));
		jo.put("func", "realValue");
		String newname = UUID.randomUUID().toString();
		jo.put("newname", newname);
		addJA(result, jo, FUNCS);
		return newname;
	}
	
	@Override
	public Object visitEval_expr_dpl_now(Eval_expr_dpl_nowContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_now(ctx);
	}

	@Override
	public Object visitNow_exp(Now_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		jo.put("type", "long");
		long now = System.currentTimeMillis();
		jo.put("value", now);
		jo.put("func", "realValue");
		String newname = UUID.randomUUID().toString();
		jo.put("newname", newname);
		addJA(result, jo, FUNCS);
		return newname;
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
		jo.put("newname", newName);
		jo.put("type", Type.BOOLEAN);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_regex(Eval_expr_dpl_regexContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_regex(ctx);
	}

	@Override
	public Object visitRegex_exp(Regex_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.REGEX().getText());
		jo.put("field", field);
		String s = ctx.STRING().getText();
		jo.put("regex", s.substring(1, s.length() - 1));
		String newName = ctx.getText().toString();
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitJsonparser_exp(Jsonparser_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.JSONPARSER().getText());
		jo.put("field", field);
		String s = ctx.STRING().getText();
		jo.put("return_field", s.substring(1, s.length() - 1));
		String newName = ctx.getText().toString();
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitUaparser_exp(Uaparser_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.UAPARSER().getText());
		jo.put("field", field);
		String s = ctx.STRING().getText();
		jo.put("return_field", s.substring(1, s.length() - 1));
		String newName = ctx.getText().toString();
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
		return newName;
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
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitEval_dpl(Eval_dplContext ctx) {
		// TODO Auto-generated method stub
		String cmd = ctx.EVAL().getText();
		String newname = ctx.ID().getText();
		result.put("cmd", cmd);
		result.put("newname", newname);
		return super.visitEval_dpl(ctx);
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
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitStats_func(Stats_funcContext ctx) {
		// TODO Auto-generated method stub
		return super.visitStats_func(ctx);
	}

	@Override
	public Object visitWhere_dpl(Where_dplContext ctx) {
		// TODO Auto-generated method stub
		String cmd = ctx.WHERE().getText();
		whereCmd.put("cmd", cmd);
		whereCmd.put("field", visit(ctx.boolean_exp()));
		return null;
	}

	@Override
	public Object visitByf(ByfContext ctx) {
		// TODO Auto-generated method stub
		return super.visitByf(ctx);
	}

	@Override
	public Object visitRename_dpl(Rename_dplContext ctx) {
		// TODO Auto-generated method stub
		return super.visitRename_dpl(ctx);
	}

	@Override
	public Object visitCapslk_exp(Capslk_expContext ctx) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		Object field = visit(ctx.eval_func());
		jo.put("func", ctx.CAPSLKFUNC().getText());
		jo.put("field", field);
		String newName = ctx.getText().toString();
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitEval_expr_dpl_capslk(Eval_expr_dpl_capslkContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_capslk(ctx);
	}

	@Override
	public Object visit(ParseTree tree) {
		// TODO Auto-generated method stub
		return super.visit(tree);
	}

	@Override
	public Object visitChildren(RuleNode arg0) {
		// TODO Auto-generated method stub
		return super.visitChildren(arg0);
	}

	@Override
	public Object visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		return super.visitErrorNode(node);
	}

	@Override
	public Object visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		return super.visitTerminal(node);
	}

	@Override
	public Object visitStats_exp_dpl_eval(Stats_exp_dpl_evalContext ctx) {
		// TODO Auto-generated method stub
		return super.visitStats_exp_dpl_eval(ctx);
	}

	@Override
	public Object visitStats_dpl(Stats_dplContext ctx) {
		// TODO Auto-generated method stub
		return super.visitStats_dpl(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_id(Eval_expr_dpl_idContext ctx) {
		// TODO Auto-generated method stub
		return ctx.ID().getText();
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
		jo.put("newname", newName);
		jo.put("type", Type.STRING);
		addJA(result, jo, FUNCS);
		return newName;
	}

	@Override
	public Object visitStats_exp_dpl_stats(Stats_exp_dpl_statsContext ctx) {
		// TODO Auto-generated method stub
		return super.visitStats_exp_dpl_stats(ctx);
	}

	@Override
	public Object visitEval_expr_dpl_split(Eval_expr_dpl_splitContext ctx) {
		// TODO Auto-generated method stub
		return super.visitEval_expr_dpl_split(ctx);
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
