package com.gof23.builder;

/**
 * 指挥者
 * 由指导者来管理建造者，用户是与指导者联系的，指导者联系建造者最后得到产品
 *
 */
public class Director {
	
	private Builder builder; 
	
	public Director( Builder builder ) { 
		this.builder = builder; 
	} 
	
	public void construct() { 
		builder.buildPartA();
		builder.buildPartB();
		builder.buildPartC(); 
	} 
}
