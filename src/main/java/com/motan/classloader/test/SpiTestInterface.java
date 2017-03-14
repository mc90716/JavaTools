package com.motan.classloader.test;

import com.motan.classloader.annotation.Spi;
import com.motan.classloader.scope.Scope;

@Spi(scope = Scope.SINGLETON)
public interface SpiTestInterface {
    long spiHello();
}