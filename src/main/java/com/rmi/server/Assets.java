package com.rmi.server;

import java.io.File;
import java.util.Map;

import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden2;


public interface Assets {	
	
	public Map<String, Object> getRoomInfo(Integer limit,Integer offset,String sort,String order,
			Map search);
	
	public Map<String, Object> findAllChangehire_CharLog(Integer limit, Integer offset, String sort,
			String order,String search);
	
	public String uploadImageFile(File file);
	
	public String uploadPdfFile(File file);
	
	public String uploadDocFile(File file);
	
	public String uploadXlsFile(File file);
	
	public Map<String, Object> findAllHidden(Integer limit,Integer offset,String sort,String order,
			Map search);
	
	public Integer insertIntoHidden(Hidden hidden);
	
	public Integer updateHidden(Hidden2 hidden2);
	
}
