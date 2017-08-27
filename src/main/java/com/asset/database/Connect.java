package com.asset.database;

import java.net.URL;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rmi.server.Assets;

public class Connect {

	private ClassPathXmlApplicationContext context;
	
	public Assets getAssets() {
		  URL url = getClass().getResource("applicationContext-beans.xml");
		  context = new ClassPathXmlApplicationContext(url.toString());
		  Assets assets= (Assets) context.getBean("assetsSpringRMI");
		  context.close();
		  return assets;
	}
	
}
