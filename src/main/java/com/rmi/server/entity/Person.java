package com.rmi.server.entity;

import java.io.Serializable;  

public class Person implements Serializable {  
    /**  
     *   
     */  
    private static final long serialVersionUID = 1L;  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public int getAge() {  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }  
  
    private String name;  
    private int age;  
  
    // 无参数的构造器  
    public Person() {  
    }  
  
    // 初始化全部属性的构造器  
    public Person(String name, int age) {  
        this.name = name;  
        this.age = age;  
    }  
}  
