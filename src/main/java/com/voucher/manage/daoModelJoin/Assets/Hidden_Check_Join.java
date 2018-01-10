package com.voucher.manage.daoModelJoin.Assets;

import java.io.Serializable;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLDouble;
import com.voucher.manage.daoSQL.annotations.SQLInteger;
import com.voucher.manage.daoSQL.annotations.SQLString;

public class Hidden_Check_Join implements Serializable{

	@SQLInteger(name="id")
	private Integer id;

    @SQLString(name="GUID")
	private String GUID;

    @SQLInteger(name="exist")
	private Integer exist;
    
    @SQLString(name="check_id")
	private String check_id;

    @SQLString(name="check_circs")
	private String check_circs;

    @SQLDateTime(name="update_time")
   	private Date update_time;
    
    @SQLDateTime(name="date")
	private Date date;

    @SQLString(name="remark")
	private String remark;

    @SQLString(name="check_name")
   	private String check_name;

    @SQLString(name="principal")
   	private String principal;
    
    @SQLString(name="campusAdmin")
   	private String campusAdmin;

    @SQLString(name="terminal")
   	private String terminal;
    
    /*
     * Hidden
     */
    
    @SQLString(name="name")
	private String name;
    
    @SQLInteger(name="hidden_level")
	private Integer hidden_level;
    
    @SQLDateTime(name="happen_time")
   	private Date happen_time;
    
    @SQLString(name="detail")
	private String detail;
    
    @SQLDouble(name="progress")
   	private Double progress;
    
    @SQLInteger(name="type")
	private Integer type;
    
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
	
	public void setCheck_id(String check_id){
		this.check_id = check_id;
	}

	public String getCheck_id(){
		return check_id;
	}

	public void setCheck_circs(String check_circs){
		this.check_circs = check_circs;
	}

	public String getCheck_circs(){
		return check_circs;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHidden_level() {
		return hidden_level;
	}

	public void setHidden_level(Integer hidden_level) {
		this.hidden_level = hidden_level;
	}

	public Date getHappen_time() {
		return happen_time;
	}

	public void setHappen_time(Date happen_time) {
		this.happen_time = happen_time;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCheck_name() {
		return check_name;
	}

	public void setCheck_name(String check_name) {
		this.check_name = check_name;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
}
