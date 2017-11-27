package com.rmi.server;

import java.io.File;
import java.util.Map;

import com.voucher.manage.daoModel.Hidden;
import com.voucher.manage.daoModel.Position;
import com.voucher.manage.daoModel.RoomInfo;

public interface Assets {	
	
	public Map<String, Object> getRoomInfo(Integer limit,Integer offset,String sort,String order,
			Map search);
	
	public Integer updateRoomInfo(RoomInfo roomInfo);
	
	public Integer deleteRoomInfo(RoomInfo roomInfo);
	
	public Map<String, Object> findAllRoomInfo_Position(Integer limit, Integer offset, String sort,
			String order,Map search);
	
	public Map<String, Object> findAllChangehire_CharLog(Integer limit, Integer offset, String sort,
			String order,String search);
	
	public String uploadImageFile(String name, byte[] file);
	
	public String uploadPdfFile(String name, byte[] file);
	
	public String uploadDocFile(String name, byte[] file);
	
	public String uploadXlsFile(String name, byte[] file);
	
	public Map<String, Object> selectAllHidden(Integer limit,Integer offset,String sort,String order,
			Map search);
	
	public Integer insertIntoHidden(Hidden hidden);
	
	public Integer updateHidden(Hidden hidden);
	
	public Integer deleteHidden(Hidden hidden);
	
	public Integer updatePosition(Position position);
	
}
