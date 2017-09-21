package com.voucher.manage.daoModel;

import java.io.Serializable;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.DBTable;
import com.voucher.manage.daoSQL.annotations.QualifiLimit;
import com.voucher.manage.daoSQL.annotations.QualifiNotIn;
import com.voucher.manage.daoSQL.annotations.QualifiOffset;
import com.voucher.manage.daoSQL.annotations.QualifiOrder;
import com.voucher.manage.daoSQL.annotations.QualifiSort;
import com.voucher.manage.daoSQL.annotations.QualifiWhere;
import com.voucher.manage.daoSQL.annotations.QualifiWhereTerm;
import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLFloat;
import com.voucher.manage.daoSQL.annotations.SQLInteger;
import com.voucher.manage.daoSQL.annotations.SQLString;

@DBTable(name="[RoomManage].[dbo].[RoomChangeHireLog]")
public class RoomChangeHireLog implements Serializable{
	private static final long serialVersionUID = 1L;

	@SQLString(name="RoomAddress")
	private String RoomAddress;

	@SQLString(name="Charter")
	private String Charter;
	
	@SQLFloat(name="OHire")
	private Float OHire;
	
	@SQLFloat(name="Area")
	private Float Area;
	
	@SQLDateTime(name="ConcludeDate")
	private Date ConcludeDate;
	
	@SQLString(name="OriginalUnit")
	private String OriginalUnit;
	
	@SQLString(name="Region")
	private String Region;
	
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

	public Float getOHire() {
		return OHire;
	}

	public void setOHire(Float oHire) {
		OHire = oHire;
	}

	public Float getArea() {
		return Area;
	}

	public void setArea(Float area) {
		Area = area;
	}

	public Date getConcludeDate() {
		return ConcludeDate;
	}

	public void setConcludeDate(Date concludeDate) {
		ConcludeDate = concludeDate;
	}
	
	


		public String getOriginalUnit() {
			return OriginalUnit;
		}

		public void setOriginalUnit(String originalUnit) {
			OriginalUnit = originalUnit;
		}

		public String getRegion() {
			return Region;
		}

		public void setRegion(String region) {
			Region = region;
		}


	
}
