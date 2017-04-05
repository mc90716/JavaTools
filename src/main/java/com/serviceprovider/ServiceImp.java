package com.serviceprovider;

public class ServiceImp implements Service {

	@Override
	public void serve() {
		System.out.println("Start Provider Service...");
	}
}
