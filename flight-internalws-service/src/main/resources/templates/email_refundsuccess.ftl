<#assign refundAskDetail=refundaskdetail.refundAskDetailResponseModel>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${sharkMessageResource.refundSuccess}</title>
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
                                <td style="font-family:Arial,sans-serif;font-size:32px;padding-bottom:20px;padding-top:20px;text-decoration:none;">${sharkMessageResource.refundSuccess}</td>
                            </tr>
                            <tr>
                                <td style="font-size:14px;padding-bottom:20px;font-family:Arial,sans-serif;">
                                ${sharkMessageResource.respect} <strong>${orderDetail.contactInfo.name!}</strong>,
                                    <p style="padding-top:5px;margin:0;">${sharkMessageResource.refundSuccessRemind}</p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0"
                                           style="font-family:Arial,sans-serif;font-size:12px;color:#212121;">
                                        <tr>
                                            <td colspan="2"
                                                style="font-size:14px;font-weight:700;padding-bottom:15px;border-bottom:1px solid #dddddd;">${sharkMessageResource.refundDetail}</td>
                                        </tr>
                                        <tr>
                                            <td valign="top" width="25%"
                                                style="font-weight:700;border-bottom:1px solid #dddddd;padding:15px 10px 14px 0;">${sharkMessageResource.orderId}</td>
                                            <td style="border-bottom:1px solid #dddddd;padding:15px 10px 14px;">${orderDetail.orderId?string["0"]}</td>
                                        </tr>
                                    <#if (refundAskDetail.refundAskPassengerInfo?? && refundAskDetail.refundAskPassengerInfo?size>=1)>
                                        <#list refundAskDetail.refundAskPassengerInfo as refundPassengerInfo>
                                            <tr>
                                                <td style="font-weight:700;padding:14px 10px 7px 0;">${sharkMessageResource.itinerary}</td>
                                                <td style="padding:14px 10px;">${refundPassengerInfo.dCity.name}
                                                    - ${refundPassengerInfo.aCity.name}
                                                    , ${refundPassengerInfo.dDate}</td>
                                            </tr>
                                            <tr>
                                                <td valign="top"
                                                    style="font-weight:700;border-bottom:1px solid #dddddd;padding:7px 10px 14px 0;">${sharkMessageResource.passenger}</td>
                                                <td style="border-bottom:1px solid #dddddd;padding:7px 0 14px 10px;">
                                                ${refundPassengerInfo.passengerName}
                                                </td>
                                            </tr>
                                        </#list>
                                    </#if>
                                    <#if (refundAskDetail.xRefundInfoTypes?? && refundAskDetail.xRefundInfoTypes?size>=1)>
                                        <tr>
                                            <td valign="top"
                                                style="font-weight:700;border-bottom:1px solid #dddddd;padding:14px 10px 0 0;">${sharkMessageResource.xProduct}</td>
                                            <td style="border-bottom:1px solid #dddddd;padding:14px 10px;">
                                                <#list refundAskDetail.xRefundInfoTypes as xProductInfo>
                                                    <p style="padding-bottom:10px;margin:0;">${xProductInfo.xProductTypeName}
                                                        x ${xProductInfo.count}</p>
                                                </#list>
                                            </td>
                                        </tr>
                                    </#if>
                                        <tr>
                                            <td valign="top"
                                                style="font-weight:700;padding:14px 10px 7px 0;">${sharkMessageResource.totalRefund}</td>
                                            <td style="padding:14px 10px;">
                                                <strong>${refundAskDetail.currency} ${refundAskDetail.payCustomerTotal}</strong>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
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