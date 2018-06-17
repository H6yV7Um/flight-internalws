package com.ctrip.ibu.flight.internalws.client.contract.getemailstatusservice;

import java.util.*;

import com.ctrip.ibu.flight.internalws.client.contract.commontypes.Email;
import com.ctrip.ibu.flight.internalws.client.contract.responseheader.ResponseHeader;
import com.ctriposs.baiji.exception.*;
import com.ctriposs.baiji.rpc.common.*;
import com.ctriposs.baiji.schema.*;
import com.ctriposs.baiji.specific.*;
import com.google.common.base.Objects;
import com.google.common.base.MoreObjects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ctriposs.baiji.rpc.common.types.ResponseStatusType;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("all")
public class GetEmailStatusResponseType implements SpecificRecord, HasResponseStatus {
    private static final long serialVersionUID = 1L;

	@JsonIgnore
    public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"GetEmailStatusResponseType\",\"namespace\":\"" + GetEmailStatusResponseType.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"ResponseStatus\",\"type\":[{\"type\":\"record\",\"name\":\"ResponseStatusType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"fields\":[{\"name\":\"Timestamp\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"Ack\",\"type\":[{\"type\":\"enum\",\"name\":\"AckCodeType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"symbols\":[\"Success\",\"Failure\",\"Warning\",\"PartialFailure\"]},\"null\"]},{\"name\":\"Errors\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"ErrorDataType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"fields\":[{\"name\":\"Message\",\"type\":[\"string\",\"null\"]},{\"name\":\"ErrorCode\",\"type\":[\"string\",\"null\"]},{\"name\":\"StackTrace\",\"type\":[\"string\",\"null\"]},{\"name\":\"SeverityCode\",\"type\":[{\"type\":\"enum\",\"name\":\"SeverityCodeType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"symbols\":[\"Error\",\"Warning\"]},\"null\"]},{\"name\":\"ErrorFields\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"ErrorFieldType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"fields\":[{\"name\":\"FieldName\",\"type\":[\"string\",\"null\"]},{\"name\":\"ErrorCode\",\"type\":[\"string\",\"null\"]},{\"name\":\"Message\",\"type\":[\"string\",\"null\"]}]}},\"null\"]},{\"name\":\"ErrorClassification\",\"type\":[{\"type\":\"enum\",\"name\":\"ErrorClassificationCodeType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"symbols\":[\"ServiceError\",\"ValidationError\",\"FrameworkError\",\"SLAError\",\"SecurityError\"]},\"null\"]}]}},\"null\"]},{\"name\":\"Build\",\"type\":[\"string\",\"null\"]},{\"name\":\"Version\",\"type\":[\"string\",\"null\"]},{\"name\":\"Extension\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"ExtensionType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"fields\":[{\"name\":\"Id\",\"type\":[\"string\",\"null\"]},{\"name\":\"Version\",\"type\":[\"string\",\"null\"]},{\"name\":\"ContentType\",\"type\":[\"string\",\"null\"]},{\"name\":\"Value\",\"type\":[\"string\",\"null\"]}]}},\"null\"]}]},\"null\"]},{\"name\":\"responseHeader\",\"type\":[{\"type\":\"record\",\"name\":\"ResponseHeader\",\"namespace\":\"" + ResponseHeader.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"resultCode\",\"type\":[\"int\",\"null\"]},{\"name\":\"errorMsg\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"resultCode\",\"type\":[\"int\",\"null\"]},{\"name\":\"resultMsg\",\"type\":[\"string\",\"null\"]},{\"name\":\"emailList\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Email\",\"namespace\":\"" + Email.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"emailId\",\"type\":[\"string\",\"null\"]},{\"name\":\"sender\",\"type\":[\"string\",\"null\"]},{\"name\":\"senderName\",\"type\":[\"string\",\"null\"]},{\"name\":\"uid\",\"type\":[\"string\",\"null\"]},{\"name\":\"eid\",\"type\":[\"string\",\"null\"]},{\"name\":\"recipient\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"cc\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"bcc\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"recipientName\",\"type\":[\"string\",\"null\"]},{\"name\":\"subject\",\"type\":[\"string\",\"null\"]},{\"name\":\"bodyTemplateId\",\"type\":[\"int\",\"null\"]},{\"name\":\"bodyContent\",\"type\":[\"string\",\"null\"]},{\"name\":\"createTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"scheduleTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"sendTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"expiredTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"sendHost\",\"type\":[\"string\",\"null\"]},{\"name\":\"emstpId\",\"type\":[\"string\",\"null\"]},{\"name\":\"errorMsg\",\"type\":[\"string\",\"null\"]},{\"name\":\"emailStatus\",\"type\":[\"int\",\"null\"]}]}},\"null\"]}]}");

    @Override
    @JsonIgnore
    public Schema getSchema() { return SCHEMA; }

    public GetEmailStatusResponseType(
        ResponseStatusType responseStatus,
        ResponseHeader responseHeader,
        Integer resultCode,
        String resultMsg,
        List<Email> emailList) {
        this.responseStatus = responseStatus;
        this.responseHeader = responseHeader;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.emailList = emailList;
    }

    public GetEmailStatusResponseType() {
    }

    @JsonProperty("ResponseStatus") 
    public ResponseStatusType responseStatus;

    public ResponseHeader responseHeader;

    public Integer resultCode;

    public String resultMsg;

    public List<Email> emailList;

    @JsonProperty("ResponseStatus") 
    public ResponseStatusType getResponseStatus() {
        return responseStatus;
    }

    @JsonProperty("ResponseStatus") 
    public void setResponseStatus(final ResponseStatusType responseStatus) {
        this.responseStatus = responseStatus;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(final ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(final Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(final String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(final List<Email> emailList) {
        this.emailList = emailList;
    }

    // Used by DatumWriter. Applications should not call.
    public Object get(int fieldPos) {
        switch (fieldPos) {
            case 0: return this.responseStatus;
            case 1: return this.responseHeader;
            case 2: return this.resultCode;
            case 3: return this.resultMsg;
            case 4: return this.emailList;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int fieldPos, Object fieldValue) {
        switch (fieldPos) {
            case 0: this.responseStatus = (ResponseStatusType)fieldValue; break;
            case 1: this.responseHeader = (ResponseHeader)fieldValue; break;
            case 2: this.resultCode = (Integer)fieldValue; break;
            case 3: this.resultMsg = (String)fieldValue; break;
            case 4: this.emailList = (List<Email>)fieldValue; break;
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

        final GetEmailStatusResponseType other = (GetEmailStatusResponseType)obj;
        return 
            Objects.equal(this.responseStatus, other.responseStatus) &&
            Objects.equal(this.responseHeader, other.responseHeader) &&
            Objects.equal(this.resultCode, other.resultCode) &&
            Objects.equal(this.resultMsg, other.resultMsg) &&
            Objects.equal(this.emailList, other.emailList);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + (this.responseStatus == null ? 0 : this.responseStatus.hashCode());
        result = 31 * result + (this.responseHeader == null ? 0 : this.responseHeader.hashCode());
        result = 31 * result + (this.resultCode == null ? 0 : this.resultCode.hashCode());
        result = 31 * result + (this.resultMsg == null ? 0 : this.resultMsg.hashCode());
        result = 31 * result + (this.emailList == null ? 0 : this.emailList.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("responseStatus", responseStatus)
            .add("responseHeader", responseHeader)
            .add("resultCode", resultCode)
            .add("resultMsg", resultMsg)
            .add("emailList", emailList)
            .toString();
    }
}
