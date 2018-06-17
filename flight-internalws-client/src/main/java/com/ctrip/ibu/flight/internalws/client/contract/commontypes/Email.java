package com.ctrip.ibu.flight.internalws.client.contract.commontypes;

import java.util.*;
import com.ctriposs.baiji.exception.*;
import com.ctriposs.baiji.schema.*;
import com.ctriposs.baiji.specific.*;
import com.google.common.base.Objects;
import com.google.common.base.MoreObjects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Calendar;

@SuppressWarnings("all")
public class Email implements SpecificRecord {
    private static final long serialVersionUID = 1L;

	@JsonIgnore
    public static final Schema SCHEMA = Schema.parse("{\"type\":\"record\",\"name\":\"Email\",\"namespace\":\"" + Email.class.getPackage().getName() + "\",\"doc\":null,\"fields\":[{\"name\":\"emailId\",\"type\":[\"string\",\"null\"]},{\"name\":\"sender\",\"type\":[\"string\",\"null\"]},{\"name\":\"senderName\",\"type\":[\"string\",\"null\"]},{\"name\":\"uid\",\"type\":[\"string\",\"null\"]},{\"name\":\"eid\",\"type\":[\"string\",\"null\"]},{\"name\":\"recipient\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"cc\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"bcc\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"recipientName\",\"type\":[\"string\",\"null\"]},{\"name\":\"subject\",\"type\":[\"string\",\"null\"]},{\"name\":\"bodyTemplateId\",\"type\":[\"int\",\"null\"]},{\"name\":\"bodyContent\",\"type\":[\"string\",\"null\"]},{\"name\":\"createTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"scheduleTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"sendTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"expiredTime\",\"type\":[\"datetime\",\"null\"]},{\"name\":\"sendHost\",\"type\":[\"string\",\"null\"]},{\"name\":\"emstpId\",\"type\":[\"string\",\"null\"]},{\"name\":\"errorMsg\",\"type\":[\"string\",\"null\"]},{\"name\":\"emailStatus\",\"type\":[\"int\",\"null\"]}]}");

    @Override
    @JsonIgnore
    public Schema getSchema() { return SCHEMA; }

    public Email(
        String emailId,
        String sender,
        String senderName,
        String uid,
        String eid,
        List<String> recipient,
        List<String> cc,
        List<String> bcc,
        String recipientName,
        String subject,
        Integer bodyTemplateId,
        String bodyContent,
        Calendar createTime,
        Calendar scheduleTime,
        Calendar sendTime,
        Calendar expiredTime,
        String sendHost,
        String emstpId,
        String errorMsg,
        Integer emailStatus) {
        this.emailId = emailId;
        this.sender = sender;
        this.senderName = senderName;
        this.uid = uid;
        this.eid = eid;
        this.recipient = recipient;
        this.cc = cc;
        this.bcc = bcc;
        this.recipientName = recipientName;
        this.subject = subject;
        this.bodyTemplateId = bodyTemplateId;
        this.bodyContent = bodyContent;
        this.createTime = createTime;
        this.scheduleTime = scheduleTime;
        this.sendTime = sendTime;
        this.expiredTime = expiredTime;
        this.sendHost = sendHost;
        this.emstpId = emstpId;
        this.errorMsg = errorMsg;
        this.emailStatus = emailStatus;
    }

    public Email() {
    }

    public String emailId;

    public String sender;

    public String senderName;

    public String uid;

    public String eid;

    public List<String> recipient;

    public List<String> cc;

    public List<String> bcc;

    public String recipientName;

    public String subject;

    public Integer bodyTemplateId;

    public String bodyContent;

    public Calendar createTime;

    public Calendar scheduleTime;

    public Calendar sendTime;

    public Calendar expiredTime;

    public String sendHost;

    public String emstpId;

    public String errorMsg;

