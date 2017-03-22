package com.gof23.observer;

/**
 * 观察者1
 *
 */
public class Observer1 implements Observer {

	@Override
	public void update() {
		System.out.println("observer1 has received!");
	}
}
