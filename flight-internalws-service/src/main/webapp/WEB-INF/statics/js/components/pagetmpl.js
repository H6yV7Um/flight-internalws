/**
 * Created by kyxie on 2017/9/3.
 */
define(function () {
    var $emailDetailTmpl = '<div class="emailDetailTmpl">'
        + '<div class="emailId">'
        + 'EmailID:<span></span>'
        + '</div>'
        + '<div class="subject">'
        + 'Subject:<span></span>'
        + '</div>'
        + '<div class="">'
        + 'Uid:<span></span>'
        + '</div>'
        + '<div class="recipient">'
        + 'Recipient:<span></span>'
        + '</div>'
        + '<div class="createTime">'
        + 'CreateTime:<span></span>'
        + '</div>'
        + '<div class="scheduleTime">'
        + 'ScheduleTime:<span></span>'
        + '</div>'
        + '<div class="sendTime">'
        + 'SendTime:<span></span>'
        + '</div>'
        + '<div class="expiredTime">'
        + 'ExpiredTime:<span></span>'
        + '</div>'
        + '<div class="eid">'
        + 'Eid:<span></span>'
        + '</div>'
        + '<div class="sendResult">'
        + 'SendResult:<span></span>'
        + '</div>'
        + '<div class="bodyContent">'
        + '<button id="btnShowEmailContent" value="">查看邮件内容</button>'
        + '</div>'
        + '</div>';

    return {
        $emailDetailTmpl : $emailDetailTmpl
    }
});