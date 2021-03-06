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
 *         改签状态事件信息
 *       
 */
@DtoDoc("改签状态事件信息")
@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TicketingOrderRebookingFlowItem", namespace = "http://soa.ctrip.com/flight/Ticket/Rebooking/QueryApi/v1", propOrder = {
    "rescheduleFlowID",
    "flowNo",
    "isEstimate",
    "estimatedStartTime",
    "estimatedFinishTime",
    "processContent"
})
@SuppressWarnings("all")
public class TicketingOrderRebookingFlowItem implements SpecificRecord {

    private static final long serialVersionUID = 1L;

	public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"TicketingOrderRebookingFlowItem\",\"namespace\":\"" + TicketingOrderRebookingFlowItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"rescheduleFlowID\",\"type\":\"long\"},{\"name\":\"flowNo\",\"type\":[\"string\",\"null\"]},{\"name\":\"isEstimate\",\"type\":\"boolean\"},{\"name\":\"estimatedStartTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"estimatedFinishTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"processContent\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"TicketingOrderRebookingFlowKvItem\",\"namespace\":\"" + TicketingOrderRebookingFlowKvItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"key\",\"type\":[\"string\",\"null\"]},{\"name\":\"actionId\",\"type\":[\"string\",\"null\"]}]}}}]}");

    @Override
    public Schema getSchema() { return SCHEMA; }

    public TicketingOrderRebookingFlowItem(
        long rescheduleFlowID, 
        String flowNo, 
        boolean isEstimate, 
        Calendar estimatedStartTime, 
        Calendar estimatedFinishTime, 
        List<TicketingOrderRebookingFlowKvItem> processContent) {
        this.rescheduleFlowID = rescheduleFlowID;
        this.flowNo = flowNo;
        this.isEstimate = isEstimate;
        this.estimatedStartTime = estimatedStartTime;
        this.estimatedFinishTime = estimatedFinishTime;
        this.processContent = processContent;
    }

    public TicketingOrderRebookingFlowItem() {
        this.estimatedStartTime = new java.util.GregorianCalendar(1, 0, 1, 0, 0, 0);
        this.estimatedStartTime.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }

    @FieldDoc("状态事件编号(已废弃)")
    @JsonProperty("RescheduleFlowID")
    @XmlElement(name = "RescheduleFlowID")
    private long rescheduleFlowID;

    @FieldDoc("状态事件号")
    @JsonProperty("FlowNo")
    @XmlElement(name = "FlowNo")
    private String flowNo;

    @FieldDoc("是否预估")
    @JsonProperty("IsEstimate")
    @XmlElement(name = "IsEstimate")
    private boolean isEstimate;

    @FieldDoc("预计开始时间")
    @JsonProperty("EstimatedStartTime")
    @XmlElement(name = "EstimatedStartTime")
    private Calendar estimatedStartTime;

    @FieldDoc("预计完成时间")
    @JsonProperty("EstimatedFinishTime")
    @XmlElement(name = "EstimatedFinishTime", nillable = true)
    private Calendar estimatedFinishTime;

    @FieldDoc("状态事件内容")
    @JsonProperty("ProcessContent")
    @XmlElement(name = "TicketingOrderRebookingFlowKvItem")
    @XmlElementWrapper(name = "ProcessContent")
    private List<TicketingOrderRebookingFlowKvItem> processContent;

    /**
     * 状态事件编号(已废弃)
     */
    public long getRescheduleFlowID() {
        return this.rescheduleFlowID;
    }

    public void setRescheduleFlowID(long rescheduleFlowID) {
        this.rescheduleFlowID = rescheduleFlowID;
    }

    /**
     * 状态事件号
     */
    public String getFlowNo() {
        return this.flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    /**
     * 是否预估
     */
    public boolean getIsEstimate() {
        return this.isEstimate;
    }

    public void setIsEstimate(boolean isEstimate) {
        this.isEstimate = isEstimate;
    }

    /**
     * 预计开始时间
     */
    public Calendar getEstimatedStartTime() {
        return this.estimatedStartTime;
    }

    public void setEstimatedStartTime(Calendar estimatedStartTime) {
        this.estimatedStartTime = estimatedStartTime;
    }

    /**
     * 预计完成时间
     */
    public Calendar getEstimatedFinishTime() {
        return this.estimatedFinishTime;
    }

    public void setEstimatedFinishTime(Calendar estimatedFinishTime) {
        this.estimatedFinishTime = estimatedFinishTime;
    }

    /**
     * 状态事件内容
     */
    public List<TicketingOrderRebookingFlowKvItem> getProcessContent() {
        return this.processContent;
    }

    public void setProcessContent(List<TicketingOrderRebookingFlowKvItem> processContent) {
        this.processContent = processContent;
    }

    // Used by DatumWriter. Applications should not call.
    @Override
    public Object get(int fieldPos) {
        switch(fieldPos) {
            case 0: return (long) this.rescheduleFlowID;
            case 1: return (String) this.flowNo;
            case 2: return (boolean) this.isEstimate;
            case 3: return (Calendar) this.estimatedStartTime;
            case 4: return (Calendar) this.estimatedFinishTime;
            case 5: return (List<TicketingOrderRebookingFlowKvItem>) this.processContent;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @Override
    public void put(int fieldPos, Object fieldValue) {
        switch(fieldPos) {
            case 0: this.rescheduleFlowID = (long)fieldValue; break;
            case 1: this.flowNo = (String)fieldValue; break;
            case 2: this.isEstimate = (boolean)fieldValue; break;
            case 3: this.estimatedStartTime = (Calendar)fieldValue; break;
            case 4: this.estimatedFinishTime = (Calendar)fieldValue; break;
            case 5: this.processContent = (List<TicketingOrderRebookingFlowKvItem>)fieldValue; break;
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

        final TicketingOrderRebookingFlowItem other = (TicketingOrderRebookingFlowItem)obj;
        return
            Objects.equal(this.rescheduleFlowID, other.rescheduleFlowID) && 
            Objects.equal(this.flowNo, other.flowNo) && 
            Objects.equal(this.isEstimate, other.isEstimate) && 
            Objects.equal(this.estimatedStartTime, other.estimatedStartTime) && 
            Objects.equal(this.estimatedFinishTime, other.estimatedFinishTime) && 
            Objects.equal(this.processContent, other.processContent);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + Objects.hashCode(this.rescheduleFlowID);
        result = 31 * result + Objects.hashCode(this.flowNo);
        result = 31 * result + Objects.hashCode(this.isEstimate);
        result = 31 * result + Objects.hashCode(this.estimatedStartTime);
        result = 31 * result + Objects.hashCode(this.estimatedFinishTime);
        result = 31 * result + Objects.hashCode(this.processContent);

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("rescheduleFlowID", rescheduleFlowID)
            .add("flowNo", flowNo)
            .add("isEstimate", isEstimate)
            .add("estimatedStartTime", estimatedStartTime)
            .add("estimatedFinishTime", estimatedFinishTime)
            .add("processContent", processContent)
            .toString();
    }
}