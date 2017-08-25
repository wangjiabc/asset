package com.rmi.server;

import com.rmi.server.entity.Person;

public interface Server {
	 String helloWorld(String name);  
	  
	    Person getPerson(String name, int age) ; 
}
