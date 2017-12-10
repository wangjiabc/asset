package com.rmi.server;


import java.util.List;
import java.util.Map;

import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModel.Assets.Position;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;

public interface Assets {	
	
	public Map<String, Object> getRoomInfo(Integer limit,Integer offset,String sort,String order,
			Map search);
	
	public Integer updateRoomInfo(RoomInfo roomInfo);
	
	public Integer deleteRoomInfo(RoomInfo roomInfo);
	
	public Map<String, Object> findAllRoomInfo_Position(Integer limit, Integer offset, String sort,
			String order,Map search);
	
	public Integer uploadImageFile(Object object,String GUID,List<String> names, List<byte[]> files);
	
	public Integer uploadPdfFile(Object object,String GUID,List<String> names, List<byte[]> files);
	
	public Integer uploadDocFile(Object object,String GUID,List<String> names, List<byte[]> files);
	
	public Integer uploadXlsFile(Object object,String GUID,List<String> names, List<byte[]> files);
	
	public Map<String,Object> selectAllHiddenDate(String GUID);
	
	public Map<String,Object> selectAllHiddenCheckDate(String check_id);
	
	public Map<String,Object> selectAllHiddenNeatenDate(String neaten_id);
	
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
	
	public Map<String, Object> selectAllHiddenCheck(Integer limit, Integer offset, String sort,
			String order,Map<String, String> search);
	
	public Integer insertHiddenCheck(Hidden_Check hidden_Check);
	
	public Integer updateHiddenCheck(Hidden_Check hidden_Check);
	
	public Integer deleteHiddenCheck(Hidden_Check hidden_Check);
	
	public Map<String, Object> selectAllHiddenNeaten(Integer limit, Integer offset, String sort,
			String order,Map<String, String> search);
	
	public Integer insertHiddenNeaten(Hidden_Neaten hidden_Neaten);
	
	public Integer updateHiddenNeaten(Hidden_Neaten hidden_Neaten);
	
	public Integer deleteHiddenNeaten(Hidden_Neaten hidden_Neaten);
	
	public List<Hidden_Join> selectHiddenOfMap(Map<String, String> search);
	
}
