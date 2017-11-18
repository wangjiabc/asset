package com.voucher.manage.daoModel.Assets;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[TTT].[dbo].[Hidden]")
public class Hidden implements Serializable{

    private static final long serialVersionUID = 1L;


    @SQLInteger(name="HiddenLevel")
	private Integer HiddenLevel;

    @SQLString(name="ChangeSpeed")
	private String ChangeSpeed;

    @SQLString(name="HiddenInstance")
	private String HiddenInstance;


	public void setHiddenLevel(Integer HiddenLevel){
		this.HiddenLevel = HiddenLevel;
	}

	public Integer getHiddenLevel(){
		return HiddenLevel;
	}

	public void setChangeSpeed(String ChangeSpeed){
		this.ChangeSpeed = ChangeSpeed;
	}

	public String getChangeSpeed(){
		return ChangeSpeed;
	}

	public void setHiddenInstance(String HiddenInstance){
		this.HiddenInstance = HiddenInstance;
	}

	public String getHiddenInstance(){
		return HiddenInstance;
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

