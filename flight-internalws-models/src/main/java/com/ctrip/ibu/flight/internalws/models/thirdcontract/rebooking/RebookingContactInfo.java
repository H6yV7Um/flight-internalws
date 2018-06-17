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
 *         改签联系人信息
 *       
 */
@DtoDoc("改签联系人信息")
@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RebookingContactInfo", namespace = "http://soa.ctrip.com/flight/Order/FlightOrderRebooking/v1", propOrder = {
    "contactName",
    "countryCode",
    "mobilePhone",
    "emailAddress",
    "resultConfirmType"
})
@SuppressWarnings("all")
public class RebookingContactInfo implements SpecificRecord {

    private static final long serialVersionUID = 1L;

	public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"RebookingContactInfo\",\"namespace\":\"" + RebookingContactInfo.class.getPackage().getName() + "\",\"fields\":[{\"name\":\"contactName\",\"type\":[\"string\",\"null\"]},{\"name\":\"countryCode\",\"type\":[\"string\",\"null\"]},{\"name\":\"mobilePhone\",\"type\":[\"string\",\"null\"]},{\"name\":\"emailAddress\",\"type\":[\"string\",\"null\"]},{\"name\":\"resultConfirmType\",\"type\":\"int\"}]}");

    @Override
    public Schema getSchema() { return SCHEMA; }

    public RebookingContactInfo(
        String contactName, 
        String countryCode, 
        String mobilePhone, 
        String emailAddress, 
        int resultConfirmType) {
        this.contactName = contactName;
        this.countryCode = countryCode;
        this.mobilePhone = mobilePhone;
        this.emailAddress = emailAddress;
        this.resultConfirmType = resultConfirmType;
    }

    public RebookingContactInfo() {
    }

    @FieldDoc("联系人姓名")
    @JsonProperty("ContactName")
    @XmlElement(name = "ContactName")
    private String contactName;

    @FieldDoc("联系人电话国家码")
    @JsonProperty("CountryCode")
    @XmlElement(name = "CountryCode")
    private String countryCode;

    @FieldDoc("联系电话")
    @JsonProperty("MobilePhone")
    @XmlElement(name = "MobilePhone")
    private String mobilePhone;

    @FieldDoc("邮箱")
    @JsonProperty("EmailAddress")
    @XmlElement(name = "EmailAddress")
    private String emailAddress;

    @FieldDoc("改签确认方式：手机短信确认=1,Email确认=2,电话=4，根据选择的确认方式，改签成功后发送确认信息给客人")
    @JsonProperty("ResultConfirmType")
    @XmlElement(name = "ResultConfirmType")
    private int resultConfirmType;

    /**
     * 联系人姓名
     */
    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * 联系人电话国家码
     */
    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 联系电话
     */
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 邮箱
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * 改签确认方式：手机短信确认=1,Email确认=2,电话=4，根据选择的确认方式，改签成功后发送确认信息给客人
     */
    public int getResultConfirmType() {
        return this.resultConfirmType;
    }

    public void setResultConfirmType(int resultConfirmType) {
        this.resultConfirmType = resultConfirmType;
    }

    // Used by DatumWriter. Applications should not call.
    @Override
    public Object get(int fieldPos) {
        switch(fieldPos) {
            case 0: return (String) this.contactName;
            case 1: return (String) this.countryCode;
            case 2: return (String) this.mobilePhone;
            case 3: return (String) this.emailAddress;
            case 4: return (int) this.resultConfirmType;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @Override
    public void put(int fieldPos, Object fieldValue) {
        switch(fieldPos) {
            case 0: this.contactName = (String)fieldValue; break;
            case 1: this.countryCode = (String)fieldValue; break;
            case 2: this.mobilePhone = (String)fieldValue; break;
            case 3: this.emailAddress = (String)fieldValue; break;
            case 4: this.resultConfirmType = (int)fieldValue; break;
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

        final RebookingContactInfo other = (RebookingContactInfo)obj;
        return
            Objects.equal(this.contactName, other.contactName) && 
            Objects.equal(this.countryCode, other.countryCode) && 
            Objects.equal(this.mobilePhone, other.mobilePhone) && 
            Objects.equal(this.emailAddress, other.emailAddress) && 
            Objects.equal(this.resultConfirmType, other.resultConfirmType);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + Objects.hashCode(this.contactName);
        result = 31 * result + Objects.hashCode(this.countryCode);
        result = 31 * result + Objects.hashCode(this.mobilePhone);
        result = 31 * result + Objects.hashCode(this.emailAddress);
        result = 31 * result + Objects.hashCode(this.resultConfirmType);

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("contactName", contactName)
            .add("countryCode", countryCode)
            .add("mobilePhone", mobilePhone)
            .add("emailAddress", emailAddress)
            .add("resultConfirmType", resultConfirmType)
            .toString();
    }
}