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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 改签明细的经停信息
 */
@DtoDoc("改签明细的经停信息")
@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TicketingOrderRebookingStopInfo", namespace = "http://soa.ctrip.com/flight/Ticket/Rebooking/QueryApi/v1", propOrder = {
    "stopCity",
    "stopTime"
})
@SuppressWarnings("all")
public class TicketingOrderRebookingStopInfo implements SpecificRecord {

    private static final long serialVersionUID = 1L;

	public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"TicketingOrderRebookingStopInfo\",\"namespace\":\"" + TicketingOrderRebookingStopInfo.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"stopCity\",\"type\":[\"string\",\"null\"]},{\"name\":\"stopTime\",\"type\":[\"string\",\"null\"]}]}");

    @Override
    public Schema getSchema() { return SCHEMA; }

    public TicketingOrderRebookingStopInfo(
        String stopCity, 
        String stopTime) {
        this.stopCity = stopCity;
        this.stopTime = stopTime;
    }

    public TicketingOrderRebookingStopInfo() {
    }

    @FieldDoc("经停城市")
    @JsonProperty("StopCity")
    @XmlElement(name = "StopCity")
    private String stopCity;

    @FieldDoc("经停时长")
    @JsonProperty("StopTime")
    @XmlElement(name = "StopTime")
    private String stopTime;

    /**
     * 经停城市
     */
    public String getStopCity() {
        return this.stopCity;
    }

    public void setStopCity(String stopCity) {
        this.stopCity = stopCity;
    }

    /**
     * 经停时长
     */
    public String getStopTime() {
        return this.stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    // Used by DatumWriter. Applications should not call.
    @Override
    public Object get(int fieldPos) {
        switch(fieldPos) {
            case 0: return (String) this.stopCity;
            case 1: return (String) this.stopTime;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @Override
    public void put(int fieldPos, Object fieldValue) {
        switch(fieldPos) {
            case 0: this.stopCity = (String)fieldValue; break;
            case 1: this.stopTime = (String)fieldValue; break;
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

        final TicketingOrderRebookingStopInfo other = (TicketingOrderRebookingStopInfo)obj;
        return
            Objects.equal(this.stopCity, other.stopCity) && 
            Objects.equal(this.stopTime, other.stopTime);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + Objects.hashCode(this.stopCity);
        result = 31 * result + Objects.hashCode(this.stopTime);

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("stopCity", stopCity)
            .add("stopTime", stopTime)
            .toString();
    }
}