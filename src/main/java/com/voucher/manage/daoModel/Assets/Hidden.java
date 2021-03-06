package com.voucher.manage.daoModel.Assets;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Hidden]")
public class Hidden implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
   	private Integer id;

    @SQLString(name="GUID")
	private String GUID;

    @SQLInteger(name="exist")
	private Integer exist;

    @SQLString(name="name")
	private String name;

    @SQLInteger(name="hidden_level")
	private Integer hidden_level;

    @SQLString(name="detail")
	private String detail;

    @SQLDateTime(name="happen_time")
	private Date happen_time;

    @SQLDouble(name="progress")
	private Double progress;

    @SQLInteger(name="principal")
	private Integer principal;

    @SQLInteger(name="type")
	private Integer type;

    @SQLString(name="state")
	private String state;

    @SQLString(name="remark")
	private String remark;

    @SQLDateTime(name="update_time")
	private Date update_time;

    @SQLDateTime(name="date")
	private Date date;

    @SQLString(name="campusAdmin")
	private String campusAdmin;

    @SQLString(name="terminal")
	private String terminal;

    @SQLString(name="ManageRegion")
	private String ManageRegion;

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

	public void setExist(Integer exist){
		this.exist = exist;
	}

	public Integer getExist(){
		return exist;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setHidden_level(Integer hidden_level){
		this.hidden_level = hidden_level;
	}

	public Integer getHidden_level(){
		return hidden_level;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return detail;
	}

	public void setHappen_time(Date happen_time){
		this.happen_time = happen_time;
	}

	public Date getHappen_time(){
		return happen_time;
	}

	public void setProgress(Double progress){
		this.progress = progress;
	}

	public Double getProgress(){
		return progress;
	}

	public void setPrincipal(Integer principal){
		this.principal = principal;
	}

	public Integer getPrincipal(){
		return principal;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getType(){
		return type;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
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

	public void setCampusAdmin(String campusAdmin){
		this.campusAdmin = campusAdmin;
	}

	public String getCampusAdmin(){
		return campusAdmin;
	}

	public void setTerminal(String terminal){
		this.terminal = terminal;
	}

	public String getTerminal(){
		return terminal;
	}

	public void setManageRegion(String ManageRegion){
		this.ManageRegion = ManageRegion;
	}

	public String getManageRegion(){
		return ManageRegion;
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

