package com.netty.constant;

public interface Constant<T extends Constant<T>> extends Comparable<T> {
	/**
	 * return The unique id assigned to this {@link Constant}
	 * @return
	 */
	int id();
	
	/**
	 * return the name of this {@link Constant}}
	 * @return
	 */
	String name();
}
