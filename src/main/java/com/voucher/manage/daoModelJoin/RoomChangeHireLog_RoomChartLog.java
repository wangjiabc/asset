package com.voucher.manage.daoModelJoin;

import java.io.Serializable;
import java.util.Date;

public class RoomChangeHireLog_RoomChartLog implements Serializable{
	private static final long serialVersionUID = 1L; 
	private String RoomAddress;

	private String Charter;
	
	private Float Area;
	
	private Date ConcludeDate;

	private Float OHire;
	
	private String ChartType;
	
	private String OriginalUnit;

	private String Region;
	
	private Date ChartBeginDate;
	
	public String getRoomAddress() {
		return RoomAddress;
	}

	public void setRoomAddress(String roomAddress) {
		RoomAddress = roomAddress;
	}

	public String getCharter() {
		return Charter;
	}

	public void setCharter(String charter) {
		Charter = charter;
	}

	

	public Float getArea() {
		return Area;
	}

	public void setArea(Float area) {
		Area = area;
	}

	public Float getOHire() {
		return OHire;
	}

	public void setOHire(Float oHire) {
		OHire = oHire;
	}

	public String getChartType() {
		return ChartType;
	}

	public void setChartType(String chartType) {
		ChartType = chartType;
	}

	public String getOriginalUnit() {
		return OriginalUnit;
	}

	public void setOriginalUnit(String originalUnit) {
		OriginalUnit = originalUnit;
	}

	public Date getConcludeDate() {
		return ConcludeDate;
	}

	public void setConcludeDate(Date concludeDate) {
		ConcludeDate = concludeDate;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public Date getChartBeginDate() {
		return ChartBeginDate;
	}

	public void setChartBeginDate(Date chartBeginDate) {
		ChartBeginDate = chartBeginDate;
	}
}