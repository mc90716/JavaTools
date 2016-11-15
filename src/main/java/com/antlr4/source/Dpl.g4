grammar Dpl; // rename to distinguish from Expr.g4

dpl		: STATSCMD (SPAN'='TIME)? stats_exp(','?stats_exp)* (BY byf)? 	# stats_dpl
		| RENAME ID AS ID			 									# rename_dpl
		| WHERE boolean_exp 											# where_dpl
		| EVAL ID '=' eval_func 										# eval_dpl
		| TABLE ID(','ID)*												# table_dpl
		| SORT op=(ADD|SUB)ID											# sort_dpl
		| LIMIT LONG													# limit_dpl
		| STATISTIC														# statistic_dpl
		| CHANGE ID STRING TO STRING									# change_dpl
		;

stats_exp:
		stats_func_exp (AS newid=ID)?									# stats_exp_dpl_stats
		| eval_func_exp (AS newid=ID)?									# stats_exp_dpl_eval
		;

eval_func_exp:
		EVAL'('eval_func')'								
		;

eval_func:
		ID															# eval_expr_dpl_id
		| round_exp													# eval_expr_dpl_round
		| substr_exp												# eval_expr_dpl_substr	
		| len_exp													# eval_expr_dpl_len
		| capslk_exp												# eval_expr_dpl_capslk
		| trim_exp													# eval_expr_dpl_trim
		| replace_exp												# eval_expr_dpl_replace
		| split_exp													# eval_expr_dpl_split
		| urldecode_exp												# eval_expr_dpl_urldecode
		| uaparser_exp												# eval_expr_dpl_uaparser
		| jsonparser_exp											# eval_expr_dpl_jsonparser
		| regex_exp													# eval_expr_dpl_regex
		| if_exp													# eval_expr_dpl_if
		| iplocation_exp											# eval_expr_dpl_iplocation
		| pow_exp													# eval_expr_dpl_pow
		| now_exp													# eval_expr_dpl_now
		| case_exp													# eval_expr_dpl_case
		| tonumber_exp												# eval_expr_dpl_tonumber
		| tostring_exp												# eval_expr_dpl_tostring
		| kv_exp													# eval_expr_dpl_kv
		| model_exp													# eval_expr_dpl_model
		| stats_func_exp											# eval_expr_dpl_stats_func
		|<assoc=right> eval_func op='^' eval_func					# eval_expr_dpl_op_power
		| eval_func op=('*'|'/'|'//') eval_func						# eval_expr_dpl_op_muldiv
		| eval_func op=('+'|'-') eval_func 							# eval_expr_dpl_op_addsub
		| (DOUBLE | LONG)											# eval_expr_dpl_number
		| STRING													# eval_expr_dpl_string
		;
		
//calc_expr:
//		calc_expr op=('*'|'/'|'+'|'-'|'^') calc_expr      					# calc_expr_dpl_op
//    	| INT                         										# calc_expr_dpl_int
//    	| ID                          										# calc_expr_dpl_id
//    	| '(' calc_expr ')'                									# calc_expr_dpl_parens
//    	;

round_exp:
		ROUND'('eval_func','LONG')'
		;

substr_exp:
		SUBSTR'('eval_func','startnum=LONG(','endnum=LONG)?')'
		;
		
len_exp:
		LEN'('eval_func')'
		;

capslk_exp:
		CAPSLKFUNC'('eval_func')'
		;

trim_exp:
		TRIMFUNC'('eval_func(','STRING)?')'
		;

replace_exp:
		REPLACE'('eval_func','STRING','STRING')'
		;

split_exp:
		SPLIT'('eval_func','STRING','LONG')'
		;

urldecode_exp:
		URLDECODE'('eval_func(','STRING)?')'
		;
		
if_exp:
		IF'('boolean_exp','eval_func','eval_func')'
		;

uaparser_exp:
		UAPARSER'('eval_func','STRING')'
		;

jsonparser_exp:
		JSONPARSER'('eval_func','STRING')'
		;
		
regex_exp:
		REGEX'('eval_func','STRING')'
		;
		
iplocation_exp:
		IPLOCATION'('eval_func','STRING')'
		;
		
pow_exp:
		POW'('eval_func','LONG')'
		;

now_exp:
		NOW'('')'
		;

