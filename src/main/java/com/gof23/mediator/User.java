package com.gof23.mediator;

/**
 * 抽象同事类
 *
 */
public abstract class User {
	
	private Mediator mediator;
	
	public Mediator getMediator(){
		return mediator;
	}
	
	public User(Mediator mediator) {
		this.mediator = mediator;
	}

	public abstract void work();
}
