<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${getResource("itinerarypdf")}</title>
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

    .mail-header-container,
    .mail-footer-container {
        width: 600px;
    !important;
    }

    @media only screen and (max-width: 600px) {
        .mail-header-container,
        .mail-footer-container {
            width: 100% !important;
        }

        .mail-contact-us {
            width: 100% !important;
            display: block !important;
            padding-bottom: 10px;
        !important;
        }

        .mail-contact-us-padding {
            height: 10px !important;
        }
    }
</style>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <!--邮件头-->
        ${emailHeader}
            <table width="600" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:12px;color:#212121;text-align:left;">
                <tr>
                    <td style="padding:15px 10px;">
                        <table border="0" cellpadding="0" cellspacing="0">
                            <tr><td style="font-size:30px;padding-bottom:20px;padding-top:20px;font-family:Arial,sans-serif;">${getResource("itinerarypdf")}</td></tr>
                            <tr>
                                <td style="font-size:14px;padding-bottom:20px;font-family:Arial,sans-serif;">
                                    <strong> ${getResource("dearCustomer")}</strong>,
                                    <br>${getResource("itineraryNotify")}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:14px;color:#212121;">
                                        <tr>
                                            <td colspan="3" style="font-size:16px;font-weight:700;padding-bottom:15px;border-bottom:1px solid #ededed;">${getResource("orderDetail")}</td>
                                        </tr>
                                        <tr>
                                            <td valign="top" width="35%" style="font-weight:700;border-bottom:1px solid #ededed;padding:15px 10px;">${getResource("orderId")}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:15px 10px;"><a href="${orderDetail.orderDetailUrl}" target="_blank" style="color:#1171b7;text-decoration:underline;">${orderDetail.orderId?string["0"]}</a></td>
                                        </tr>
                                    <#list orderDetail.flightInfoList as flightInfo>
                                        <tr>
                                            <#if orderDetail.flightWay == "RT">
                                                <#if flightInfo.segmentNo == 1>
                                                    <td valign="top"
                                                        style="font-weight:700;border-bottom:1px solid #ededed;padding:15px 10px;">${getResource("departure")}</td>
                                                <#elseif flightInfo.segmentNo == 2>
                                                    <td valign="top"
                                                        style="font-weight:700;border-bottom:1px solid #ededed;padding:15px 10px;">${getResource("return")}</td>
                                                </#if>
                                            <#elseif orderDetail.flightWay == "OW">
                                                <td valign="top"
                                                    style="font-weight:700;border-bottom:1px solid #ededed;padding:15px 10px;">${getResource("flightDetails")}</td>
                                            <#else>
                                                <td valign="top"
                                                    style="font-weight:700;border-bottom:1px solid #ededed;padding:15px 10px;">${getResource("flightSeg")?replace('${"$"}{segNo}','${flightInfo.segmentNo}')}</td>
                                            </#if>
                                            <td colspan="2"
                                                style="border-bottom:1px solid #ededed;padding:15px 10px 5px;">
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
                                            <td valign="top"
                                                style="font-weight:700;border-bottom:1px solid #ededed;padding:15px 10px;">${getResource("passengerNameAndTicketNo")}</td>
                                            <td colspan="2"
                                                style="border-bottom:1px solid #ededed;padding:15px 10px 0;">
                                                <table border="0" cellpadding="0" cellspacing="0" width="100%"
                                                       style="font-family:Arial,sans-serif;font-size:12px;color:#212121;">
                                                <#list orderDetail.passengerList as passenger>
                                                    <tr>
                                                        <td style="padding-bottom:10px;">${passenger.name}</td>
                                                        <td style="padding-bottom:10px;">${passenger.eTickets?join(", ")}</td>
                                                    </tr>
                                                </#list>
                                                </table>
                                            </td>
                                        </tr>
                                    <#assign paymentInfo=orderDetail.paymentInfo payCurrencyType=orderDetail.paymentInfo.currencyType>
                                    <#if (paymentInfo.payTotalPrice > 0)>
                                        <tr>
                                            <td style="font-weight:700;border-bottom:1px solid #ededed;padding:15px 10px;">${getResource("total")}</td>
                                            <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                <td colspan="2"
                                                    style="font-weight:700;border-bottom:1px solid #ededed;padding:15px 10px;">${payCurrencyType} ${paymentInfo.payTotalPrice?string("0")}</td>
                                            <#else>
                                                <td colspan="2"
                                                    style="font-weight:700;border-bottom:1px solid #ededed;padding:15px 10px;">${payCurrencyType} ${paymentInfo.payTotalPrice}</td>
                                            </#if>
                                        </tr>
                                    </#if>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--邮件尾-->
        ${emailFooter}
        </td>
    </tr>
</table>
</body>
</html>