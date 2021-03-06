package com.asset;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.RoomInfoProperty;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModelJoin.RoomChangeHireLog_RoomChartLog;

import javafx.collections.ObservableList;

public class TestSqlServerClient {
	  public static void main(String[] args) throws InterruptedException, ExecutionException {  
	    // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-beans.xml");

		//  Assets assets= new Connect().getAssets();  
	
		  Assets assets= new Connect().get();
		  
	      Integer limit=10;
	      Integer offset=10;
	      String sort=null;
	      String order=null;
		  String search=null;
		  
		  List<RoomInfo> roomInfo;
	      Map<String,Object> map; 
	     // map=assets.getRoomInfo(limit, offset, sort, order, search);  
	    /*  List<RoomChangeHireLog_RoomChartLog> changeHireLog_RoomChartLogs;
	      map=assets.findAllChangehire_CharLog(limit, offset, sort, order, search); 
	      MyTestUtil.print(map);
         int total=(int) map.get("rows");
          changeHireLog_RoomChartLogs=(List<RoomChangeHireLog_RoomChartLog>) map.get("value");
       //   Iterator<RoomInfo> iterator=roomInfo.iterator();
          /*
          while (iterator.hasNext()) {
			System.out.println(iterator.next().getAddress());
			System.out.println(iterator.next().getOriginalAddress());
	       }
	    
          
           Iterator iterator=changeHireLog_RoomChartLogs.iterator();
          
           while (iterator.hasNext()) {
			MyTestUtil.print(iterator.next());
		}
           
         // System.out.println("total="+total);
          
		//  Map<String, Object> o=new RoomInfoData().get(limit, offset, sort, order, search);
	     

       /*   List<RoomInfoProperty> roomInfoDatas= (List<RoomInfoProperty>) new RowData(roomInfo,RoomInfoProperty.class).get();
          
		  MyTestUtil.print(roomInfoDatas);
		  
		  
		  
		  Iterator iterator2=roomInfoDatas.iterator();
		  
		  while (iterator2.hasNext()) {
              RoomInfoProperty roomInfoProperty=(RoomInfoProperty) iterator2.next();
			  System.out.println("address="+roomInfoProperty.getAddress());
		}
		  */
	  }
}
