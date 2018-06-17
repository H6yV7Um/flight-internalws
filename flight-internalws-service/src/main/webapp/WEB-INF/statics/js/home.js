/**
 * Created by kyxie on 2017/8/30.
 */
require.config({
    paths:{
        jquery:'lib/jquery-3.2.1',
        utils:'utils/utils',
        pagetmpl : 'components/pagetmpl'
    }
});

require(['jquery','utils','pagetmpl'],function ($,utils,pagetmpl) {
   //console.log($().jquery);

    init();

   /**
    * 初始化页面展示
    * */
   function init() {

       //绑定邮件发送事件
       $('#sendEmailBtn').on('click',function (event) {
           var sendEmailReq = getSendEmailReq();
           if (verifySendEmailReq(sendEmailReq)){
               sendEmail(sendEmailReq,processSendEmailResponse);
           }else {
               alert('邮件请求不合法');
           }
       });

       //绑定获取邮件状态事件
       $('#btnGetEmailStatus').on('click',function (event) {
           var getEmailStatusRequest = getEmailStatusReq();
           if (getEmailStatusRequest && getEmailStatusRequest.emailIdList && getEmailStatusRequest.emailIdList.length >= 0){
               $.ajax('/test/email/getEmailStatus',{
                   type : 'POST',
                   async : true,
                   contentType : 'application/json;charset=UTF-8',
                   data : JSON.stringify(getEmailStatusRequest),
                   success : function (data) {
                       processGetEmailStatusResponse(data);
                   },
                   error : function (jqXHR) {
                       console.log('获取邮件发送状态请求出错，响应status:' + jqXHR.status + ',statusText:' + jqXHR.statusText);
                   },
                   complete : function () {
                       console.log('获取邮件发送状态请求完成');
                   }
               });
           }
       });
   }

   /**
    * 获取邮件发送请求
    * */
   function getSendEmailReq() {

       var sendEmailReq = {
           orderID : parseInt($('#orderId').val()),
           uid : $('#uid').val(),
           isCustomContent : $('#isCustomContent').val() === '1' ? true : false,
           isBodyHtml : $('#isContentHtml').val() === '1' ? true : false,
           emailTemplateType : $('#templateTypeList').find('option:selected').val(),
           languageType : $('#languageTypeList').find('option:selected').val(),
           recipientEmailList : [],
           additionalIdList : [],
           subject : $('#subject').val(),
           bodyContent : $('#customContent').val(),
           cc : [],
           bcc : []
       };

       var recipientListStr = $('#recipientEmail').val();
       if (recipientListStr){
           var list = recipientListStr.split(',');
           if (list && list.length >= 1){
               for (var i = 0;i < list.length;i++){
                   if (list[i].trim(' ').length >= 1){
                       sendEmailReq.recipientEmailList.push(list[i]);
                   }
               }
           }
       }

       var ccListStr = $('#ccEmail').val();
       if (ccListStr){
           var list = ccListStr.split(',');
           if (list && list.length >= 1){
               for (var i = 0;i < list.length;i++){
                   if (list[i].trim(' ').length >= 1){
                       sendEmailReq.cc.push(list[i]);
                   }
               }
           }
       }

       var bccListStr = $('#bccEmail').val();
       if (bccListStr){
           var list = bccListStr.split(',');
           if (list && list.length >= 1){
               for (var i = 0;i < list.length;i++){
                   if (list[i].trim(' ').length >= 1){
                       sendEmailReq.bcc.push(list[i]);
                   }
               }
           }
       }

       var additionIdListStr = $('#additionIdList').val();
       if (additionIdListStr){
           var list = additionIdListStr.split(',');
           if (list && list.length >= 1){
               for (var i = 0;i < list.length;i++){
                   if (list[i].trim(' ').length >= 1){
                       sendEmailReq.additionalIdList.push(list[i]);
                   }
               }
           }
       }

       return sendEmailReq;
   }

   function getEmailStatusReq() {
       var getEmailStatus = {
           sendCode : $('#sendCode').val(),
           emailIdList : []
       };
       var emailIdListStr = $('#emailId').val();
       if (emailIdListStr){
           var emailIdList = emailIdListStr.split(',');
           if (emailIdList && emailIdList.length >= 1){
               getEmailStatus.emailIdList = emailIdList;
           }
       }
       return getEmailStatus;
   }

   /**
    * 验证邮件发送请求
    * */
   function verifySendEmailReq(sendEmailReq) {

       if (sendEmailReq === undefined || sendEmailReq === null || typeof (sendEmailReq) !== 'object'){
           return false;
       }

       //自定义邮件规则
       if (sendEmailReq.isCustomContent){
           var recipientList = sendEmailReq.recipientEmailList;
           if (recipientList === undefined || recipientList === null || recipientList.recipientList.length === 0){
               return false;
           }
       }else {
           var orderId = sendEmailReq.orderID;
           if (orderId === undefined || orderId === null){
               return false;
           }
           var parsedOrderId = parseInt(orderId);
           if (parsedOrderId === NaN || parsedOrderId <= 0){
               return false;
           }
           if (sendEmailReq.emailTemplateType === 0){
               return false;
           }
       }

       return true;
   }

   /**
    * 发送邮件
    * @param sendEmailReq 邮件发送请求
    * @param responseHandler 响应处理
    * */
   function sendEmail(sendEmailRequest,responseHandler) {
       $.ajax('/test/email/sendEmail',{
           type:'POST',
           async:true,
           contentType : 'application/json;charset=UTF-8',
           data : JSON.stringify(sendEmailRequest),//名字要和后台服务的参数名一致,且必须是JSON字符串
           success:function (data) {
               responseHandler(data);
           },
           error:function (jqXHR) {
               console.log('邮件发送请求出错，响应status:' + jqXHR.status + ',statusText:' + jqXHR.statusText);
           },
           complete : function (arr) {
               console.log('邮件发送请求完成');
           }
       });
   }

   /**
    * 处理邮件发送响应
    * */
   function processSendEmailResponse(responseData) {
       if (responseData === undefined || responseData === null){
           console.log('响应为NULL');
       }else {
           if (responseData.sendResult === 0){
               console.log('发送成功。接口响应如下：' + responseData.resultMessage);
           }else {
               console.log('发送失败。接口响应如下：' + responseData.resultMessage);
           }
       }
   }

   function processGetEmailStatusResponse(responseData) {
       if (responseData){
           var $emailDetail = $('#emailDetail');
           var $emailShowArea = $(pagetmpl.$emailDetailTmpl);//邮件详细信息模板
           var emailList = responseData.emailList;
           if (emailList && emailList.length >= 1){
               for (var i = 0;i < emailList.length;i++){
                   var emailDetail = emailList[i];
                   $emailShowArea.children('div.emailId').children('span').text(emailDetail.emailId);
                   $emailShowArea.children('div.subject').children('span').text(emailDetail.subject);
                   $emailShowArea.children('div.uid').children('span').text(emailDetail.uid);
                   $emailShowArea.children('div.recipient').children('span').text(emailDetail.recipient);
                   $emailShowArea.children('div.createTime').children('span').text(emailDetail.createTime);
                   $emailShowArea.children('div.scheduleTime').children('span').text(emailDetail.scheduleTime);
                   $emailShowArea.children('div.sendTime').children('span').text(emailDetail.sendTime);
                   $emailShowArea.children('div.expiredTime').children('span').text(emailDetail.expiredTime);
                   $emailShowArea.children('div.eid').children('span').text(emailDetail.eid);
                   $emailShowArea.children('div.sendResult').children('span').text(emailDetail.sendResult);
                   $emailShowArea.children('div.bodyContent').children('button').attr('value',emailDetail.bodyContent);
                   $emailShowArea.on('click','div.BodyContent',function (event) {
                       var target = $(event).target;
                       var content = target.attr('value');
                   });
                   $emailShowArea.appendTo($emailDetail);
               }
           }
       }
   }

   function showBodyContent(target) {
       var body = $(target).attr('value');
       document.write(body);
       document.close();
   }
});