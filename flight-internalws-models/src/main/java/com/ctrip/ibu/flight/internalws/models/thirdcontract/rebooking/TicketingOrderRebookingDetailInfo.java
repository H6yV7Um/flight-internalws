/**
 * Autogenerated by soa-sdk-toolkit
 *
 * DO NOT EDIT DIRECTLY
 */
package com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking;

import com.ctriposs.baiji.convert.TypeConverter;
import com.ctriposs.baiji.exception.BaijiRuntimeException;
import com.ctriposs.baiji.rpc.common.apidoc.DtoDoc;
import com.ctriposs.baiji.rpc.common.apidoc.FieldDoc;
import com.ctriposs.baiji.schema.Field;
import com.ctriposs.baiji.schema.RecordSchema;
import com.ctriposs.baiji.schema.Schema;
import com.ctriposs.baiji.specific.SpecificRecord;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 改签单明细信息
 */
@DtoDoc("改签单明细信息")
@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TicketingOrderRebookingDetailInfo", namespace = "http://soa.ctrip.com/flight/Ticket/Rebooking/QueryApi/v1", propOrder = {
    "passengerName",
    "sequence",
    "flight",
    "takeOffTime",
    "arrivalTime",
    "ticketNo",
    "subClass",
    "printPrice",
    "dCity",
    "aCity",
    "dPort",
    "aPort",
    "subsidy",
    "oilFee",
    "tax",
    "airline",
    "airType",
    "shareFlight",
    "dTerminal",
    "aTerminal",
    "flightTime",
    "isRebookPolicyMatch",
    "airLineCode",
    "ticketingOrderRebookingStopInfoList",
    "clazz",
    "rebookFee",
    "craftType",
    "aTerminalBuildingID",
    "dTerminalBuildingID",
    "refundInfo",
    "supplierRefundInfo",
    "originAttachAmount",
    "mealType"
})
@SuppressWarnings("all")
public class TicketingOrderRebookingDetailInfo implements SpecificRecord {

    private static final long serialVersionUID = 1L;

