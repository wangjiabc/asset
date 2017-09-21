package com.voucher.manage.daoModel;

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
import com.voucher.manage.daoSQL.annotations.SQLString;

@DBTable(name="[RoomManage].[dbo].[RoomChartLog]")
public class RoomChartLog {
	private static final long serialVersionUID = 1L;

	@SQLString(name="RoomAddress")
	private String RoomAddress;

	@SQLString(name="Charter")
	private String Charter;
	
	@SQLFloat(name="Area")
	private Float Area;
	
	@SQLDateTime(name="ConcludeDate")
	private Date ConcludeDate;
	
	@SQLString(name="ChartType")
	private String ChartType;
	
	@SQLDateTime(name="ChartBeginDate")
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

	public Date getConcludeDate() {
		return ConcludeDate;
	}

	public void setConcludeDate(Date concludeDate) {
		ConcludeDate = concludeDate;
	}
	
	
	 /*
	   * 数据库查询参数
	   */
	   
	   @QualifiLimit(name="limit")
	   private Integer limit;
	   @QualifiOffset(name="offset")
	   private Integer offset;
	   @QualifiNotIn(name="notIn")
	   private String notIn;
	   @QualifiSort(name="sort")
	   private String sort;
	   @QualifiOrder(name="order")
	   private String order;
	   /*
	    * 数据库查询where变量
	    */
	   @QualifiWhere(name="where")
	   private String[] where;
	   @QualifiWhereTerm(name="whereTerm")
	   private String whereTerm;              //多个where的连接条件        
	   
	   
	   public Integer getOffset() {
			return offset;
		}

		public void setOffset(Integer offset) {
			this.offset = offset;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}

		public String getOrder() {
			return order;
		}

		public void setOrder(String order) {
			this.order = order;
		}

		public Integer getLimit() {
			return limit;
		}

		public void setLimit(Integer limit) {
			this.limit = limit;
		}

		public String[] getWhere() {
			return where;
		}

		public void setWhere(String[] where) {
			this.where = where;
		}

		public String getNotIn() {
			return notIn;
		}

		public void setNotIn(String notIn) {
			this.notIn = notIn;
		}

		public String getChartType() {
			return ChartType;
		}

		public void setChartType(String chartType) {
			ChartType = chartType;
		}

		public Date getChartBeginDate() {
			return ChartBeginDate;
		}

		public void setChartBeginDate(Date chartBeginDate) {
			ChartBeginDate = chartBeginDate;
		}

		public String getWhereTerm() {
			return whereTerm;
		}

		public void setWhereTerm(String whereTerm) {
			this.whereTerm = whereTerm;
		}
	
}
