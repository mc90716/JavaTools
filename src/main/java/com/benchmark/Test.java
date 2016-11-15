package com.benchmark;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(value = { Mode.All })
public class Test {
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
                //.include(".*" + Test.class.getSimpleName() + ".*")
                .include("com.benchmark.Test.*")
                .forks(1)
                .build();

		new Runner(opt).run();
	}
}
