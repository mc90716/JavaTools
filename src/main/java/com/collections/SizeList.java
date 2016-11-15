package com.collections;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一个双向链表，在头部插入数据的时候检查链表长度是否达到容量，如果达到容量的话删除链表尾部一个节点
 * @param <E>
 */
public class SizeList<E> {

	private final int capacity;
	private final AtomicInteger size = new AtomicInteger();
	
	private Entry<E> head = null;
	private Entry<E> tail = null;
	
	public SizeList(int capacity) {
		this.capacity = capacity;
	}
	

	/**
	 * 删除尾部元素，触发删除尾部的操作是在add操作上(size达到capacity)
	 */
	public E remove() {
		E element = tail.value;
		Entry<E> pre = tail.before;
		this.tail = pre;
		if (pre == null) {
			this.head = null;
		} else {
			pre.next = null;
		}
		this.size.decrementAndGet();
		return element;
	}
	
	/**
	 * 添加元素到List的头部
	 * @param e
	 * @return
	 */
	public boolean add(E e) {
		int curSize = this.size.get();
		if(curSize >= this.capacity) {
			//删掉最后一个元素
			remove();
		}
		if(this.head == null) {
			this.head = this.tail = new Entry<>(e, null, null);
		} else {
			Entry<E> entry = new Entry<>(e, null, this.head);
			this.head.before = entry;
			this.head = entry;
		}
		this.size.incrementAndGet();
		return true;
	}
	
	public int getSize() {
		return this.size.get();
	}
	
	static class Entry<E> {
		E value;
		Entry<E> before;
		Entry<E> next;
		
		public Entry(E v, Entry<E> b, Entry<E> n) {
			value = v;
			before = b;
			next = n;
		}
	}
	
	public static void main(String[] args) {
		SizeList<String> list = new SizeList<String>(3);
		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");
		list.add("555");
		System.out.println(list.head.value);
		System.out.println(list.head.next.value);
		System.out.println(list.head.next.next.value);
		System.out.println(list.head.next.next.next.value);
		System.out.println(list.head.next.next.next.next.value);
	}
}
