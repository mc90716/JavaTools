package com.gof23.visitor;

/**
 * 访问者模式的测试
 * 访问者模式分离对象的数据和行为，可以实现不修改已有类的情况下，增加新的操作
 */
public class Test {

	public static void main(String[] args) {
		Park park = new Park();
		
		VisitorA visitorA = new VisitorA();
		park.accept(visitorA);
		
		VisitorB visitorB = new VisitorB();
		park.accept(visitorB);
		
		VisitorManager visitorManager = new VisitorManager();
		park.accept(visitorManager);
	}

}
