package com.gof23.proxy_4.test;

public class UserMgrImpl implements UserMgr {

	@Override
	public void addUser() {
		System.out.println("1: 插入记录到user表");
		System.out.println("2: 做日志在另外一张表");
	}
	
}
