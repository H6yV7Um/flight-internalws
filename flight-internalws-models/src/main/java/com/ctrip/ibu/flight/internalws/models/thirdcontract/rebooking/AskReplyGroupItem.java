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
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 *         咨询单回复组
 *       
 */
@DtoDoc("咨询单回复组")
@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AskReplyGroupItem", namespace = "http://soa.ctrip.com/flight/Order/FlightOrderRebooking/v1", propOrder = {
    "groupNo",
    "askReplySequenceRelationList",
    "askReplyDetailList",
    "bNoShowFeeDetail",
    "aNoShowFeeDetail"
})
@SuppressWarnings("all")
public class AskReplyGroupItem implements SpecificRecord {

    private static final long serialVersionUID = 1L;

	public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"AskReplyGroupItem\",\"namespace\":\"" + AskReplyGroupItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"groupNo\",\"type\":\"int\"},{\"name\":\"askReplySequenceRelationList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AskReplySequenceRelationItem\",\"namespace\":\"" + AskReplySequenceRelationItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"segmentNo\",\"type\":\"int\"},{\"name\":\"originSequence\",\"type\":\"int\"},{\"name\":\"newSequence\",\"type\":\"int\"},{\"name\":\"isRebookSequence\",\"type\":\"boolean\"},{\"name\":\"isSurface\",\"type\":\"boolean\"}]}}},{\"name\":\"askReplyDetailList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AskReplyDetailItem\",\"namespace\":\"" + AskReplyDetailItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"rescheduleAskRepDetailID\",\"type\":\"long\"},{\"name\":\"segmentNo\",\"type\":\"int\"},{\"name\":\"sequence\",\"type\":\"int\"},{\"name\":\"takeOffTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"arrivalTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"aPortBuildingID\",\"type\":[\"int\",\"null\"]},{\"name\":\"aPortBuildingName\",\"type\":[\"string\",\"null\"]},{\"name\":\"dPortBuildingID\",\"type\":[\"int\",\"null\"]},{\"name\":\"dPortBuildingName\",\"type\":[\"string\",\"null\"]},{\"name\":\"flightNo\",\"type\":[\"string\",\"null\"]},{\"name\":\"subClass\",\"type\":[\"string\",\"null\"]},{\"name\":\"Class\",\"type\":[\"string\",\"null\"]},{\"name\":\"aPort\",\"type\":[\"string\",\"null\"]},{\"name\":\"dPort\",\"type\":[\"string\",\"null\"]},{\"name\":\"recordNo\",\"type\":[\"string\",\"null\"]},{\"name\":\"officeNO\",\"type\":[\"string\",\"null\"]},{\"name\":\"carrierFlightNo\",\"type\":[\"string\",\"null\"]},{\"name\":\"dCity\",\"type\":[\"int\",\"null\"]},{\"name\":\"aCity\",\"type\":[\"int\",\"null\"]},{\"name\":\"flyType\",\"type\":[\"string\",\"null\"]},{\"name\":\"craftType\",\"type\":[\"string\",\"null\"]},{\"name\":\"noShowTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"isConfirm\",\"type\":[\"string\",\"null\"]},{\"name\":\"aNoShowNonRer\",\"type\":[\"string\",\"null\"]}]}}},{\"name\":\"bNoShowFeeDetail\",\"type\":{\"type\":\"record\",\"name\":\"AskReplyFeeDetail\",\"namespace\":\"" + AskReplyFeeDetail.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"feeDetailID\",\"type\":\"long\"},{\"name\":\"payAmount\",\"type\":[\"string\",\"null\"]},{\"name\":\"printPrice\",\"type\":[\"string\",\"null\"]},{\"name\":\"salePrice\",\"type\":[\"string\",\"null\"]},{\"name\":\"oilFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"tax\",\"type\":[\"string\",\"null\"]},{\"name\":\"currency\",\"type\":[\"string\",\"null\"]},{\"name\":\"currencyRate\",\"type\":[\"string\",\"null\"]},{\"name\":\"totalRebookFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"dateChangeFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"priceDifferential\",\"type\":[\"string\",\"null\"]},{\"name\":\"taxDifferential\",\"type\":[\"string\",\"null\"]},{\"name\":\"ctripServiceFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierCurrency\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierCurrencyRate\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierTotalRebookFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierDateChangeFee\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierPriceDifferential\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierTaxDifferential\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierSalePrice\",\"type\":[\"string\",\"null\"]},{\"name\":\"supplierTax\",\"type\":[\"string\",\"null\"]}]}},{\"name\":\"aNoShowFeeDetail\",\"type\":\"AskReplyFeeDetail\"}]}");

    @Override
    public Schema getSchema() { return SCHEMA; }

    public AskReplyGroupItem(
        int groupNo, 
        List<AskReplySequenceRelationItem> askReplySequenceRelationList, 
        List<AskReplyDetailItem> askReplyDetailList, 
        AskReplyFeeDetail bNoShowFeeDetail, 
        AskReplyFeeDetail aNoShowFeeDetail) {
        this.groupNo = groupNo;
        this.askReplySequenceRelationList = askReplySequenceRelationList;
        this.askReplyDetailList = askReplyDetailList;
        this.bNoShowFeeDetail = bNoShowFeeDetail;
        this.aNoShowFeeDetail = aNoShowFeeDetail;
    }

    public AskReplyGroupItem() {
    }

    @FieldDoc("回复组序号")
    @JsonProperty("GroupNo")
    @XmlElement(name = "GroupNo")
    private int groupNo;

    @FieldDoc("改签程与原航程对应关系集合")
    @JsonProperty("AskReplySequenceRelationList")
    @XmlElement(name = "AskReplySequenceRelationItem")
    @XmlElementWrapper(name = "AskReplySequenceRelationList")
    private List<AskReplySequenceRelationItem> askReplySequenceRelationList;

    @FieldDoc("咨询单回复明细集合")
    @JsonProperty("AskReplyDetailList")
    @XmlElement(name = "AskReplyDetailItem")
    @XmlElementWrapper(name = "AskReplyDetailList")
    private List<AskReplyDetailItem> askReplyDetailList;

    @FieldDoc("NoShow前费用明细")
    @JsonProperty("BNoShowFeeDetail")
    @XmlElement(name = "BNoShowFeeDetail")
    private AskReplyFeeDetail bNoShowFeeDetail;

    @FieldDoc("NoShow后费用明细")
    @JsonProperty("ANoShowFeeDetail")
    @XmlElement(name = "ANoShowFeeDetail")
    private AskReplyFeeDetail aNoShowFeeDetail;

    /**
     * 回复组序号
     */
    public int getGroupNo() {
        return this.groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    /**
     * 改签程与原航程对应关系集合
     */
    public List<AskReplySequenceRelationItem> getAskReplySequenceRelationList() {
        return this.askReplySequenceRelationList;
    }

    public void setAskReplySequenceRelationList(List<AskReplySequenceRelationItem> askReplySequenceRelationList) {
        this.askReplySequenceRelationList = askReplySequenceRelationList;
    }

    /**
     * 咨询单回复明细集合
     */
    public List<AskReplyDetailItem> getAskReplyDetailList() {
        return this.askReplyDetailList;
    }

    public void setAskReplyDetailList(List<AskReplyDetailItem> askReplyDetailList) {
        this.askReplyDetailList = askReplyDetailList;
    }

    /**
     * NoShow前费用明细
     */
    public AskReplyFeeDetail getBNoShowFeeDetail() {
        return this.bNoShowFeeDetail;
    }

    public void setBNoShowFeeDetail(AskReplyFeeDetail bNoShowFeeDetail) {
        this.bNoShowFeeDetail = bNoShowFeeDetail;
    }

    /**
     * NoShow后费用明细
     */
    public AskReplyFeeDetail getANoShowFeeDetail() {
        return this.aNoShowFeeDetail;
    }

    public void setANoShowFeeDetail(AskReplyFeeDetail aNoShowFeeDetail) {
        this.aNoShowFeeDetail = aNoShowFeeDetail;
    }

    // Used by DatumWriter. Applications should not call.
    @Override
    public Object get(int fieldPos) {
        switch(fieldPos) {
            case 0: return (int) this.groupNo;
            case 1: return (List<AskReplySequenceRelationItem>) this.askReplySequenceRelationList;
            case 2: return (List<AskReplyDetailItem>) this.askReplyDetailList;
            case 3: return (AskReplyFeeDetail) this.bNoShowFeeDetail;
            case 4: return (AskReplyFeeDetail) this.aNoShowFeeDetail;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @Override
    public void put(int fieldPos, Object fieldValue) {
        switch(fieldPos) {
            case 0: this.groupNo = (int)fieldValue; break;
            case 1: this.askReplySequenceRelationList = (List<AskReplySequenceRelationItem>)fieldValue; break;
            case 2: this.askReplyDetailList = (List<AskReplyDetailItem>)fieldValue; break;
            case 3: this.bNoShowFeeDetail = (AskReplyFeeDetail)fieldValue; break;
            case 4: this.aNoShowFeeDetail = (AskReplyFeeDetail)fieldValue; break;
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

        final AskReplyGroupItem other = (AskReplyGroupItem)obj;
        return
            Objects.equal(this.groupNo, other.groupNo) && 
            Objects.equal(this.askReplySequenceRelationList, other.askReplySequenceRelationList) && 
            Objects.equal(this.askReplyDetailList, other.askReplyDetailList) && 
            Objects.equal(this.bNoShowFeeDetail, other.bNoShowFeeDetail) && 
            Objects.equal(this.aNoShowFeeDetail, other.aNoShowFeeDetail);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + Objects.hashCode(this.groupNo);
        result = 31 * result + Objects.hashCode(this.askReplySequenceRelationList);
        result = 31 * result + Objects.hashCode(this.askReplyDetailList);
        result = 31 * result + Objects.hashCode(this.bNoShowFeeDetail);
        result = 31 * result + Objects.hashCode(this.aNoShowFeeDetail);

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("groupNo", groupNo)
            .add("askReplySequenceRelationList", askReplySequenceRelationList)
            .add("askReplyDetailList", askReplyDetailList)
            .add("bNoShowFeeDetail", bNoShowFeeDetail)
            .add("aNoShowFeeDetail", aNoShowFeeDetail)
            .toString();
    }
}