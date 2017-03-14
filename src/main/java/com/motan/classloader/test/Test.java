package com.motan.classloader.test;

import com.motan.classloader.classloader.ExtensionLoader;

public class Test {
	public static void main(String[] args) throws Exception {
		// 单例
		ExtensionLoader.getExtensionLoader(SpiTestInterface.class).getExtension("spitest").spiHello();
		// 多例
		ExtensionLoader.getExtensionLoader(SpiPrototypeInterface.class).getExtension("spiPrototypeTest").spiHello();
	}
}
