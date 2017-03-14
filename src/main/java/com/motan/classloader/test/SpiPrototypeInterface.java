package com.motan.classloader.test;

import com.motan.classloader.annotation.Spi;
import com.motan.classloader.scope.Scope;

@Spi(scope = Scope.PROTOTYPE)
public interface SpiPrototypeInterface {
    long spiHello();
}
