package com.gof23.bridge;

/**
 * 具体什么类型的礼物
 * 相当于RefinedAbstraction
 *
 */
public class WildGift extends Gift{

	public WildGift(GiftImpl giftImpl){
		this.giftImpl = giftImpl;
	}
}
