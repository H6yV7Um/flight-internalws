package com.ctrip.ibu.flight.internalws.contract.responseheader;

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
        "* 请求头")
public class ResponseHeader implements SpecificRecord {
    private static final long serialVersionUID = 1L;

	@JsonIgnore
    public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"ResponseHeader\",\"namespace\":\"" + ResponseHeader.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"resultCode\",\"type\":[\"int\",\"null\"]},{\"name\":\"errorMsg\",\"type\":[\"string\",\"null\"]}]}");

    @Override
    @JsonIgnore
    public Schema getSchema() { return SCHEMA; }

    public ResponseHeader(
        Integer resultCode,
        String errorMsg) {
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
    }

    public ResponseHeader() {
    }

    @FieldDoc("*" + 
              "* 调用结果")
    public Integer resultCode;


    @FieldDoc("*" + 
              "* 错误信息")
    public String errorMsg;

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(final Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(final String errorMsg) {
        this.errorMsg = errorMsg;
    }

    // Used by DatumWriter. Applications should not call.
    public Object get(int fieldPos) {
        switch (fieldPos) {
            case 0: return this.resultCode;
            case 1: return this.errorMsg;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int fieldPos, Object fieldValue) {
        switch (fieldPos) {
            case 0: this.resultCode = (Integer)fieldValue; break;
            case 1: this.errorMsg = (String)fieldValue; break;
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

        final ResponseHeader other = (ResponseHeader)obj;
        return 
            Objects.equal(this.resultCode, other.resultCode) &&
            Objects.equal(this.errorMsg, other.errorMsg);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + (this.resultCode == null ? 0 : this.resultCode.hashCode());
        result = 31 * result + (this.errorMsg == null ? 0 : this.errorMsg.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("resultCode", resultCode)
            .add("errorMsg", errorMsg)
            .toString();
    }
}
