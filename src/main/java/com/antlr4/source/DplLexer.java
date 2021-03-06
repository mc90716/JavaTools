// Generated from Dpl.g4 by ANTLR 4.4
package com.antlr4.source;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DplLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, STATSCMD=4, TRIMFUNC=5, CAPSLKFUNC=6, RENAME=7, 
		STATS=8, WHERE=9, EVAL=10, CHART=11, TIMECHART=12, TABLE=13, SORT=14, 
		LIMIT=15, STATISTIC=16, CHANGE=17, COUNT=18, DC=19, SUMSQ=20, SUM=21, 
		AVG=22, MIN=23, MAX=24, ROUND=25, SUBSTR=26, LEN=27, LOWER=28, UPPER=29, 
		TRIM=30, LTRIM=31, RTRIM=32, REPLACE=33, SPLIT=34, URLDECODE=35, IF=36, 
		UAPARSER=37, JSONPARSER=38, REGEX=39, IPLOCATION=40, POW=41, NOW=42, CASE=43, 
		TONUMBER=44, TOSTRING=45, KV=46, MODEL=47, CONTAINS=48, TO=49, AS=50, 
		BY=51, SPAN=52, AUTO=53, POWER=54, MUL=55, DIVDIV=56, DIV=57, ADD=58, 
		SUB=59, ASSIGN=60, LT=61, LT_EQ=62, GT=63, GT_EQ=64, EQ=65, NOT_EQ1=66, 
		NOT_EQ2=67, TIME=68, LONG=69, DOUBLE=70, ZHONGWEN=71, ID=72, ESC=73, STRING=74, 
		NEWLINE=75, WS=76;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'", "'0'", "'1'", 
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "':'", "';'", 
		"'<'", "'='", "'>'", "'?'", "'@'", "'A'", "'B'", "'C'", "'D'", "'E'", 
		"'F'", "'G'", "'H'", "'I'", "'J'", "'K'", "'L'"
	};
	public static final String[] ruleNames = {
		"T__2", "T__1", "T__0", "DIGIT", "ALPHA", "A", "B", "C", "D", "E", "F", 
		"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
		"U", "V", "W", "X", "Y", "Z", "STATSCMD", "TRIMFUNC", "CAPSLKFUNC", "RENAME", 
		"STATS", "WHERE", "EVAL", "CHART", "TIMECHART", "TABLE", "SORT", "LIMIT", 
		"STATISTIC", "CHANGE", "COUNT", "DC", "SUMSQ", "SUM", "AVG", "MIN", "MAX", 
		"ROUND", "SUBSTR", "LEN", "LOWER", "UPPER", "TRIM", "LTRIM", "RTRIM", 
		"REPLACE", "SPLIT", "URLDECODE", "IF", "UAPARSER", "JSONPARSER", "REGEX", 
		"IPLOCATION", "POW", "NOW", "CASE", "TONUMBER", "TOSTRING", "KV", "MODEL", 
		"CONTAINS", "TO", "AS", "BY", "SPAN", "AUTO", "POWER", "MUL", "DIVDIV", 
		"DIV", "ADD", "SUB", "ASSIGN", "LT", "LT_EQ", "GT", "GT_EQ", "EQ", "NOT_EQ1", 
		"NOT_EQ2", "TIME", "LONG", "DOUBLE", "ZHONGWEN", "ID", "ESC", "STRING", 
		"NEWLINE", "WS"
	};


	public DplLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Dpl.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2N\u02a8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\3\2\3\2\3\3"+
		"\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32"+
		"\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3"+
		"!\5!\u0115\n!\3\"\3\"\3\"\5\"\u011a\n\"\3#\3#\5#\u011e\n#\3$\3$\3$\3$"+
		"\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3"+
		"(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3"+
		"+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3"+
		".\3.\3.\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61"+
		"\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\65\3\65"+
		"\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67"+
		"\3\67\38\38\38\38\39\39\39\39\39\39\3:\3:\3:\3:\3:\3:\3;\3;\3;\3;\3;\3"+
		"<\3<\3<\3<\3<\3<\3=\3=\3=\3=\3=\3=\3>\3>\3>\3>\3>\3>\3>\3>\3?\3?\3?\3"+
		"?\3?\3?\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3A\3A\3A\3B\3B\3B\3B\3B\3B\3B\3"+
		"B\3B\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3D\3D\3D\3D\3D\3D\3E\3E\3E\3E\3"+
		"E\3E\3E\3E\3E\3E\3E\3F\3F\3F\3F\3G\3G\3G\3G\3H\3H\3H\3H\3H\3I\3I\3I\3"+
		"I\3I\3I\3I\3I\3I\3J\3J\3J\3J\3J\3J\3J\3J\3J\3K\3K\3K\3L\3L\3L\3L\3L\3"+
		"L\3M\3M\3M\3M\3M\3M\3M\3M\3M\3N\3N\3N\3O\3O\3O\3P\3P\3P\3Q\3Q\3Q\3Q\3"+
		"Q\3R\3R\3R\3R\3R\3S\3S\3T\3T\3U\3U\3U\3V\3V\3W\3W\3X\3X\3Y\3Y\3Z\3Z\3"+
		"[\3[\3[\3\\\3\\\3]\3]\3]\3^\3^\3^\3_\3_\3_\3`\3`\3`\3a\3a\3a\3a\5a\u0261"+
		"\na\3b\3b\3b\6b\u0266\nb\rb\16b\u0267\5b\u026a\nb\3c\3c\3c\6c\u026f\n"+
		"c\rc\16c\u0270\5c\u0273\nc\3c\3c\6c\u0277\nc\rc\16c\u0278\5c\u027b\nc"+
		"\3d\3d\3e\3e\3e\5e\u0282\ne\3e\3e\3e\3e\7e\u0288\ne\fe\16e\u028b\13e\3"+
		"f\3f\3f\3f\5f\u0291\nf\3g\3g\3g\7g\u0296\ng\fg\16g\u0299\13g\3g\3g\3h"+
		"\5h\u029e\nh\3h\3h\3i\6i\u02a3\ni\ri\16i\u02a4\3i\3i\3\u0297\2j\3\3\5"+
		"\4\7\5\t\2\13\2\r\2\17\2\21\2\23\2\25\2\27\2\31\2\33\2\35\2\37\2!\2#\2"+
		"%\2\'\2)\2+\2-\2/\2\61\2\63\2\65\2\67\29\2;\2=\2?\2A\6C\7E\bG\tI\nK\13"+
		"M\fO\rQ\16S\17U\20W\21Y\22[\23]\24_\25a\26c\27e\30g\31i\32k\33m\34o\35"+
		"q\36s\37u w!y\"{#}$\177%\u0081&\u0083\'\u0085(\u0087)\u0089*\u008b+\u008d"+
		",\u008f-\u0091.\u0093/\u0095\60\u0097\61\u0099\62\u009b\63\u009d\64\u009f"+
		"\65\u00a1\66\u00a3\67\u00a58\u00a79\u00a9:\u00ab;\u00ad<\u00af=\u00b1"+
		">\u00b3?\u00b5@\u00b7A\u00b9B\u00bbC\u00bdD\u00bfE\u00c1F\u00c3G\u00c5"+
		"H\u00c7I\u00c9J\u00cbK\u00cdL\u00cfM\u00d1N\3\2!\3\2\62;\5\2C\\aac|\4"+
		"\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKk"+
		"k\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2"+
		"TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\|"+
		"|\3\2\63;\3\2\u4e02\u9fa7\4\2\13\13\"\"\u02a2\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2"+
		"K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3"+
		"\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2"+
		"\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2"+
		"q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3"+
		"\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2"+
		"\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f"+
		"\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2"+
		"\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\2\u00a1"+
		"\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2\2\2\u00a9\3\2\2"+
		"\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1\3\2\2\2\2\u00b3"+
		"\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2\2\2\u00bb\3\2\2"+
		"\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\2\u00c3\3\2\2\2\2\u00c5"+
		"\3\2\2\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2\2\2\u00cb\3\2\2\2\2\u00cd\3\2\2"+
		"\2\2\u00cf\3\2\2\2\2\u00d1\3\2\2\2\3\u00d3\3\2\2\2\5\u00d5\3\2\2\2\7\u00d7"+
		"\3\2\2\2\t\u00d9\3\2\2\2\13\u00db\3\2\2\2\r\u00dd\3\2\2\2\17\u00df\3\2"+
		"\2\2\21\u00e1\3\2\2\2\23\u00e3\3\2\2\2\25\u00e5\3\2\2\2\27\u00e7\3\2\2"+
		"\2\31\u00e9\3\2\2\2\33\u00eb\3\2\2\2\35\u00ed\3\2\2\2\37\u00ef\3\2\2\2"+
		"!\u00f1\3\2\2\2#\u00f3\3\2\2\2%\u00f5\3\2\2\2\'\u00f7\3\2\2\2)\u00f9\3"+
		"\2\2\2+\u00fb\3\2\2\2-\u00fd\3\2\2\2/\u00ff\3\2\2\2\61\u0101\3\2\2\2\63"+
		"\u0103\3\2\2\2\65\u0105\3\2\2\2\67\u0107\3\2\2\29\u0109\3\2\2\2;\u010b"+
		"\3\2\2\2=\u010d\3\2\2\2?\u010f\3\2\2\2A\u0114\3\2\2\2C\u0119\3\2\2\2E"+
		"\u011d\3\2\2\2G\u011f\3\2\2\2I\u0126\3\2\2\2K\u012c\3\2\2\2M\u0132\3\2"+
		"\2\2O\u0137\3\2\2\2Q\u013d\3\2\2\2S\u0147\3\2\2\2U\u014d\3\2\2\2W\u0152"+
		"\3\2\2\2Y\u0158\3\2\2\2[\u0162\3\2\2\2]\u0169\3\2\2\2_\u016f\3\2\2\2a"+
		"\u0172\3\2\2\2c\u0178\3\2\2\2e\u017c\3\2\2\2g\u0180\3\2\2\2i\u0184\3\2"+
		"\2\2k\u0188\3\2\2\2m\u018e\3\2\2\2o\u0195\3\2\2\2q\u0199\3\2\2\2s\u019f"+
		"\3\2\2\2u\u01a5\3\2\2\2w\u01aa\3\2\2\2y\u01b0\3\2\2\2{\u01b6\3\2\2\2}"+
		"\u01be\3\2\2\2\177\u01c4\3\2\2\2\u0081\u01ce\3\2\2\2\u0083\u01d1\3\2\2"+
		"\2\u0085\u01da\3\2\2\2\u0087\u01e5\3\2\2\2\u0089\u01eb\3\2\2\2\u008b\u01f6"+
		"\3\2\2\2\u008d\u01fa\3\2\2\2\u008f\u01fe\3\2\2\2\u0091\u0203\3\2\2\2\u0093"+
		"\u020c\3\2\2\2\u0095\u0215\3\2\2\2\u0097\u0218\3\2\2\2\u0099\u021e\3\2"+
		"\2\2\u009b\u0227\3\2\2\2\u009d\u022a\3\2\2\2\u009f\u022d\3\2\2\2\u00a1"+
		"\u0230\3\2\2\2\u00a3\u0235\3\2\2\2\u00a5\u023a\3\2\2\2\u00a7\u023c\3\2"+
		"\2\2\u00a9\u023e\3\2\2\2\u00ab\u0241\3\2\2\2\u00ad\u0243\3\2\2\2\u00af"+
		"\u0245\3\2\2\2\u00b1\u0247\3\2\2\2\u00b3\u0249\3\2\2\2\u00b5\u024b\3\2"+
		"\2\2\u00b7\u024e\3\2\2\2\u00b9\u0250\3\2\2\2\u00bb\u0253\3\2\2\2\u00bd"+
		"\u0256\3\2\2\2\u00bf\u0259\3\2\2\2\u00c1\u0260\3\2\2\2\u00c3\u0269\3\2"+
		"\2\2\u00c5\u027a\3\2\2\2\u00c7\u027c\3\2\2\2\u00c9\u0281\3\2\2\2\u00cb"+
		"\u0290\3\2\2\2\u00cd\u0292\3\2\2\2\u00cf\u029d\3\2\2\2\u00d1\u02a2\3\2"+
		"\2\2\u00d3\u00d4\7+\2\2\u00d4\4\3\2\2\2\u00d5\u00d6\7.\2\2\u00d6\6\3\2"+
		"\2\2\u00d7\u00d8\7*\2\2\u00d8\b\3\2\2\2\u00d9\u00da\t\2\2\2\u00da\n\3"+
		"\2\2\2\u00db\u00dc\t\3\2\2\u00dc\f\3\2\2\2\u00dd\u00de\t\4\2\2\u00de\16"+
		"\3\2\2\2\u00df\u00e0\t\5\2\2\u00e0\20\3\2\2\2\u00e1\u00e2\t\6\2\2\u00e2"+
		"\22\3\2\2\2\u00e3\u00e4\t\7\2\2\u00e4\24\3\2\2\2\u00e5\u00e6\t\b\2\2\u00e6"+
		"\26\3\2\2\2\u00e7\u00e8\t\t\2\2\u00e8\30\3\2\2\2\u00e9\u00ea\t\n\2\2\u00ea"+
		"\32\3\2\2\2\u00eb\u00ec\t\13\2\2\u00ec\34\3\2\2\2\u00ed\u00ee\t\f\2\2"+
		"\u00ee\36\3\2\2\2\u00ef\u00f0\t\r\2\2\u00f0 \3\2\2\2\u00f1\u00f2\t\16"+
		"\2\2\u00f2\"\3\2\2\2\u00f3\u00f4\t\17\2\2\u00f4$\3\2\2\2\u00f5\u00f6\t"+
		"\20\2\2\u00f6&\3\2\2\2\u00f7\u00f8\t\21\2\2\u00f8(\3\2\2\2\u00f9\u00fa"+
		"\t\22\2\2\u00fa*\3\2\2\2\u00fb\u00fc\t\23\2\2\u00fc,\3\2\2\2\u00fd\u00fe"+
		"\t\24\2\2\u00fe.\3\2\2\2\u00ff\u0100\t\25\2\2\u0100\60\3\2\2\2\u0101\u0102"+
		"\t\26\2\2\u0102\62\3\2\2\2\u0103\u0104\t\27\2\2\u0104\64\3\2\2\2\u0105"+
		"\u0106\t\30\2\2\u0106\66\3\2\2\2\u0107\u0108\t\31\2\2\u01088\3\2\2\2\u0109"+
		"\u010a\t\32\2\2\u010a:\3\2\2\2\u010b\u010c\t\33\2\2\u010c<\3\2\2\2\u010d"+
		"\u010e\t\34\2\2\u010e>\3\2\2\2\u010f\u0110\t\35\2\2\u0110@\3\2\2\2\u0111"+
		"\u0115\5I%\2\u0112\u0115\5Q)\2\u0113\u0115\5O(\2\u0114\u0111\3\2\2\2\u0114"+
		"\u0112\3\2\2\2\u0114\u0113\3\2\2\2\u0115B\3\2\2\2\u0116\u011a\5w<\2\u0117"+
		"\u011a\5y=\2\u0118\u011a\5u;\2\u0119\u0116\3\2\2\2\u0119\u0117\3\2\2\2"+
		"\u0119\u0118\3\2\2\2\u011aD\3\2\2\2\u011b\u011e\5q9\2\u011c\u011e\5s:"+
		"\2\u011d\u011b\3\2\2\2\u011d\u011c\3\2\2\2\u011eF\3\2\2\2\u011f\u0120"+
		"\5/\30\2\u0120\u0121\5\25\13\2\u0121\u0122\5\'\24\2\u0122\u0123\5\r\7"+
		"\2\u0123\u0124\5%\23\2\u0124\u0125\5\25\13\2\u0125H\3\2\2\2\u0126\u0127"+
		"\5\61\31\2\u0127\u0128\5\63\32\2\u0128\u0129\5\r\7\2\u0129\u012a\5\63"+
		"\32\2\u012a\u012b\5\61\31\2\u012bJ\3\2\2\2\u012c\u012d\59\35\2\u012d\u012e"+
		"\5\33\16\2\u012e\u012f\5\25\13\2\u012f\u0130\5/\30\2\u0130\u0131\5\25"+
		"\13\2\u0131L\3\2\2\2\u0132\u0133\5\25\13\2\u0133\u0134\5\67\34\2\u0134"+
		"\u0135\5\r\7\2\u0135\u0136\5#\22\2\u0136N\3\2\2\2\u0137\u0138\5\21\t\2"+
		"\u0138\u0139\5\33\16\2\u0139\u013a\5\r\7\2\u013a\u013b\5/\30\2\u013b\u013c"+
		"\5\63\32\2\u013cP\3\2\2\2\u013d\u013e\5\63\32\2\u013e\u013f\5\35\17\2"+
		"\u013f\u0140\5%\23\2\u0140\u0141\5\25\13\2\u0141\u0142\5\21\t\2\u0142"+
		"\u0143\5\33\16\2\u0143\u0144\5\r\7\2\u0144\u0145\5/\30\2\u0145\u0146\5"+
		"\63\32\2\u0146R\3\2\2\2\u0147\u0148\5\63\32\2\u0148\u0149\5\r\7\2\u0149"+
		"\u014a\5\17\b\2\u014a\u014b\5#\22\2\u014b\u014c\5\25\13\2\u014cT\3\2\2"+
		"\2\u014d\u014e\5\61\31\2\u014e\u014f\5)\25\2\u014f\u0150\5/\30\2\u0150"+
		"\u0151\5\63\32\2\u0151V\3\2\2\2\u0152\u0153\5#\22\2\u0153\u0154\5\35\17"+
		"\2\u0154\u0155\5%\23\2\u0155\u0156\5\35\17\2\u0156\u0157\5\63\32\2\u0157"+
		"X\3\2\2\2\u0158\u0159\5\61\31\2\u0159\u015a\5\63\32\2\u015a\u015b\5\r"+
		"\7\2\u015b\u015c\5\63\32\2\u015c\u015d\5\35\17\2\u015d\u015e\5\61\31\2"+
		"\u015e\u015f\5\63\32\2\u015f\u0160\5\35\17\2\u0160\u0161\5\21\t\2\u0161"+
		"Z\3\2\2\2\u0162\u0163\5\21\t\2\u0163\u0164\5\33\16\2\u0164\u0165\5\r\7"+
		"\2\u0165\u0166\5\'\24\2\u0166\u0167\5\31\r\2\u0167\u0168\5\25\13\2\u0168"+
		"\\\3\2\2\2\u0169\u016a\5\21\t\2\u016a\u016b\5)\25\2\u016b\u016c\5\65\33"+
		"\2\u016c\u016d\5\'\24\2\u016d\u016e\5\63\32\2\u016e^\3\2\2\2\u016f\u0170"+
		"\5\23\n\2\u0170\u0171\5\21\t\2\u0171`\3\2\2\2\u0172\u0173\5\61\31\2\u0173"+
		"\u0174\5\65\33\2\u0174\u0175\5%\23\2\u0175\u0176\5\61\31\2\u0176\u0177"+
		"\5-\27\2\u0177b\3\2\2\2\u0178\u0179\5\61\31\2\u0179\u017a\5\65\33\2\u017a"+
		"\u017b\5%\23\2\u017bd\3\2\2\2\u017c\u017d\5\r\7\2\u017d\u017e\5\67\34"+
		"\2\u017e\u017f\5\31\r\2\u017ff\3\2\2\2\u0180\u0181\5%\23\2\u0181\u0182"+
		"\5\35\17\2\u0182\u0183\5\'\24\2\u0183h\3\2\2\2\u0184\u0185\5%\23\2\u0185"+
		"\u0186\5\r\7\2\u0186\u0187\5;\36\2\u0187j\3\2\2\2\u0188\u0189\5/\30\2"+
		"\u0189\u018a\5)\25\2\u018a\u018b\5\65\33\2\u018b\u018c\5\'\24\2\u018c"+
		"\u018d\5\23\n\2\u018dl\3\2\2\2\u018e\u018f\5\61\31\2\u018f\u0190\5\65"+
		"\33\2\u0190\u0191\5\17\b\2\u0191\u0192\5\61\31\2\u0192\u0193\5\63\32\2"+
		"\u0193\u0194\5/\30\2\u0194n\3\2\2\2\u0195\u0196\5#\22\2\u0196\u0197\5"+
		"\25\13\2\u0197\u0198\5\'\24\2\u0198p\3\2\2\2\u0199\u019a\5#\22\2\u019a"+
		"\u019b\5)\25\2\u019b\u019c\59\35\2\u019c\u019d\5\25\13\2\u019d\u019e\5"+
		"/\30\2\u019er\3\2\2\2\u019f\u01a0\5\65\33\2\u01a0\u01a1\5+\26\2\u01a1"+
		"\u01a2\5+\26\2\u01a2\u01a3\5\25\13\2\u01a3\u01a4\5/\30\2\u01a4t\3\2\2"+
		"\2\u01a5\u01a6\5\63\32\2\u01a6\u01a7\5/\30\2\u01a7\u01a8\5\35\17\2\u01a8"+
		"\u01a9\5%\23\2\u01a9v\3\2\2\2\u01aa\u01ab\5#\22\2\u01ab\u01ac\5\63\32"+
		"\2\u01ac\u01ad\5/\30\2\u01ad\u01ae\5\35\17\2\u01ae\u01af\5%\23\2\u01af"+
		"x\3\2\2\2\u01b0\u01b1\5/\30\2\u01b1\u01b2\5\63\32\2\u01b2\u01b3\5/\30"+
		"\2\u01b3\u01b4\5\35\17\2\u01b4\u01b5\5%\23\2\u01b5z\3\2\2\2\u01b6\u01b7"+
		"\5/\30\2\u01b7\u01b8\5\25\13\2\u01b8\u01b9\5+\26\2\u01b9\u01ba\5#\22\2"+
		"\u01ba\u01bb\5\r\7\2\u01bb\u01bc\5\21\t\2\u01bc\u01bd\5\25\13\2\u01bd"+
		"|\3\2\2\2\u01be\u01bf\5\61\31\2\u01bf\u01c0\5+\26\2\u01c0\u01c1\5#\22"+
		"\2\u01c1\u01c2\5\35\17\2\u01c2\u01c3\5\63\32\2\u01c3~\3\2\2\2\u01c4\u01c5"+
		"\5\65\33\2\u01c5\u01c6\5/\30\2\u01c6\u01c7\5#\22\2\u01c7\u01c8\5\23\n"+
		"\2\u01c8\u01c9\5\25\13\2\u01c9\u01ca\5\21\t\2\u01ca\u01cb\5)\25\2\u01cb"+
		"\u01cc\5\23\n\2\u01cc\u01cd\5\25\13\2\u01cd\u0080\3\2\2\2\u01ce\u01cf"+
		"\5\35\17\2\u01cf\u01d0\5\27\f\2\u01d0\u0082\3\2\2\2\u01d1\u01d2\5\65\33"+
		"\2\u01d2\u01d3\5\r\7\2\u01d3\u01d4\5+\26\2\u01d4\u01d5\5\r\7\2\u01d5\u01d6"+
		"\5/\30\2\u01d6\u01d7\5\61\31\2\u01d7\u01d8\5\25\13\2\u01d8\u01d9\5/\30"+
		"\2\u01d9\u0084\3\2\2\2\u01da\u01db\5\37\20\2\u01db\u01dc\5\61\31\2\u01dc"+
		"\u01dd\5)\25\2\u01dd\u01de\5\'\24\2\u01de\u01df\5+\26\2\u01df\u01e0\5"+
		"\r\7\2\u01e0\u01e1\5/\30\2\u01e1\u01e2\5\61\31\2\u01e2\u01e3\5\25\13\2"+
		"\u01e3\u01e4\5/\30\2\u01e4\u0086\3\2\2\2\u01e5\u01e6\5/\30\2\u01e6\u01e7"+
		"\5\25\13\2\u01e7\u01e8\5\31\r\2\u01e8\u01e9\5\25\13\2\u01e9\u01ea\5;\36"+
		"\2\u01ea\u0088\3\2\2\2\u01eb\u01ec\5\35\17\2\u01ec\u01ed\5+\26\2\u01ed"+
		"\u01ee\5#\22\2\u01ee\u01ef\5)\25\2\u01ef\u01f0\5\21\t\2\u01f0\u01f1\5"+
		"\r\7\2\u01f1\u01f2\5\63\32\2\u01f2\u01f3\5\35\17\2\u01f3\u01f4\5)\25\2"+
		"\u01f4\u01f5\5\'\24\2\u01f5\u008a\3\2\2\2\u01f6\u01f7\5+\26\2\u01f7\u01f8"+
		"\5)\25\2\u01f8\u01f9\59\35\2\u01f9\u008c\3\2\2\2\u01fa\u01fb\5\'\24\2"+
		"\u01fb\u01fc\5)\25\2\u01fc\u01fd\59\35\2\u01fd\u008e\3\2\2\2\u01fe\u01ff"+
		"\5\21\t\2\u01ff\u0200\5\r\7\2\u0200\u0201\5\61\31\2\u0201\u0202\5\25\13"+
		"\2\u0202\u0090\3\2\2\2\u0203\u0204\5\63\32\2\u0204\u0205\5)\25\2\u0205"+
		"\u0206\5\'\24\2\u0206\u0207\5\65\33\2\u0207\u0208\5%\23\2\u0208\u0209"+
		"\5\17\b\2\u0209\u020a\5\25\13\2\u020a\u020b\5/\30\2\u020b\u0092\3\2\2"+
		"\2\u020c\u020d\5\63\32\2\u020d\u020e\5)\25\2\u020e\u020f\5\61\31\2\u020f"+
		"\u0210\5\63\32\2\u0210\u0211\5/\30\2\u0211\u0212\5\35\17\2\u0212\u0213"+
		"\5\'\24\2\u0213\u0214\5\31\r\2\u0214\u0094\3\2\2\2\u0215\u0216\5!\21\2"+
		"\u0216\u0217\5\67\34\2\u0217\u0096\3\2\2\2\u0218\u0219\5%\23\2\u0219\u021a"+
		"\5)\25\2\u021a\u021b\5\23\n\2\u021b\u021c\5\25\13\2\u021c\u021d\5#\22"+
		"\2\u021d\u0098\3\2\2\2\u021e\u021f\5\21\t\2\u021f\u0220\5)\25\2\u0220"+
		"\u0221\5\'\24\2\u0221\u0222\5\63\32\2\u0222\u0223\5\r\7\2\u0223\u0224"+
		"\5\35\17\2\u0224\u0225\5\'\24\2\u0225\u0226\5\61\31\2\u0226\u009a\3\2"+
		"\2\2\u0227\u0228\5\63\32\2\u0228\u0229\5)\25\2\u0229\u009c\3\2\2\2\u022a"+
		"\u022b\5\r\7\2\u022b\u022c\5\61\31\2\u022c\u009e\3\2\2\2\u022d\u022e\5"+
		"\17\b\2\u022e\u022f\5=\37\2\u022f\u00a0\3\2\2\2\u0230\u0231\5\61\31\2"+
		"\u0231\u0232\5+\26\2\u0232\u0233\5\r\7\2\u0233\u0234\5\'\24\2\u0234\u00a2"+
		"\3\2\2\2\u0235\u0236\5\r\7\2\u0236\u0237\5\65\33\2\u0237\u0238\5\63\32"+
		"\2\u0238\u0239\5)\25\2\u0239\u00a4\3\2\2\2\u023a\u023b\7`\2\2\u023b\u00a6"+
		"\3\2\2\2\u023c\u023d\7,\2\2\u023d\u00a8\3\2\2\2\u023e\u023f\7\61\2\2\u023f"+
		"\u0240\7\61\2\2\u0240\u00aa\3\2\2\2\u0241\u0242\7\61\2\2\u0242\u00ac\3"+
		"\2\2\2\u0243\u0244\7-\2\2\u0244\u00ae\3\2\2\2\u0245\u0246\7/\2\2\u0246"+
		"\u00b0\3\2\2\2\u0247\u0248\7?\2\2\u0248\u00b2\3\2\2\2\u0249\u024a\7>\2"+
		"\2\u024a\u00b4\3\2\2\2\u024b\u024c\7>\2\2\u024c\u024d\7?\2\2\u024d\u00b6"+
		"\3\2\2\2\u024e\u024f\7@\2\2\u024f\u00b8\3\2\2\2\u0250\u0251\7@\2\2\u0251"+
		"\u0252\7?\2\2\u0252\u00ba\3\2\2\2\u0253\u0254\7?\2\2\u0254\u0255\7?\2"+
		"\2\u0255\u00bc\3\2\2\2\u0256\u0257\7#\2\2\u0257\u0258\7?\2\2\u0258\u00be"+
		"\3\2\2\2\u0259\u025a\7>\2\2\u025a\u025b\7@\2\2\u025b\u00c0\3\2\2\2\u025c"+
		"\u025d\5\u00c3b\2\u025d\u025e\5\13\6\2\u025e\u0261\3\2\2\2\u025f\u0261"+
		"\5\u00a3R\2\u0260\u025c\3\2\2\2\u0260\u025f\3\2\2\2\u0261\u00c2\3\2\2"+
		"\2\u0262\u026a\5\t\5\2\u0263\u0265\t\36\2\2\u0264\u0266\5\t\5\2\u0265"+
		"\u0264\3\2\2\2\u0266\u0267\3\2\2\2\u0267\u0265\3\2\2\2\u0267\u0268\3\2"+
		"\2\2\u0268\u026a\3\2\2\2\u0269\u0262\3\2\2\2\u0269\u0263\3\2\2\2\u026a"+
		"\u00c4\3\2\2\2\u026b\u026c\5\u00c3b\2\u026c\u0272\7\60\2\2\u026d\u026f"+
		"\5\t\5\2\u026e\u026d\3\2\2\2\u026f\u0270\3\2\2\2\u0270\u026e\3\2\2\2\u0270"+
		"\u0271\3\2\2\2\u0271\u0273\3\2\2\2\u0272\u026e\3\2\2\2\u0272\u0273\3\2"+
		"\2\2\u0273\u027b\3\2\2\2\u0274\u0276\7\60\2\2\u0275\u0277\5\t\5\2\u0276"+
		"\u0275\3\2\2\2\u0277\u0278\3\2\2\2\u0278\u0276\3\2\2\2\u0278\u0279\3\2"+
		"\2\2\u0279\u027b\3\2\2\2\u027a\u026b\3\2\2\2\u027a\u0274\3\2\2\2\u027b"+
		"\u00c6\3\2\2\2\u027c\u027d\t\37\2\2\u027d\u00c8\3\2\2\2\u027e\u0282\7"+
		"B\2\2\u027f\u0282\5\13\6\2\u0280\u0282\5\u00c7d\2\u0281\u027e\3\2\2\2"+
		"\u0281\u027f\3\2\2\2\u0281\u0280\3\2\2\2\u0282\u0289\3\2\2\2\u0283\u0288"+
		"\5\13\6\2\u0284\u0288\5\t\5\2\u0285\u0288\5\u00c7d\2\u0286\u0288\7\60"+
		"\2\2\u0287\u0283\3\2\2\2\u0287\u0284\3\2\2\2\u0287\u0285\3\2\2\2\u0287"+
		"\u0286\3\2\2\2\u0288\u028b\3\2\2\2\u0289\u0287\3\2\2\2\u0289\u028a\3\2"+
		"\2\2\u028a\u00ca\3\2\2\2\u028b\u0289\3\2\2\2\u028c\u028d\7^\2\2\u028d"+
		"\u0291\7$\2\2\u028e\u028f\7^\2\2\u028f\u0291\7^\2\2\u0290\u028c\3\2\2"+
		"\2\u0290\u028e\3\2\2\2\u0291\u00cc\3\2\2\2\u0292\u0297\7$\2\2\u0293\u0296"+
		"\5\u00cbf\2\u0294\u0296\13\2\2\2\u0295\u0293\3\2\2\2\u0295\u0294\3\2\2"+
		"\2\u0296\u0299\3\2\2\2\u0297\u0298\3\2\2\2\u0297\u0295\3\2\2\2\u0298\u029a"+
		"\3\2\2\2\u0299\u0297\3\2\2\2\u029a\u029b\7$\2\2\u029b\u00ce\3\2\2\2\u029c"+
		"\u029e\7\17\2\2\u029d\u029c\3\2\2\2\u029d\u029e\3\2\2\2\u029e\u029f\3"+
		"\2\2\2\u029f\u02a0\7\f\2\2\u02a0\u00d0\3\2\2\2\u02a1\u02a3\t \2\2\u02a2"+
		"\u02a1\3\2\2\2\u02a3\u02a4\3\2\2\2\u02a4\u02a2\3\2\2\2\u02a4\u02a5\3\2"+
		"\2\2\u02a5\u02a6\3\2\2\2\u02a6\u02a7\bi\2\2\u02a7\u00d2\3\2\2\2\25\2\u0114"+
		"\u0119\u011d\u0260\u0267\u0269\u0270\u0272\u0278\u027a\u0281\u0287\u0289"+
		"\u0290\u0295\u0297\u029d\u02a4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}