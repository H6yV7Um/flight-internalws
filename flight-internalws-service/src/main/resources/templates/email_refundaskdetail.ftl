<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${sharkMessageResource.refundCostConfirmed}</title>
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
${emailHeader}
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
                                ${sharkMessageResource.refundCostConfirmed}
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:14px;padding-bottom:20px;font-family:Arial,sans-serif;">
                                    <strong>${refundaskdetail.refundAskDetailResponseModel.contactInfo.name}</strong>,
                                    <p style="padding-top:5px;margin:0;">${sharkMessageResource.refundCostConfirmedRemind}</p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0"
                                           style="font-family:Arial,sans-serif;font-size:12px;color:#212121;">
                                        <tr>
                                            <td colspan="2"
                                                style="font-size:14px;font-weight:700;padding-bottom:15px;border-bottom:1px solid #dddddd;">
                                                ${sharkMessageResource.refundDetail}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" width="25%"
                                                style="font-weight:700;border-bottom:1px solid #dddddd;padding:15px 10px 14px 0;">
                                                ${sharkMessageResource.orderId}
                                            </td>
                                            <td style="border-bottom:1px solid #dddddd;padding:15px 10px 14px;">${refundaskdetail.refundAskDetailResponseModel.orderId?string["0"]}</td>
                                        </tr>
                                    <#if (refundaskdetail.refundAskDetailResponseModel.refundAskPassengerInfo??&&refundaskdetail.refundAskDetailResponseModel.refundAskPassengerInfo?size > 0)>
                                        <#list refundaskdetail.refundAskDetailResponseModel.refundAskPassengerInfo as oriSegmentInfo>
                                            <tr>
                                                <td style="font-weight:700;padding:14px 10px 7px 0;">${sharkMessageResource.itinerary}</td>
                                                <td style="padding:14px 10px;">${oriSegmentInfo.dCity.name}
                                                    -  ${oriSegmentInfo.aCity.name},${oriSegmentInfo.dDate?date}</br></td>
                                            </tr>
                                            <tr>
                                                <td valign="top"
                                                    style="font-weight:700;border-bottom:1px solid #dddddd;padding:7px 10px 14px 0;">
                                                ${sharkMessageResource.passenger}da
                                                </td>
                                                <td style="border-bottom:1px solid #dddddd;padding:7px 0 14px 10px;">
                                                ${oriSegmentInfo.passengerName}
                                                </td>
                                                </td>
                                            </tr>
                                        </#list>
                                    </#if>
                                        <tr>
                                            <td valign="top"
                                                style="font-weight:700;border-bottom:1px solid #dddddd;padding:14px 10px 0;">
                                            ${sharkMessageResource.xProduct}
                                            </td>
                                            <td style="border-bottom:1px solid #dddddd;padding:14px 10px;">
                                            <#if (refundaskdetail.refundAskDetailResponseModel.xRefundInfoTypes??&&refundaskdetail.refundAskDetailResponseModel.xRefundInfoTypes?size > 0)>
                                                <#list refundaskdetail.refundAskDetailResponseModel.xRefundInfoTypes as xproduct>
                                                    <p>${xproduct.xProductTypeName} x ${xproduct.count}</p>
                                                </#list>
                                            </#if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="font-weight:700;padding:14px 10px 7px 0;">
                                                ${sharkMessageResource.totalAmount}
                                            </td>
                                            <td style="padding:14px 10px;">
                                                <strong>${refundaskdetail.refundAskDetailResponseModel.currency} ${refundaskdetail.refundAskDetailResponseModel.payCustomerTotal}</strong>
                                                <p style="padding-top:10px;margin:0;">${sharkMessageResource.refundInstructions}</p>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="padding: 10px 0 30px;">
                                    <a href="" target="_blank"
                                       style="text-decoration:none;color:#ffffff;font-size:16px;">
                                        <table border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td style="background:#FFA900;padding: 15px 30px;border-radius: 4px;"><a
                                                        href="${refundaskdetail.linkUrl}" target="_blank"
                                                        style="text-decoration:none;color:#ffffff;font-size:16px;font-weight:700;">${sharkMessageResource.applyForRefund}</a>
                                                </td>
                                            </tr>
                                        </table>
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
</body>
</html>