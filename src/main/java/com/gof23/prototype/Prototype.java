package com.gof23.prototype;

/**
 * 克隆自身的类
 *
 */
public class Prototype implements Cloneable {
 
     private String name;
     
     public void setName(String name) {
         this.name = name;
     }
     
     public String getName() {
         return this.name;
     }
 
     public Object clone(){
         try {
             return super.clone();
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
     }
}
