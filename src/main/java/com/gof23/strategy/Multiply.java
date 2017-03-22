package com.gof23.strategy;

/**
 * 具体的策略实现 
 * 相乘计算
 *
 */
public class Multiply extends AbstractCalculator implements ICalculator {  
	  
    @Override  
    public int calculate(String exp) {  
        int arrayInt[] = split(exp,"\\*");  
        return arrayInt[0]*arrayInt[1];  
    }  
}
