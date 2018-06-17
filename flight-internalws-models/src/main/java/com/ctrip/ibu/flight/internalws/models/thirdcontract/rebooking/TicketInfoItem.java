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
 * 
 *         改签乘客票号信息
 *       
 */
@DtoDoc("改签乘客票号信息")
@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TicketInfoItem", namespace = "http://soa.ctrip.com/flight/Order/FlightOrderRebooking/v1", propOrder = {
    "sequence",
    "airLineCode",
    "ticketNO"
})
@SuppressWarnings("all")
public class TicketInfoItem implements SpecificRecord {

    private static final long serialVersionUID = 1L;

	public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"TicketInfoItem\",\"namespace\":\"" + TicketInfoItem.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"sequence\",\"type\":\"int\"},{\"name\":\"airLineCode\",\"type\":[\"string\",\"null\"]},{\"name\":\"ticketNO\",\"type\":[\"string\",\"null\"]}]}");

    @Override
    public Schema getSchema() { return SCHEMA; }

    public TicketInfoItem(
        int sequence, 
        String airLineCode, 
        String ticketNO) {
        this.sequence = sequence;
        this.airLineCode = airLineCode;
        this.ticketNO = ticketNO;
    }

    public TicketInfoItem() {
    }

    @FieldDoc("航段序号")
    @JsonProperty("Sequence")
    @XmlElement(name = "Sequence")
    private int sequence;

    @FieldDoc("票号三字码")
    @JsonProperty("AirLineCode")
    @XmlElement(name = "AirLineCode")
    private String airLineCode;

    @FieldDoc("票号")
    @JsonProperty("TicketNO")
    @XmlElement(name = "TicketNO")
    private String ticketNO;

    /**
     * 航段序号
     */
    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * 票号三字码
     */
    public String getAirLineCode() {
        return this.airLineCode;
    }

    public void setAirLineCode(String airLineCode) {
        this.airLineCode = airLineCode;
    }

    /**
     * 票号
     */
    public String getTicketNO() {
        return this.ticketNO;
    }

    public void setTicketNO(String ticketNO) {
        this.ticketNO = ticketNO;
    }

    // Used by DatumWriter. Applications should not call.
    @Override
    public Object get(int fieldPos) {
        switch(fieldPos) {
            case 0: return (int) this.sequence;
            case 1: return (String) this.airLineCode;
            case 2: return (String) this.ticketNO;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @Override
    public void put(int fieldPos, Object fieldValue) {
        switch(fieldPos) {
            case 0: this.sequence = (int)fieldValue; break;
            case 1: this.airLineCode = (String)fieldValue; break;
            case 2: this.ticketNO = (String)fieldValue; break;
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

        final TicketInfoItem other = (TicketInfoItem)obj;
        return
            Objects.equal(this.sequence, other.sequence) && 
            Objects.equal(this.airLineCode, other.airLineCode) && 
            Objects.equal(this.ticketNO, other.ticketNO);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + Objects.hashCode(this.sequence);
        result = 31 * result + Objects.hashCode(this.airLineCode);
        result = 31 * result + Objects.hashCode(this.ticketNO);

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("sequence", sequence)
            .add("airLineCode", airLineCode)
            .add("ticketNO", ticketNO)
            .toString();
    }
}