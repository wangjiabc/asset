package com.rmi.server;


import java.util.List;
import java.util.Map;

import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModel.Assets.Position;

public interface Assets {	
	
	public Map<String, Object> getRoomInfo(Integer limit,Integer offset,String sort,String order,
			Map search);
	
	public Integer updateRoomInfo(RoomInfo roomInfo);
	
	public Integer deleteRoomInfo(RoomInfo roomInfo);
	
	public Map<String, Object> findAllRoomInfo_Position(Integer limit, Integer offset, String sort,
			String order,Map search);
	
	public Integer uploadImageFile(String GUID,List<String> names, List<byte[]> files);
	
	public Integer uploadPdfFile(String GUID,List<String> names, List<byte[]> files);
	
	public Integer uploadDocFile(String GUID,List<String> names, List<byte[]> files);
	
	public Integer uploadXlsFile(String GUID,List<String> names, List<byte[]> files);
	
	public Map<String,Object> selectAllHiddenDate(String GUID);
	
	public Map<String, Object> selectAllHidden(Integer limit,Integer offset,String sort,String order,
			Map search);
	
	public Map<String, Object> selectAllHidden_Jion(Integer limit, Integer offset, String sort,
			String order,Map<String, String> search);
	
	public Integer insertIntoHidden(Hidden hidden);
	
	public Integer updateHidden(Hidden hidden);
	
	public Integer deleteHidden(Hidden hidden);
	
	public Integer updatePosition(Position position);
	
    public List<Hidden_Level> setctAllHiddenLevel();
	
	public Integer insertHiddenLevel(Hidden_Level hidden_level);
	
	public Integer deleteHiddenLevel(Hidden_Level hidden_level);
	
    public List<Hidden_Type> selectAllHiddenType();
	
	public Integer insertHiddenType(Hidden_Type hidden_Type);
	
	public Integer deleteHiddenType(Hidden_Type hidden_Type);
	
    public List<Hidden_User> selectAllHiddenUser();
	
	public Integer insertHiddenUser(Hidden_User hidden_User);
	
	public Integer deleteHiddenUser(Hidden_User hidden_User);
	
}