    public Integer emailStatus;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(final String senderName) {
        this.senderName = senderName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(final String eid) {
        this.eid = eid;
    }

    public List<String> getRecipient() {
        return recipient;
    }

    public void setRecipient(final List<String> recipient) {
        this.recipient = recipient;
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

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(final String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public Integer getBodyTemplateId() {
        return bodyTemplateId;
    }

    public void setBodyTemplateId(final Integer bodyTemplateId) {
        this.bodyTemplateId = bodyTemplateId;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(final String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(final Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(final Calendar scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Calendar getSendTime() {
        return sendTime;
    }

    public void setSendTime(final Calendar sendTime) {
        this.sendTime = sendTime;
    }

    public Calendar getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(final Calendar expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getSendHost() {
        return sendHost;
    }

    public void setSendHost(final String sendHost) {
        this.sendHost = sendHost;
    }

    public String getEmstpId() {
        return emstpId;
    }

    public void setEmstpId(final String emstpId) {
        this.emstpId = emstpId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(final String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(final Integer emailStatus) {
        this.emailStatus = emailStatus;
    }

    // Used by DatumWriter. Applications should not call.
    public Object get(int fieldPos) {
        switch (fieldPos) {
            case 0: return this.emailId;
            case 1: return this.sender;
            case 2: return this.senderName;
            case 3: return this.uid;
            case 4: return this.eid;
            case 5: return this.recipient;
            case 6: return this.cc;
            case 7: return this.bcc;
            case 8: return this.recipientName;
            case 9: return this.subject;
            case 10: return this.bodyTemplateId;
            case 11: return this.bodyContent;
            case 12: return this.createTime;
            case 13: return this.scheduleTime;
            case 14: return this.sendTime;
            case 15: return this.expiredTime;
            case 16: return this.sendHost;
            case 17: return this.emstpId;
            case 18: return this.errorMsg;
            case 19: return this.emailStatus;
            default: throw new BaijiRuntimeException("Bad index " + fieldPos + " in get()");
        }
    }

    // Used by DatumReader. Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int fieldPos, Object fieldValue) {
        switch (fieldPos) {
            case 0: this.emailId = (String)fieldValue; break;
            case 1: this.sender = (String)fieldValue; break;
            case 2: this.senderName = (String)fieldValue; break;
            case 3: this.uid = (String)fieldValue; break;
            case 4: this.eid = (String)fieldValue; break;
            case 5: this.recipient = (List<String>)fieldValue; break;
            case 6: this.cc = (List<String>)fieldValue; break;
            case 7: this.bcc = (List<String>)fieldValue; break;
            case 8: this.recipientName = (String)fieldValue; break;
            case 9: this.subject = (String)fieldValue; break;
            case 10: this.bodyTemplateId = (Integer)fieldValue; break;
            case 11: this.bodyContent = (String)fieldValue; break;
            case 12: this.createTime = (Calendar)fieldValue; break;
            case 13: this.scheduleTime = (Calendar)fieldValue; break;
            case 14: this.sendTime = (Calendar)fieldValue; break;
            case 15: this.expiredTime = (Calendar)fieldValue; break;
            case 16: this.sendHost = (String)fieldValue; break;
            case 17: this.emstpId = (String)fieldValue; break;
            case 18: this.errorMsg = (String)fieldValue; break;
            case 19: this.emailStatus = (Integer)fieldValue; break;
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

        final Email other = (Email)obj;
        return 
            Objects.equal(this.emailId, other.emailId) &&
            Objects.equal(this.sender, other.sender) &&
            Objects.equal(this.senderName, other.senderName) &&
            Objects.equal(this.uid, other.uid) &&
            Objects.equal(this.eid, other.eid) &&
            Objects.equal(this.recipient, other.recipient) &&
            Objects.equal(this.cc, other.cc) &&
            Objects.equal(this.bcc, other.bcc) &&
            Objects.equal(this.recipientName, other.recipientName) &&
            Objects.equal(this.subject, other.subject) &&
            Objects.equal(this.bodyTemplateId, other.bodyTemplateId) &&
            Objects.equal(this.bodyContent, other.bodyContent) &&
            Objects.equal(this.createTime, other.createTime) &&
            Objects.equal(this.scheduleTime, other.scheduleTime) &&
            Objects.equal(this.sendTime, other.sendTime) &&
            Objects.equal(this.expiredTime, other.expiredTime) &&
            Objects.equal(this.sendHost, other.sendHost) &&
            Objects.equal(this.emstpId, other.emstpId) &&
            Objects.equal(this.errorMsg, other.errorMsg) &&
            Objects.equal(this.emailStatus, other.emailStatus);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + (this.emailId == null ? 0 : this.emailId.hashCode());
        result = 31 * result + (this.sender == null ? 0 : this.sender.hashCode());
        result = 31 * result + (this.senderName == null ? 0 : this.senderName.hashCode());
        result = 31 * result + (this.uid == null ? 0 : this.uid.hashCode());
        result = 31 * result + (this.eid == null ? 0 : this.eid.hashCode());
        result = 31 * result + (this.recipient == null ? 0 : this.recipient.hashCode());
        result = 31 * result + (this.cc == null ? 0 : this.cc.hashCode());
        result = 31 * result + (this.bcc == null ? 0 : this.bcc.hashCode());
        result = 31 * result + (this.recipientName == null ? 0 : this.recipientName.hashCode());
        result = 31 * result + (this.subject == null ? 0 : this.subject.hashCode());
        result = 31 * result + (this.bodyTemplateId == null ? 0 : this.bodyTemplateId.hashCode());
        result = 31 * result + (this.bodyContent == null ? 0 : this.bodyContent.hashCode());
        result = 31 * result + (this.createTime == null ? 0 : this.createTime.hashCode());
        result = 31 * result + (this.scheduleTime == null ? 0 : this.scheduleTime.hashCode());
        result = 31 * result + (this.sendTime == null ? 0 : this.sendTime.hashCode());
        result = 31 * result + (this.expiredTime == null ? 0 : this.expiredTime.hashCode());
        result = 31 * result + (this.sendHost == null ? 0 : this.sendHost.hashCode());
        result = 31 * result + (this.emstpId == null ? 0 : this.emstpId.hashCode());
        result = 31 * result + (this.errorMsg == null ? 0 : this.errorMsg.hashCode());
        result = 31 * result + (this.emailStatus == null ? 0 : this.emailStatus.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("emailId", emailId)
            .add("sender", sender)
            .add("senderName", senderName)
            .add("uid", uid)
            .add("eid", eid)
            .add("recipient", recipient)
            .add("cc", cc)
            .add("bcc", bcc)
            .add("recipientName", recipientName)
            .add("subject", subject)
            .add("bodyTemplateId", bodyTemplateId)
            .add("bodyContent", bodyContent)
            .add("createTime", createTime)
            .add("scheduleTime", scheduleTime)
            .add("sendTime", sendTime)
            .add("expiredTime", expiredTime)
            .add("sendHost", sendHost)
            .add("emstpId", emstpId)
            .add("errorMsg", errorMsg)
            .add("emailStatus", emailStatus)
            .toString();
    }
}
