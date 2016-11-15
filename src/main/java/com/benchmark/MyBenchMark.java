package com.benchmark;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

public class MyBenchMark {
	@BenchmarkMode(value = { Mode.All })
	public void testMethod() {
	}
}
