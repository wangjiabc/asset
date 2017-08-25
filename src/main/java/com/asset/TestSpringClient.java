package com.asset;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rmi.server.Server;
import com.rmi.server.entity.Person;

public class TestSpringClient {  
	  public static void main(String[] args) {  
	      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-beans.xml");
	      Server testrmiSpring = (Server) context.getBean("testrmiSpring");    
	      System.out.println(testrmiSpring.helloWorld("测试rmi"));  
	      Person person=testrmiSpring.getPerson("jljio", 22);  
	      System.out.println(person.getName());  
	  }  
	}  