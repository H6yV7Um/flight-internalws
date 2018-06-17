<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${getResource("bookingCanceled_1")}</title>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<style type="text/css">
    td[class="blue"]:hover {
        cursor: pointer;
        background-color: #3498db;
    }

    td[class="orange"]:hover {
        cursor: pointer;
        background-color: #ffc600;
    }
</style>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
        ${emailHeader}
            <table width="600" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:14px;color:#212121;text-align:left;">
                <tr>
                    <td style="padding:0 20px;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr><td style="font-family:Arial,sans-serif;font-size:30px;padding-bottom:15px;padding-top:15px;color:#e74c3c;">${getResource("bookingCanceled_1")}</td></tr>
                        <#assign  paymentInfo=orderDetail.paymentInfo payCurrencyType=orderDetail.paymentInfo.currencyType payCurrencyPrice=orderDetail.paymentInfo.payTotalPrice>
                            <tr>
                                <td style="font-size:14px;padding-bottom:20px;font-family:Arial,sans-serif;"><strong>${getResource("dearCustomer")}</strong>,
                                    <#if orderDetail.cancelReason == "highRisk" || orderDetail.cancelReason == "cheat">
                                    <br>${getResource("highRiskCancelReason")}
                                    <#elseif orderDetail.cancelReason == "jobCancel">
                                    <br>${getResource("jobCancelReason")}
                                    <#else>
                                    <br>${getResource("bookingCanceled_2")}${getResource("refundDuration")}
                                    </#if>
                                    <br>${getResource("bookingCanceled_3")}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:14px;color:#212121;">
                                        <tr>
                                            <td colspan="3" style="font-size:16px;font-weight:700;padding-bottom:10px;border-bottom:1px solid #ededed;">${getResource("bookingCanceled_5")}</td>
                                        </tr>
                                        <tr>
                                            <td valign="top" width="35%" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("orderid")}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;"><a href="${orderDetail.orderDetailUrl}" target="_blank" style="color:#1171b7;text-decoration:underline;">${orderDetail.orderId?string["0"]}</a></td>
                                        </tr>
                                    <#list orderDetail.flightInfoList as flightInfo>
                                        <tr>
                                            <#if orderDetail.flightWay == "RT">
                                                <#if flightInfo_index == 0>
                                                    <td valign="top"
                                                        style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("departRoute")}</td>
                                                <#else>
                                                    <td valign="top"
                                                        style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("returnRoute")}</td>
                                                </#if>
                                            <#elseif orderDetail.flightWay == "OW">
                                                <td valign="top"
                                                    style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("departRoute")}</td>
                                            <#else>
                                                <td valign="top"
                                                    style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("flightRoute")} ${flightInfo_index + 1}</td>
                                            </#if>
                                            <td colspan="2"
                                                style="border-bottom:1px solid #ededed;padding:10px 10px 2px;">
                                                <p style="margin:0;padding:0 0 8px;">${flightInfo.dCity.name}
                                                    - ${flightInfo.aCity.name}</p>
                                                <p style="margin:0;padding:0 0 8px;"><span>${getResource("depart")}
                                                    :</span> ${flightInfo.dDate?datetime}</p>
                                                <p style="margin:0;padding:0 0 8px;"><span>${getResource("arrival")}
                                                    :</span> ${flightInfo.aDate?datetime}</p>
                                            </td>
                                        </tr>
                                    </#list>
                                        <tr>
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("passenger")}</td>
                                            <td colspan="2" style="border-bottom:1px solid #ededed;padding:10px 10px 0;">
                                                <table border="0" cellpadding="0" cellspacing="0" width="100%" style="font-family:Arial,sans-serif;font-size:12px;color:#212121;">
                                                <#list orderDetail.passengerList as passenger>
                                                    <tr>
                                                        <td style="padding-bottom:10px;">${passenger.name}</td>
                                                    </tr>
                                                </#list>
                                                </table>
                                            </td>
                                        </tr>
                                        <!--关联订单-->
                                    <#if (orderDetail.relatedOrderInfoList?? && orderDetail.relatedOrderInfoList?size > 0)>
                                        <tr>
                                            <td bgcolor="#ffffff" colspan="2"
                                                style="padding-top:10px;padding-bottom:20px;">
                                                ${getResource("relatedOrderDesc")}
                                                <#assign relatedOrderCnt=orderDetail.relatedOrderInfoList?size>
                                                <#list orderDetail.relatedOrderInfoList as relatedOrderInfo>
                                                    <#if (relatedOrderInfo_index < relatedOrderCnt - 1)>
                                                        <a href="${relatedOrderInfo.orderDetailUrl}" target="_blank"
                                                           style="color:#1171b7;text-decoration:underline;">${relatedOrderInfo.orderId?string["0"]}</a>
                                                        <span>,</span>
                                                    <#else>
                                                        <a href="${relatedOrderInfo.orderDetailUrl}" target="_blank"
                                                           style="color:#1171b7;text-decoration:underline;">${relatedOrderInfo.orderId?string["0"]}</a>
                                                    </#if>
                                                </#list>
                                            </td>
                                        </tr>
                                    </#if>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="padding-top:10px;padding-bottom:20px;">
                                    <table border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:12px;color:#212121;">
                                        <tr>
                                            <td bgcolor="#1171b7" style="font-size:18px;height:56px;line-height:1;padding:0 20px;border-radius:5px;" class="blue">
                                                <a href="${orderDetail.orderDetailUrl}" target="_blank" style="color:#ffffff;text-decoration:none;line-height:56px; min-height:56px; display:block;">
                                                ${getResource("manageBooking")}
                                                </a>
                                            </td>
                                            <td width="20"></td>
                                            <td bgcolor="#ffa000" style="font-size:18px;height:56px;line-height:1;padding:0 20px;border-radius:5px;" class="orange">
                                                <a href="${orderDetail.firstPageUrl}" target="_blank" style="color:#ffffff;text-decoration:none;line-height:56px; min-height:56px; display:block;">
                                                ${getResource("another")}
                                                </a>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        ${emailFooter}
        </td>
    </tr>
</table>
</body>
</html>