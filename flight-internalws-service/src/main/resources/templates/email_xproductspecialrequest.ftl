<#assign specialServiceInfo=offlineValueAddedInfo.specialServiceInfo>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${offlineValueAddedInfo.statusDesc} - ${sharkMessageResource.offlineSpecialService}</title>
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
                                <td style="font-family:Arial,sans-serif;font-size:32px;padding-bottom:20px;padding-top:20px;text-decoration:none;">${offlineValueAddedInfo.statusDesc} - ${sharkMessageResource.offlineSpecialService}</td>
                            </tr>
                            <tr>
                                <td style="font-size:14px;padding-bottom:20px;font-family:Arial,sans-serif;">
                                ${sharkMessageResource.respect} <strong>${orderDetail.contactInfo.name!}</strong>,
                                    <p style="padding-top:5px;margin:0;">${offlineValueAddedInfo.remind}</p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0"
                                           style="font-family:Arial,sans-serif;font-size:12px;color:#212121;">
                                        <tr>
                                            <td colspan="2"
                                                style="font-size:14px;font-weight:700;padding-bottom:15px;border-bottom:1px solid #dddddd;">${sharkMessageResource.orderDetail}</td>
                                        </tr>
                                        <tr>
                                            <td valign="top" width="25%"
                                                style="font-weight:700;padding:15px 10px 7px 0;">${sharkMessageResource.orderId}</td>
                                            <td style="padding:15px 10px 7px;">${orderDetail.orderId?string["0"]}</td>
                                        </tr>
                                    <#--行程区域-->
                                    <#if (offlineValueAddedInfo.flightColumnList?? && offlineValueAddedInfo.flightColumnList?size>=1)>
                                        <tr>
                                            <td style="font-weight:700;padding:7px 10px 7px 0;">${sharkMessageResource.flightInfo}</td>
                                            <td style="padding:7px 10px;">
                                                <#list offlineValueAddedInfo.flightColumnList as flightColumn>
                                                    <#if (flightColumn_index<offlineValueAddedInfo.flightColumnList?size-1)>
                                                    ${flightColumn.dCity.name}
                                                        - ${flightColumn.aCity.name} ${flightColumn.flightNo}<br/>
                                                    <#else>
                                                    ${flightColumn.dCity.name}
                                                        - ${flightColumn.aCity.name} ${flightColumn.flightNo}
                                                    </#if>
                                                </#list>
                                            </td>
                                        </tr>
                                    </#if>
                                    <#if (specialServiceInfo.passengerNameList?? && specialServiceInfo.passengerNameList?size>=1)>
                                        <tr>
                                            <td valign="top"
                                                style="font-weight:700;border-bottom:1px solid #dddddd;padding:7px 10px 14px 0;">${sharkMessageResource.passenger}</td>
                                            <td style="border-bottom:1px solid #dddddd;padding:7px 0 14px 10px;">
                                                <#list specialServiceInfo.passengerNameList as passengerName>
                                                    <#if (passengerName_index < specialServiceInfo.passengerNameList?size-1)>
                                                    ${passengerName},
                                                    <#else>
                                                    ${passengerName}
                                                    </#if>
                                                </#list>
                                            </td>
                                        </tr>
                                    </#if>
                                        <tr>
                                            <td style="font-weight:700;padding:14px 10px 7px 0;">${sharkMessageResource.productType}</td>
                                            <td style="padding:14px 10px 7px;">${specialServiceInfo.serviceName}</td>
                                        </tr>
                                        <tr>
                                            <td style="font-weight:700;padding:7px 10px 7px 0;">${sharkMessageResource.quantity}</td>
                                            <td style="padding:7px 10px;font-size:12px;">
                                                x ${specialServiceInfo.number}</td>
                                        </tr>
                                        <tr>
                                            <td style="font-weight:700;padding:14px 10px 7px 0;">${sharkMessageResource.totalAmount}</td>
                                            <td style="font-weight:700;;padding:14px 10px;">${offlineValueAddedInfo.currency} ${offlineValueAddedInfo.salePrice}</td>
                                        </tr>
                                    <#if (offlineValueAddedInfo.statusType == 0)>
                                        <tr>
                                            <td colspan="2"
                                                style="border-top:1px solid #dddddd;padding-top:15px;padding-bottom:15px;">${sharkMessageResource.timelyPaymentReminder?replace('${"$"}{minute}','15')}</td>
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
                                                        style="display:block;background:#FFA900;height:48px;line-height:48px;text-decoration:none;color:#ffffff;font-size:16px;font-weight:700;">${sharkMessageResource.gotoPay}</a>
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