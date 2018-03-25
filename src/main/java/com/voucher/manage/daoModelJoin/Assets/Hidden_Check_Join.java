package com.voucher.manage.daoModelJoin.Assets;

import java.io.Serializable;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLDouble;
import com.voucher.manage.daoSQL.annotations.SQLFloat;
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

    @SQLDateTime(name="happen_time")
	private Date happen_time;
    
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
     * RoomInfo
     */

    @SQLString(name="Num")
	private String Num;

    @SQLString(name="OriginalNum")
	private String OriginalNum;

    @SQLString(name="Address")
	private String Address;

    @SQLString(name="OriginalAddress")
	private String OriginalAddress;

    @SQLString(name="Region")
	private String Region;

    @SQLString(name="Segment")
	private String Segment;

    @SQLString(name="ManageRegion")
	private String ManageRegion;

    @SQLString(name="RoomProperty")
	private String RoomProperty;

    @SQLString(name="Useful")
	private String Useful;

    @SQLString(name="Floor")
	private String Floor;

    @SQLString(name="State")
	private String State;

    @SQLString(name="Structure")
	private String Structure;

    @SQLFloat(name="BuildArea")
	private Float BuildArea;

    @SQLString(name="RoomType")
	private String RoomType;

    @SQLString(name="IsCity")
	private String IsCity;

    @SQLString(name="Manager")
	private String Manager;

    @SQLString(name="ManagerPhone")
	private String ManagerPhone;

    @SQLString(name="IsStreet")
	private String IsStreet;

    @SQLString(name="FitMent")
	private String FitMent;

    @SQLString(name="BeFrom")
	private String BeFrom;

    @SQLDateTime(name="InDate")
	private Date InDate;

    @SQLString(name="PropertyRightNo")
	private String PropertyRightNo;

    @SQLFloat(name="PropertyRightArea")
	private Float PropertyRightArea;

    @SQLString(name="DesignUseful")
	private String DesignUseful;

    @SQLInteger(name="BuildYear")
	private Integer BuildYear;

    @SQLString(name="PropertyRightUnit")
	private String PropertyRightUnit;

    @SQLString(name="RealPropertyRightUnit")
	private String RealPropertyRightUnit;

    @SQLString(name="PropertyCardUnit")
	private String PropertyCardUnit;

    @SQLString(name="GlebeCardUnit")
	private String GlebeCardUnit;

    @SQLString(name="TransferUnit")
	private String TransferUnit;

    @SQLString(name="GlebeCardNo")
	private String GlebeCardNo;

    @SQLString(name="GlebeUseType")
	private String GlebeUseType;

    @SQLString(name="GlebeEndDate")
	private String GlebeEndDate;

    @SQLString(name="GlebeTypeUseful")
	private String GlebeTypeUseful;

    @SQLString(name="PlanUseful")
	private String PlanUseful;

    @SQLString(name="BefromFile")
	private String BefromFile;

    @SQLString(name="NoPRNResion")
	private String NoPRNResion;

    @SQLString(name="NoGCResion")
	private String NoGCResion;

    @SQLString(name="RealEstateNo")
	private String RealEstateNo;

    @SQLString(name="PropertyMemo")
	private String PropertyMemo;

    @SQLFloat(name="OriginalAmount")
	private Float OriginalAmount;

    @SQLFloat(name="AllDepreciation")
	private Float AllDepreciation;

    @SQLFloat(name="DepreciationYear")
	private Float DepreciationYear;

    @SQLFloat(name="NetWorth")
	private Float NetWorth;

    @SQLFloat(name="EvaluationPrice")
	private Float EvaluationPrice;

    @SQLFloat(name="EvaluationSinglePrice")
	private Float EvaluationSinglePrice;

    @SQLDateTime(name="EvaluationPlace")
	private Date EvaluationPlace;

    @SQLFloat(name="BefromAmount")
	private Float BefromAmount;

    @SQLFloat(name="MarketHire")
	private Float MarketHire;

    @SQLString(name="EvaluationUnit")
	private String EvaluationUnit;

    @SQLString(name="EvaluationNo")
	private String EvaluationNo;

    @SQLString(name="IsPawn")
	private String IsPawn;

    @SQLString(name="PawnUnit")
	private String PawnUnit;

    @SQLString(name="OriginalUnit")
	private String OriginalUnit;

    @SQLString(name="FinanceMemo")
	private String FinanceMemo;

    @SQLString(name="Utility")
	private String Utility;

    @SQLString(name="ChartGUID")
	private String ChartGUID;

    @SQLString(name="AddressCode")
	private String AddressCode;

    @SQLString(name="OriginalAddressCode")
	private String OriginalAddressCode;

    @SQLString(name="SecurityClassification")
	private String SecurityClassification;

    @SQLString(name="DangerClassification")
	private String DangerClassification;

    @SQLString(name="HiddenDanger")
	private String HiddenDanger;

    @SQLString(name="ResponsiblePerson")
	private String ResponsiblePerson;

    @SQLString(name="sMemo")
	private String sMemo;

    @SQLString(name="BelongUnit")
	private String BelongUnit;

    @SQLString(name="FileIndex")
	private String FileIndex;

    @SQLString(name="SecurityRegion")
	private String SecurityRegion;

    @SQLInteger(name="RoomCount")
	private Integer RoomCount;

    @SQLFloat(name="LandArea")
	private Float LandArea;

    @SQLFloat(name="UseYears")
	private Float UseYears;
    
    /*
     * Position
     */
    
    @SQLDouble(name="lng")
   	private Double lng;

    @SQLDouble(name="lat")
   	private Double lat;
    
    @SQLString(name="city")
	private String city;

    @SQLString(name="district")
	private String district;

    @SQLString(name="street")
	private String street;
    
    /*
     * WeiXin_User
     */
    @SQLString(name="phone")
	private String phone;

    @SQLString(name="user_name")
	private String user_name;
    
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
	
	
	
	public void setNum(String Num){
		this.Num = Num;
	}

	public String getNum(){
		return Num;
	}

	public void setOriginalNum(String OriginalNum){
		this.OriginalNum = OriginalNum;
	}

	public String getOriginalNum(){
		return OriginalNum;
	}

	public void setAddress(String Address){
		this.Address = Address;
	}

	public String getAddress(){
		return Address;
	}

	public void setOriginalAddress(String OriginalAddress){
		this.OriginalAddress = OriginalAddress;
	}

	public String getOriginalAddress(){
		return OriginalAddress;
	}

	public void setRegion(String Region){
		this.Region = Region;
	}

	public String getRegion(){
		return Region;
	}

	public void setSegment(String Segment){
		this.Segment = Segment;
	}

	public String getSegment(){
		return Segment;
	}

	public void setManageRegion(String ManageRegion){
		this.ManageRegion = ManageRegion;
	}

	public String getManageRegion(){
		return ManageRegion;
	}

	public void setRoomProperty(String RoomProperty){
		this.RoomProperty = RoomProperty;
	}

	public String getRoomProperty(){
		return RoomProperty;
	}

	public void setUseful(String Useful){
		this.Useful = Useful;
	}

	public String getUseful(){
		return Useful;
	}

	public void setFloor(String Floor){
		this.Floor = Floor;
	}

	public String getFloor(){
		return Floor;
	}

	public void setState(String State){
		this.State = State;
	}

	public String getState(){
		return State;
	}

	public void setStructure(String Structure){
		this.Structure = Structure;
	}

	public String getStructure(){
		return Structure;
	}

	public void setBuildArea(Float BuildArea){
		this.BuildArea = BuildArea;
	}

	public Float getBuildArea(){
		return BuildArea;
	}

	public void setRoomType(String RoomType){
		this.RoomType = RoomType;
	}

	public String getRoomType(){
		return RoomType;
	}

	public void setIsCity(String IsCity){
		this.IsCity = IsCity;
	}

	public String getIsCity(){
		return IsCity;
	}

	public void setManager(String Manager){
		this.Manager = Manager;
	}

	public String getManager(){
		return Manager;
	}

	public void setManagerPhone(String ManagerPhone){
		this.ManagerPhone = ManagerPhone;
	}

	public String getManagerPhone(){
		return ManagerPhone;
	}

	public void setIsStreet(String IsStreet){
		this.IsStreet = IsStreet;
	}

	public String getIsStreet(){
		return IsStreet;
	}

	public void setFitMent(String FitMent){
		this.FitMent = FitMent;
	}

	public String getFitMent(){
		return FitMent;
	}

	public void setBeFrom(String BeFrom){
		this.BeFrom = BeFrom;
	}

	public String getBeFrom(){
		return BeFrom;
	}

	public void setInDate(Date InDate){
		this.InDate = InDate;
	}

	public Date getInDate(){
		return InDate;
	}

	public void setPropertyRightNo(String PropertyRightNo){
		this.PropertyRightNo = PropertyRightNo;
	}

	public String getPropertyRightNo(){
		return PropertyRightNo;
	}

	public void setPropertyRightArea(Float PropertyRightArea){
		this.PropertyRightArea = PropertyRightArea;
	}

	public Float getPropertyRightArea(){
		return PropertyRightArea;
	}

	public void setDesignUseful(String DesignUseful){
		this.DesignUseful = DesignUseful;
	}

	public String getDesignUseful(){
		return DesignUseful;
	}

	public void setBuildYear(Integer BuildYear){
		this.BuildYear = BuildYear;
	}

	public Integer getBuildYear(){
		return BuildYear;
	}

	public void setPropertyRightUnit(String PropertyRightUnit){
		this.PropertyRightUnit = PropertyRightUnit;
	}

	public String getPropertyRightUnit(){
		return PropertyRightUnit;
	}

	public void setRealPropertyRightUnit(String RealPropertyRightUnit){
		this.RealPropertyRightUnit = RealPropertyRightUnit;
	}

	public String getRealPropertyRightUnit(){
		return RealPropertyRightUnit;
	}

	public void setPropertyCardUnit(String PropertyCardUnit){
		this.PropertyCardUnit = PropertyCardUnit;
	}

	public String getPropertyCardUnit(){
		return PropertyCardUnit;
	}

	public void setGlebeCardUnit(String GlebeCardUnit){
		this.GlebeCardUnit = GlebeCardUnit;
	}

	public String getGlebeCardUnit(){
		return GlebeCardUnit;
	}

	public void setTransferUnit(String TransferUnit){
		this.TransferUnit = TransferUnit;
	}

	public String getTransferUnit(){
		return TransferUnit;
	}

	public void setGlebeCardNo(String GlebeCardNo){
		this.GlebeCardNo = GlebeCardNo;
	}

	public String getGlebeCardNo(){
		return GlebeCardNo;
	}

	public void setGlebeUseType(String GlebeUseType){
		this.GlebeUseType = GlebeUseType;
	}

	public String getGlebeUseType(){
		return GlebeUseType;
	}

	public void setGlebeEndDate(String GlebeEndDate){
		this.GlebeEndDate = GlebeEndDate;
	}

	public String getGlebeEndDate(){
		return GlebeEndDate;
	}

	public void setGlebeTypeUseful(String GlebeTypeUseful){
		this.GlebeTypeUseful = GlebeTypeUseful;
	}

	public String getGlebeTypeUseful(){
		return GlebeTypeUseful;
	}

	public void setPlanUseful(String PlanUseful){
		this.PlanUseful = PlanUseful;
	}

	public String getPlanUseful(){
		return PlanUseful;
	}

	public void setBefromFile(String BefromFile){
		this.BefromFile = BefromFile;
	}

	public String getBefromFile(){
		return BefromFile;
	}

	public void setNoPRNResion(String NoPRNResion){
		this.NoPRNResion = NoPRNResion;
	}

	public String getNoPRNResion(){
		return NoPRNResion;
	}

	public void setNoGCResion(String NoGCResion){
		this.NoGCResion = NoGCResion;
	}

	public String getNoGCResion(){
		return NoGCResion;
	}

	public void setRealEstateNo(String RealEstateNo){
		this.RealEstateNo = RealEstateNo;
	}

	public String getRealEstateNo(){
		return RealEstateNo;
	}

	public void setPropertyMemo(String PropertyMemo){
		this.PropertyMemo = PropertyMemo;
	}

	public String getPropertyMemo(){
		return PropertyMemo;
	}

	public void setOriginalAmount(Float OriginalAmount){
		this.OriginalAmount = OriginalAmount;
	}

	public Float getOriginalAmount(){
		return OriginalAmount;
	}

	public void setAllDepreciation(Float AllDepreciation){
		this.AllDepreciation = AllDepreciation;
	}

	public Float getAllDepreciation(){
		return AllDepreciation;
	}

	public void setDepreciationYear(Float DepreciationYear){
		this.DepreciationYear = DepreciationYear;
	}

	public Float getDepreciationYear(){
		return DepreciationYear;
	}

	public void setNetWorth(Float NetWorth){
		this.NetWorth = NetWorth;
	}

	public Float getNetWorth(){
		return NetWorth;
	}

	public void setEvaluationPrice(Float EvaluationPrice){
		this.EvaluationPrice = EvaluationPrice;
	}

	public Float getEvaluationPrice(){
		return EvaluationPrice;
	}

	public void setEvaluationSinglePrice(Float EvaluationSinglePrice){
		this.EvaluationSinglePrice = EvaluationSinglePrice;
	}

	public Float getEvaluationSinglePrice(){
		return EvaluationSinglePrice;
	}

	public void setEvaluationPlace(Date EvaluationPlace){
		this.EvaluationPlace = EvaluationPlace;
	}

	public Date getEvaluationPlace(){
		return EvaluationPlace;
	}

	public void setBefromAmount(Float BefromAmount){
		this.BefromAmount = BefromAmount;
	}

	public Float getBefromAmount(){
		return BefromAmount;
	}

	public void setMarketHire(Float MarketHire){
		this.MarketHire = MarketHire;
	}

	public Float getMarketHire(){
		return MarketHire;
	}

	public void setEvaluationUnit(String EvaluationUnit){
		this.EvaluationUnit = EvaluationUnit;
	}

	public String getEvaluationUnit(){
		return EvaluationUnit;
	}

	public void setEvaluationNo(String EvaluationNo){
		this.EvaluationNo = EvaluationNo;
	}

	public String getEvaluationNo(){
		return EvaluationNo;
	}

	public void setIsPawn(String IsPawn){
		this.IsPawn = IsPawn;
	}

	public String getIsPawn(){
		return IsPawn;
	}

	public void setPawnUnit(String PawnUnit){
		this.PawnUnit = PawnUnit;
	}

	public String getPawnUnit(){
		return PawnUnit;
	}

	public void setOriginalUnit(String OriginalUnit){
		this.OriginalUnit = OriginalUnit;
	}

	public String getOriginalUnit(){
		return OriginalUnit;
	}

	public void setFinanceMemo(String FinanceMemo){
		this.FinanceMemo = FinanceMemo;
	}

	public String getFinanceMemo(){
		return FinanceMemo;
	}

	public void setUtility(String Utility){
		this.Utility = Utility;
	}

	public String getUtility(){
		return Utility;
	}

	public void setChartGUID(String ChartGUID){
		this.ChartGUID = ChartGUID;
	}

	public String getChartGUID(){
		return ChartGUID;
	}

	public void setAddressCode(String AddressCode){
		this.AddressCode = AddressCode;
	}

	public String getAddressCode(){
		return AddressCode;
	}

	public void setOriginalAddressCode(String OriginalAddressCode){
		this.OriginalAddressCode = OriginalAddressCode;
	}

	public String getOriginalAddressCode(){
		return OriginalAddressCode;
	}

	public void setSecurityClassification(String SecurityClassification){
		this.SecurityClassification = SecurityClassification;
	}

	public String getSecurityClassification(){
		return SecurityClassification;
	}

	public void setDangerClassification(String DangerClassification){
		this.DangerClassification = DangerClassification;
	}

	public String getDangerClassification(){
		return DangerClassification;
	}

	public void setHiddenDanger(String HiddenDanger){
		this.HiddenDanger = HiddenDanger;
	}

	public String getHiddenDanger(){
		return HiddenDanger;
	}

	public void setResponsiblePerson(String ResponsiblePerson){
		this.ResponsiblePerson = ResponsiblePerson;
	}

	public String getResponsiblePerson(){
		return ResponsiblePerson;
	}

	public void setSMemo(String sMemo){
		this.sMemo = sMemo;
	}

	public String getSMemo(){
		return sMemo;
	}

	public void setBelongUnit(String BelongUnit){
		this.BelongUnit = BelongUnit;
	}

	public String getBelongUnit(){
		return BelongUnit;
	}

	public void setFileIndex(String FileIndex){
		this.FileIndex = FileIndex;
	}

	public String getFileIndex(){
		return FileIndex;
	}

	public void setSecurityRegion(String SecurityRegion){
		this.SecurityRegion = SecurityRegion;
	}

	public String getSecurityRegion(){
		return SecurityRegion;
	}

	public void setRoomCount(Integer RoomCount){
		this.RoomCount = RoomCount;
	}

	public Integer getRoomCount(){
		return RoomCount;
	}

	public void setLandArea(Float LandArea){
		this.LandArea = LandArea;
	}

	public Float getLandArea(){
		return LandArea;
	}

	public void setUseYears(Float UseYears){
		this.UseYears = UseYears;
	}

	public Float getUseYears(){
		return UseYears;
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
	
	public void setLng(Double lng){
		this.lng = lng;
	}

	public Double getLng(){
		return lng;
	}

	public void setLat(Double lat){
		this.lat = lat;
	}

	public Double getLat(){
		return lat;
	}

	public Date getHappen_time() {
		return happen_time;
	}

	public void setHappen_time(Date happen_time) {
		this.happen_time = happen_time;
	}
	
	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setDistrict(String district){
		this.district = district;
	}

	public String getDistrict(){
		return district;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
