package com.asset;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asset.database.Connect;
import com.asset.model.RoomInfoProperty;
import com.asset.model.roomInfoData;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;

import javafx.collections.ObservableList;

public class TestSqlServerClient {
	  public static void main(String[] args) {  
	    // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-beans.xml");

		  Assets assets= new Connect().getAssets();  
	
	      Integer limit=10;
	      Integer offset=10;
	      String sort=null;
	      String order=null;
		  String search=null;
	/*	  
		  List<RoomInfo> roomInfo;
	      Map<String,Object> map; 
	      map=assets.getRoomInfo(limit, offset, sort, order, search);  
          int total=(int) map.get("total");
          roomInfo=(List<RoomInfo>) map.get("rows");
          Iterator<RoomInfo> iterator=roomInfo.iterator();
          while (iterator.hasNext()) {
			System.out.println(iterator.next().getAddress());
			System.out.println(iterator.next().getOriginalAddress());
	       }
          System.out.println("total="+total);
          */
		  Map<String, Object> o=new roomInfoData().get(limit, offset, sort, order, search);
	      
		  System.out.println(o);
	  }
}
