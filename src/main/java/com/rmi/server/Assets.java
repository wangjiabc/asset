package com.rmi.server;

import java.io.File;
import java.util.Map;


public interface Assets {
	public enum type{
		IMAGE,XLS,PDF,DOC;
	}
		
	public Map<String, Object> getRoomInfo(Integer limit,Integer offset,String sort,String order,
			String search);
	
	public Map<String, Object> findAllChangehire_CharLog(Integer limit, Integer offset, String sort,
			String order,String search);
	
	public String uploadFile(File file,type fileType);
}
