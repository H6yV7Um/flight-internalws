<#assign baggageInfo=offlineValueAddedInfo.baggageInfo>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${offlineValueAddedInfo.statusDesc} - ${getResource("offlineValueAddedBaggage")}</title>
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
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table class="mail-header-container" align="center" border="0" cellpadding="0" cellspacing="0"
       style="font-family:'Arial', sans-serif;font-size:14px;color:#5d6a73;text-align:left;padding:0 20px;width: 600px;">
${emailHeader}
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <table width="600" border="0" cellpadding="0" cellspacing="0"
                   style="font-family:Arial,sans-serif;font-size:12px;color:#212121;text-align:left;">
                <tr>
                    <td style="padding:5px 20px 0;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                                <td style="font-family:Arial,sans-serif;font-size:32px;padding-bottom:20px;padding-top:20px;text-decoration:none;">
                                ${offlineValueAddedInfo.statusDesc} - ${getResource("offlineValueAddedBaggage")}
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:14px;padding-bottom:20px;font-family:Arial,sans-serif;">
                                ${getResource("dearCustomer")},
                                    <p style="padding-top:5px;margin:0;">${offlineValueAddedInfo.remind}</p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0"
                                           style="font-family:Arial,sans-serif;font-size:12px;color:#212121;">
                                        <tr>
                                            <td colspan="2"
                                                style="font-size:14px;font-weight:700;padding-bottom:15px;border-bottom:1px solid #dddddd;">
                                            ${getResource("orderDetail")}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" width="25%"
                                                style="font-weight:700;padding:15px 10px 7px 0;">${getResource("orderId")}
                                            </td>
                                            <td style="padding:15px 10px 7px;">${orderDetail.orderId?string["0"]}</td>
                                        </tr>

                                    <#--行程区域-->
                                    <#if (offlineValueAddedInfo.flightColumnList?? && offlineValueAddedInfo.flightColumnList?size>=1)>
                                        <tr>
                                            <td style="font-weight:700;padding:7px 10px 7px 0;">${getResource("flightInfo")}</td>
                                            <td style="padding:7px 10px;">
                                                <#list offlineValueAddedInfo.flightColumnList as flightColumn>
                                                    <#if (flightColumn_index<offlineValueAddedInfo.flightColumnList?size-1)>
                                                    ${flightColumn.dCity.name}
                                                        - ${flightColumn.aCity.name}<br/>
                                                    <#else>
                                                    ${flightColumn.dCity.name}
                                                        - ${flightColumn.aCity.name}
                                                    </#if>
                                                </#list>
                                            </td>
                                        </tr>
                                    </#if>
                                    <#--乘机人区域-->
                                    <#if (baggageInfo.passengerNameList?? && baggageInfo.passengerNameList?size>=1)>
                                        <tr>
                                            <td valign="top"
                                                style="font-weight:700;border-bottom:1px solid #dddddd;padding:7px 10px 14px 0;">${getResource("passenger")}</td>
                                            <td style="border-bottom:1px solid #dddddd;padding:7px 0 14px 10px;">
                                                <#list baggageInfo.passengerNameList as passengerName>
                                                    <#if (passengerName_index < baggageInfo.passengerNameList?size-1)>
                                                    ${passengerName},
                                                    <#else>
                                                    ${passengerName}
                                                    </#if>
                                                </#list>
                                            </td>
                                        </tr>
                                    </#if>

                                    <#--规格-->
                                        <tr>
                                            <td style="font-weight:700;padding:14px 10px 7px 0;">${getResource("offlineValueAddedBaggage")}</td>
                                        <#if baggageInfo.number?? && baggageInfo.number gt 0>
                                            <td style="padding:14px 10px 7px;">${getResource("paidBaggageDisplayPattern")?replace('${"$"}{piece}',baggageInfo.number)?replace('${"$"}{weight}',baggageInfo.specification)}<br/></td>
                                        <#else>
                                            <td style="padding:14px 10px 7px;">${baggageInfo.specification}kg</td>
                                        </#if>
                                        </tr>
                                    <#--数量-->
                                        <tr>
                                            <td style="font-weight:700;padding:7px 10px 7px 0;">${getResource("quantity")}</td>
                                            <td style="padding:7px 10px;font-size:12px;">x ${baggageInfo.totalNumber}</td>
                                        </tr>
                                        <tr>
                                            <td style="font-weight:700;padding:14px 10px 7px 0;">${getResource("totalAmount")}</td>
                                            <td style="font-weight:700;;padding:14px 10px;">${offlineValueAddedInfo.currency} ${offlineValueAddedInfo.salePrice}</td>
                                        </tr>
                                    <#--仅待支付展示15分钟支付话述-->
                                    <#if (offlineValueAddedInfo.statusType == 0)>
                                        <tr>
                                            <td colspan="2"
                                                style="border-top:1px solid #dddddd;padding-top:15px;padding-bottom:15px;">
                                            ${getResource("timelyPaymentReminder")?replace('${"$"}{minute}','15')}
                                            </td>
                                        </tr>
                                    </#if>
                                    </table>
                                </td>
                            </tr>
                        <#if (offlineValueAddedInfo.statusType == 0 && offlineValueAddedInfo.salePrice > 0)>
                            <tr>
                                <td align="center" style="height:48px;">
                                    <a href="${offlineValueAddedInfo.payUrl}" target="_blank"
                                       style="text-decoration:none;color:#ffffff;font-size:16px;">
                                        <table border="0" cellpadding="0" cellspacing="0" style="width:125px;">
                                            <tr>
                                                <td style="background:#FFA900;height:48px;padding: 0 20px;border-radius: 4px;text-align: center;"><a
                                                        href="${offlineValueAddedInfo.payUrl}" target="_blank"
                                                        style="display:block;background:#FFA900;height:48px;line-height:48px;text-decoration:none;color:#ffffff;font-size:16px;font-weight:700;">${getResource("gotoPay")}</a>
                                                </td>
                                            </tr>
                                        </table>
                                    </a>
                                </td>
                            </tr>
                        </#if>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table class="mail-footer-container" align="center" border="0" cellpadding="0" cellspacing="0"
       style="font-family:'Arial', sans-serif;font-size:14px;color:#5d6a73;text-align:left;padding:0 20px;width: 600px;">
${emailFooter}
</table>
</body>
</html>