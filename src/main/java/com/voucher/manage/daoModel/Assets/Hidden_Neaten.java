package com.voucher.manage.daoModel.Assets;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Assets].[dbo].[Hidden_Neaten]")
public class Hidden_Neaten implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
	private Integer id;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="neaten_id")
	private String neaten_id;

    @SQLString(name="neaten_name")
	private String neaten_name;

    @SQLString(name="principal")
	private String principal;

    @SQLDateTime(name="happen_time")
	private Date happen_time;

    @SQLString(name="neaten_instance")
	private String neaten_instance;

    @SQLDateTime(name="update_time")
	private Date update_time;
    
    @SQLDateTime(name="date")
	private Date date;

    @SQLString(name="remark")
	private String remark;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setNeaten_id(String neaten_id){
		this.neaten_id = neaten_id;
	}

	public String getNeaten_id(){
		return neaten_id;
	}

	public void setNeaten_name(String neaten_name){
		this.neaten_name = neaten_name;
	}

	public String getNeaten_name(){
		return neaten_name;
	}

	public void setPrincipal(String principal){
		this.principal = principal;
	}

	public String getPrincipal(){
		return principal;
	}

	public void setHappen_time(Date happen_time){
		this.happen_time = happen_time;
	}

	public Date getHappen_time(){
		return happen_time;
	}

	public void setNeaten_instance(String neaten_instance){
		this.neaten_instance = neaten_instance;
	}

	public String getNeaten_instance(){
		return neaten_instance;
	}

	public void setUpdate_time(Date update_time){
		this.update_time = update_time;
	}

	public Date getUpdate_time(){
		return update_time;
	}
	
	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return date;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
	}




/*
*数据库查询参数
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
    @QualifiWhere(name="where")
    private String[] where;
    @QualifiWhereTerm(name="whereTerm")
    private String whereTerm;


	public void setLimit(Integer limit){
		this.limit = limit;
	}

	public Integer getLimit(){
		return limit;
	}

	public void setOffset(Integer offset){
		this.offset = offset;
	}

	public Integer getOffset(){
		return offset;
	}

	public void setNotIn(String notIn){
		this.notIn = notIn;
	}

	public String getNotIn(){
		return notIn;
	}

	public void setSort(String sort){
		this.sort = sort;
	}

	public String getSort(){
		return sort;
	}

	public void setOrder(String order){
		this.order = order;
	}

	public String getOrder(){
		return order;
	}

	public void setWhere(String[] where){
		this.where = where;
	}

	public String[] getWhere(){
		return where;
	}

	public void setWhereTerm(String whereTerm){
		this.whereTerm = whereTerm;
	}

	public String getWhereTerm(){
		return whereTerm;
	}

}

