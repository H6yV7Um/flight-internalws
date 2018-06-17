package com.ctrip.ibu.flight.internalws.contract.sendemailservice;

import com.ctrip.ibu.flight.internalws.contract.responseheader.ResponseHeader;
import com.ctriposs.baiji.exception.BaijiRuntimeException;
import com.ctriposs.baiji.rpc.common.HasResponseStatus;
import com.ctriposs.baiji.rpc.common.apidoc.DtoDoc;
import com.ctriposs.baiji.rpc.common.apidoc.FieldDoc;
import com.ctriposs.baiji.rpc.common.types.ResponseStatusType;
import com.ctriposs.baiji.schema.Field;
import com.ctriposs.baiji.schema.RecordSchema;
import com.ctriposs.baiji.schema.Schema;
import com.ctriposs.baiji.specific.SpecificRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

@SuppressWarnings("all")
@DtoDoc("*" + 
        "* 邮件发送响应")
public class SendEmailResponseType implements SpecificRecord, HasResponseStatus {
    private static final long serialVersionUID = 1L;

	@JsonIgnore
    public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"SendEmailResponseType\",\"namespace\":\"" + SendEmailResponseType.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"ResponseStatus\",\"type\":[{\"type\":\"record\",\"name\":\"ResponseStatusType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"fields\":[{\"name\":\"Timestamp\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"Ack\",\"type\":[{\"type\":\"enum\",\"name\":\"AckCodeType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"symbols\":[\"Success\",\"Failure\",\"Warning\",\"PartialFailure\"]},\"null\"]},{\"name\":\"Errors\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"ErrorDataType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"fields\":[{\"name\":\"Message\",\"type\":[\"string\",\"null\"]},{\"name\":\"ErrorCode\",\"type\":[\"string\",\"null\"]},{\"name\":\"StackTrace\",\"type\":[\"string\",\"null\"]},{\"name\":\"SeverityCode\",\"type\":[{\"type\":\"enum\",\"name\":\"SeverityCodeType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"symbols\":[\"Error\",\"Warning\"]},\"null\"]},{\"name\":\"ErrorFields\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"ErrorFieldType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"fields\":[{\"name\":\"FieldName\",\"type\":[\"string\",\"null\"]},{\"name\":\"ErrorCode\",\"type\":[\"string\",\"null\"]},{\"name\":\"Message\",\"type\":[\"string\",\"null\"]}]}},\"null\"]},{\"name\":\"ErrorClassification\",\"type\":[{\"type\":\"enum\",\"name\":\"ErrorClassificationCodeType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"symbols\":[\"ServiceError\",\"ValidationError\",\"FrameworkError\",\"SLAError\",\"SecurityError\"]},\"null\"]}]}},\"null\"]},{\"name\":\"Build\",\"type\":[\"string\",\"null\"]},{\"name\":\"Version\",\"type\":[\"string\",\"null\"]},{\"name\":\"Extension\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"ExtensionType\",\"namespace\":\"com.ctriposs.baiji.rpc.common.types\",\"doc\":null,\"fields\":[{\"name\":\"Id\",\"type\":[\"string\",\"null\"]},{\"name\":\"Version\",\"type\":[\"string\",\"null\"]},{\"name\":\"ContentType\",\"type\":[\"string\",\"null\"]},{\"name\":\"Value\",\"type\":[\"string\",\"null\"]}]}},\"null\"]}]},\"null\"]},{\"name\":\"responseHeader\",\"type\":[{\"type\":\"record\",\"name\":\"ResponseHeader\",\"namespace\":\"" + ResponseHeader.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"resultCode\",\"type\":[\"int\",\"null\"]},{\"name\":\"errorMsg\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"sendResult\",\"type\":[\"int\",\"null\"]},{\"name\":\"actualResult\",\"type\":[\"string\",\"null\"]},{\"name\":\"resultMessage\",\"type\":[\"string\",\"null\"]},{\"name\":\"emailIdList\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"emailViewUrl\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]}]}");

    @Override
    @JsonIgnore
    public Schema getSchema() { return SCHEMA; }

    public SendEmailResponseType(
        ResponseStatusType responseStatus,
        ResponseHeader responseHeader,
        Integer sendResult,
        String actualResult,
        String resultMessage,
        List<String> emailIdList,
        List<String> emailViewUrl) {
        this.responseStatus = responseStatus;
        this.responseHeader = responseHeader;
        this.sendResult = sendResult;
        this.actualResult = actualResult;
        this.resultMessage = resultMessage;
        this.emailIdList = emailIdList;
        this.emailViewUrl = emailViewUrl;
    }

    public SendEmailResponseType() {
    }

    @JsonProperty("ResponseStatus") 
    @FieldDoc("*" + 
              "* 接口响应状态")
    public ResponseStatusType responseStatus;


    @FieldDoc("*" + 
              "* 响应头")
    public ResponseHeader responseHeader;


    @FieldDoc("*" + 
              "* 发送结果，0：成功，1：失败")
    public Integer sendResult;


    @FieldDoc("实际结果")
    public String actualResult;


    @FieldDoc("*" + 
              "* 发送结果描述")
    public String resultMessage;


    @FieldDoc("*" + 
              "* EmailID列表")
    public List<String> emailIdList;


    @FieldDoc("*" + 
              "* 邮件查看URL")
    public List<String> emailViewUrl;

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

    public Integer getSendResult() {
        return sendResult;
    }

    public void setSendResult(final Integer sendResult) {
        this.sendResult = sendResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(final String actualResult) {
        this.actualResult = actualResult;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(final String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<String> getEmailIdList() {
        return emailIdList;
    }

    public void setEmailIdList(final List<String> emailIdList) {
        this.emailIdList = emailIdList;
    }

    public List<String> getEmailViewUrl() {
        return emailViewUrl;
    }

    public void setEmailViewUrl(final List<String> emailViewUrl) {
        this.emailViewUrl = emailViewUrl;
    }

    // Used by DatumWriter. Applications should not call.
    public Object get(int fieldPos) {
        switch (fieldPos) {
            case 0: return this.responseStatus;
            case 1: return this.responseHeader;
            case 2: return this.sendResult;
            case 3: return this.actualResult;
            case 4: return this.resultMessage;
            case 5: return this.emailIdList;
            case 6: return this.emailViewUrl;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int fieldPos, Object fieldValue) {
        switch (fieldPos) {
            case 0: this.responseStatus = (ResponseStatusType)fieldValue; break;
            case 1: this.responseHeader = (ResponseHeader)fieldValue; break;
            case 2: this.sendResult = (Integer)fieldValue; break;
            case 3: this.actualResult = (String)fieldValue; break;
            case 4: this.resultMessage = (String)fieldValue; break;
            case 5: this.emailIdList = (List<String>)fieldValue; break;
            case 6: this.emailViewUrl = (List<String>)fieldValue; break;
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

        final SendEmailResponseType other = (SendEmailResponseType)obj;
        return 
            Objects.equal(this.responseStatus, other.responseStatus) &&
            Objects.equal(this.responseHeader, other.responseHeader) &&
            Objects.equal(this.sendResult, other.sendResult) &&
            Objects.equal(this.actualResult, other.actualResult) &&
            Objects.equal(this.resultMessage, other.resultMessage) &&
            Objects.equal(this.emailIdList, other.emailIdList) &&
            Objects.equal(this.emailViewUrl, other.emailViewUrl);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + (this.responseStatus == null ? 0 : this.responseStatus.hashCode());
        result = 31 * result + (this.responseHeader == null ? 0 : this.responseHeader.hashCode());
        result = 31 * result + (this.sendResult == null ? 0 : this.sendResult.hashCode());
        result = 31 * result + (this.actualResult == null ? 0 : this.actualResult.hashCode());
        result = 31 * result + (this.resultMessage == null ? 0 : this.resultMessage.hashCode());
        result = 31 * result + (this.emailIdList == null ? 0 : this.emailIdList.hashCode());
        result = 31 * result + (this.emailViewUrl == null ? 0 : this.emailViewUrl.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("responseStatus", responseStatus)
            .add("responseHeader", responseHeader)
            .add("sendResult", sendResult)
            .add("actualResult", actualResult)
            .add("resultMessage", resultMessage)
            .add("emailIdList", emailIdList)
            .add("emailViewUrl", emailViewUrl)
            .toString();
    }
}
