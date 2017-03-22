package com.gof23.bridge;

/**
 * 桥接模式的测试
 */
public class Test_Boy {
	
	public void pursue(MM mm){
		Gift gift = new WarmGift(new Flower());  // 礼物是“温暖的花”
		give(gift, mm);
	}
	
	public void give(Gift g, MM mm){}
}
