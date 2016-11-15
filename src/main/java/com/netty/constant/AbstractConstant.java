package com.netty.constant;

import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractConstant<T extends AbstractConstant<T>> implements Constant<T>{

	private int id;
	private String name;
	private volatile long uniqueKey;
	
	public AbstractConstant(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public int id() {
		return id;
	}

	@Override
	public String name() {
		return name;
	}

	public long uniqueKey() {
        long uniqueKey;
        if ((uniqueKey = this.uniqueKey) == 0) {
            synchronized (this) {
                while ((uniqueKey = this.uniqueKey) == 0) {
                    this.uniqueKey = ThreadLocalRandom.current().nextLong();
                }
            }
        }
        return uniqueKey;
	}
	public int compareTo(T o) {
        if (this == o) {
            return 0;
        }

        int returnCode = hashCode() - o.hashCode();
        if (returnCode != 0) {
            return returnCode;
        }

        @SuppressWarnings("UnnecessaryLocalVariable") 
        AbstractConstant<T> other = o;
        long thisUK = uniqueKey();
        long otherUK = other.uniqueKey();
        if (thisUK < otherUK) {
            return -1;
        }
        if (thisUK > otherUK) {
            return 1;
        }

        throw new Error("failed to compare two different constants");
    }

    @Override
    public final boolean equals(Object obj) { // make it final, cannot be override
        return super.equals(obj);
    }

    @Override
    public final int hashCode() { // make it final, cannot be override
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Constant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
