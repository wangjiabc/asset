package com.rmi.server;


import java.util.List;
import java.util.Map;

import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Assets;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModel.Assets.Position;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Data_Join;
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
	
	public Integer updateHiddenLevel(Hidden_Level hidden_Level);
	
    public List<Hidden_Type> selectAllHiddenType();
	
	public Integer insertHiddenType(Hidden_Type hidden_Type);
	
	public Integer deleteHiddenType(Hidden_Type hidden_Type);
	
	public Integer updateHiddenType(Hidden_Type hidden_Type);
	
	public Map<String , Object> selectHiddenUser(String campusAdmin);
	
	public Map<String, Object> selectAllHiddenUser(Integer limit, Integer offset, String sort,
			String order,Map<String, String> search);
	
	public Integer insertHiddenUser(Hidden_User hidden_User);
	
	public Integer deleteHiddenUser(Hidden_User hidden_User);

	public Integer updateHiddenUser(Hidden_User hidden_User);
	
	public Integer updateUserPassword(Hidden_User hidden_User,String OldPw);
	
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
	
	public Integer insertIntoHidden_Assets(Hidden_Assets hidden_Assets);
	
	public Integer deleteHidden_Assets(Hidden_Assets hidden_Assets);
	
	public Map findAssetByHideen(Integer limit, Integer offset, String sort, String order,
			Map<String, String> search);
	
	public Integer findNotHidden();
	
	public Integer findInHidden();
	
	public String findLastHidden();
	
	public String  findIgnoreHidden();
	
	public Map<String, Object> hiddenQuery(Integer hiddenLevel);
}
