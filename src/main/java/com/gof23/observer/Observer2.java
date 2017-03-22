package com.gof23.observer;

/**
 * 观察者2
 *
 */
public class Observer2 implements Observer {

	@Override
	public void update() {
		System.out.println("observer2 has received!");
	}
}
