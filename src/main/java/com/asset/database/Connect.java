package com.asset.database;

import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.controlsfx.dialog.Dialogs;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rmi.server.Assets;

public class Connect {

	private ClassPathXmlApplicationContext context;
	
	private Future<Assets> future;
	
	public Connect() {
		// TODO Auto-generated constructor stub
		ExecutorService pool=Executors.newCachedThreadPool();
		GetConnect getConnect=new GetConnect();
		future=pool.submit(getConnect);
	}
	
	//创建数据库连接池
	class GetConnect implements Callable<Assets>{
		@Override
		public Assets call() throws Exception {
			// TODO Auto-generated method stub
			 URL url = getClass().getResource("applicationContext-beans.xml");
			  context = new ClassPathXmlApplicationContext(url.toString());
			  Assets assets= (Assets) context.getBean("assetsSpringRMI");
			  context.close();
			  return assets;
		}
		
	}
	
	public Assets get(){
		Assets assets=null;
		try {
			assets = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Dialogs.create()
	          .title("错误")
	          .message("网络连接错误")
	          .owner(null)
	          .showWarning();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Dialogs.create()
	          .title("错误")
	          .message("网络连接错误")
	          .owner(null)
	          .showWarning();
		}
		System.out.println("assets="+assets.getClass());
		return assets;
	}
	
	
	
	public Assets getAssets() {
		  URL url = getClass().getResource("applicationContext-beans.xml");
		  context = new ClassPathXmlApplicationContext(url.toString());
		  Assets assets= (Assets) context.getBean("assetsSpringRMI");
		  context.close();
		  return assets;
	}
	
}
