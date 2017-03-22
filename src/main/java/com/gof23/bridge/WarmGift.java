package com.gof23.bridge;

/**
 * 具体什么类型的礼物
 * 相当于RefinedAbstraction
 *
 */
public class WarmGift extends Gift{

	public WarmGift(GiftImpl giftImpl){
		this.giftImpl = giftImpl;  // this.giftImpl是在Gift类中定义的
	}
}
