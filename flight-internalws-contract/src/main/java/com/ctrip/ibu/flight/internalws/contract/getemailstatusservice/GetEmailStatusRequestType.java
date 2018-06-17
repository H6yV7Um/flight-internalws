package com.ctrip.ibu.flight.internalws.contract.getemailstatusservice;

import java.util.*;

import com.ctrip.ibu.flight.internalws.contract.commontypes.ClientSource;
import com.ctrip.ibu.flight.internalws.contract.requestheader.RequestHeader;
import com.ctriposs.baiji.exception.*;
import com.ctriposs.baiji.schema.*;
import com.ctriposs.baiji.specific.*;
import com.google.common.base.Objects;
import com.google.common.base.MoreObjects;
import com.ctriposs.baiji.rpc.common.apidoc.DtoDoc;
import com.ctriposs.baiji.rpc.common.apidoc.FieldDoc;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("all")
@DtoDoc("*" + 
        "* 获取邮件发送状态请求")
public class GetEmailStatusRequestType implements SpecificRecord {
    private static final long serialVersionUID = 1L;

	@JsonIgnore
    public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"GetEmailStatusRequestType\",\"namespace\":\"" + GetEmailStatusRequestType.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"requestHeader\",\"type\":[{\"type\":\"record\",\"name\":\"RequestHeader\",\"namespace\":\"" + RequestHeader.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"appId\",\"type\":[\"string\",\"null\"]},{\"name\":\"clientIp\",\"type\":[\"string\",\"null\"]},{\"name\":\"clientType\",\"type\":[{\"type\":\"enum\",\"name\":\"ClientSource\",\"namespace\":\"" + ClientSource.class.getPackage().getName() + "\",\"doc\":null,\"symbols\":[\"UNKNOWN\",\"ONLINE\",\"ANDROID\",\"IOS\",\"WAP\"]},\"null\"]},{\"name\":\"clientSource\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"emailIdList\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"sendCode\",\"type\":[\"string\",\"null\"]}]}");

    @Override
    @JsonIgnore
    public Schema getSchema() { return SCHEMA; }

    public GetEmailStatusRequestType(
        RequestHeader requestHeader,
        List<String> emailIdList,
        String sendCode) {
        this.requestHeader = requestHeader;
        this.emailIdList = emailIdList;
        this.sendCode = sendCode;
    }

    public GetEmailStatusRequestType() {
    }

    @FieldDoc("*" + 
              "* 请求头")
    public RequestHeader requestHeader;


    @FieldDoc("*" + 
              "* 需要查询的EmailId列表")
    public List<String> emailIdList;


    @FieldDoc("*" + 
              "* 邮件发送通道ID")
    public String sendCode;

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(final RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public List<String> getEmailIdList() {
        return emailIdList;
    }

    public void setEmailIdList(final List<String> emailIdList) {
        this.emailIdList = emailIdList;
    }

    public String getSendCode() {
        return sendCode;
    }

    public void setSendCode(final String sendCode) {
        this.sendCode = sendCode;
    }

    // Used by DatumWriter. Applications should not call.
    public Object get(int fieldPos) {
        switch (fieldPos) {
            case 0: return this.requestHeader;
            case 1: return this.emailIdList;
            case 2: return this.sendCode;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int fieldPos, Object fieldValue) {
        switch (fieldPos) {
            case 0: this.requestHeader = (RequestHeader)fieldValue; break;
            case 1: this.emailIdList = (List<String>)fieldValue; break;
            case 2: this.sendCode = (String)fieldValue; break;
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

        final GetEmailStatusRequestType other = (GetEmailStatusRequestType)obj;
        return 
            Objects.equal(this.requestHeader, other.requestHeader) &&
            Objects.equal(this.emailIdList, other.emailIdList) &&
            Objects.equal(this.sendCode, other.sendCode);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + (this.requestHeader == null ? 0 : this.requestHeader.hashCode());
        result = 31 * result + (this.emailIdList == null ? 0 : this.emailIdList.hashCode());
        result = 31 * result + (this.sendCode == null ? 0 : this.sendCode.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("requestHeader", requestHeader)
            .add("emailIdList", emailIdList)
            .add("sendCode", sendCode)
            .toString();
    }
}
