package com.ctrip.ibu.flight.internalws.models.message;

import java.util.List;

/**
 * 邮件附件上传接口请求Request模型
 * Created by kyxie on 2017/8/3.
 */
public class EmailAttachmentUploadRequestDO {

    //附件列表
    private List<EmailAttachmentDO> attachmentList;

    public List<EmailAttachmentDO> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<EmailAttachmentDO> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
