package com.ctrip.ibu.flight.internalws.models.message;

import java.util.List;

/**
 * 附件上传接口响应模型
 * Created by kyxie on 2017/8/3.
 */
public class EmailAttachmentUploadResponseDO {

    private Integer resultCode;

    private String resultMsg;

    private List<EmailAttachmentDO> attachmentList;

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<EmailAttachmentDO> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<EmailAttachmentDO> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
