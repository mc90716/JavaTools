package com.enumeration;

public enum ProxyGenerator {
	
	JDKPROXY(new ProxyDelegate() {
		@Override
		public void proxy() {
			
		}
	}),
	CGLIB(new ProxyDelegate(){
		@Override
		public void proxy() {
			
		}});
	
	private ProxyDelegate delegate;
	
	ProxyGenerator(ProxyDelegate delegate) {
		this.delegate = delegate;
	}

	public void newProxy() {
		delegate.proxy();
	}
	
	interface ProxyDelegate {
		//proxy方法还可以接受一个接口类型的参数，用于执行接口的某个方法
		void proxy();
	}
}