case_exp:
		CASE'('boolean_exp','eval_func(','boolean_exp','eval_func)*')'
		;
		
tonumber_exp:
		TONUMBER'('eval_func')'
		;

tostring_exp:
		TOSTRING'('eval_func')'
		;

kv_exp:
		KV'('eval_func','ID(','ID)*')'
		;

model_exp:
		MODEL'('eval_func')'
		;

stats_func_exp:
		stats_func ('('( eval_func_exp | ID | count_eval_boolean )')')?
		;		

count_eval_boolean:
		EVAL'('boolean_exp')'
		;

stats_func:
		COUNT | SUMSQ | SUM | AVG | DC | MAX | MIN
		;

boolean_exp:
		eval_func op=( ASSIGN | LT | LT_EQ | GT | GT_EQ | EQ | NOT_EQ1 | NOT_EQ2 ) eval_func		# boolean_expr_dpl_op
		| contains_exp																				# boolean_expr_dpl_contains
		;

contains_exp:
		CONTAINS'('eval_func','STRING')'
		;

byf:	ID(','?ID)*										
		;

fragment DIGIT : [0-9];
fragment ALPHA : [a-zA-Z_];
fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];

// cmd set
STATSCMD: STATS | TIMECHART | CHART ;

// eval func
TRIMFUNC: LTRIM | RTRIM | TRIM ;
CAPSLKFUNC: LOWER | UPPER ;

// cmd
RENAME	: R E N A M E ;
STATS	: S T A T S ;
WHERE	: W H E R E ;
EVAL	: E V A L ;
CHART	: C H A R T ;
TIMECHART: T I M E C H A R T ;
TABLE	: T A B L E ;
SORT	: S O R T ;
LIMIT	: L I M I T ;
STATISTIC: S T A T I S T I C ;
CHANGE	: C H A N G E ;

// stats func
COUNT	: C O U N T ;
DC		: D C ;
SUMSQ	: S U M S Q ;
SUM		: S U M ;
AVG		: A V G ;
MIN		: M I N ;
MAX		: M A X ;

ROUND	: R O U N D ;
SUBSTR	: S U B S T R ;
LEN		: L E N ;
LOWER	: L O W E R ;
UPPER	: U P P E R ;
TRIM	: T R I M ;	
LTRIM	: L T R I M ;
RTRIM	: R T R I M ;
REPLACE	: R E P L A C E ;
SPLIT	: S P L I T ;
URLDECODE: U R L D E C O D E ;
IF		: I F ;
UAPARSER: U A P A R S E R ;
JSONPARSER: J S O N P A R S E R ;
REGEX	: R E G E X ;
IPLOCATION: I P L O C A T I O N ;
POW		: P O W ;
NOW		: N O W ;
CASE	: C A S E ;
TONUMBER: T O N U M B E R ;
TOSTRING: T O S T R I N G ;	
KV		: K V ;
MODEL	: M O D E L ;

CONTAINS: C O N T A I N S ;

TO		: T O ;

AS		: A S ;
BY		: B Y ;
SPAN	: S P A N ;
AUTO	: A U T O ;

POWER:  '^' ;
MUL :   '*' ; // assigns token name to '*' used above in grammar
DIVDIV : '//' ;
DIV :   '/' ;
ADD :   '+' ;
SUB :   '-' ;

ASSIGN : '=' ;
LT	: '<' ;
LT_EQ: '<=' ;
GT	: '>' ;
GT_EQ: '>=' ;
EQ	: '==' ;
NOT_EQ1 : '!=' ;
NOT_EQ2 : '<>' ;

TIME	: LONG ALPHA | AUTO ;
LONG	: DIGIT | [1-9] DIGIT+ ;
DOUBLE	: LONG'.'(DIGIT+)?
		| '.'DIGIT+
		;
ZHONGWEN: [\u4e00-\u9fa5];

ID		: ('@'|ALPHA|ZHONGWEN) (ALPHA|DIGIT|ZHONGWEN|'.')* ;
ESC		: '\\"'|'\\\\' ;
STRING	: '"'(ESC|.)*?'"' ;

NEWLINE : '\r'? '\n' ;     // return newlines to parser (is end-statement signal)
WS  	: [ \t]+ -> skip ; // toss out whitespace
