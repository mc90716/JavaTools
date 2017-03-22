package com.gof23.iterator;

/**
 * 聚集接口
 *
 */
public interface Aggregate {  
    
    public Iterator CreateIterator();  
      
    /*取得集合元素*/  
    public Object get(int i);  
      
    /*取得集合大小*/  
    public int size();  
} 
