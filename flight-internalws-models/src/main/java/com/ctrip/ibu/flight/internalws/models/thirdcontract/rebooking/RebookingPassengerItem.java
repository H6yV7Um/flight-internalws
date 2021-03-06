/**
 * Autogenerated by soa-sdk-toolkit
 *
 * DO NOT EDIT DIRECTLY
 */
package com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking;

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
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 *         改签乘客信息
 *       
 */
@DtoDoc("改签乘客信息")
@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RebookingPassengerItem", namespace = "http://soa.ctrip.com/flight/Order/FlightOrderRebooking/v1", propOrder = {
    "passengerName",
    "bookingUser",
    "ageType",
    "cardType",
    "cardNo",
    "birthDayDate",
    "nationality",
    "cardExpiryDate",
    "ticketInfoList",
    "originTicketInfoList",
    "courtesyTitle",
    "gender"
})
@SuppressWarnings("all")
public class RebookingPassengerItem implements SpecificRecord {

    private static final long serialVersionUID = 1L;

	public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"RebookingPassengerItem\",\"namespace\":\"" + RebookingPassengerItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"passengerName\",\"type\":[\"string\",\"null\"]},{\"name\":\"bookingUser\",\"type\":[\"string\",\"null\"]},{\"name\":\"ageType\",\"type\":[\"string\",\"null\"]},{\"name\":\"cardType\",\"type\":[\"int\",\"null\"]},{\"name\":\"cardNo\",\"type\":[\"string\",\"null\"]},{\"name\":\"birthDayDate\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"nationality\",\"type\":[\"string\",\"null\"]},{\"name\":\"cardExpiryDate\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"ticketInfoList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"TicketInfoItem\",\"namespace\":\"" + TicketInfoItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"sequence\",\"type\":\"int\"},{\"name\":\"airLineCode\",\"type\":[\"string\",\"null\"]},{\"name\":\"ticketNO\",\"type\":[\"string\",\"null\"]}]}}},{\"name\":\"originTicketInfoList\",\"type\":{\"type\":\"array\",\"items\":\"TicketInfoItem\"}},{\"name\":\"courtesyTitle\",\"type\":[\"string\",\"null\"]},{\"name\":\"gender\",\"type\":[\"string\",\"null\"]}]}");

    @Override
    public Schema getSchema() { return SCHEMA; }

    public RebookingPassengerItem(
        String passengerName, 
        String bookingUser, 
        String ageType, 
        Integer cardType, 
        String cardNo, 
        Calendar birthDayDate, 
        String nationality, 
        Calendar cardExpiryDate, 
        List<TicketInfoItem> ticketInfoList, 
        List<TicketInfoItem> originTicketInfoList, 
        String courtesyTitle, 
        String gender) {
        this.passengerName = passengerName;
        this.bookingUser = bookingUser;
        this.ageType = ageType;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.birthDayDate = birthDayDate;
        this.nationality = nationality;
        this.cardExpiryDate = cardExpiryDate;
        this.ticketInfoList = ticketInfoList;
        this.originTicketInfoList = originTicketInfoList;
        this.courtesyTitle = courtesyTitle;
        this.gender = gender;
    }

    public RebookingPassengerItem() {
    }

    @FieldDoc("乘客")
    @JsonProperty("PassengerName")
    @XmlElement(name = "PassengerName")
    private String passengerName;

    @FieldDoc("订位乘机人，当在Eterm里时，可能是生僻字就是拼音，但乘机人会是汉字")
    @JsonProperty("BookingUser")
    @XmlElement(name = "BookingUser")
    private String bookingUser;

    @FieldDoc("乘客类型ADU,CHI,BAB")
    @JsonProperty("AgeType")
    @XmlElement(name = "AgeType")
    private String ageType;

    @FieldDoc("身份证件类型")
    @JsonProperty("CardType")
    @XmlElement(name = "CardType", nillable = true)
    private Integer cardType;

    @FieldDoc("乘客的证件号码")
    @JsonProperty("CardNo")
    @XmlElement(name = "CardNo")
    private String cardNo;

    @FieldDoc("出生日期 婴儿必传")
    @JsonProperty("BirthDayDate")
    @XmlElement(name = "BirthDayDate", nillable = true)
    private Calendar birthDayDate;

    @FieldDoc("乘客国籍")
    @JsonProperty("Nationality")
    @XmlElement(name = "Nationality")
    private String nationality;

    @FieldDoc("证件有效期")
    @JsonProperty("CardExpiryDate")
    @XmlElement(name = "CardExpiryDate", nillable = true)
    private Calendar cardExpiryDate;

    @FieldDoc("票号信息，存json")
    @JsonProperty("TicketInfoList")
    @XmlElement(name = "TicketInfoItem")
    @XmlElementWrapper(name = "TicketInfoList")
    private List<TicketInfoItem> ticketInfoList;

    @FieldDoc("原始票号信息")
    @JsonProperty("OriginTicketInfoList")
    @XmlElement(name = "TicketInfoItem")
    @XmlElementWrapper(name = "OriginTicketInfoList")
    private List<TicketInfoItem> originTicketInfoList;

    @FieldDoc("称谓")
    @JsonProperty("CourtesyTitle")
    @XmlElement(name = "CourtesyTitle")
    private String courtesyTitle;

    @FieldDoc("性别")
    @JsonProperty("Gender")
    @XmlElement(name = "Gender")
    private String gender;

    /**
     * 乘客
     */
    public String getPassengerName() {
        return this.passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     * 订位乘机人，当在Eterm里时，可能是生僻字就是拼音，但乘机人会是汉字
     */
    public String getBookingUser() {
        return this.bookingUser;
    }

    public void setBookingUser(String bookingUser) {
        this.bookingUser = bookingUser;
    }

    /**
     * 乘客类型ADU,CHI,BAB
     */
    public String getAgeType() {
        return this.ageType;
    }

    public void setAgeType(String ageType) {
        this.ageType = ageType;
    }

    /**
     * 身份证件类型
     */
    public Integer getCardType() {
        return this.cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * 乘客的证件号码
     */
    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 出生日期 婴儿必传
     */
    public Calendar getBirthDayDate() {
        return this.birthDayDate;
    }

    public void setBirthDayDate(Calendar birthDayDate) {
        this.birthDayDate = birthDayDate;
    }

    /**
     * 乘客国籍
     */
    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * 证件有效期
     */
    public Calendar getCardExpiryDate() {
        return this.cardExpiryDate;
    }

    public void setCardExpiryDate(Calendar cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    /**
     * 票号信息，存json
     */
    public List<TicketInfoItem> getTicketInfoList() {
        return this.ticketInfoList;
    }

    public void setTicketInfoList(List<TicketInfoItem> ticketInfoList) {
        this.ticketInfoList = ticketInfoList;
    }

    /**
     * 原始票号信息
     */
    public List<TicketInfoItem> getOriginTicketInfoList() {
        return this.originTicketInfoList;
    }

    public void setOriginTicketInfoList(List<TicketInfoItem> originTicketInfoList) {
        this.originTicketInfoList = originTicketInfoList;
    }

    /**
     * 称谓
     */
    public String getCourtesyTitle() {
        return this.courtesyTitle;
    }

    public void setCourtesyTitle(String courtesyTitle) {
        this.courtesyTitle = courtesyTitle;
    }

    /**
     * 性别
     */
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Used by DatumWriter. Applications should not call.
    @Override
    public Object get(int fieldPos) {
        switch(fieldPos) {
            case 0: return (String) this.passengerName;
            case 1: return (String) this.bookingUser;
            case 2: return (String) this.ageType;
            case 3: return (Integer) this.cardType;
            case 4: return (String) this.cardNo;
            case 5: return (Calendar) this.birthDayDate;
            case 6: return (String) this.nationality;
            case 7: return (Calendar) this.cardExpiryDate;
            case 8: return (List<TicketInfoItem>) this.ticketInfoList;
            case 9: return (List<TicketInfoItem>) this.originTicketInfoList;
            case 10: return (String) this.courtesyTitle;
            case 11: return (String) this.gender;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @Override
    public void put(int fieldPos, Object fieldValue) {
        switch(fieldPos) {
            case 0: this.passengerName = (String)fieldValue; break;
            case 1: this.bookingUser = (String)fieldValue; break;
            case 2: this.ageType = (String)fieldValue; break;
            case 3: this.cardType = (Integer)fieldValue; break;
            case 4: this.cardNo = (String)fieldValue; break;
            case 5: this.birthDayDate = (Calendar)fieldValue; break;
            case 6: this.nationality = (String)fieldValue; break;
            case 7: this.cardExpiryDate = (Calendar)fieldValue; break;
            case 8: this.ticketInfoList = (List<TicketInfoItem>)fieldValue; break;
            case 9: this.originTicketInfoList = (List<TicketInfoItem>)fieldValue; break;
            case 10: this.courtesyTitle = (String)fieldValue; break;
            case 11: this.gender = (String)fieldValue; break;
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

        final RebookingPassengerItem other = (RebookingPassengerItem)obj;
        return
            Objects.equal(this.passengerName, other.passengerName) && 
            Objects.equal(this.bookingUser, other.bookingUser) && 
            Objects.equal(this.ageType, other.ageType) && 
            Objects.equal(this.cardType, other.cardType) && 
            Objects.equal(this.cardNo, other.cardNo) && 
            Objects.equal(this.birthDayDate, other.birthDayDate) && 
            Objects.equal(this.nationality, other.nationality) && 
            Objects.equal(this.cardExpiryDate, other.cardExpiryDate) && 
            Objects.equal(this.ticketInfoList, other.ticketInfoList) && 
            Objects.equal(this.originTicketInfoList, other.originTicketInfoList) && 
            Objects.equal(this.courtesyTitle, other.courtesyTitle) && 
            Objects.equal(this.gender, other.gender);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + Objects.hashCode(this.passengerName);
        result = 31 * result + Objects.hashCode(this.bookingUser);
        result = 31 * result + Objects.hashCode(this.ageType);
        result = 31 * result + Objects.hashCode(this.cardType);
        result = 31 * result + Objects.hashCode(this.cardNo);
        result = 31 * result + Objects.hashCode(this.birthDayDate);
        result = 31 * result + Objects.hashCode(this.nationality);
        result = 31 * result + Objects.hashCode(this.cardExpiryDate);
        result = 31 * result + Objects.hashCode(this.ticketInfoList);
        result = 31 * result + Objects.hashCode(this.originTicketInfoList);
        result = 31 * result + Objects.hashCode(this.courtesyTitle);
        result = 31 * result + Objects.hashCode(this.gender);

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("passengerName", passengerName)
            .add("bookingUser", bookingUser)
            .add("ageType", ageType)
            .add("cardType", cardType)
            .add("cardNo", cardNo)
            .add("birthDayDate", birthDayDate)
            .add("nationality", nationality)
            .add("cardExpiryDate", cardExpiryDate)
            .add("ticketInfoList", ticketInfoList)
            .add("originTicketInfoList", originTicketInfoList)
            .add("courtesyTitle", courtesyTitle)
            .add("gender", gender)
            .toString();
    }
}