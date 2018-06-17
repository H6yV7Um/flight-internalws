<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${sharkMessageResource.rescheduleAskInfoTitle}</title>
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
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <!--邮件头-->
        ${emailHeader}
            <table width="600" border="0" cellpadding="0" cellspacing="0"
                   style="font-family:Arial,sans-serif;font-size:12px;color:#212121;text-align:left;">
                <tr>
                    <td style="padding:5px 20px 0;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                                <td style="font-family:Arial,sans-serif;font-size:28px;padding-bottom:20px;padding-top:20px;text-decoration:none;">
                                ${sharkMessageResource.rescheduleAskInfoTitle}
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:14px;padding-bottom:20px;border-bottom:1px solid #dddddd;font-family:Arial,sans-serif;">
                                    <strong>${sharkMessageResource.respect} ${rescheduleAskInfo.contactInfo.name}</strong>,
                                    <p style="padding-top:5px;margin:0;">${sharkMessageResource.rescheduleAskInfoNotice}</p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0"
                                           style="font-family:Arial,sans-serif;font-size:12px;color:#212121;">
                                        <tr>
                                            <td colspan="2"
                                                style="font-size:14px;font-weight:700;padding-top:15px;padding-bottom:15px;">
                                            ${sharkMessageResource.newFlightTitle}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" width="25%" style="font-weight:700;padding:0 10px 7px 0;">
                                            ${sharkMessageResource.orderId}
                                            </td>
                                            <td style="padding:0 10px 7px;"> ${rescheduleAskInfo.orderId?c}</td>
                                        </tr>
                                        <tr>
                                            <td valign="top" width="25%"
                                                style="font-weight:700;padding:7px 10px 7px 0;">
                                            ${sharkMessageResource.originalItinerary}
                                            </td>
                                            <td style="padding:7px 10px 7px;">
                                            <#if (rescheduleAskInfo.oriSegmentInfoList??&&rescheduleAskInfo.oriSegmentInfoList?size > 0)>
                                                <#list rescheduleAskInfo.oriSegmentInfoList as oriSegmentInfo>
                                                ${oriSegmentInfo.dDate?date}, ${oriSegmentInfo.dCity.name}
                                                    -  ${oriSegmentInfo.aCity.name}<br/>
                                                </#list>
                                            </#if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top"
                                                style="font-weight:700;border-bottom:1px solid #dddddd;padding:7px 10px 20px 0;">
                                            ${sharkMessageResource.rescheduledPassenger}
                                            </td>
                                            <td style="border-bottom:1px solid #dddddd;padding:7px 0 20px 10px;">
                                            <#if (rescheduleAskInfo.passengerInfoList??&&rescheduleAskInfo.passengerInfoList?size>0)>
                                            <#list rescheduleAskInfo.passengerInfoList as passengerInfo>
                                            ${passengerInfo.name}<br/>
                                            </#list>
                                        </#if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size:14px;font-weight:700;padding-top:20px;">
                                            ${sharkMessageResource.targetFlightIntention}
                                            </td>
                                        </tr>
                                    <#if (rescheduleAskInfo.askDetailList??&&rescheduleAskInfo.askDetailList?size>0)>
                                        <#list rescheduleAskInfo.askDetailList as askDetail>
                                            <tr>
                                                <td valign="top" width="25%"
                                                    style="font-weight:700;padding:20px 10px 7px 0;">
                                                ${sharkMessageResource.newItinerary}
                                                </td>
                                                <td style="padding:20px 10px 7px;">${askDetail.departDate?date}, ${askDetail.departCityName} - ${askDetail.arriveCityName}</td>
                                            </tr>
                                            <tr>
                                                <td valign="top" width="25%"
                                                    style="font-weight:700;padding:7px 10px 7px 0;">
                                                ${sharkMessageResource.flightNo}
                                                </td>
                                                <td style="padding:7px 10px 7px;">${askDetail.flightNo}</td>
                                            </tr>
                                            <tr>
                                                <td valign="top" width="25%"
                                                    style="font-weight:700;padding:7px 10px 7px 0;">
                                                ${sharkMessageResource.flightClass}
                                                </td>
                                                <td style="padding:7px 10px 7px;">${askDetail.clazz}</td>
                                            </tr>
                                            <tr>
                                                <td valign="top" width="25%"
                                                    style="font-weight:700;padding:7px 10px 7px 0;">
                                                ${sharkMessageResource.flyTypeDesc}
                                                </td>
                                                <td style="padding:7px 10px 7px;">${askDetail.flyTypeDesc}</td>
                                            </tr>
                                            <tr>
                                                <td valign="top" width="25%"
                                                    style="font-weight:700;padding:7px 10px 7px 0;">
                                                ${sharkMessageResource.craftTypeDesc}
                                                </td>
                                                <td style="padding:7px 10px 7px;">${askDetail.craftTypeDesc}</td>
                                            </tr>
                                            <tr>
                                                <td valign="top"
                                                    style="font-weight:700;border-bottom:1px solid #dddddd;padding:7px 10px 20px 0;">
                                                ${sharkMessageResource.takeOffTimeOption}
                                                </td>
                                                <td style="border-bottom:1px solid #dddddd;padding:7px 10px 20px;">
                                                ${askDetail.takeOffTimeOption}
                                                </td>
                                            </tr>
                                        </#list>
                                    </#if>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="padding: 30px 0 30px;">
                                    <a href="" target="_blank"
                                       style="text-decoration:none;color:#ffffff;font-size:16px;">
                                        <table border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td style="background:#FFA900;padding: 15px 50px;border-radius: 4px;text-align: center;"><a
                                                        href="${rescheduleAskInfo.rescheduleFlightsUrl}" target="_blank"
                                                        style="text-decoration:none;color:#ffffff;font-size:16px;font-weight:700;">${sharkMessageResource.checkRescheduledFlights}</a>
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
            <!--邮件尾-->
        ${emailFooter}
        </td>
    </tr>
</table>
</body>
</html>