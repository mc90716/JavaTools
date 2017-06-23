package com.serviceprovider;

public class ProviderImp implements Provider {

	static {
		ServiceManager.registerProvider("NewService", new ProviderImp());
	}
	
	@Override
	public Service newService() {
		return new ServiceImp();
	}

}
