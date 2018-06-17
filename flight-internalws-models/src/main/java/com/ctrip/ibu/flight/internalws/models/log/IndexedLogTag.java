package com.ctrip.ibu.flight.internalws.models.log;

/**
 * ElkLog的索引字段，非索引字段不要加到这个枚举中
 * Created by kyxie on 2017/7/12.
 */
public enum IndexedLogTag {

    //APPID
    AppId,

    //订单号
    OrderId,

    //用户ID
    Uid,

    //订单来源
    ServerFrom,

    //日志记录场景描述
    Scene,

    //方法名
    ActionId,

  //方法或者过程名称
    ActionName,

    //消息类型，Email or SMS
    MessageType,

    //模板类型
    EmailTemplateType,

    //语言类型
    EmailLanguageType,

    //返回码
    ReturnCode,

    //真正的结果
    ActualResult,

    //操作人
    operator,

    //Log级别,Debug,Info,Warn,Error
    LogLevel,

    //邮件发送ID
    EmailId,

    //备注
    Note,

    //获取AccessToken接口返回码
    AccessTokenReturnCode,

    //PDF生成结果
    PdfGenerateResult,

    /**
     * AccessToken生成结果
     * */
    AccessTokenResult,

    BookingChannel,

    EngineType

}
