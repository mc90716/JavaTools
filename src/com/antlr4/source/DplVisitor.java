// Generated from Dpl.g4 by ANTLR 4.4
package com.antlr4.source;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DplParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DplVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code boolean_expr_dpl_contains}
	 * labeled alternative in {@link DplParser#boolean_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_expr_dpl_contains(@NotNull DplParser.Boolean_expr_dpl_containsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code where_dpl}
	 * labeled alternative in {@link DplParser#dpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere_dpl(@NotNull DplParser.Where_dplContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#model_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_exp(@NotNull DplParser.Model_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#capslk_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCapslk_exp(@NotNull DplParser.Capslk_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stats_exp_dpl_eval}
	 * labeled alternative in {@link DplParser#stats_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStats_exp_dpl_eval(@NotNull DplParser.Stats_exp_dpl_evalContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#split_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSplit_exp(@NotNull DplParser.Split_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_stats_func}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_stats_func(@NotNull DplParser.Eval_expr_dpl_stats_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#round_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRound_exp(@NotNull DplParser.Round_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#tostring_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTostring_exp(@NotNull DplParser.Tostring_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_if}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_if(@NotNull DplParser.Eval_expr_dpl_ifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_id}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_id(@NotNull DplParser.Eval_expr_dpl_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#urldecode_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUrldecode_exp(@NotNull DplParser.Urldecode_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_regex}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_regex(@NotNull DplParser.Eval_expr_dpl_regexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_model}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_model(@NotNull DplParser.Eval_expr_dpl_modelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stats_exp_dpl_stats}
	 * labeled alternative in {@link DplParser#stats_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStats_exp_dpl_stats(@NotNull DplParser.Stats_exp_dpl_statsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#tonumber_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTonumber_exp(@NotNull DplParser.Tonumber_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#uaparser_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUaparser_exp(@NotNull DplParser.Uaparser_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#case_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_exp(@NotNull DplParser.Case_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_round}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_round(@NotNull DplParser.Eval_expr_dpl_roundContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#contains_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContains_exp(@NotNull DplParser.Contains_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_dpl}
	 * labeled alternative in {@link DplParser#dpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_dpl(@NotNull DplParser.Eval_dplContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stats_dpl}
	 * labeled alternative in {@link DplParser#dpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStats_dpl(@NotNull DplParser.Stats_dplContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#count_eval_boolean}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCount_eval_boolean(@NotNull DplParser.Count_eval_booleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_case}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_case(@NotNull DplParser.Eval_expr_dpl_caseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_number}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_number(@NotNull DplParser.Eval_expr_dpl_numberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_tonumber}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_tonumber(@NotNull DplParser.Eval_expr_dpl_tonumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean_expr_dpl_op}
	 * labeled alternative in {@link DplParser#boolean_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_expr_dpl_op(@NotNull DplParser.Boolean_expr_dpl_opContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_now}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_now(@NotNull DplParser.Eval_expr_dpl_nowContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#len_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLen_exp(@NotNull DplParser.Len_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_urldecode}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_urldecode(@NotNull DplParser.Eval_expr_dpl_urldecodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#replace_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReplace_exp(@NotNull DplParser.Replace_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_replace}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_replace(@NotNull DplParser.Eval_expr_dpl_replaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_jsonparser}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_jsonparser(@NotNull DplParser.Eval_expr_dpl_jsonparserContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statistic_dpl}
	 * labeled alternative in {@link DplParser#dpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatistic_dpl(@NotNull DplParser.Statistic_dplContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#trim_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrim_exp(@NotNull DplParser.Trim_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_op_addsub}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_op_addsub(@NotNull DplParser.Eval_expr_dpl_op_addsubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_op_power}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_op_power(@NotNull DplParser.Eval_expr_dpl_op_powerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_op_muldiv}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_op_muldiv(@NotNull DplParser.Eval_expr_dpl_op_muldivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_trim}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_trim(@NotNull DplParser.Eval_expr_dpl_trimContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#now_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNow_exp(@NotNull DplParser.Now_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_kv}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_kv(@NotNull DplParser.Eval_expr_dpl_kvContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_pow}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_pow(@NotNull DplParser.Eval_expr_dpl_powContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#kv_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKv_exp(@NotNull DplParser.Kv_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#jsonparser_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonparser_exp(@NotNull DplParser.Jsonparser_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#pow_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPow_exp(@NotNull DplParser.Pow_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#eval_func_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_func_exp(@NotNull DplParser.Eval_func_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#iplocation_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIplocation_exp(@NotNull DplParser.Iplocation_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_split}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_split(@NotNull DplParser.Eval_expr_dpl_splitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sort_dpl}
	 * labeled alternative in {@link DplParser#dpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSort_dpl(@NotNull DplParser.Sort_dplContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_string}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_string(@NotNull DplParser.Eval_expr_dpl_stringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code change_dpl}
	 * labeled alternative in {@link DplParser#dpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChange_dpl(@NotNull DplParser.Change_dplContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_len}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_len(@NotNull DplParser.Eval_expr_dpl_lenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_substr}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_substr(@NotNull DplParser.Eval_expr_dpl_substrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_tostring}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_tostring(@NotNull DplParser.Eval_expr_dpl_tostringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code limit_dpl}
	 * labeled alternative in {@link DplParser#dpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimit_dpl(@NotNull DplParser.Limit_dplContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#byf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitByf(@NotNull DplParser.ByfContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#stats_func_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStats_func_exp(@NotNull DplParser.Stats_func_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code table_dpl}
	 * labeled alternative in {@link DplParser#dpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_dpl(@NotNull DplParser.Table_dplContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_capslk}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_capslk(@NotNull DplParser.Eval_expr_dpl_capslkContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_iplocation}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_iplocation(@NotNull DplParser.Eval_expr_dpl_iplocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#substr_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubstr_exp(@NotNull DplParser.Substr_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eval_expr_dpl_uaparser}
	 * labeled alternative in {@link DplParser#eval_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_expr_dpl_uaparser(@NotNull DplParser.Eval_expr_dpl_uaparserContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#regex_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegex_exp(@NotNull DplParser.Regex_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rename_dpl}
	 * labeled alternative in {@link DplParser#dpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRename_dpl(@NotNull DplParser.Rename_dplContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#if_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_exp(@NotNull DplParser.If_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link DplParser#stats_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStats_func(@NotNull DplParser.Stats_funcContext ctx);
}