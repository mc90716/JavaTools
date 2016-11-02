package com.es.priority;

/**
 * 优先级的实现，fork elasticsearch
 * @author 云袭
 *
 */
public class Priority implements Comparable<Priority> {

	private final byte value;

	public static final Priority IMMEDIATE = new Priority((byte) -1);
	public static final Priority URGENT = new Priority((byte) 0);
	public static final Priority HIGN = new Priority((byte) 1);
	public static final Priority NORMAL = new Priority((byte) 2);
	public static final Priority LOW = new Priority((byte) 3);
	public static final Priority LANGUID = new Priority((byte) 3);

	private static final Priority[] values = new Priority[] { IMMEDIATE, URGENT, HIGN, NORMAL, LOW, LANGUID };

	public Priority(byte value) {
		this.value = value;
	}

	public static Priority[] values() {
		return values;
	}

	public boolean after(Priority p) {
		return value > p.value;
	}
	
	@Override
	public int compareTo(Priority o) {
		if (this == o) {
			return 0;
		}
		return this.value > o.value ? 1 : -1;
	}

	@Override
	public int hashCode() {
		return (int)value;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || obj.getClass() != Priority.class) {
			return false;
		}
		Priority p = (Priority)obj;
		return value == p.value;
	}

	@Override
	public String toString() {
		switch (value) {
		case (int) -1: return "IMMEDIATE";
		case (int) 0: return "URGENT";
		case (int) 1: return "HIGN";
		case (int) 2: return "NORMAL";
		case (int) 3: return "LOW";
		 default:
             return "LANGUID";
		}
	}
}
