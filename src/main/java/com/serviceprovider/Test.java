package com.serviceprovider;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException {

		//触发ProviderImp类的初始化操作
		Class.forName("com.serviceprovider.ProviderImp");

		Service s = ServiceManager.newInstance("NewService");
		s.serve();
		
	}
}
