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
 *         咨询单
 *       
 */
@DtoDoc("咨询单")
@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RescheduleAskItem", namespace = "http://soa.ctrip.com/flight/Order/FlightOrderRebooking/v1", propOrder = {
    "rescheduleAskID",
    "askStatus",
    "language",
    "bookingValidationDeadline",
    "askDetailList",
    "askReplyGroupList"
})
@SuppressWarnings("all")
public class RescheduleAskItem implements SpecificRecord {

    private static final long serialVersionUID = 1L;

	public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"RescheduleAskItem\",\"namespace\":\"" + RescheduleAskItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"rescheduleAskID\",\"type\":\"long\"},{\"name\":\"askStatus\",\"type\":[\"string\",\"null\"]},{\"name\":\"language\",\"type\":[\"string\",\"null\"]},{\"name\":\"bookingValidationDeadline\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"askDetailList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AskDetailItem\",\"namespace\":\"" + AskDetailItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"rescheduleAskDetailID\",\"type\":\"long\"},{\"name\":\"groupNo\",\"type\":\"int\"},{\"name\":\"daPort\",\"type\":[\"string\",\"null\"]},{\"name\":\"takeOffDate\",\"type\":[\"string\",\"null\"]},{\"name\":\"takeOffTime\",\"type\":[\"string\",\"null\"]},{\"name\":\"flightNo\",\"type\":[\"string\",\"null\"]},{\"name\":\"bestPrice\",\"type\":[\"string\",\"null\"]},{\"name\":\"flyType\",\"type\":[\"string\",\"null\"]},{\"name\":\"Class\",\"type\":[\"string\",\"null\"]},{\"name\":\"craftType\",\"type\":[\"string\",\"null\"]}]}}},{\"name\":\"askReplyGroupList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AskReplyGroupItem\",\"namespace\":\"" + AskReplyGroupItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"groupNo\",\"type\":\"int\"},{\"name\":\"askReplySequenceRelationList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AskReplySequenceRelationItem\",\"namespace\":\"" + AskReplySequenceRelationItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"segmentNo\",\"type\":\"int\"},{\"name\":\"originSequence\",\"type\":\"int\"},{\"name\":\"newSequence\",\"type\":\"int\"},{\"name\":\"isRebookSequence\",\"type\":\"boolean\"},{\"name\":\"isSurface\",\"type\":\"boolean\"}]}}},{\"name\":\"askReplyDetailList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AskReplyDetailItem\",\"namespace\":\"" + AskReplyDetailItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"rescheduleAskRepDetailID\",\"type\":\"long\"},{\"name\":\"segmentNo\",\"type\":\"int\"},{\"name\":\"sequence\",\"type\":\"int\"},{\"name\":\"takeOffTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"arrivalTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"aPortBuildingID\",\"type\":[\"int\",\"null\"]},{\"name\":\"aPortBuildingName\",\"type\":[\"string\",\"null\"]},{\"name\":\"dPortBuildingID\",\"type\":[\"int\",\"null\"]},{\"name\":\"dPortBuildingName\",\"type\":[\"string\",\"null\"]},{\"name\":\"flightNo\",\"type\":[\"string\",\"null\"]},{\"name\":\"subClass\",\"type\":[\"string\",\"null\"]},{\"name\":\"Class\",\"type\":[\"string\",\"null\"]},{\"name\":\"aPort\",\"type\":[\"string\",\"null\"]},{\"name\":\"dPort\",\"type\":[\"string\",\"null\"]},{\"name\":\"recordNo\",\"type\":[\"string\",\"null\"]},{\"name\":\"officeNO\",\"type\":[\"string\",\"null\"]},{\"name\":\"carrierFlightNo\",\"type\":[\"string\",\"null\"]},{\"name\":\"dCity\",\"type\":[\"int\",\"null\"]},{\"name\":\"aCity\",\"type\":[\"int\",\"null\"]},{\"name\":\"flyType\",\"type\":[\"string\",\"null\"]},{\"name\":\"craftType\",\"type\":[\"string\",\"null\"]},{\"name\":\"noShowTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"isConfirm\",\"type\":[\"string\",\"null\"]},{\"name\":\"aNoShowNonRer\",\"type\":[\"string\",\"null\"]}]}}},{\"name\":\"bNoShowFeeDetail\",\"type\":{\"type\":\"record\",\"name\":\"AskReplyFeeDetail\",\"namespace\":\"" + AskReplyFeeDetail.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"feeDetailID\",\"type\":\"long\"},{\"name\":\"payAmount\",\"type\":[\"string\",\"null\"]},{\"name\":\"printPrice\",\"type\":[\"string\",\"null\"]},{\"name\":\"salePrice\",\"type\":[\"string\",\"null\"]},{\"name\":\"oilFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"tax\",\"type\":[\"string\",\"null\"]},{\"name\":\"currency\",\"type\":[\"string\",\"null\"]},{\"name\":\"currencyRate\",\"type\":[\"string\",\"null\"]},{\"name\":\"totalRebookFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"dateChangeFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"priceDifferential\",\"type\":[\"string\",\"null\"]},{\"name\":\"taxDifferential\",\"type\":[\"string\",\"null\"]},{\"name\":\"ctripServiceFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierCurrency\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierCurrencyRate\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierTotalRebookFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierDateChangeFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierPriceDifferential\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierTaxDifferential\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierSalePrice\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierTax\",\"type\":[\"string\",\"null\"]}]}},{\"name\":\"aNoShowFeeDetail\",\"type\":\"AskReplyFeeDetail\"}]}}}]}");

    @Override
    public Schema getSchema() { return SCHEMA; }

    public RescheduleAskItem(
        long rescheduleAskID, 
        String askStatus, 
        String language, 
        Calendar bookingValidationDeadline, 
        List<AskDetailItem> askDetailList, 
        List<AskReplyGroupItem> askReplyGroupList) {
        this.rescheduleAskID = rescheduleAskID;
        this.askStatus = askStatus;
        this.language = language;
        this.bookingValidationDeadline = bookingValidationDeadline;
        this.askDetailList = askDetailList;
        this.askReplyGroupList = askReplyGroupList;
    }

    public RescheduleAskItem() {
        this.bookingValidationDeadline = new java.util.GregorianCalendar(1, 0, 1, 0, 0, 0);
        this.bookingValidationDeadline.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }

    @FieldDoc("咨询单编号")
    @JsonProperty("RescheduleAskID")
    @XmlElement(name = "RescheduleAskID")
    private long rescheduleAskID;

    @FieldDoc("咨询单状态（W：待处理，P：处理中，D：员工已回填，S：用户已确认咨询单完成）")
    @JsonProperty("AskStatus")
    @XmlElement(name = "AskStatus")
    private String askStatus;

    @FieldDoc("语种" +
              "执行标准 " +
              "Locale: language + country （取 2 字码） " +
              "Language: ISO 639 ，Country: ISO 3166 " +
              "例如：en-US，ja-JP，ko-KR，fr-FR，de-DE，es-ES，ru-RU，zh-HK，en-HK，en-SG，ms-MY，id-ID，th-TH，zh-CN")
    @JsonProperty("Language")
    @XmlElement(name = "Language")
    private String language;

    @FieldDoc("验舱验价超时时限,超过此时限的需要重新发起验舱验价")
    @JsonProperty("BookingValidationDeadline")
    @XmlElement(name = "BookingValidationDeadline")
    private Calendar bookingValidationDeadline;

    @FieldDoc("咨询明细")
    @JsonProperty("AskDetailList")
    @XmlElement(name = "AskDetailItem")
    @XmlElementWrapper(name = "AskDetailList")
    private List<AskDetailItem> askDetailList;

    @FieldDoc("回复明细")
    @JsonProperty("AskReplyGroupList")
    @XmlElement(name = "AskReplyGroupItem")
    @XmlElementWrapper(name = "AskReplyGroupList")
    private List<AskReplyGroupItem> askReplyGroupList;

    /**
     * 咨询单编号
     */
    public long getRescheduleAskID() {
        return this.rescheduleAskID;
    }

    public void setRescheduleAskID(long rescheduleAskID) {
        this.rescheduleAskID = rescheduleAskID;
    }

    /**
     * 咨询单状态（W：待处理，P：处理中，D：员工已回填，S：用户已确认咨询单完成）
     */
    public String getAskStatus() {
        return this.askStatus;
    }

    public void setAskStatus(String askStatus) {
        this.askStatus = askStatus;
    }

    /**
     * 语种
     *             执行标准 
     *             Locale: language + country （取 2 字码） 
     *             Language: ISO 639 ，Country: ISO 3166 
     *             例如：en-US，ja-JP，ko-KR，fr-FR，de-DE，es-ES，ru-RU，zh-HK，en-HK，en-SG，ms-MY，id-ID，th-TH，zh-CN
     */
    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 验舱验价超时时限,超过此时限的需要重新发起验舱验价
     */
    public Calendar getBookingValidationDeadline() {
        return this.bookingValidationDeadline;
    }

    public void setBookingValidationDeadline(Calendar bookingValidationDeadline) {
        this.bookingValidationDeadline = bookingValidationDeadline;
    }

    /**
     * 咨询明细
     */
    public List<AskDetailItem> getAskDetailList() {
        return this.askDetailList;
    }

    public void setAskDetailList(List<AskDetailItem> askDetailList) {
        this.askDetailList = askDetailList;
    }

    /**
     * 回复明细
     */
    public List<AskReplyGroupItem> getAskReplyGroupList() {
        return this.askReplyGroupList;
    }

    public void setAskReplyGroupList(List<AskReplyGroupItem> askReplyGroupList) {
        this.askReplyGroupList = askReplyGroupList;
    }

    // Used by DatumWriter. Applications should not call.
    @Override
    public Object get(int fieldPos) {
        switch(fieldPos) {
            case 0: return (long) this.rescheduleAskID;
            case 1: return (String) this.askStatus;
            case 2: return (String) this.language;
            case 3: return (Calendar) this.bookingValidationDeadline;
            case 4: return (List<AskDetailItem>) this.askDetailList;
            case 5: return (List<AskReplyGroupItem>) this.askReplyGroupList;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @Override
    public void put(int fieldPos, Object fieldValue) {
        switch(fieldPos) {
            case 0: this.rescheduleAskID = (long)fieldValue; break;
            case 1: this.askStatus = (String)fieldValue; break;
            case 2: this.language = (String)fieldValue; break;
            case 3: this.bookingValidationDeadline = (Calendar)fieldValue; break;
            case 4: this.askDetailList = (List<AskDetailItem>)fieldValue; break;
            case 5: this.askReplyGroupList = (List<AskReplyGroupItem>)fieldValue; break;
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

        final RescheduleAskItem other = (RescheduleAskItem)obj;
        return
            Objects.equal(this.rescheduleAskID, other.rescheduleAskID) && 
            Objects.equal(this.askStatus, other.askStatus) && 
            Objects.equal(this.language, other.language) && 
            Objects.equal(this.bookingValidationDeadline, other.bookingValidationDeadline) && 
            Objects.equal(this.askDetailList, other.askDetailList) && 
            Objects.equal(this.askReplyGroupList, other.askReplyGroupList);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + Objects.hashCode(this.rescheduleAskID);
        result = 31 * result + Objects.hashCode(this.askStatus);
        result = 31 * result + Objects.hashCode(this.language);
        result = 31 * result + Objects.hashCode(this.bookingValidationDeadline);
        result = 31 * result + Objects.hashCode(this.askDetailList);
        result = 31 * result + Objects.hashCode(this.askReplyGroupList);

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("rescheduleAskID", rescheduleAskID)
            .add("askStatus", askStatus)
            .add("language", language)
            .add("bookingValidationDeadline", bookingValidationDeadline)
            .add("askDetailList", askDetailList)
            .add("askReplyGroupList", askReplyGroupList)
            .toString();
    }
}