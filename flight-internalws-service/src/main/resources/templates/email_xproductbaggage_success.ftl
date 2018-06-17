<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
    <#if orderDetail.trademark == "Trip">
    ${getResource("xproductbaggageisusedsuccesstitle")?replace('${"$"}{trademark}','Trip.com')}
    <#elseif orderDetail.trademark == "Ctrip">
    ${getResource("xproductbaggageisusedsuccesstitle")?replace('${"$"}{trademark}','Ctrip')}
    </#if>
    </title>
    <style type="text/css">
        td[class="blue"]:hover{cursor:pointer;background-color:#3498db;}
        td[class="orange"]:hover{cursor:pointer;background-color:#ffc600;}
        .mail-header-container,
        .mail-footer-container{width: 600px;!important;}
        @media only screen and (max-width: 600px){
            .mail-header-container,
            .mail-footer-container{width: 100%!important;}
            .mail-contact-us{width: 100%!important;display: block!important;padding-bottom: 10px;!important;}
            .mail-contact-us-padding{height: 10px!important;}
        }
    </style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
${emailHeader}
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <table width="600" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:12px;color:#212121;text-align:left;">
                <tr>
                    <td style="padding:5px 20px 0;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr><td colspan="2" style="font-family:Arial,sans-serif;font-size:32px;padding-bottom:20px;padding-top:20px;text-decoration:none;">
                            <#if orderDetail.trademark == "Trip">
                            ${getResource("xproductbaggageisusedsuccesstitle")?replace('${"$"}{trademark}','Trip.com')}
                            <#elseif orderDetail.trademark == "Ctrip">
                            ${getResource("xproductbaggageisusedsuccesstitle")?replace('${"$"}{trademark}','Ctrip')}
                            </#if>
                            </td></tr>
                            <tr>
                                <td colspan="2" style="font-size:14px;padding-bottom:25px;font-family:Arial,sans-serif;">
                                    <strong>${orderDetail.contactInfo.name}</strong>,
                                <#if orderDetail.trademark == "Trip">
                                    <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("xproductbaggageisusedsuccessContent")?replace('${"$"}{trademark}','Trip.com')}</p>
                                    <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("xproductbaggageisusednote")?replace('${"$"}{trademark}','Trip.com')}</p>
                                <#elseif orderDetail.trademark == "Ctrip">
                                    <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("xproductbaggageisusedsuccessContent")?replace('${"$"}{trademark}','Ctrip')}</p>
                                    <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("xproductbaggageisusednote")?replace('${"$"}{trademark}','Ctrip')}</p>
                                </#if>

                                </td>
                            </tr>
                            <tr>
                                <td style="font-family:Arial,sans-serif;width: 25%;padding-bottom: 25px;">${getResource("xproductbaggageisusedsuccessBookNo")}</td>
                                <td style="font-family:Arial,sans-serif;color:#2681FF;text-align: center;padding-bottom: 25px;">${orderDetail.orderId?string["0"]}</td>
                            </tr>
                            <tr>
                                <td colspan="2" style="border-top:1px solid #dddddd;height: 10px;"></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:12px;color:#212121;border-collapse: collapse;">
                                        <tr>
                                            <td colspan="3" style="font-size:14px;font-weight:700;padding-top:15px;padding-bottom:15px;">${getResource("xproductbaggageisusedsuccessBaggageInfo")}</td>
                                        </tr>
                                        <tr>
                                            <td valign="top" width="33%" style="padding:10px;border:1px solid #dddddd;color:#888888;">${getResource("xproductbaggageisusedsuccessPassengerInfo")}</td>
                                            <td style="padding:10px;border:1px solid #dddddd;color:#888888;">${getResource("xproductbaggageisusedsuccessBaggageDetailInfo")}</td>
                                            <td style="padding:10px;border:1px solid #dddddd;color:#888888;">${getResource("xproductbaggageisusedsuccessBaggagePriceInfo")}</td>
                                        </tr>
                                    <#if (xproductBaggageDetail.segmentValueAddedBaggageDetailList??&&xproductBaggageDetail.segmentValueAddedBaggageDetailList?size > 0)>
                                        <#list xproductBaggageDetail.segmentValueAddedBaggageDetailList as detail>
                                            <tr>
                                                <td colspan="3" style="padding:10px;border:1px solid #dddddd;font-weight: 700;background: #F7F7F7;">${detail.dCityName} - ${detail.aCityName}</td>
                                            </tr>
                                            <#list detail.segmentValueAddedBaggagePassengerDetailList as passenger>
                                                <tr>
                                                    <td valign="top" width="33%" style="padding:10px;border:1px solid #dddddd;">${passenger.passengerName}</td>
                                                    <td style="padding:10px;border:1px solid #dddddd;">${passenger.xProductWeightInfo.weight}${passenger.xProductWeightInfo.weightUnits}</td>
                                                    <td style="padding:10px;border:1px solid #dddddd;">${passenger.xProductPriceInfo.currency} ${passenger.xProductPriceInfo.salePrice}</td>
                                                </tr>
                                            </#list>
                                        </#list>
                                    </#if>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="right" style="padding: 15px 0 15px;font-size:14px;">
                                ${getResource("xproductbaggageisusedsuccessBaggageTotalAmount")} <span style="color:#2681FF;font-size:24px;margin-left: 5px;">${xproductBaggageDetail.currency} ${xproductBaggageDetail.totalAmount}</span>
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
</body>
</html>