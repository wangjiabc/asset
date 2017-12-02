package com.rmi.server;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_level;
import com.voucher.manage.daoModel.Assets.Position;

public interface Assets {	
	
	public Map<String, Object> getRoomInfo(Integer limit,Integer offset,String sort,String order,
			Map search);
	
	public Integer updateRoomInfo(RoomInfo roomInfo);
	
	public Integer deleteRoomInfo(RoomInfo roomInfo);
	
	public Map<String, Object> findAllRoomInfo_Position(Integer limit, Integer offset, String sort,
			String order,Map search);
	
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
	
    public List<Hidden_level> setctAllHiddenLevel();
	
	public Integer insertHiddenLevel(Hidden_level hidden_level);
	
	public Integer deleteHiddenLevel(Hidden_level hidden_level);
	
}
