package com.ctrip.ibu.flight.internalws.models.errorcode.emailsend;

/**
 * 获取邮件数据处理类错误码
 * Create by kyxie on 2018/4/15 17:05
 */
public enum GetEmailDataImplResultCode {

    SUCCESS(0,"成功"),

    NO_IMPLEMENT(0,"没有实现类");

    private final Integer val;

    private final String desc;

    GetEmailDataImplResultCode(Integer val, String desc){
        this.val = val;
        this.desc = desc;
    }

    public Integer getVal() {
        return val;
    }

    public String getDesc() {
        return desc;
    }
}
