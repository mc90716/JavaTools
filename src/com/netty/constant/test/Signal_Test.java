package com.netty.constant.test;

import com.netty.constant.AbstractConstant;
import com.netty.constant.Constant;
import com.netty.constant.ConstantPool;

/**
 * 常量池测试
 * @author 云袭
 *
 */
public class Signal_Test implements Constant<Signal_Test>{
	
	private static final class SignalConstant extends AbstractConstant<SignalConstant> {
        SignalConstant(int id, String name) {
            super(id, name);
        }
    }

	private static final ConstantPool<Signal_Test> pool = new ConstantPool<Signal_Test>() {

		@Override
		protected Signal_Test newConstant(int id, String name) {
			return new Signal_Test(id, name);
		}
	};
	
	private final SignalConstant constant;
	
	public Signal_Test(int id, String name) {
		this.constant = new SignalConstant(id, name);
	}
	
	public static Signal_Test valueOf(String name) {
		return pool.valueOf(name);
	}
	
	@Override
	public int compareTo(Signal_Test o) {
		if(this == o) {
			return 0;
		}
		return this.compareTo(o);
	}

	@Override
	public int id() {
		return constant.id();
	}

	@Override
	public String name() {
		return constant.name();
	}
	
}
