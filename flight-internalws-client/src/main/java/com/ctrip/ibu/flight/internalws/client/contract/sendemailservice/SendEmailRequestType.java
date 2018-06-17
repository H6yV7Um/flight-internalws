package com.ctrip.ibu.flight.internalws.client.contract.sendemailservice;

import java.util.*;

import com.ctrip.ibu.flight.internalws.client.contract.commontypes.ClientSource;
import com.ctrip.ibu.flight.internalws.client.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.client.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.client.contract.requestheader.RequestHeader;
import com.ctriposs.baiji.exception.*;
import com.ctriposs.baiji.schema.*;
import com.ctriposs.baiji.specific.*;
import com.google.common.base.Objects;
import com.google.common.base.MoreObjects;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("all")
public class SendEmailRequestType implements SpecificRecord {
    private static final long serialVersionUID = 1L;

	@JsonIgnore
    public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"SendEmailRequestType\",\"namespace\":\"" + SendEmailRequestType.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"requestHeader\",\"type\":[{\"type\":\"record\",\"name\":\"RequestHeader\",\"namespace\":\"" + RequestHeader.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"appId\",\"type\":[\"string\",\"null\"]},{\"name\":\"clientIp\",\"type\":[\"string\",\"null\"]},{\"name\":\"clientType\",\"type\":[{\"type\":\"enum\",\"name\":\"ClientSource\",\"namespace\":\"" + ClientSource.class.getPackage().getName() + "\",\"doc\":null,\"symbols\":[\"UNKNOWN\",\"ONLINE\",\"ANDROID\",\"IOS\",\"WAP\"]},\"null\"]},{\"name\":\"clientSource\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"orderID\",\"type\":[\"long\",\"null\"]},{\"name\":\"uid\",\"type\":[\"string\",\"null\"]},{\"name\":\"emailTemplateType\",\"type\":[{\"type\":\"enum\",\"name\":\"EmailTemplateType\",\"namespace\":\"" + EmailTemplateType.class.getPackage().getName() + "\",\"doc\":null,\"symbols\":[\"unspecified\",\"ReservationConfirmation\",\"PaymentSuccess\",\"PaymentFailed\",\"PaymentCanceled\",\"FlightChangeNotify\",\"FlightChangeCanceled\",\"FlightChangeFeedback\",\"NewFlightSuccess\",\"NewFlightPaymentSuccess\",\"NewFlightCanceled\",\"NewFlightTobePaid\",\"NewFlightConsultingVerified\",\"InsuranceIssued\",\"InsuranceRefunded\",\"InsuranceChanged\",\"XProductSubmitted\",\"XProductPaymentSuccess\",\"XProductIssued\",\"XProductCanceled\",\"RefundFeeVerified\",\"RefundSuccess\",\"EReceipt\",\"ItineraryDetail\"]},\"null\"]},{\"name\":\"languageType\",\"type\":[{\"type\":\"enum\",\"name\":\"LanguageType\",\"namespace\":\"" + LanguageType.class.getPackage().getName() + "\",\"doc\":null,\"symbols\":[\"unspecified\",\"en_US\",\"en_HK\",\"zh_HK\",\"zh_TW\",\"zh_CN\",\"ja_JP\",\"ko_KR\",\"fr_FR\",\"en_SG\",\"de_DE\",\"ru_RU\",\"es_ES\",\"ms_MY\",\"th_TH\",\"id_ID\",\"en_AU\",\"vi_VN\",\"tl_PH\",\"it_IT\"]},\"null\"]},{\"name\":\"isCustomContent\",\"type\":[\"boolean\",\"null\"]},{\"name\":\"subject\",\"type\":[\"string\",\"null\"]},{\"name\":\"bodyContent\",\"type\":[\"string\",\"null\"]},{\"name\":\"isBodyHtml\",\"type\":[\"boolean\",\"null\"]},{\"name\":\"additionalIds\",\"type\":[{\"type\":\"map\",\"values\":\"string\"},\"null\"]},{\"name\":\"operator\",\"type\":[\"string\",\"null\"]},{\"name\":\"recipientEmailList\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"cc\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"bcc\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"remark\",\"type\":[\"string\",\"null\"]}]}");

    @Override
    @JsonIgnore
    public Schema getSchema() { return SCHEMA; }

    public SendEmailRequestType(
        RequestHeader requestHeader,
        Long orderID,
        String uid,
        EmailTemplateType emailTemplateType,
        LanguageType languageType,
        Boolean isCustomContent,
        String subject,
        String bodyContent,
        Boolean isBodyHtml,
        Map<String, String> additionalIds,
        String operator,
        List<String> recipientEmailList,
        List<String> cc,
        List<String> bcc,
        String remark) {
        this.requestHeader = requestHeader;
        this.orderID = orderID;
        this.uid = uid;
        this.emailTemplateType = emailTemplateType;
        this.languageType = languageType;
        this.isCustomContent = isCustomContent;
        this.subject = subject;
        this.bodyContent = bodyContent;
        this.isBodyHtml = isBodyHtml;
        this.additionalIds = additionalIds;
        this.operator = operator;
        this.recipientEmailList = recipientEmailList;
        this.cc = cc;
        this.bcc = bcc;
        this.remark = remark;
    }

    public SendEmailRequestType() {
    }

    public RequestHeader requestHeader;

    public Long orderID;

    public String uid;

    public EmailTemplateType emailTemplateType;

    public LanguageType languageType;

    public Boolean isCustomContent;

    public String subject;

    public String bodyContent;

    public Boolean isBodyHtml;

    public Map<String, String> additionalIds;

    public String operator;

    public List<String> recipientEmailList;

    public List<String> cc;

    public List<String> bcc;

    public String remark;

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(final RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(final Long orderID) {
        this.orderID = orderID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public EmailTemplateType getEmailTemplateType() {
        return emailTemplateType;
    }

    public void setEmailTemplateType(final EmailTemplateType emailTemplateType) {
        this.emailTemplateType = emailTemplateType;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(final LanguageType languageType) {
        this.languageType = languageType;
    }

    public Boolean isIsCustomContent() {
        return isCustomContent;
    }

    public void setIsCustomContent(final Boolean isCustomContent) {
        this.isCustomContent = isCustomContent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(final String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public Boolean isIsBodyHtml() {
        return isBodyHtml;
    }

    public void setIsBodyHtml(final Boolean isBodyHtml) {
        this.isBodyHtml = isBodyHtml;
    }

    public Map<String, String> getAdditionalIds() {
        return additionalIds;
    }

    public void setAdditionalIds(final Map<String, String> additionalIds) {
        this.additionalIds = additionalIds;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(final String operator) {
        this.operator = operator;
    }

    public List<String> getRecipientEmailList() {
        return recipientEmailList;
    }

    public void setRecipientEmailList(final List<String> recipientEmailList) {
        this.recipientEmailList = recipientEmailList;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(final List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(final List<String> bcc) {
        this.bcc = bcc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    // Used by DatumWriter. Applications should not call.
    public Object get(int fieldPos) {
        switch (fieldPos) {
            case 0: return this.requestHeader;
            case 1: return this.orderID;
            case 2: return this.uid;
            case 3: return this.emailTemplateType;
            case 4: return this.languageType;
            case 5: return this.isCustomContent;
            case 6: return this.subject;
            case 7: return this.bodyContent;
            case 8: return this.isBodyHtml;
            case 9: return this.additionalIds;
            case 10: return this.operator;
            case 11: return this.recipientEmailList;
            case 12: return this.cc;
            case 13: return this.bcc;
            case 14: return this.remark;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int fieldPos, Object fieldValue) {
        switch (fieldPos) {
            case 0: this.requestHeader = (RequestHeader)fieldValue; break;
            case 1: this.orderID = (Long)fieldValue; break;
            case 2: this.uid = (String)fieldValue; break;
            case 3: this.emailTemplateType = (EmailTemplateType)fieldValue; break;
            case 4: this.languageType = (LanguageType)fieldValue; break;
            case 5: this.isCustomContent = (Boolean)fieldValue; break;
            case 6: this.subject = (String)fieldValue; break;
            case 7: this.bodyContent = (String)fieldValue; break;
            case 8: this.isBodyHtml = (Boolean)fieldValue; break;
            case 9: this.additionalIds = (Map<String, String>)fieldValue; break;
            case 10: this.operator = (String)fieldValue; break;
            case 11: this.recipientEmailList = (List<String>)fieldValue; break;
            case 12: this.cc = (List<String>)fieldValue; break;
            case 13: this.bcc = (List<String>)fieldValue; break;
            case 14: this.remark = (String)fieldValue; break;
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

        final SendEmailRequestType other = (SendEmailRequestType)obj;
        return 
            Objects.equal(this.requestHeader, other.requestHeader) &&
            Objects.equal(this.orderID, other.orderID) &&
            Objects.equal(this.uid, other.uid) &&
            Objects.equal(this.emailTemplateType, other.emailTemplateType) &&
            Objects.equal(this.languageType, other.languageType) &&
            Objects.equal(this.isCustomContent, other.isCustomContent) &&
            Objects.equal(this.subject, other.subject) &&
            Objects.equal(this.bodyContent, other.bodyContent) &&
            Objects.equal(this.isBodyHtml, other.isBodyHtml) &&
            Objects.equal(this.additionalIds, other.additionalIds) &&
            Objects.equal(this.operator, other.operator) &&
            Objects.equal(this.recipientEmailList, other.recipientEmailList) &&
            Objects.equal(this.cc, other.cc) &&
            Objects.equal(this.bcc, other.bcc) &&
            Objects.equal(this.remark, other.remark);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + (this.requestHeader == null ? 0 : this.requestHeader.hashCode());
        result = 31 * result + (this.orderID == null ? 0 : this.orderID.hashCode());
        result = 31 * result + (this.uid == null ? 0 : this.uid.hashCode());
        result = 31 * result + (this.emailTemplateType == null ? 0 : this.emailTemplateType.hashCode());
        result = 31 * result + (this.languageType == null ? 0 : this.languageType.hashCode());
        result = 31 * result + (this.isCustomContent == null ? 0 : this.isCustomContent.hashCode());
        result = 31 * result + (this.subject == null ? 0 : this.subject.hashCode());
        result = 31 * result + (this.bodyContent == null ? 0 : this.bodyContent.hashCode());
        result = 31 * result + (this.isBodyHtml == null ? 0 : this.isBodyHtml.hashCode());
        result = 31 * result + (this.additionalIds == null ? 0 : this.additionalIds.hashCode());
        result = 31 * result + (this.operator == null ? 0 : this.operator.hashCode());
        result = 31 * result + (this.recipientEmailList == null ? 0 : this.recipientEmailList.hashCode());
        result = 31 * result + (this.cc == null ? 0 : this.cc.hashCode());
        result = 31 * result + (this.bcc == null ? 0 : this.bcc.hashCode());
        result = 31 * result + (this.remark == null ? 0 : this.remark.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("requestHeader", requestHeader)
            .add("orderID", orderID)
            .add("uid", uid)
            .add("emailTemplateType", emailTemplateType)
            .add("languageType", languageType)
            .add("isCustomContent", isCustomContent)
            .add("subject", subject)
            .add("bodyContent", bodyContent)
            .add("isBodyHtml", isBodyHtml)
            .add("additionalIds", additionalIds)
            .add("operator", operator)
            .add("recipientEmailList", recipientEmailList)
            .add("cc", cc)
            .add("bcc", bcc)
            .add("remark", remark)
            .toString();
    }
}
