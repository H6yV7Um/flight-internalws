package com.ctrip.ibu.flight.internalws.models.message;

/**
 * 邮件附件实体
 * Created by kyxie on 2017/7/7.
 */
public class EmailAttachmentDTO {

    //附件名
    private String attachmentName;

    //附件路径
    private String attachmentPath;

    //附件字节流
    private byte[] attachmentContent;

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public byte[] getAttachmentContent() {
        return attachmentContent;
    }

    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
}