	public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"TicketingOrderRebookingDetailInfo\",\"namespace\":\"" + TicketingOrderRebookingDetailInfo.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"passengerName\",\"type\":[\"string\",\"null\"]},{\"name\":\"sequence\",\"type\":\"int\"},{\"name\":\"flight\",\"type\":[\"string\",\"null\"]},{\"name\":\"takeOffTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"arrivalTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"ticketNo\",\"type\":[\"string\",\"null\"]},{\"name\":\"subClass\",\"type\":[\"string\",\"null\"]},{\"name\":\"printPrice\",\"type\":[\"string\",\"null\"]},{\"name\":\"dCity\",\"type\":\"int\"},{\"name\":\"aCity\",\"type\":\"int\"},{\"name\":\"dPort\",\"type\":[\"string\",\"null\"]},{\"name\":\"aPort\",\"type\":[\"string\",\"null\"]},{\"name\":\"subsidy\",\"type\":[\"string\",\"null\"]},{\"name\":\"oilFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"tax\",\"type\":[\"string\",\"null\"]},{\"name\":\"airline\",\"type\":[\"string\",\"null\"]},{\"name\":\"airType\",\"type\":[\"string\",\"null\"]},{\"name\":\"shareFlight\",\"type\":[\"string\",\"null\"]},{\"name\":\"dTerminal\",\"type\":[\"string\",\"null\"]},{\"name\":\"aTerminal\",\"type\":[\"string\",\"null\"]},{\"name\":\"flightTime\",\"type\":[\"string\",\"null\"]},{\"name\":\"isRebookPolicyMatch\",\"type\":\"boolean\"},{\"name\":\"airLineCode\",\"type\":[\"string\",\"null\"]},{\"name\":\"ticketingOrderRebookingStopInfoList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"TicketingOrderRebookingStopInfo\",\"namespace\":\"" + TicketingOrderRebookingStopInfo.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"stopCity\",\"type\":[\"string\",\"null\"]},{\"name\":\"stopTime\",\"type\":[\"string\",\"null\"]}]}}},{\"name\":\"Class\",\"type\":[\"string\",\"null\"]},{\"name\":\"rebookFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"craftType\",\"type\":[\"string\",\"null\"]},{\"name\":\"aTerminalBuildingID\",\"type\":[\"string\",\"null\"]},{\"name\":\"dTerminalBuildingID\",\"type\":[\"string\",\"null\"]},{\"name\":\"refundInfo\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierRefundInfo\",\"type\":[\"string\",\"null\"]},{\"name\":\"originAttachAmount\",\"type\":[\"string\",\"null\"]},{\"name\":\"mealType\",\"type\":[\"int\",\"null\"]}]}");

    @Override
    public Schema getSchema() { return SCHEMA; }

    public TicketingOrderRebookingDetailInfo(
        String passengerName, 
        int sequence, 
        String flight, 
        Calendar takeOffTime, 
        Calendar arrivalTime, 
        String ticketNo, 
        String subClass, 
        BigDecimal printPrice, 
        int dCity, 
        int aCity, 
        String dPort, 
        String aPort, 
        BigDecimal subsidy, 
        BigDecimal oilFee, 
        BigDecimal tax, 
        String airline, 
        String airType, 
        String shareFlight, 
        String dTerminal, 
        String aTerminal, 
        String flightTime, 
        boolean isRebookPolicyMatch, 
        String airLineCode, 
        List<TicketingOrderRebookingStopInfo> ticketingOrderRebookingStopInfoList, 
        String clazz, 
        BigDecimal rebookFee, 
        String craftType, 
        BigDecimal aTerminalBuildingID, 
        BigDecimal dTerminalBuildingID, 
        String refundInfo, 
        String supplierRefundInfo, 
        BigDecimal originAttachAmount, 
        Integer mealType) {
        this.passengerName = passengerName;
        this.sequence = sequence;
        this.flight = flight;
        this.takeOffTime = takeOffTime;
        this.arrivalTime = arrivalTime;
        this.ticketNo = ticketNo;
        this.subClass = subClass;
        this.printPrice = printPrice;
        this.dCity = dCity;
        this.aCity = aCity;
        this.dPort = dPort;
        this.aPort = aPort;
        this.subsidy = subsidy;
        this.oilFee = oilFee;
        this.tax = tax;
        this.airline = airline;
        this.airType = airType;
        this.shareFlight = shareFlight;
        this.dTerminal = dTerminal;
        this.aTerminal = aTerminal;
        this.flightTime = flightTime;
        this.isRebookPolicyMatch = isRebookPolicyMatch;
        this.airLineCode = airLineCode;
        this.ticketingOrderRebookingStopInfoList = ticketingOrderRebookingStopInfoList;
        this.clazz = clazz;
        this.rebookFee = rebookFee;
        this.craftType = craftType;
        this.aTerminalBuildingID = aTerminalBuildingID;
        this.dTerminalBuildingID = dTerminalBuildingID;
        this.refundInfo = refundInfo;
        this.supplierRefundInfo = supplierRefundInfo;
        this.originAttachAmount = originAttachAmount;
        this.mealType = mealType;
    }

    public TicketingOrderRebookingDetailInfo() {
        this.takeOffTime = new java.util.GregorianCalendar(1, 0, 1, 0, 0, 0);
        this.takeOffTime.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        this.arrivalTime = new java.util.GregorianCalendar(1, 0, 1, 0, 0, 0);
        this.arrivalTime.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        this.printPrice = new BigDecimal(0);
        this.subsidy = new BigDecimal(0);
        this.oilFee = new BigDecimal(0);
        this.tax = new BigDecimal(0);
        this.rebookFee = new BigDecimal(0);
        this.aTerminalBuildingID = new BigDecimal(0);
        this.dTerminalBuildingID = new BigDecimal(0);
        this.originAttachAmount = new BigDecimal(0);
    }

    @FieldDoc("乘客名")
    @JsonProperty("PassengerName")
    @XmlElement(name = "PassengerName")
    private String passengerName;

    @FieldDoc("程")
    @JsonProperty("Sequence")
    @XmlElement(name = "Sequence")
    private int sequence;

    @FieldDoc("航班号")
    @JsonProperty("Flight")
    @XmlElement(name = "Flight")
    private String flight;

    @FieldDoc("起飞时间")
    @JsonProperty("TakeOffTime")
    @XmlElement(name = "TakeOffTime")
    private Calendar takeOffTime;

    @FieldDoc("到达时间")
    @JsonProperty("ArrivalTime")
    @XmlElement(name = "ArrivalTime")
    private Calendar arrivalTime;

    @FieldDoc("票号")
    @JsonProperty("TicketNo")
    @XmlElement(name = "TicketNo")
    private String ticketNo;

    @FieldDoc("子舱位")
    @JsonProperty("SubClass")
    @XmlElement(name = "SubClass")
    private String subClass;

    @FieldDoc("面价")
    @JsonProperty("PrintPrice")
    @XmlElement(name = "PrintPrice")
    private BigDecimal printPrice;

    @FieldDoc("出发城市")
    @JsonProperty("DCity")
    @XmlElement(name = "DCity")
    private int dCity;

    @FieldDoc("到达城市")
    @JsonProperty("ACity")
    @XmlElement(name = "ACity")
    private int aCity;

    @FieldDoc("出发机场")
    @JsonProperty("DPort")
    @XmlElement(name = "DPort")
    private String dPort;

    @FieldDoc("到达机场")
    @JsonProperty("APort")
    @XmlElement(name = "APort")
    private String aPort;

    @FieldDoc("飞享金")
    @JsonProperty("Subsidy")
    @XmlElement(name = "Subsidy")
    private BigDecimal subsidy;

    @FieldDoc("燃油")
    @JsonProperty("OilFee")
    @XmlElement(name = "OilFee")
    private BigDecimal oilFee;

    @FieldDoc("税")
    @JsonProperty("Tax")
    @XmlElement(name = "Tax")
    private BigDecimal tax;

    @FieldDoc("航空公司")
    @JsonProperty("Airline")
    @XmlElement(name = "Airline")
    private String airline;

    @FieldDoc("机型")
    @JsonProperty("AirType")
    @XmlElement(name = "AirType")
    private String airType;

    @FieldDoc("共享航班号")
    @JsonProperty("ShareFlight")
    @XmlElement(name = "ShareFlight")
    private String shareFlight;

    @FieldDoc("出发航站楼")
    @JsonProperty("DTerminal")
    @XmlElement(name = "DTerminal")
    private String dTerminal;

    @FieldDoc("到达航站楼")
    @JsonProperty("ATerminal")
    @XmlElement(name = "ATerminal")
    private String aTerminal;

    @JsonProperty("FlightTime")
    @XmlElement(name = "FlightTime")
    private String flightTime;

    @FieldDoc("是否匹配改签政策")
    @JsonProperty("IsRebookPolicyMatch")
    @XmlElement(name = "IsRebookPolicyMatch")
    private boolean isRebookPolicyMatch;

    @FieldDoc("航司编码")
    @JsonProperty("AirLineCode")
    @XmlElement(name = "AirLineCode")
    private String airLineCode;

    @FieldDoc("经停信息列表")
    @JsonProperty("TicketingOrderRebookingStopInfoList")
    @XmlElement(name = "TicketingOrderRebookingStopInfoList")
    private List<TicketingOrderRebookingStopInfo> ticketingOrderRebookingStopInfoList;

    @FieldDoc("改签后的舱等")
    @JsonProperty("Class")
    @XmlElement(name = "Class")
    private String clazz;

    @FieldDoc("改签费用")
    @JsonProperty("RebookFee")
    @XmlElement(name = "RebookFee")
    private BigDecimal rebookFee;

    @FieldDoc("改签航班机型")
    @JsonProperty("CraftType")
    @XmlElement(name = "CraftType")
    private String craftType;

    @FieldDoc("改签到达航站楼ID")
    @JsonProperty("ATerminalBuildingID")
    @XmlElement(name = "ATerminalBuildingID")
    private BigDecimal aTerminalBuildingID;

    @FieldDoc("改签出发航站楼ID")
    @JsonProperty("DTerminalBuildingID")
    @XmlElement(name = "DTerminalBuildingID")
    private BigDecimal dTerminalBuildingID;

    @FieldDoc("退改公式，对客户")
    @JsonProperty("RefundInfo")
    @XmlElement(name = "RefundInfo")
    private String refundInfo;

    @FieldDoc("退改公式，对供应商")
    @JsonProperty("SupplierRefundInfo")
    @XmlElement(name = "SupplierRefundInfo")
    private String supplierRefundInfo;

    @FieldDoc("让利金额")
    @JsonProperty("OriginAttachAmount")
    @XmlElement(name = "OriginAttachAmount")
    private BigDecimal originAttachAmount;

    @FieldDoc("餐食类型 0: 表示无配餐,1:表示正餐:,2:表示小吃")
    @JsonProperty("MealType")
    @XmlElement(name = "MealType", nillable = true)
    private Integer mealType;

    /**
     * 乘客名
     */
    public String getPassengerName() {
        return this.passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     * 程
     */
    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * 航班号
     */
    public String getFlight() {
        return this.flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    /**
     * 起飞时间
     */
    public Calendar getTakeOffTime() {
        return this.takeOffTime;
    }

    public void setTakeOffTime(Calendar takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    /**
     * 到达时间
     */
    public Calendar getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(Calendar arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * 票号
     */
    public String getTicketNo() {
        return this.ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    /**
     * 子舱位
     */
    public String getSubClass() {
        return this.subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    /**
     * 面价
     */
    public BigDecimal getPrintPrice() {
        return this.printPrice;
    }

    public void setPrintPrice(BigDecimal printPrice) {
        this.printPrice = printPrice;
    }

    /**
     * 出发城市
     */
    public int getDCity() {
        return this.dCity;
    }

    public void setDCity(int dCity) {
        this.dCity = dCity;
    }

    /**
     * 到达城市
     */
    public int getACity() {
        return this.aCity;
    }

    public void setACity(int aCity) {
        this.aCity = aCity;
    }

    /**
     * 出发机场
     */
    public String getDPort() {
        return this.dPort;
    }

    public void setDPort(String dPort) {
        this.dPort = dPort;
    }

    /**
     * 到达机场
     */
    public String getAPort() {
        return this.aPort;
    }

    public void setAPort(String aPort) {
        this.aPort = aPort;
    }

    /**
     * 飞享金
     */
    public BigDecimal getSubsidy() {
        return this.subsidy;
    }

    public void setSubsidy(BigDecimal subsidy) {
        this.subsidy = subsidy;
    }

    /**
     * 燃油
     */
    public BigDecimal getOilFee() {
        return this.oilFee;
    }

    public void setOilFee(BigDecimal oilFee) {
        this.oilFee = oilFee;
    }

    /**
     * 税
     */
    public BigDecimal getTax() {
        return this.tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * 航空公司
     */
    public String getAirline() {
        return this.airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * 机型
     */
    public String getAirType() {
        return this.airType;
    }

    public void setAirType(String airType) {
        this.airType = airType;
    }

    /**
     * 共享航班号
     */
    public String getShareFlight() {
        return this.shareFlight;
    }

    public void setShareFlight(String shareFlight) {
        this.shareFlight = shareFlight;
    }

    /**
     * 出发航站楼
     */
    public String getDTerminal() {
        return this.dTerminal;
    }

    public void setDTerminal(String dTerminal) {
        this.dTerminal = dTerminal;
    }

    /**
     * 到达航站楼
     */
    public String getATerminal() {
        return this.aTerminal;
    }

    public void setATerminal(String aTerminal) {
        this.aTerminal = aTerminal;
    }

    public String getFlightTime() {
        return this.flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    /**
     * 是否匹配改签政策
     */
    public boolean getIsRebookPolicyMatch() {
        return this.isRebookPolicyMatch;
    }

    public void setIsRebookPolicyMatch(boolean isRebookPolicyMatch) {
        this.isRebookPolicyMatch = isRebookPolicyMatch;
    }

    /**
     * 航司编码
     */
    public String getAirLineCode() {
        return this.airLineCode;
    }

    public void setAirLineCode(String airLineCode) {
        this.airLineCode = airLineCode;
    }

    /**
     * 经停信息列表
     */
    public List<TicketingOrderRebookingStopInfo> getTicketingOrderRebookingStopInfoList() {
        return this.ticketingOrderRebookingStopInfoList;
    }

    public void setTicketingOrderRebookingStopInfoList(List<TicketingOrderRebookingStopInfo> ticketingOrderRebookingStopInfoList) {
        this.ticketingOrderRebookingStopInfoList = ticketingOrderRebookingStopInfoList;
    }

    /**
     * 改签后的舱等
     */
    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    /**
     * 改签费用
     */
    public BigDecimal getRebookFee() {
        return this.rebookFee;
    }

    public void setRebookFee(BigDecimal rebookFee) {
        this.rebookFee = rebookFee;
    }

    /**
     * 改签航班机型
     */
    public String getCraftType() {
        return this.craftType;
    }

    public void setCraftType(String craftType) {
        this.craftType = craftType;
    }

    /**
     * 改签到达航站楼ID
     */
    public BigDecimal getATerminalBuildingID() {
        return this.aTerminalBuildingID;
    }

    public void setATerminalBuildingID(BigDecimal aTerminalBuildingID) {
        this.aTerminalBuildingID = aTerminalBuildingID;
    }

    /**
     * 改签出发航站楼ID
     */
    public BigDecimal getDTerminalBuildingID() {
        return this.dTerminalBuildingID;
    }

    public void setDTerminalBuildingID(BigDecimal dTerminalBuildingID) {
        this.dTerminalBuildingID = dTerminalBuildingID;
    }

    /**
     * 退改公式，对客户
     */
    public String getRefundInfo() {
        return this.refundInfo;
    }

    public void setRefundInfo(String refundInfo) {
        this.refundInfo = refundInfo;
    }

    /**
     * 退改公式，对供应商
     */
    public String getSupplierRefundInfo() {
        return this.supplierRefundInfo;
    }

    public void setSupplierRefundInfo(String supplierRefundInfo) {
        this.supplierRefundInfo = supplierRefundInfo;
    }

    /**
     * 让利金额
     */
    public BigDecimal getOriginAttachAmount() {
        return this.originAttachAmount;
    }

    public void setOriginAttachAmount(BigDecimal originAttachAmount) {
        this.originAttachAmount = originAttachAmount;
    }

    /**
     * 餐食类型 0: 表示无配餐,1:表示正餐:,2:表示小吃
     */
    public Integer getMealType() {
        return this.mealType;
    }

    public void setMealType(Integer mealType) {
        this.mealType = mealType;
    }

    // Used by DatumWriter. Applications should not call.
    @Override
    public Object get(int fieldPos) {
        switch(fieldPos) {
            case 0: return (String) this.passengerName;
            case 1: return (int) this.sequence;
            case 2: return (String) this.flight;
            case 3: return (Calendar) this.takeOffTime;
            case 4: return (Calendar) this.arrivalTime;
            case 5: return (String) this.ticketNo;
            case 6: return (String) this.subClass;
            case 7: return TypeConverter.convert(this.printPrice, String.class);
            case 8: return (int) this.dCity;
            case 9: return (int) this.aCity;
            case 10: return (String) this.dPort;
            case 11: return (String) this.aPort;
            case 12: return TypeConverter.convert(this.subsidy, String.class);
            case 13: return TypeConverter.convert(this.oilFee, String.class);
            case 14: return TypeConverter.convert(this.tax, String.class);
            case 15: return (String) this.airline;
            case 16: return (String) this.airType;
            case 17: return (String) this.shareFlight;
            case 18: return (String) this.dTerminal;
            case 19: return (String) this.aTerminal;
            case 20: return (String) this.flightTime;
            case 21: return (boolean) this.isRebookPolicyMatch;
            case 22: return (String) this.airLineCode;
            case 23: return (List<TicketingOrderRebookingStopInfo>) this.ticketingOrderRebookingStopInfoList;
            case 24: return (String) this.clazz;
            case 25: return TypeConverter.convert(this.rebookFee, String.class);
            case 26: return (String) this.craftType;
            case 27: return TypeConverter.convert(this.aTerminalBuildingID, String.class);
            case 28: return TypeConverter.convert(this.dTerminalBuildingID, String.class);
            case 29: return (String) this.refundInfo;
            case 30: return (String) this.supplierRefundInfo;
            case 31: return TypeConverter.convert(this.originAttachAmount, String.class);
            case 32: return (Integer) this.mealType;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @Override
    public void put(int fieldPos, Object fieldValue) {
        switch(fieldPos) {
            case 0: this.passengerName = (String)fieldValue; break;
            case 1: this.sequence = (int)fieldValue; break;
            case 2: this.flight = (String)fieldValue; break;
            case 3: this.takeOffTime = (Calendar)fieldValue; break;
            case 4: this.arrivalTime = (Calendar)fieldValue; break;
            case 5: this.ticketNo = (String)fieldValue; break;
            case 6: this.subClass = (String)fieldValue; break;
            case 7: this.printPrice = TypeConverter.convert((String)fieldValue, BigDecimal.class); break;
            case 8: this.dCity = (int)fieldValue; break;
            case 9: this.aCity = (int)fieldValue; break;
            case 10: this.dPort = (String)fieldValue; break;
            case 11: this.aPort = (String)fieldValue; break;
            case 12: this.subsidy = TypeConverter.convert((String)fieldValue, BigDecimal.class); break;
            case 13: this.oilFee = TypeConverter.convert((String)fieldValue, BigDecimal.class); break;
            case 14: this.tax = TypeConverter.convert((String)fieldValue, BigDecimal.class); break;
            case 15: this.airline = (String)fieldValue; break;
            case 16: this.airType = (String)fieldValue; break;
            case 17: this.shareFlight = (String)fieldValue; break;
            case 18: this.dTerminal = (String)fieldValue; break;
            case 19: this.aTerminal = (String)fieldValue; break;
            case 20: this.flightTime = (String)fieldValue; break;
            case 21: this.isRebookPolicyMatch = (boolean)fieldValue; break;
            case 22: this.airLineCode = (String)fieldValue; break;
            case 23: this.ticketingOrderRebookingStopInfoList = (List<TicketingOrderRebookingStopInfo>)fieldValue; break;
            case 24: this.clazz = (String)fieldValue; break;
            case 25: this.rebookFee = TypeConverter.convert((String)fieldValue, BigDecimal.class); break;
            case 26: this.craftType = (String)fieldValue; break;
            case 27: this.aTerminalBuildingID = TypeConverter.convert((String)fieldValue, BigDecimal.class); break;
            case 28: this.dTerminalBuildingID = TypeConverter.convert((String)fieldValue, BigDecimal.class); break;
            case 29: this.refundInfo = (String)fieldValue; break;
            case 30: this.supplierRefundInfo = (String)fieldValue; break;
            case 31: this.originAttachAmount = TypeConverter.convert((String)fieldValue, BigDecimal.class); break;
            case 32: this.mealType = (Integer)fieldValue; break;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in put()");
        }
    }

    @Override
    public Object get(String fieldName) {
        Schema schema = getSchema();
        if (!(schema instanceof RecordSchema)) {
            return null;
        }
        Field field = ((RecordSchema) schema).getField(fieldName);
        return field != null ? get(field.getPos()) : null;
    }

    @Override
    public void put(String fieldName, Object fieldValue) {
        Schema schema = getSchema();
        if (!(schema instanceof RecordSchema)) {
            return;
        }
        Field field = ((RecordSchema) schema).getField(fieldName);
        if (field != null) {
            put(field.getPos(), fieldValue);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final TicketingOrderRebookingDetailInfo other = (TicketingOrderRebookingDetailInfo)obj;
        return
            Objects.equal(this.passengerName, other.passengerName) && 
            Objects.equal(this.sequence, other.sequence) && 
            Objects.equal(this.flight, other.flight) && 
            Objects.equal(this.takeOffTime, other.takeOffTime) && 
            Objects.equal(this.arrivalTime, other.arrivalTime) && 
            Objects.equal(this.ticketNo, other.ticketNo) && 
            Objects.equal(this.subClass, other.subClass) && 
            Objects.equal(this.printPrice, other.printPrice) && 
            Objects.equal(this.dCity, other.dCity) && 
            Objects.equal(this.aCity, other.aCity) && 
            Objects.equal(this.dPort, other.dPort) && 
            Objects.equal(this.aPort, other.aPort) && 
            Objects.equal(this.subsidy, other.subsidy) && 
            Objects.equal(this.oilFee, other.oilFee) && 
            Objects.equal(this.tax, other.tax) && 
            Objects.equal(this.airline, other.airline) && 
            Objects.equal(this.airType, other.airType) && 
            Objects.equal(this.shareFlight, other.shareFlight) && 
            Objects.equal(this.dTerminal, other.dTerminal) && 
            Objects.equal(this.aTerminal, other.aTerminal) && 
            Objects.equal(this.flightTime, other.flightTime) && 
            Objects.equal(this.isRebookPolicyMatch, other.isRebookPolicyMatch) && 
            Objects.equal(this.airLineCode, other.airLineCode) && 
            Objects.equal(this.ticketingOrderRebookingStopInfoList, other.ticketingOrderRebookingStopInfoList) && 
            Objects.equal(this.clazz, other.clazz) && 
            Objects.equal(this.rebookFee, other.rebookFee) && 
            Objects.equal(this.craftType, other.craftType) && 
            Objects.equal(this.aTerminalBuildingID, other.aTerminalBuildingID) && 
            Objects.equal(this.dTerminalBuildingID, other.dTerminalBuildingID) && 
            Objects.equal(this.refundInfo, other.refundInfo) && 
            Objects.equal(this.supplierRefundInfo, other.supplierRefundInfo) && 
            Objects.equal(this.originAttachAmount, other.originAttachAmount) && 
            Objects.equal(this.mealType, other.mealType);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + Objects.hashCode(this.passengerName);
        result = 31 * result + Objects.hashCode(this.sequence);
        result = 31 * result + Objects.hashCode(this.flight);
        result = 31 * result + Objects.hashCode(this.takeOffTime);
        result = 31 * result + Objects.hashCode(this.arrivalTime);
        result = 31 * result + Objects.hashCode(this.ticketNo);
        result = 31 * result + Objects.hashCode(this.subClass);
        result = 31 * result + Objects.hashCode(this.printPrice);
        result = 31 * result + Objects.hashCode(this.dCity);
        result = 31 * result + Objects.hashCode(this.aCity);
        result = 31 * result + Objects.hashCode(this.dPort);
        result = 31 * result + Objects.hashCode(this.aPort);
        result = 31 * result + Objects.hashCode(this.subsidy);
        result = 31 * result + Objects.hashCode(this.oilFee);
        result = 31 * result + Objects.hashCode(this.tax);
        result = 31 * result + Objects.hashCode(this.airline);
        result = 31 * result + Objects.hashCode(this.airType);
        result = 31 * result + Objects.hashCode(this.shareFlight);
        result = 31 * result + Objects.hashCode(this.dTerminal);
        result = 31 * result + Objects.hashCode(this.aTerminal);
        result = 31 * result + Objects.hashCode(this.flightTime);
        result = 31 * result + Objects.hashCode(this.isRebookPolicyMatch);
        result = 31 * result + Objects.hashCode(this.airLineCode);
        result = 31 * result + Objects.hashCode(this.ticketingOrderRebookingStopInfoList);
        result = 31 * result + Objects.hashCode(this.clazz);
        result = 31 * result + Objects.hashCode(this.rebookFee);
        result = 31 * result + Objects.hashCode(this.craftType);
        result = 31 * result + Objects.hashCode(this.aTerminalBuildingID);
        result = 31 * result + Objects.hashCode(this.dTerminalBuildingID);
        result = 31 * result + Objects.hashCode(this.refundInfo);
        result = 31 * result + Objects.hashCode(this.supplierRefundInfo);
        result = 31 * result + Objects.hashCode(this.originAttachAmount);
        result = 31 * result + Objects.hashCode(this.mealType);

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("passengerName", passengerName)
            .add("sequence", sequence)
            .add("flight", flight)
            .add("takeOffTime", takeOffTime)
            .add("arrivalTime", arrivalTime)
            .add("ticketNo", ticketNo)
            .add("subClass", subClass)
            .add("printPrice", printPrice)
            .add("dCity", dCity)
            .add("aCity", aCity)
            .add("dPort", dPort)
            .add("aPort", aPort)
            .add("subsidy", subsidy)
            .add("oilFee", oilFee)
            .add("tax", tax)
            .add("airline", airline)
            .add("airType", airType)
            .add("shareFlight", shareFlight)
            .add("dTerminal", dTerminal)
            .add("aTerminal", aTerminal)
            .add("flightTime", flightTime)
            .add("isRebookPolicyMatch", isRebookPolicyMatch)
            .add("airLineCode", airLineCode)
            .add("ticketingOrderRebookingStopInfoList", ticketingOrderRebookingStopInfoList)
            .add("clazz", clazz)
            .add("rebookFee", rebookFee)
            .add("craftType", craftType)
            .add("aTerminalBuildingID", aTerminalBuildingID)
            .add("dTerminalBuildingID", dTerminalBuildingID)
            .add("refundInfo", refundInfo)
            .add("supplierRefundInfo", supplierRefundInfo)
            .add("originAttachAmount", originAttachAmount)
            .add("mealType", mealType)
            .toString();
    }
}