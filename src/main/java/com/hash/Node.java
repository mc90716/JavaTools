package com.hash;

public class Node {
	private String name;
	//weight表示每个节点的权重，在一致性Hash算法中表示虚拟节点的个数
	private int weight;
	public Node(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
