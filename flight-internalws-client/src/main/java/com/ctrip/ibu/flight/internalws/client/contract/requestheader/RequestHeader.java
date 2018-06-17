package com.ctrip.ibu.flight.internalws.client.contract.requestheader;

import com.ctrip.ibu.flight.internalws.client.contract.commontypes.ClientSource;
import com.ctriposs.baiji.exception.*;
import com.ctriposs.baiji.schema.*;
import com.ctriposs.baiji.specific.*;
import com.google.common.base.Objects;
import com.google.common.base.MoreObjects;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("all")
public class RequestHeader implements SpecificRecord {
    private static final long serialVersionUID = 1L;

	@JsonIgnore
    public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"RequestHeader\",\"namespace\":\"" + RequestHeader.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"appId\",\"type\":[\"string\",\"null\"]},{\"name\":\"clientIp\",\"type\":[\"string\",\"null\"]},{\"name\":\"clientType\",\"type\":[{\"type\":\"enum\",\"name\":\"ClientSource\",\"namespace\":\"" + ClientSource.class.getPackage().getName() + "\",\"doc\":null,\"symbols\":[\"UNKNOWN\",\"ONLINE\",\"ANDROID\",\"IOS\",\"WAP\"]},\"null\"]},{\"name\":\"clientSource\",\"type\":[\"string\",\"null\"]}]}");

    @Override
    @JsonIgnore
    public Schema getSchema() { return SCHEMA; }

    public RequestHeader(
        String appId,
        String clientIp,
        ClientSource clientType,
        String clientSource) {
        this.appId = appId;
        this.clientIp = clientIp;
        this.clientType = clientType;
        this.clientSource = clientSource;
    }

    public RequestHeader() {
    }

    public String appId;

    public String clientIp;

    public ClientSource clientType;

    public String clientSource;

    public String getAppId() {
        return appId;
    }

    public void setAppId(final String appId) {
        this.appId = appId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(final String clientIp) {
        this.clientIp = clientIp;
    }

    public ClientSource getClientType() {
        return clientType;
    }

    public void setClientType(final ClientSource clientType) {
        this.clientType = clientType;
    }

    public String getClientSource() {
        return clientSource;
    }

    public void setClientSource(final String clientSource) {
        this.clientSource = clientSource;
    }

    // Used by DatumWriter. Applications should not call.
    public Object get(int fieldPos) {
        switch (fieldPos) {
            case 0: return this.appId;
            case 1: return this.clientIp;
            case 2: return this.clientType;
            case 3: return this.clientSource;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int fieldPos, Object fieldValue) {
        switch (fieldPos) {
            case 0: this.appId = (String)fieldValue; break;
            case 1: this.clientIp = (String)fieldValue; break;
            case 2: this.clientType = (ClientSource)fieldValue; break;
            case 3: this.clientSource = (String)fieldValue; break;
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

        final RequestHeader other = (RequestHeader)obj;
        return 
            Objects.equal(this.appId, other.appId) &&
            Objects.equal(this.clientIp, other.clientIp) &&
            Objects.equal(this.clientType, other.clientType) &&
            Objects.equal(this.clientSource, other.clientSource);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + (this.appId == null ? 0 : this.appId.hashCode());
        result = 31 * result + (this.clientIp == null ? 0 : this.clientIp.hashCode());
        result = 31 * result + (this.clientType == null ? 0 : this.clientType.hashCode());
        result = 31 * result + (this.clientSource == null ? 0 : this.clientSource.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("appId", appId)
            .add("clientIp", clientIp)
            .add("clientType", clientType)
            .add("clientSource", clientSource)
            .toString();
    }
}
