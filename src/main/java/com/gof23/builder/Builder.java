package com.gof23.builder;

/**
 * Builder是为创建一个Product对象的各个部件指定的抽象接口
 */
public interface Builder { 
	
	void buildPartA(); 
	void buildPartB(); 
	void buildPartC(); 

	Product getResult(); 
}
