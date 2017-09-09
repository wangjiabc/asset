package com.asset.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.asset.database.Connect;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class roomInfoData {
	 private ObservableList<RoomInfoProperty> roomInfoData = FXCollections.observableArrayList();
	 
	 
	 
	 public Map<String, Object> get(Integer limit,Integer offset,
			 String sort,String order,String search) {
		 Assets assets=null;
		try {
			assets = new Connect().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  List<RoomInfo> roomInfo;
	      Map<String,Object> map,result; 
	      map=assets.getRoomInfo(limit, offset, sort, order, search);  
          int total=(int) map.get("total");
          roomInfo=(List<RoomInfo>) map.get("rows");
          Iterator<RoomInfo> iterator=roomInfo.iterator();
          while (iterator.hasNext()) {
        	  RoomInfoProperty roomInfoProperty=new RoomInfoProperty();
        	  RoomInfo room=iterator.next();
        	  roomInfoProperty.setAddress(room.getAddress());
        	  roomInfoProperty.setGUID(room.getGUID());
        	  roomInfoProperty.setNum(room.getNum());
        	  roomInfoProperty.setOriginalAddress(room.getOriginalAddress());
        	  roomInfoProperty.setOriginalNum(room.getOriginalNum());
        	  roomInfoData.add(roomInfoProperty);
          }
          
          result=new HashMap<>();
          
          result.put("value", roomInfoData);
          result.put("total", total);
          
          return result;
	}
	 
}
