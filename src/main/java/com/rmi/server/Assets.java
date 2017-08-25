package com.rmi.server;

import java.util.Map;


public interface Assets {
	public Map<String, Object> getRoomInfo(Integer limit,Integer offset,String sort,String order,
			String search);
}
